package com.dmcclean780.myfirstmod.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class MegaTrunkPlacer extends TrunkPlacer {

    private static final double BRANCH_CHANCE = 0.25;
    private static final double ASCEND_CHANCE = 0.6;
    private static final double BEND_CHANCE = 0.5;
    private static final double LEAF_CHANCE = 0.9;

    public static final MapCodec<MegaTrunkPlacer> CODEC = RecordCodecBuilder
            .mapCodec(pineTrunkPlacerInstance -> trunkPlacerParts(pineTrunkPlacerInstance)
                    .apply(pineTrunkPlacerInstance, MegaTrunkPlacer::new));

    public MegaTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.MEGA_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel,
            BiConsumer<BlockPos, BlockState> pBlockSetter,
            RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        // THIS WHERE BLOCK PLACING LOGIC GOES

        BlockPos[] trunkPositions = {
                pPos.east(),
                pPos.south(),
                pPos.south().east(),
                pPos.south(2).east(),
                pPos.south().east(2),
                pPos,
                pPos.east(2),
                pPos.south(2),
                pPos.south(2).east(2)
        };

        placeBottomDirt(pLevel, pBlockSetter, pRandom, trunkPositions, pConfig);

        int height = pFreeTreeHeight + pRandom.nextInt(heightRandA, heightRandA + 3)
                + pRandom.nextInt(heightRandB - 1, heightRandB + 1);

        placeRoots(pLevel, pBlockSetter, pRandom, pPos, pConfig, pFreeTreeHeight, height);

        List<FoliagePlacer.FoliageAttachment> foliageList = Lists.newArrayList();

        for (int i = 0; i < height; i++) {

            if (i < height / 3) {
                placeBottomTreeLayer(pLevel, pBlockSetter, pRandom, trunkPositions, pConfig, i);
            } else if (i < height / 3 * 2) {
                placeMiddleTreeLayer(pLevel, pBlockSetter, pRandom, trunkPositions, pConfig, i);
            } else {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i).east().south(), pConfig);

                int length = pRandom.nextInt(20);
                if (i % 2 == 0 && pRandom.nextBoolean()) {
                    if (pRandom.nextFloat() > BRANCH_CHANCE) {
                        createNorthBranch(pLevel, pBlockSetter, pRandom, pFreeTreeHeight, pPos, pConfig, length, i, foliageList);
                    }

                    if (pRandom.nextFloat() > BRANCH_CHANCE) {
                        createSouthBranch(pLevel, pBlockSetter, pRandom, pFreeTreeHeight, pPos, pConfig, length, i, foliageList);
                    }

                    if (pRandom.nextFloat() > BRANCH_CHANCE) {
                        createEastBranch(pLevel, pBlockSetter, pRandom, pFreeTreeHeight, pPos, pConfig, length, i, foliageList);
                    }

                    if (pRandom.nextFloat() > BRANCH_CHANCE) {
                        createWestBranch(pLevel, pBlockSetter, pRandom, pFreeTreeHeight, pPos, pConfig, length, i, foliageList);
                    }
                }
            }
        }

        return foliageList;
    }

    public void placeBottomDirt(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
            RandomSource pRandom, BlockPos[] trunkPositions, TreeConfiguration pConfig) {

        for (BlockPos pos : trunkPositions) {
            setDirtAt(pLevel, pBlockSetter, pRandom, pos.below(), pConfig);
        }
    }

    public void placeBottomTreeLayer(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
            RandomSource pRandom, BlockPos[] trunkPositions, TreeConfiguration pConfig, int i) {

        for (BlockPos pos : trunkPositions) {
            placeLog(pLevel, pBlockSetter, pRandom, pos.above(i), pConfig);
        }
    }

    public void placeMiddleTreeLayer(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
            RandomSource pRandom, BlockPos[] trunkPositions, TreeConfiguration pConfig, int i) {

        for (int j = 0; j < trunkPositions.length - 4; j++) {
            placeLog(pLevel, pBlockSetter, pRandom, trunkPositions[j].above(i), pConfig);
        }
    }

    public void placeRoots(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
            RandomSource pRandom, BlockPos pPos, TreeConfiguration pConfig, int i, int height) {

        int rootHeight = height / 6 * 2;
        int xValue = (int) (Math.log(rootHeight) / Math.log(2));
        if (xValue < 3) {
            xValue = 3;
        }
        int x = 0;
        int limit = x + xValue;
        int y = 1 << limit;
        placeLog(pLevel, pBlockSetter, pRandom, pPos.north(1).east().above(0), pConfig);
        while (1 << limit > 0) {
            y = 1 << limit;
            for (int j = 0; j < y; j++) {
                placeLog(pLevel, pBlockSetter, pRandom, pPos.north(Math.abs(x) + 1).east().above(j), pConfig);
                placeLog(pLevel, pBlockSetter, pRandom, pPos.west(Math.abs(x) + 1).south().above(j), pConfig);
                placeLog(pLevel, pBlockSetter, pRandom, pPos.south(Math.abs(x) + 3).east().above(j), pConfig);
                placeLog(pLevel, pBlockSetter, pRandom, pPos.east(Math.abs(x) + 3).south().above(j), pConfig);
            }
            x--;
            limit = x + xValue;
        }
        placeLog(pLevel, pBlockSetter, pRandom, pPos.north(Math.abs(x) + 1).east(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.west(Math.abs(x) + 1).south(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.south(Math.abs(x) + 3).east(), pConfig);
        placeLog(pLevel, pBlockSetter, pRandom, pPos.east(Math.abs(x) + 3).south(), pConfig);

    }

    public static List<FoliagePlacer.FoliageAttachment> createNorthBranch(LevelSimulatedReader pLevel,
            BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom,
            int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig, int length, int i,
            List<FoliagePlacer.FoliageAttachment> foliageList) {

        int slope = 0;
        int bend = 0;
        boolean bendEast = pRandom.nextBoolean();
        for (int x = 0; x < length; x++) {
            slope = pRandom.nextDouble() > ASCEND_CHANCE ? slope + 1 : slope;
            bend = pRandom.nextDouble() > BEND_CHANCE ? bend + 1 : bend;
            if (bendEast) {
                pBlockSetter.accept(pPos.above(i).east(1 + bend).south().above(slope).relative(Direction.NORTH, x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
            } else {
                pBlockSetter.accept(pPos.above(i).east().south().above(slope).west(bend).relative(Direction.NORTH, x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
            }
            if (pRandom.nextFloat() > LEAF_CHANCE) {
                if (bendEast) {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(
                            pPos.above(i).east(1 + bend).south(1).above(slope).relative(Direction.NORTH, x), 0, false));
                } else {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(
                            pPos.above(i).east().south().above(slope).west(bend).relative(Direction.NORTH, x), 0,
                            false));
                }
            }
        }
        if (bendEast) {
            foliageList.add(new FoliagePlacer.FoliageAttachment(
                    pPos.above(i).east(1 + bend).south(1).above(slope).relative(Direction.NORTH, length), 0, false));
        } else {
            foliageList.add(new FoliagePlacer.FoliageAttachment(
                    pPos.above(i).east().south().above(slope).west(bend).relative(Direction.NORTH, length), 0, false));
        }

        return foliageList;
    }

    public static List<FoliagePlacer.FoliageAttachment> createSouthBranch(LevelSimulatedReader pLevel,
            BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom,
            int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig, int length, int i,
            List<FoliagePlacer.FoliageAttachment> foliageList) {

        int slope = 0;
        int bend = 0;
        boolean bendEast = pRandom.nextBoolean();
        for (int x = 0; x < length; x++) {
            slope = pRandom.nextDouble() > ASCEND_CHANCE ? slope + 1 : slope;
            bend = pRandom.nextDouble() > BEND_CHANCE ? bend + 1 : bend;
            if (bendEast) {
                pBlockSetter.accept(
                        pPos.above(i).east(1 + bend).south().above(slope).relative(Direction.SOUTH, x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
            } else {
                pBlockSetter.accept(
                        pPos.above(i).east().south().above(slope).west(bend).relative(Direction.SOUTH,
                                x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z))));
            }
            if (pRandom.nextFloat() > LEAF_CHANCE) {
                if (bendEast) {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east(1 + bend)
                            .south().above(slope).relative(Direction.SOUTH, x), 0, false));
                } else {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                            .above(slope).west(bend).relative(Direction.SOUTH, x), 0, false));
                }
            }
        }
        if (bendEast) {
            foliageList.add(new FoliagePlacer.FoliageAttachment(
                    pPos.above(i).east(1 + bend).south().above(slope).relative(Direction.SOUTH, length),
                    0, false));
        } else {
            foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                    .above(slope).west(bend).relative(Direction.SOUTH, length), 0, false));
        }

        return foliageList;
    }

    public static List<FoliagePlacer.FoliageAttachment> createEastBranch(LevelSimulatedReader pLevel,
            BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom,
            int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig, int length, int i,
            List<FoliagePlacer.FoliageAttachment> foliageList) {

        int slope = 0;
        int bend = 0;
        boolean bendSouth = pRandom.nextBoolean();
        for (int x = 0; x < length; x++) {
            slope = pRandom.nextDouble() > ASCEND_CHANCE ? slope + 1 : slope;
            bend = pRandom.nextDouble() > BEND_CHANCE ? bend + 1 : bend;
            if (bendSouth) {
                pBlockSetter.accept(
                        pPos.above(i).east().south(1 + bend).above(slope).relative(Direction.EAST, x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
            } else {
                pBlockSetter.accept(
                        pPos.above(i).east().south().above(slope).north(bend).relative(Direction.EAST,
                                x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
            }
            if (pRandom.nextFloat() > LEAF_CHANCE) {
                if (bendSouth) {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east()
                            .south(1 + bend).above(slope).relative(Direction.EAST, x), 0, false));
                } else {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                            .above(slope).north(bend).relative(Direction.EAST, x), 0, false));
                }
            }
        }
        if (bendSouth) {
            foliageList.add(new FoliagePlacer.FoliageAttachment(
                    pPos.above(i).east().south(1 + bend).above(slope).relative(Direction.EAST, length),
                    0, false));
        } else {
            foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                    .above(slope).north(bend).relative(Direction.EAST, length), 0, false));
        }

        return foliageList;
    }

    public static List<FoliagePlacer.FoliageAttachment> createWestBranch(LevelSimulatedReader pLevel,
            BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom,
            int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig, int length, int i,
            List<FoliagePlacer.FoliageAttachment> foliageList) {

        int slope = 0;
        int bend = 0;
        boolean bendSouth = pRandom.nextBoolean();
        for (int x = 0; x < length; x++) {
            slope = pRandom.nextDouble() > ASCEND_CHANCE ? slope + 1 : slope;
            bend = pRandom.nextDouble() > BEND_CHANCE ? bend + 1 : bend;
            if (bendSouth) {
                pBlockSetter.accept(
                        pPos.above(i).east().south(1 + bend).above(slope).relative(Direction.WEST, x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
            } else {
                pBlockSetter.accept(
                        pPos.above(i).east().south().above(slope).north(bend).relative(Direction.WEST,
                                x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
            }
            if (pRandom.nextFloat() > LEAF_CHANCE) {
                if (bendSouth) {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east()
                            .south(1 + bend).above(slope).relative(Direction.WEST, x), 0, false));
                } else {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                            .above(slope).north(bend).relative(Direction.WEST, x), 0, false));
                }
            }
        }
        if (bendSouth) {
            foliageList.add(new FoliagePlacer.FoliageAttachment(
                    pPos.above(i).east().south(1 + bend).above(slope).relative(Direction.WEST, length),
                    0, false));
        } else {
            foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                    .above(slope).north(bend).relative(Direction.WEST, length), 0, false));
        }

        return foliageList;
    }

    public static List<FoliagePlacer.FoliageAttachment> createSouthWestBranch(LevelSimulatedReader pLevel,
            BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom,
            int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig, int length, int i,
            List<FoliagePlacer.FoliageAttachment> foliageList) {

        int slope = 0;
        int bend = 0;
        boolean bendSouth = pRandom.nextBoolean();
        for (int x = 0; x < length; x++) {
            slope = pRandom.nextDouble() > ASCEND_CHANCE ? slope + 1 : slope;
            bend = pRandom.nextDouble() > BEND_CHANCE ? bend + 1 : bend;
            if (bendSouth) {
                pBlockSetter.accept(
                        pPos.above(i).east().south(1 + bend).above(slope).relative(Direction.WEST, x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
            } else {
                pBlockSetter.accept(
                        pPos.above(i).east().south().above(slope).north(bend).relative(Direction.WEST,
                                x),
                        ((BlockState) Function.identity()
                                .apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                        .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X))));
            }
            if (pRandom.nextFloat() > LEAF_CHANCE) {
                if (bendSouth) {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east()
                            .south(1 + bend).above(slope).relative(Direction.WEST, x), 0, false));
                } else {
                    foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                            .above(slope).north(bend).relative(Direction.WEST, x), 0, false));
                }
            }
        }
        if (bendSouth) {
            foliageList.add(new FoliagePlacer.FoliageAttachment(
                    pPos.above(i).east().south(1 + bend).above(slope).relative(Direction.WEST, length),
                    0, false));
        } else {
            foliageList.add(new FoliagePlacer.FoliageAttachment(pPos.above(i).east().south()
                    .above(slope).north(bend).relative(Direction.WEST, length), 0, false));
        }

        return foliageList;
    }
}

package com.dmcclean780.myfirstmod.worldgen.tree.custom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.dmcclean780.myfirstmod.worldgen.tree.ModTreeDecoratorTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.util.ExtraCodecs;

public class VinesFromLeavesDecorator extends AttachedToLeavesDecorator {

    public static final MapCodec<VinesFromLeavesDecorator> CODEC =
    RecordCodecBuilder.mapCodec((p_225996_) -> {
    return p_225996_.group(Codec.floatRange(0.0F,
    1.0F).fieldOf("probability").forGetter((p_226014_) -> {
    return p_226014_.probability;
    }), Codec.intRange(0,
    16).fieldOf("exclusion_radius_xz").forGetter((p_226012_) -> {
    return p_226012_.exclusionRadiusXZ;
    }), Codec.intRange(0, 16).fieldOf("exclusion_radius_y").forGetter((p_226010_) -> {
    return p_226010_.exclusionRadiusY;
    }), BlockStateProvider.CODEC.fieldOf("block_provider").forGetter((p_226008_)-> {
    return p_226008_.blockProvider;
    }), Codec.intRange(1,
    16).fieldOf("required_empty_blocks").forGetter((p_226006_) -> {
    return p_226006_.requiredEmptyBlocks;
    }),ExtraCodecs.nonEmptyList(Direction.CODEC.listOf()).fieldOf("directions").forGetter((p_225998_)-> {
    return p_225998_.directions;
    }), Codec.intRange(0, 32).fieldOf("min_length").forGetter((p_225994_) -> {
        return p_225994_.minLength;
    }), Codec.intRange(0, 32).fieldOf("rand_length").forGetter((p_225992_) -> {
        return p_225992_.randLength;
    })).apply(p_225996_, VinesFromLeavesDecorator::new);
    });

    protected int minLength;
    protected int randLength;

    public VinesFromLeavesDecorator(float probability, int exclusionRadiusXZ, int exclusionRadiusY,
            BlockStateProvider blockProvider, int requiredEmptyBlocks, List<Direction> directions, int minLength,
            int randLength) {
        super(probability, exclusionRadiusXZ, exclusionRadiusY, blockProvider, requiredEmptyBlocks, directions);
        this.minLength = minLength;
        this.randLength = randLength;
    }

    @Override
    public void place(TreeDecorator.Context context) {

        Set<BlockPos> set = new HashSet();
        RandomSource randomsource = context.random();
        Iterator var4 = Util.shuffledCopy(context.leaves(), randomsource).iterator();
        int length = this.minLength + randomsource.nextInt(this.randLength);
        while(true) {
            BlockPos blockpos;
            Direction direction;
            BlockPos blockpos1;
            do {
                do {
                do {
                    if (!var4.hasNext()) {
                        return;
                    }

                    blockpos = (BlockPos)var4.next();
                    direction = (Direction)Util.getRandom(this.directions, randomsource);
                    blockpos1 = blockpos.relative(direction);
                } while(set.contains(blockpos1));
                } while(!(randomsource.nextFloat() < this.probability));
            } while(!this.hasRequiredEmptyBlocks(context, blockpos, direction));

            BlockPos blockpos2 = blockpos1.offset(-this.exclusionRadiusXZ, -this.exclusionRadiusY, -this.exclusionRadiusXZ);
            BlockPos blockpos3 = blockpos1.offset(this.exclusionRadiusXZ, this.exclusionRadiusY, this.exclusionRadiusXZ);
            Iterator var10 = BlockPos.betweenClosed(blockpos2, blockpos3).iterator();

            while(var10.hasNext()) {
                BlockPos blockpos4 = (BlockPos)var10.next();
                set.add(blockpos4.immutable());
            }
            for(int i=0; i<length+randomsource.nextInt(1); i++) {
                if(this.hasRequiredEmptyBlocks(context, blockpos1.below(i), direction)){
                    context.setBlock(blockpos1.below(i), this.blockProvider.getState(randomsource, blockpos1.below(i)));
                }
                else{
                    break;
                }
            }
            
        }
    }

    private boolean hasRequiredEmptyBlocks(TreeDecorator.Context context, BlockPos pos, Direction direction) {
        for (int i = 1; i <= this.requiredEmptyBlocks; ++i) {
            BlockPos blockpos = pos.relative(direction, i);
            if (!context.isAir(blockpos)) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.VINES_FROM_LEAVES.get();
    }

}

package com.dmcclean780.myfirstmod.block;

import java.util.function.Function;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.custom.ModFlammableRotatedPillarBlock;
import com.dmcclean780.myfirstmod.item.ModItems;
import com.dmcclean780.myfirstmod.worldgen.tree.ModTreeGrowers;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MyFirstMod.MODID);

    public static final DeferredBlock<Block> TIN_STONE_ORE = registerBlock(
            "tin_stone_ore",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    // .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TIN_DEEPSLATE_ORE = registerBlock(
            "tin_deepslate_ore",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    // .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock(
            "raw_tin_block",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    // .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock(
            "tin_block",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    // .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> LIMESTONE = registerBlock(
            "limestone",
            registryName -> new Block(BlockBehaviour.Properties.of()
                    // .setId(ResourceKey.create(Registries.BLOCK, registryName))
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> BLOODWOOD_LOG = registerBlock(
            "bloodwood_log",
            registryName -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<Block> BLOODWOOD_WOOD = registerBlock(
            "bloodwood_wood",
            registryName -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG = registerBlock(
            "stripped_bloodwood_log",
            registryName -> new ModFlammableRotatedPillarBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_WOOD = registerBlock(
            "stripped_bloodwood_wood",
            registryName -> new ModFlammableRotatedPillarBlock(
                    BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> BLOODWOOD_PLANKS = registerBlock(
            "bloodwood_planks",
            registryName -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });

    public static final DeferredBlock<StairBlock> BLOODWOOD_STAIRS = registerBlock("bloodwood_stairs",
            registryName -> new StairBlock(ModBlocks.BLOODWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredBlock<SlabBlock> BLOODWOOD_SLAB = registerBlock("bloodwood_slab",
            registryName -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    public static final DeferredBlock<PressurePlateBlock> BLOODWOOD_PRESSURE_PLATE = registerBlock(
            "bloodwood_pressure_plate",
            registryName -> new PressurePlateBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));
    public static final DeferredBlock<ButtonBlock> BLOODWOOD_BUTTON = registerBlock("bloodwood_button",
            registryName -> new ButtonBlock(BlockSetType.OAK, 20,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    public static final DeferredBlock<FenceBlock> BLOODWOOD_FENCE = registerBlock("bloodwood_fence",
            registryName -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredBlock<FenceGateBlock> BLOODWOOD_FENCE_GATE = registerBlock("bloodwood_fence_gate",
            registryName -> new FenceGateBlock(WoodType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));
    public static final DeferredBlock<DoorBlock> BLOODWOOD_DOOR = registerBlock("bloodwood_door",
            registryName -> new DoorBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));
    public static final DeferredBlock<TrapDoorBlock> BLOODWOOD_TRAPDOOR = registerBlock("bloodwood_trapdoor",
            registryName -> new TrapDoorBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));

    
                    public static final DeferredBlock<Block> BLOODWOOD_LEAVES = registerBlock(
            "bloodwood_leaves",
            registryName -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });

    public static final DeferredBlock<Block> BLOODWOOD_SAPLING = registerBlock(
            "bloodwood_sapling",
            registryName -> new SaplingBlock(ModTreeGrowers.BLOODWOOD_TREE,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
            Function<ResourceLocation, T> blockFactory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, blockFactory);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerSimpleBlockItem(
                name,
                block,
                new Item.Properties());
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}

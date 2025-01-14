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
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MyFirstMod.MODID);

    public static final DeferredBlock<Block> TIN_STONE_ORE = registerBlock(
        "tin_stone_ore", 
        registryName -> new Block(BlockBehaviour.Properties.of()
            //.setId(ResourceKey.create(Registries.BLOCK, registryName))
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE))
    );

    public static final DeferredBlock<Block> TIN_DEEPSLATE_ORE = registerBlock(
        "tin_deepslate_ore", 
        registryName -> new Block(BlockBehaviour.Properties.of()
            //.setId(ResourceKey.create(Registries.BLOCK, registryName))
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE))
    );

    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock(
        "raw_tin_block", 
        registryName -> new Block(BlockBehaviour.Properties.of()
            //.setId(ResourceKey.create(Registries.BLOCK, registryName))
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE))
    );

    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock(
        "tin_block", 
        registryName -> new Block(BlockBehaviour.Properties.of()
            //.setId(ResourceKey.create(Registries.BLOCK, registryName))
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE))
    );

    public static final DeferredBlock<Block> LIMESTONE = registerBlock(
        "limestone",
        registryName -> new Block(BlockBehaviour.Properties.of()
            //.setId(ResourceKey.create(Registries.BLOCK, registryName))
            .strength(4f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.STONE))
    );

    public static final DeferredBlock<Block> OLYMPIAN_LOG = registerBlock(
        "olympian_log",
        registryName -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG))
    );

    public static final DeferredBlock<Block> OLYMPIAN_WOOD = registerBlock(
        "olympian_wood",
        registryName -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD))
    );

    public static final DeferredBlock<Block> STRIPPED_OLYMPIAN_LOG = registerBlock(
        "stripped_olympian_log",
        registryName -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG))
    );

    public static final DeferredBlock<Block> STRIPPED_OLYMPIAN_WOOD = registerBlock(
        "stripped_olympian_wood",
        registryName -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))
    );

    public static final DeferredBlock<Block> OLYMPIAN_PLANKS = registerBlock(
        "olympian_planks",
        registryName -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)){
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
        }
    );

    public static final DeferredBlock<Block> OLYMPIAN_LEAVES = registerBlock(
        "olympian_leaves",
        registryName -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)){
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
       }
    );

    public static final DeferredBlock<Block> OLYMPIAN_SAPLING = registerBlock(
        "olympian_sapling",
        registryName -> new SaplingBlock(ModTreeGrowers.OLYMPIAN_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING))
    );

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<ResourceLocation, T> blockFactory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, blockFactory);
        registerBlockItem(name, toReturn);
        return toReturn;
}

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.registerSimpleBlockItem(
            name, 
            block,
            new Item.Properties()
        );
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

package com.dmcclean780.myfirstmod.blocks;

import java.util.function.Function;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.fluid.ModFluids;
import com.dmcclean780.myfirstmod.items.AllItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.level.block.SoundType;

public class AllBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MyFirstMod.MODID);
    
    public static final DeferredBlock<Block> BLOCK_OF_COKE = registerBlock(
        "block_of_coke",
        registryName -> new Block(BlockBehaviour.Properties.of()
            //.setId(ResourceKey.create(Registries.BLOCK, registryName))
            .strength(4f)
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

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Function<ResourceLocation, T> blockFactory) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, blockFactory);
        registerBlockItem(name, toReturn);
        return toReturn;
}

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        AllItems.ITEMS.registerSimpleBlockItem(
            name, 
            block,
            new Item.Properties()
        );
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}

package com.dmcclean780.myfirstmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MyFirstMod.MODID);

    public static final Supplier<CreativeModeTab> MY_FIRST_TAB_BLOCKS_TAB = CREATIVE_MODE_TAB.register(
        "my_first_mod_blocks_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TIN_STONE_ORE.get()))
            .title(Component.translatable("creativetab.myfirstmod.my_first_mod_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.TIN_DEEPSLATE_ORE);
                output.accept(ModBlocks.TIN_STONE_ORE);
                output.accept(ModBlocks.LIMESTONE);
                output.accept(ModBlocks.RAW_TIN_BLOCK);
                output.accept(ModBlocks.TIN_BLOCK);
                output.accept(ModBlocks.OLYMPIAN_LOG);
                output.accept(ModBlocks.OLYMPIAN_WOOD);
                output.accept(ModBlocks.STRIPPED_OLYMPIAN_LOG);
                output.accept(ModBlocks.STRIPPED_OLYMPIAN_WOOD);
                output.accept(ModBlocks.OLYMPIAN_PLANKS);
                output.accept(ModBlocks.OLYMPIAN_LEAVES);

            }).build()
    );

    public static final Supplier<CreativeModeTab> MY_FIRST_TAB_TAB = CREATIVE_MODE_TAB.register(
        "my_first_mod_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TIN_INGOT.get()))
            .title(Component.translatable("creativetab.myfirstmod.my_first_mod"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.LIMESTONE_PIECE);
                output.accept(ModItems.TIN_INGOT);
                output.accept(ModItems.RAW_TIN);
                output.accept(ModBlocks.OLYMPIAN_SAPLING);
            }).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}

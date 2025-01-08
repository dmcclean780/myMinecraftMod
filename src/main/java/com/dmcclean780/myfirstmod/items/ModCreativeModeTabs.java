package com.dmcclean780.myfirstmod.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.blocks.AllBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, MyFirstMod.MODID);

    public static final Supplier<CreativeModeTab> MY_FIRST_TAB_BLOCKS_TAB = CREATIVE_MODE_TAB.register(
        "my_first_mod_blocks_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(AllBlocks.BLOCK_OF_COKE.get()))
            .title(Component.translatable("creativetab.myfirstmod.my_first_mod_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(AllBlocks.BLOCK_OF_COKE);
                output.accept(AllBlocks.LIMESTONE);
            }).build()
    );

    public static final Supplier<CreativeModeTab> MY_FIRST_TAB_TAB = CREATIVE_MODE_TAB.register(
        "my_first_mod_tab",
        () -> CreativeModeTab.builder().icon(() -> new ItemStack(AllItems.COKE.get()))
            .title(Component.translatable("creativetab.myfirstmod.my_first_mod"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(AllItems.COKE);
                output.accept(AllItems.LIMESTONE_PIECE);
            }).build()
    );

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}

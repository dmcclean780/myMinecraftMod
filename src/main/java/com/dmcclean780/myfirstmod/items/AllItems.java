package com.dmcclean780.myfirstmod.items;

import com.dmcclean780.myfirstmod.MyFirstMod;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class AllItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyFirstMod.MODID);
    
    public static final DeferredItem<Item> COKE = ITEMS.registerItem(
        "coke", 
        Item :: new,
        new Item.Properties()
    );

    public static final DeferredItem<Item> LIMESTONE_PIECE = ITEMS.registerItem(
        "limestone_piece", 
        Item :: new,
        new Item.Properties()
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
package com.dmcclean780.myfirstmod.item;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.entity.ModEntities;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MyFirstMod.MODID);

    public static final DeferredItem<Item> RAW_TIN = ITEMS.registerItem(
        "raw_tin", 
        Item :: new,
        new Item.Properties()
    );

    public static final DeferredItem<Item> TIN_INGOT = ITEMS.registerItem(
        "tin_ingot", 
        Item :: new,
        new Item.Properties()
    );

    public static final DeferredItem<Item> LIMESTONE_PIECE = ITEMS.registerItem(
        "limestone_piece", 
        Item :: new,
        new Item.Properties()
    );

    public static final DeferredItem<Item> GECKO_SPAWN_EGG = ITEMS.register(
        "gecko_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GECKO, 0x31afaf, 0xffac00,
                    new Item.Properties())
    );

    public static final DeferredItem<Item> BASILISK_SPAWN_EGG = ITEMS.register(
        "basilisk_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.BASILISK, 0xffffff, 0x109104,
                    new Item.Properties())
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
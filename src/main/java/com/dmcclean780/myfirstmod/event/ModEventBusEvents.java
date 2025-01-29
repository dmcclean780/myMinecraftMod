package com.dmcclean780.myfirstmod.event;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.entity.ModEntities;
import com.dmcclean780.myfirstmod.entity.client.BasiliskModel;
import com.dmcclean780.myfirstmod.entity.client.GeckoModel;
import com.dmcclean780.myfirstmod.entity.custom.BasiliskEntity;
import com.dmcclean780.myfirstmod.entity.custom.GeckoEntity;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = MyFirstMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
        event.registerLayerDefinition(BasiliskModel.LAYER_LOCATION, BasiliskModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.BASILISK.get(), BasiliskEntity.createAttributes().build());
        event.put(ModEntities.GECKO.get(), GeckoEntity.createAttributes().build());
    }
}

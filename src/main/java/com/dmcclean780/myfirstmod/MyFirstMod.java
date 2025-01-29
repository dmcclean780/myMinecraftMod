package com.dmcclean780.myfirstmod;

import org.slf4j.Logger;

import com.dmcclean780.myfirstmod.block.ModBlocks;
import com.dmcclean780.myfirstmod.entity.ModEntities;
import com.dmcclean780.myfirstmod.entity.client.BasiliskRenderer;
import com.dmcclean780.myfirstmod.entity.client.GeckoRenderer;
import com.dmcclean780.myfirstmod.entity.custom.GeckoEntity;
import com.dmcclean780.myfirstmod.item.ModItems;
import com.dmcclean780.myfirstmod.worldgen.biome.ModTerraBlender;
import com.dmcclean780.myfirstmod.worldgen.biome.surface.ModSurfaceRules;
import com.dmcclean780.myfirstmod.worldgen.tree.ModTreeDecoratorTypes;
import com.dmcclean780.myfirstmod.worldgen.tree.ModTrunkPlacerTypes;
import com.dmcclean780.myfirstmod.item.ModCreativeModeTabs;
import com.mojang.logging.LogUtils;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MyFirstMod.MODID)
public class MyFirstMod {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "myfirstmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public MyFirstMod(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (MyFirstMod)
        // to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in
        // this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModTrunkPlacerTypes.register(modEventBus);
        ModTreeDecoratorTypes.register(modEventBus);

        ModTerraBlender.registerBiomes();
        ModEntities.register(modEventBus);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config
        // file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, ModSurfaceRules.makeRules());
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.TIN_INGOT);
            event.accept(ModItems.RAW_TIN);
            event.accept(ModItems.LIMESTONE_PIECE);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.TIN_DEEPSLATE_ORE);
            event.accept(ModBlocks.TIN_STONE_ORE);
            event.accept(ModBlocks.LIMESTONE);
            event.accept(ModBlocks.RAW_TIN_BLOCK);
            event.accept(ModBlocks.TIN_BLOCK);
            event.accept(ModBlocks.BLOODWOOD_LOG);
            event.accept(ModBlocks.BLOODWOOD_WOOD);
            event.accept(ModBlocks.STRIPPED_BLOODWOOD_LOG);
            event.accept(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
            event.accept(ModBlocks.BLOODWOOD_PLANKS);
            event.accept(ModBlocks.BLOODWOOD_BUTTON);
            event.accept(ModBlocks.BLOODWOOD_DOOR);
            event.accept(ModBlocks.BLOODWOOD_TRAPDOOR);
            event.accept(ModBlocks.BLOODWOOD_FENCE);
            event.accept(ModBlocks.BLOODWOOD_FENCE_GATE);
            event.accept(ModBlocks.BLOODWOOD_PRESSURE_PLATE);
            event.accept(ModBlocks.BLOODWOOD_SLAB);
            event.accept(ModBlocks.BLOODWOOD_STAIRS);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.BLOODWOOD_LEAVES);
            event.accept(ModBlocks.BLOODWOOD_SAPLING);
        }
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.GECKO_SPAWN_EGG);
            event.accept(ModItems.BASILISK_SPAWN_EGG);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods
    // in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
            EntityRenderers.register(ModEntities.BASILISK.get(), BasiliskRenderer::new);
        }
    }
}

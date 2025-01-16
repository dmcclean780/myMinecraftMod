package com.dmcclean780.myfirstmod.worldgen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> LIMESTONE_PLACED_KEY = registerKey("limestone_placed");
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> UNCOMMON_OLYMPIAN_TREE_PALACED_KEY = registerKey("uncommon_olympian_tree_placed");
    public static final ResourceKey<PlacedFeature> OLYMPIAN_TREE_PALACED_KEY = registerKey("olympian_tree_placed");
    public static final ResourceKey<PlacedFeature> LARGE_OLYMPIAN_TREE_PALACED_KEY = registerKey("large_olympian_tree_placed");
    public static final ResourceKey<PlacedFeature> GIANT_OLYMPIAN_TREE_PALACED_KEY = registerKey("giant_olympian_tree_placed");
    
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, LIMESTONE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIMESTONE_KEY), 
            ModOrePlacement.commonOrePlacement(2, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))
        );

        register(context, TIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY), 
            ModOrePlacement.commonOrePlacement(16, HeightRangePlacement.triangle(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(112)))
        );

        register(context, UNCOMMON_OLYMPIAN_TREE_PALACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLYMPIAN_TREE_KEY),
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05F, 1),
            ModBlocks.OLYMPIAN_SAPLING.get()));

        register(context, OLYMPIAN_TREE_PALACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLYMPIAN_TREE_KEY),
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1),
            ModBlocks.OLYMPIAN_SAPLING.get()));

        register(context, LARGE_OLYMPIAN_TREE_PALACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLYMPIAN_TREE_KEY_LARGE),
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1),
            ModBlocks.OLYMPIAN_SAPLING.get()));

        register(context, GIANT_OLYMPIAN_TREE_PALACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLYMPIAN_TREE_KEY_GIANT),
            VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 1),
            ModBlocks.OLYMPIAN_SAPLING.get()));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MyFirstMod.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}

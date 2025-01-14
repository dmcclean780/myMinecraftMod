package com.dmcclean780.myfirstmod.worldgen;

import java.util.List;
import java.util.OptionalInt;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;
import com.dmcclean780.myfirstmod.worldgen.tree.custom.PineTrunkPlacer;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> LIMESTONE_KEY = registerKey("limestone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLYMPIAN_TREE_KEY = registerKey("olympian_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLYMPIAN_TREE_KEY_LARGE = registerKey("olympian_tree_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLYMPIAN_TREE_KEY_GIANT = registerKey("olympian_tree_giant");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);

        List<OreConfiguration.TargetBlockState> overworldLimestone = List.of(
            OreConfiguration.target(stoneReplacables, ModBlocks.LIMESTONE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldTin = List.of(
            OreConfiguration.target(stoneReplacables, ModBlocks.TIN_STONE_ORE.get().defaultBlockState()),
            OreConfiguration.target(stoneReplacables, ModBlocks.TIN_DEEPSLATE_ORE.get().defaultBlockState())
        );

        register(context, LIMESTONE_KEY, Feature.ORE, new OreConfiguration(overworldLimestone, 64));
        register(context, TIN_ORE_KEY, Feature.ORE, new OreConfiguration(overworldTin, 15));

        register(context, OLYMPIAN_TREE_KEY_LARGE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.OLYMPIAN_LOG.get()),
                new FancyTrunkPlacer(7, 24, 14),

                BlockStateProvider.simple(ModBlocks.OLYMPIAN_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(5), ConstantInt.of(0), ConstantInt.of(4), 0.25F, 0.5F, 0.16666667F, 0.33333334F),

                new TwoLayersFeatureSize(1, 0, 2)).build());
        
        register(context, OLYMPIAN_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.OLYMPIAN_LOG.get()),
                    new FancyTrunkPlacer(7, 11, 7),
    
                    BlockStateProvider.simple(ModBlocks.OLYMPIAN_LEAVES.get()),
                    new CherryFoliagePlacer(ConstantInt.of(5), ConstantInt.of(0), ConstantInt.of(4), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
    
                    new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, OLYMPIAN_TREE_KEY_GIANT, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(ModBlocks.OLYMPIAN_LOG.get()),
                    new PineTrunkPlacer( 20, 11, 7),
        
                    BlockStateProvider.simple(ModBlocks.OLYMPIAN_LEAVES.get()),
                    new CherryFoliagePlacer(ConstantInt.of(6), ConstantInt.of(4), ConstantInt.of(7), 0.5F, 0.5F, 0.6F, 0.6F),
                    //new FancyFoliagePlacer(ConstantInt.of(7), ConstantInt.of(7), 7),

                    new TwoLayersFeatureSize(1, 0, 2)).build());


    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MyFirstMod.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

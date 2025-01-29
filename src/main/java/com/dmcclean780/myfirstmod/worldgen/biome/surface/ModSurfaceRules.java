package com.dmcclean780.myfirstmod.worldgen.biome.surface;

import com.dmcclean780.myfirstmod.worldgen.biome.ModBiomes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    //private static final SurfaceRules.RuleSource SAPPHIRE = makeStateRule(ModBlocks.LIMESTONE.get());
    //private static final SurfaceRules.RuleSource RAW_SAPPHIRE = makeStateRule(ModBlocks.LIMESTONE.get());


    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, false, 0,CaveSurface.FLOOR),
                        SurfaceRules.sequence(
                            SurfaceRules.ifTrue(
                                SurfaceRules.waterBlockCheck(0, 0), GRASS_BLOCK),
                                DIRT
                            )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), 
                        DIRT
                    )
                )
            )
        );

        return SurfaceRules.sequence(

                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BLEEDING_FOREST), grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}

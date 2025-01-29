package com.dmcclean780.myfirstmod.worldgen.biome;

import com.dmcclean780.myfirstmod.MyFirstMod;

import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerraBlender {
    public static void registerBiomes(){
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(MyFirstMod.MODID, "overworld"), 2));
    }
}

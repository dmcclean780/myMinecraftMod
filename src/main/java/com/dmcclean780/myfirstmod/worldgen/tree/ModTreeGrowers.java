package com.dmcclean780.myfirstmod.worldgen.tree;

import java.util.Optional;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.worldgen.ModConfiguredFeatures;

import net.minecraft.world.level.block.grower.TreeGrower;



public class ModTreeGrowers {
    public static final TreeGrower BLOODWOOD_TREE = new TreeGrower(
        MyFirstMod.MODID + ":bloodwood_tree", 
        0.3F, //Secondary Tree Chance
        Optional.of(ModConfiguredFeatures.BLOODWOOD_TREE_KEY_GIANT), Optional.empty(), //Mega Tree
        Optional.of(ModConfiguredFeatures.BLOODWOOD_TREE_KEY), Optional.of(ModConfiguredFeatures.BLOODWOOD_TREE_KEY_LARGE), //Tree 
        Optional.empty(), Optional.empty() // Flower Tree
    );
}

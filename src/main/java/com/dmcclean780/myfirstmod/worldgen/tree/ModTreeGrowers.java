package com.dmcclean780.myfirstmod.worldgen.tree;

import java.util.Optional;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.worldgen.ModConfiguredFeatures;

import net.minecraft.world.level.block.grower.TreeGrower;



public class ModTreeGrowers {
    public static final TreeGrower OLYMPIAN_TREE = new TreeGrower(
        MyFirstMod.MODID + ":olympian_tree", 
        0.3F, //Secondary Tree Chance
        Optional.of(ModConfiguredFeatures.OLYMPIAN_TREE_KEY_GIANT), Optional.empty(), //Mega Tree
        Optional.of(ModConfiguredFeatures.OLYMPIAN_TREE_KEY), Optional.of(ModConfiguredFeatures.OLYMPIAN_TREE_KEY_LARGE), //Tree 
        Optional.empty(), Optional.empty() // Flower Tree
    );
}

package com.dmcclean780.myfirstmod.worldgen.tree;

import java.util.function.Supplier;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.worldgen.tree.custom.MegaTrunkPlacer;
import com.dmcclean780.myfirstmod.worldgen.tree.custom.VinesFromLeavesDecorator;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModTreeDecoratorTypes {
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR =
            DeferredRegister.create(Registries.TREE_DECORATOR_TYPE, MyFirstMod.MODID);

    public static final Supplier<TreeDecoratorType<VinesFromLeavesDecorator>> VINES_FROM_LEAVES =
            TREE_DECORATOR.register("vines_from_leaves", () -> new TreeDecoratorType<VinesFromLeavesDecorator>(VinesFromLeavesDecorator.CODEC));

    public static void register(IEventBus eventBus) {
        TREE_DECORATOR.register(eventBus);
    }
}

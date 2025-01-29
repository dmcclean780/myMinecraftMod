package com.dmcclean780.myfirstmod.worldgen.tree;

import java.util.function.Supplier;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.worldgen.tree.custom.MegaTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MyFirstMod.MODID);

    public static final Supplier<TrunkPlacerType<MegaTrunkPlacer>> MEGA_TRUNK_PLACER =
            TRUNK_PLACER.register("giant_trunk_placer", () -> new TrunkPlacerType<MegaTrunkPlacer>(MegaTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}

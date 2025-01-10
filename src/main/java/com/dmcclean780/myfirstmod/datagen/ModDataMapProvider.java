package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.blocks.AllBlocks;
import com.dmcclean780.myfirstmod.items.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    protected ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather() {
        this.builder(NeoForgeDataMaps.FURNACE_FUELS)
                .add(AllItems.COKE.getId(), new FurnaceFuel(16000), false)
                .add(AllBlocks.BLOCK_OF_COKE.getId(), new FurnaceFuel(16000), false);
    }
}

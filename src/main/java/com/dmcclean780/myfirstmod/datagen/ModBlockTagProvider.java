package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.openal.AL;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MyFirstMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TIN_STONE_ORE.get())
                .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
                .add(ModBlocks.LIMESTONE.get())
                .add(ModBlocks.TIN_BLOCK.get())
                .add(ModBlocks.RAW_TIN_BLOCK.get());
               
        tag(BlockTags.NEEDS_STONE_TOOL)
            .add(ModBlocks.LIMESTONE.get())
            .add(ModBlocks.TIN_STONE_ORE.get())
            .add(ModBlocks.TIN_DEEPSLATE_ORE.get())
            .add(ModBlocks.RAW_TIN_BLOCK.get())
            .add(ModBlocks.TIN_BLOCK.get());
        
        this.tag(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.OLYMPIAN_LOG.get())
            .add(ModBlocks.OLYMPIAN_WOOD.get())
            .add(ModBlocks.STRIPPED_OLYMPIAN_LOG.get())
            .add(ModBlocks.STRIPPED_OLYMPIAN_WOOD.get());
                

    }
}

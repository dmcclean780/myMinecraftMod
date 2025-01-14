package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;
import com.dmcclean780.myfirstmod.item.ModItems;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.minecraft.world.level.block.Block;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MyFirstMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.LIMESTONE_PIECE.get());
        basicItem(ModItems.TIN_INGOT.get());
        basicItem(ModItems.RAW_TIN.get());

        saplingItem(ModBlocks.OLYMPIAN_SAPLING);
    }

    private ItemModelBuilder saplingItem(DeferredBlock<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(MyFirstMod.MODID,"block/" + item.getId().getPath()));
    }
}

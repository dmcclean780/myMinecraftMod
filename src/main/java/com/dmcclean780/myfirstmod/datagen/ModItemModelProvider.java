package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.items.AllItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MyFirstMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(AllItems.COKE.get());
        basicItem(AllItems.LIMESTONE_PIECE.get());
    }
}

package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.blocks.AllBlocks;
import com.dmcclean780.myfirstmod.items.AllItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.BLOCK_OF_COKE.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', AllItems.COKE.get())
                .unlockedBy("has_coke", has(AllItems.COKE)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.COKE.get(), 9)
                .requires(AllBlocks.BLOCK_OF_COKE)
                .unlockedBy("has_block_of_coke", has(AllBlocks.BLOCK_OF_COKE)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.LIMESTONE.get())
                .pattern("BB")
                .pattern("BB")
                .define('B', AllItems.LIMESTONE_PIECE.get())
                .unlockedBy("has_limestone_piece", has(AllItems.LIMESTONE_PIECE)).save(recipeOutput);

        
        List<ItemLike> COKE_INGREDIENTS = List.of(Items.COAL);
        oreBlasting(recipeOutput, COKE_INGREDIENTS, RecipeCategory.MISC, AllItems.COKE.get(), 0.25f, 1000, "coke");

        List<ItemLike> COKE_BLOCK_INGREDIENTS = List.of(Items.COAL_BLOCK);
        oreBlasting(recipeOutput, COKE_BLOCK_INGREDIENTS, RecipeCategory.MISC, AllBlocks.BLOCK_OF_COKE.get(), 0.25f, 1000, "coke");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, MyFirstMod.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}

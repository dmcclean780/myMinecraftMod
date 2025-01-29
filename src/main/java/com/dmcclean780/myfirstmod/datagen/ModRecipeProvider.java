package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;
import com.dmcclean780.myfirstmod.item.ModItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LIMESTONE.get())
                .pattern("BB")
                .pattern("BB")
                .define('B', ModItems.LIMESTONE_PIECE.get())
                .unlockedBy("has_limestone_piece", has(ModItems.LIMESTONE_PIECE)).save(recipeOutput);



        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_TIN_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.RAW_TIN)
                .unlockedBy("has_raw_tin", has(ModItems.RAW_TIN)).save(recipeOutput);
        
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_TIN, 9)
                .requires(ModBlocks.RAW_TIN_BLOCK.get())
                .unlockedBy("has_raw_tin_block", has(ModBlocks.RAW_TIN_BLOCK.get())).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TIN_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.TIN_INGOT)
                .unlockedBy("has_tin_ingot", has(ModItems.TIN_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TIN_INGOT, 9)
                .requires(ModBlocks.TIN_BLOCK.get())
                .unlockedBy("has_tin_block", has(ModBlocks.TIN_BLOCK.get())).save(recipeOutput);

        List<ItemLike> tin_smelting_items = List.of(ModBlocks.TIN_STONE_ORE.get(), ModBlocks.TIN_DEEPSLATE_ORE.get(), ModItems.RAW_TIN.get());
        oreSmelting(recipeOutput, tin_smelting_items, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 200, "tin");
        oreBlasting(recipeOutput, tin_smelting_items, RecipeCategory.MISC, ModItems.TIN_INGOT, 0.25f, 100, "tin");

        stairBuilder(ModBlocks.BLOODWOOD_STAIRS.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS)).group("bloodwood")
                .unlockedBy("has_bloodwood", has(ModBlocks.BLOODWOOD_PLANKS)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODWOOD_SLAB.get(), ModBlocks.BLOODWOOD_PLANKS.get());

        buttonBuilder(ModBlocks.BLOODWOOD_BUTTON.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS.get())).group("bloodwood")
                .unlockedBy("has_bloodwood", has(ModBlocks.BLOODWOOD_PLANKS.get())).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.BLOODWOOD_PRESSURE_PLATE.get(), ModBlocks.BLOODWOOD_PLANKS.get());

        fenceBuilder(ModBlocks.BLOODWOOD_FENCE.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS.get())).group("bloodwood")
                .unlockedBy("has_bloodwood", has(ModBlocks.BLOODWOOD_PLANKS.get())).save(recipeOutput);
        fenceGateBuilder(ModBlocks.BLOODWOOD_FENCE_GATE.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS.get())).group("bloodwood")
                .unlockedBy("has_bloodwood", has(ModBlocks.BLOODWOOD_PLANKS.get())).save(recipeOutput);

        doorBuilder(ModBlocks.BLOODWOOD_DOOR.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS.get())).group("bloodwood")
                .unlockedBy("has_bloodwood", has(ModBlocks.BLOODWOOD_PLANKS.get())).save(recipeOutput);
        trapdoorBuilder(ModBlocks.BLOODWOOD_TRAPDOOR.get(), Ingredient.of(ModBlocks.BLOODWOOD_PLANKS.get())).group("bloodwood")
                .unlockedBy("has_bloodwood", has(ModBlocks.BLOODWOOD_PLANKS.get())).save(recipeOutput);
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

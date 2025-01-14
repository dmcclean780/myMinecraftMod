package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.MyFirstMod;
import com.dmcclean780.myfirstmod.block.ModBlocks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MyFirstMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TIN_STONE_ORE);
        blockWithItem(ModBlocks.TIN_DEEPSLATE_ORE);
        blockWithItem(ModBlocks.TIN_BLOCK);
        blockWithItem(ModBlocks.RAW_TIN_BLOCK);

        blockWithItem(ModBlocks.LIMESTONE);

        logBlock(((RotatedPillarBlock) ModBlocks.OLYMPIAN_LOG.get()));
        axisBlock( ( (RotatedPillarBlock)  ModBlocks.OLYMPIAN_WOOD.get() ) , blockTexture( ModBlocks.OLYMPIAN_LOG.get() ));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_OLYMPIAN_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_OLYMPIAN_WOOD.get()), blockTexture(ModBlocks.STRIPPED_OLYMPIAN_LOG.get()));

        blockItem(ModBlocks.OLYMPIAN_LOG);
        blockItem(ModBlocks.OLYMPIAN_WOOD);
        blockItem(ModBlocks.STRIPPED_OLYMPIAN_LOG);
        blockItem(ModBlocks.STRIPPED_OLYMPIAN_WOOD);
        blockWithItem(ModBlocks.OLYMPIAN_PLANKS);

        leavesBlock(ModBlocks.OLYMPIAN_LEAVES);
        saplingBlock(ModBlocks.OLYMPIAN_SAPLING);


    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }


    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("myfirstmod:block/" + deferredBlock.getId().getPath()));
    }
}

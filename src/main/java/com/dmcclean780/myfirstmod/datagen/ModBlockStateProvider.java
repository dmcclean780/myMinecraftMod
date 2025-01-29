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

        logBlock(((RotatedPillarBlock) ModBlocks.BLOODWOOD_LOG.get()));
        axisBlock( ( (RotatedPillarBlock)  ModBlocks.BLOODWOOD_WOOD.get() ) , blockTexture( ModBlocks.BLOODWOOD_LOG.get() ));
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_BLOODWOOD_WOOD.get()), blockTexture(ModBlocks.STRIPPED_BLOODWOOD_LOG.get()));

        blockItem(ModBlocks.BLOODWOOD_LOG);
        blockItem(ModBlocks.BLOODWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_BLOODWOOD_WOOD);
        blockWithItem(ModBlocks.BLOODWOOD_PLANKS);

        leavesBlock(ModBlocks.BLOODWOOD_LEAVES);
        saplingBlock(ModBlocks.BLOODWOOD_SAPLING);

        stairsBlock(ModBlocks.BLOODWOOD_STAIRS.get(), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()));
        slabBlock(ModBlocks.BLOODWOOD_SLAB.get(), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()));

        buttonBlock(ModBlocks.BLOODWOOD_BUTTON.get(), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()));
        pressurePlateBlock(ModBlocks.BLOODWOOD_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()));

        fenceBlock(ModBlocks.BLOODWOOD_FENCE.get(), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()));
        fenceGateBlock(ModBlocks.BLOODWOOD_FENCE_GATE.get(), blockTexture(ModBlocks.BLOODWOOD_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.BLOODWOOD_DOOR.get(), modLoc("block/bloodwood_door_bottom"), modLoc("block/bloodwood_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.BLOODWOOD_TRAPDOOR.get(), modLoc("block/bloodwood_trapdoor"), true, "cutout");

        blockItem(ModBlocks.BLOODWOOD_STAIRS);
        blockItem(ModBlocks.BLOODWOOD_SLAB);
        blockItem(ModBlocks.BLOODWOOD_PRESSURE_PLATE);
        blockItem(ModBlocks.BLOODWOOD_FENCE_GATE);
        blockItem(ModBlocks.BLOODWOOD_TRAPDOOR, "_bottom");
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

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("myfirstmod:block/" + deferredBlock.getId().getPath() + appendix));
    }
}

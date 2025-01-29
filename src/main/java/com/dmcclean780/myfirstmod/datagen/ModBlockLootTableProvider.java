package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.block.ModBlocks;
import com.dmcclean780.myfirstmod.item.ModItems;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        

        add(ModBlocks.LIMESTONE.get(),
                block -> createMultipleOreDrops(ModBlocks.LIMESTONE.get(), ModItems.LIMESTONE_PIECE.get(), 2, 5));

    
        dropSelf(ModBlocks.TIN_BLOCK.get());
        dropSelf(ModBlocks.RAW_TIN_BLOCK.get());
        add(ModBlocks.TIN_DEEPSLATE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.TIN_DEEPSLATE_ORE.get(), ModItems.RAW_TIN.get(), 1, 1));
        add(ModBlocks.TIN_STONE_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.TIN_STONE_ORE.get(), ModItems.RAW_TIN.get(), 1, 1));

        dropSelf(ModBlocks.BLOODWOOD_LOG.get());
        dropSelf(ModBlocks.BLOODWOOD_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_BLOODWOOD_LOG.get());
        dropSelf(ModBlocks.STRIPPED_BLOODWOOD_WOOD.get());
        dropSelf(ModBlocks.BLOODWOOD_PLANKS.get());
        dropSelf(ModBlocks.BLOODWOOD_STAIRS.get());
        dropSelf(ModBlocks.BLOODWOOD_BUTTON.get());
        dropSelf(ModBlocks.BLOODWOOD_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BLOODWOOD_TRAPDOOR.get());
        dropSelf(ModBlocks.BLOODWOOD_FENCE.get());
        dropSelf(ModBlocks.BLOODWOOD_FENCE_GATE.get());
        add(ModBlocks.BLOODWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.BLOODWOOD_SLAB.get()));
        add(ModBlocks.BLOODWOOD_DOOR.get(), 
                block -> createDoorTable(ModBlocks.BLOODWOOD_DOOR.get()));
        dropSelf(ModBlocks.BLOODWOOD_SAPLING.get());
        this.add(ModBlocks.BLOODWOOD_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.BLOODWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        // this.add(ModBlocks.BLOODIED_GRASS.get(), block ->
        //         createGrassDrops(Blocks.DIRT));
        // this.add(ModBlocks.BLOODIED_DIRT.get(), block ->
        //         createGrassDrops(Blocks.DIRT));
        

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}

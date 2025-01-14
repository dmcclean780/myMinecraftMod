package com.dmcclean780.myfirstmod.datagen;

import com.dmcclean780.myfirstmod.block.ModBlocks;
import com.dmcclean780.myfirstmod.item.ModItems;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
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

        dropSelf(ModBlocks.OLYMPIAN_LOG.get());
        dropSelf(ModBlocks.OLYMPIAN_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_OLYMPIAN_LOG.get());
        dropSelf(ModBlocks.STRIPPED_OLYMPIAN_WOOD.get());
        dropSelf(ModBlocks.OLYMPIAN_PLANKS.get());
        dropSelf(ModBlocks.OLYMPIAN_SAPLING.get());
        this.add(ModBlocks.OLYMPIAN_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.OLYMPIAN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

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

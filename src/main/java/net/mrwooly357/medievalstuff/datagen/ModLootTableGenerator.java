package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.LUMISHROOM_CAP);
        addDrop(ModBlocks.LUMISHROOM_LOG);
        addDrop(ModBlocks.LUMISHROOM_WOOD);
        addDrop(ModBlocks.STRIPPED_LUMISHROOM_LOG);
        addDrop(ModBlocks.STRIPPED_LUMISHROOM_WOOD);
        addDrop(ModBlocks.LUMISHROOM_PLANKS);
        addDrop(ModBlocks.LUMISHROOM);

        addDrop(ModBlocks.GLOOMY_STONE);
        addDrop(ModBlocks.GLOOMY_STONE_STAIRS);
        addDrop(ModBlocks.GLOOMY_STONE_SLAB, slabDrops(ModBlocks.GLOOMY_STONE_SLAB));
        addDrop(ModBlocks.GLOOMY_STONE_BUTTON);
        addDrop(ModBlocks.GLOOMY_STONE_PRESSURE_PLATE);

        addDrop(ModBlocks.GLOOMY_STONE_BRICKS);
        addDrop(ModBlocks.GLOOMY_STONE_BRICK_STAIRS);
        addDrop(ModBlocks.GLOOMY_STONE_BRICK_SLAB, slabDrops(ModBlocks.GLOOMY_STONE_BRICK_SLAB));
        addDrop(ModBlocks.GLOOMY_STONE_BRICK_WALL);


        addDrop(ModBlocks.RAW_SILVER_BLOCK);
        addDrop(ModBlocks.SILVER_BLOCK);

        addDrop(ModBlocks.SILVER_ORE, oreDrops(ModBlocks.SILVER_ORE, ModItems.RAW_SILVER));
        addDrop(ModBlocks.DEEPSLATE_SILVER_ORE, multipleOreDrops(ModBlocks.DEEPSLATE_SILVER_ORE, ModItems.RAW_SILVER, 1, 2));

        addDrop(ModBlocks.COPPERSTONE_HEATER);
    }

    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}

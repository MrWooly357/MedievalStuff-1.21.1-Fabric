package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.AbstractHeaterBlockEntity;
import net.mrwooly357.medievalstuff.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.LUMISHROOM_LOG.asItem(), ModBlocks.LUMISHROOM_WOOD.asItem(),
                        ModBlocks.STRIPPED_LUMISHROOM_LOG.asItem(), ModBlocks.STRIPPED_LUMISHROOM_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.LUMISHROOM_PLANKS.asItem());

        getOrCreateTagBuilder(ModTags.Items.HEATERS_CRAFTING_RECIPES_FUEL)
                .add(Items.COAL)
                .add(Items.CHARCOAL);

        getOrCreateTagBuilder(ModTags.Items.HEATER_FUEL_EXCEPTIONS)
                .add(Items.LAVA_BUCKET);

        getOrCreateTagBuilder(ModTags.Items.HEATER_ARSONISTS)
                .add(Items.FIRE_CHARGE)
                .add(Items.FLINT_AND_STEEL);
    }
}

package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        /* Mod */
        getOrCreateTagBuilder(ModTags.Items.CUSTOM_BOWS)
                .add(ModItems.SHORT_COPPER_BOW)
                .add(ModItems.TWOBOW);

        getOrCreateTagBuilder(ModTags.Items.HEATER_CRAFTING_RECIPE_FUEL)
                .add(Items.COAL)
                .add(Items.CHARCOAL);

        getOrCreateTagBuilder(ModTags.Items.HEATER_FUEL_EXCEPTIONS)
                .add(Items.LAVA_BUCKET);

        getOrCreateTagBuilder(ModTags.Items.HEATER_ARSONISTS)
                .add(Items.FIRE_CHARGE)
                .add(Items.FLINT_AND_STEEL);

        getOrCreateTagBuilder(ModTags.Items.DOOR_BREAKING)
                .addTag(ItemTags.AXES)
                .add(ModItems.SILVER_AXE);

        /* Vanilla */
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.SILVER_PICKAXE);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.SILVER_AXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.SILVER_SHOVEL);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.SILVER_HOE);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.SILVER_SWORD);
    }
}

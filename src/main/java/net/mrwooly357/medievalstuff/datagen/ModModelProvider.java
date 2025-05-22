package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        //Common items
        itemModelGenerator.register(ModItems.RAW_SILVER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAR, Models.GENERATED);

        //Food
        itemModelGenerator.register(ModItems.PIECE_OF_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAR_OF_JELLY, Models.GENERATED);

        //Common tools and weapons
        itemModelGenerator.register(ModItems.SILVER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_HOE, Models.HANDHELD);

        //Common weapons
        itemModelGenerator.register(ModItems.COPPER_KHOPESH, Models.HANDHELD);

        //Advanced tools and weapons
        itemModelGenerator.register(ModItems.SILVER_DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WEIGHTLESS_DAGGER_TIER_1, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WEIGHTLESS_DAGGER_TIER_2, Models.HANDHELD);

        //Armor
        itemModelGenerator.register(ModItems.SILVER_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_BOOTS, Models.GENERATED);

        //Spawn items
        itemModelGenerator.register(
                ModItems.JELLY_SPAWN_EGG, new Model(
                        Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()
                )
        );
        itemModelGenerator.register(
                ModItems.FALLEN_KNIGHT_SPAWN_EGG, new Model(
                        Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()
                )
        );
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPERSTONE_BRICKS);
    }
}

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
        itemModelGenerator.register(ModItems.SACRED_ALLOY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.SACRED_ANCIENT_MANUSCRIPT, Models.GENERATED);

        //Food
        itemModelGenerator.register(ModItems.PIECE_OF_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.JAR_OF_JELLY, Models.GENERATED);

        //Common tools and weapons
        itemModelGenerator.register(ModItems.SILVER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SILVER_HOE, Models.HANDHELD);

        //Advanced tools and weapons
        itemModelGenerator.register(ModItems.SILVER_DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WEIGHTLESS_DAGGER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WEIGHTLESS_DAGGER_TIER_2, Models.HANDHELD);

        itemModelGenerator.register(ModItems.THE_GREAT_PALADINS_CLAYMORE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SACRED_ALLOY_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SACRED_ALLOY_TREECHOPPER, Models.HANDHELD);

        //Armor
        itemModelGenerator.register(ModItems.SILVER_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_BOOTS, Models.GENERATED);

        itemModelGenerator.register(ModItems.THE_GREAT_PALADINS_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.THE_GREAT_PALADINS_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.THE_GREAT_PALADINS_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.THE_GREAT_PALADINS_BOOTS, Models.GENERATED);

        //Spawn items
        itemModelGenerator.register(ModItems.JELLY_SPAWN_EGG,
                new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty()));
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUMISHROOM_CAP);
        blockStateModelGenerator.registerLog(ModBlocks.LUMISHROOM_LOG).log(ModBlocks.LUMISHROOM_LOG).wood(ModBlocks.LUMISHROOM_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_LUMISHROOM_LOG).log(ModBlocks.STRIPPED_LUMISHROOM_LOG).wood(ModBlocks.STRIPPED_LUMISHROOM_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LUMISHROOM_PLANKS);
        blockStateModelGenerator.registerTintableCrossBlockState(ModBlocks.LUMISHROOM, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerItemModel(ModBlocks.LUMISHROOM);

        BlockStateModelGenerator.BlockTexturePool gloomyStoneTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GLOOMY_STONE);
        gloomyStoneTexturePool.stairs(ModBlocks.GLOOMY_STONE_STAIRS);
        gloomyStoneTexturePool.slab(ModBlocks.GLOOMY_STONE_SLAB);
        gloomyStoneTexturePool.button(ModBlocks.GLOOMY_STONE_BUTTON);
        gloomyStoneTexturePool.pressurePlate(ModBlocks.GLOOMY_STONE_PRESSURE_PLATE);

        BlockStateModelGenerator.BlockTexturePool gloomyStoneBrickTexturePool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.GLOOMY_STONE_BRICKS);
        gloomyStoneBrickTexturePool.stairs(ModBlocks.GLOOMY_STONE_BRICK_STAIRS);
        gloomyStoneBrickTexturePool.slab(ModBlocks.GLOOMY_STONE_BRICK_SLAB);
        gloomyStoneBrickTexturePool.wall(ModBlocks.GLOOMY_STONE_BRICK_WALL);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_SILVER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_BLOCK);


        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SILVER_ORE);
    }
}

package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.mrwooly357.medievalstuff.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.GLOOMY_STONE,
                        ModBlocks.GLOOMY_STONE_STAIRS,
                        ModBlocks.GLOOMY_STONE_SLAB,
                        ModBlocks.GLOOMY_STONE_PRESSURE_PLATE,
                        ModBlocks.GLOOMY_STONE_BUTTON,
                        ModBlocks.GLOOMY_STONE_BRICKS,
                        ModBlocks.GLOOMY_STONE_BRICK_STAIRS,
                        ModBlocks.GLOOMY_STONE_BRICK_SLAB,
                        ModBlocks.GLOOMY_STONE_BRICK_WALL,

                        ModBlocks.RAW_SILVER_BLOCK,
                        ModBlocks.SILVER_BLOCK,
                        ModBlocks.SILVER_ORE,
                        ModBlocks.DEEPSLATE_SILVER_ORE);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.RAW_SILVER_BLOCK,
                        ModBlocks.SILVER_BLOCK,
                        ModBlocks.SILVER_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DEEPSLATE_SILVER_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);

        getOrCreateTagBuilder(BlockTags.WALLS).add(ModBlocks.GLOOMY_STONE_BRICK_WALL);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.LUMISHROOM_LOG, ModBlocks.LUMISHROOM_WOOD,
                        ModBlocks.STRIPPED_LUMISHROOM_LOG, ModBlocks.STRIPPED_LUMISHROOM_WOOD);
    }
}

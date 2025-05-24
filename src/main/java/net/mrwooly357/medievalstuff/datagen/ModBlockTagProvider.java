package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        /* Mod */
        getOrCreateTagBuilder(ModTags.Blocks.NEEDS_SILVER_TOOL);

        getOrCreateTagBuilder(ModTags.Blocks.WOODEN_DOORS)
                .add(Blocks.OAK_DOOR)
                .add(Blocks.SPRUCE_DOOR)
                .add(Blocks.BIRCH_DOOR)
                .add(Blocks.JUNGLE_DOOR)
                .add(Blocks.DARK_OAK_DOOR)
                .add(Blocks.ACACIA_DOOR)
                .add(Blocks.MANGROVE_DOOR)
                .add(Blocks.CHERRY_DOOR)
                .add(Blocks.BAMBOO_DOOR)
                .add(Blocks.CRIMSON_DOOR)
                .add(Blocks.WARPED_DOOR);

        getOrCreateTagBuilder(ModTags.Blocks.METAL_DOORS)
                .add(Blocks.IRON_DOOR)
                .add(Blocks.COPPER_DOOR)
                .add(Blocks.EXPOSED_COPPER_DOOR)
                .add(Blocks.WEATHERED_COPPER_DOOR)
                .add(Blocks.OXIDIZED_COPPER_DOOR)
                .add(Blocks.WAXED_COPPER_DOOR)
                .add(Blocks.WAXED_EXPOSED_COPPER_DOOR)
                .add(Blocks.WAXED_WEATHERED_COPPER_DOOR)
                .add(Blocks.WAXED_OXIDIZED_COPPER_DOOR);

        /* Vanilla */
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(
                        ModBlocks.COPPERSTONE_BRICKS,

                        ModBlocks.RAW_SILVER_BLOCK,
                        ModBlocks.SILVER_BLOCK,
                        ModBlocks.SILVER_ORE,
                        ModBlocks.DEEPSLATE_SILVER_ORE,
                        ModBlocks.COPPERSTONE_HEATER,
                        ModBlocks.COPPER_TANK,
                        ModBlocks.COPPERSTONE_FORGE_CONTROLLER
                );

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(
                        ModBlocks.RAW_SILVER_BLOCK,
                        ModBlocks.SILVER_BLOCK,
                        ModBlocks.SILVER_ORE,
                        ModBlocks.COPPERSTONE_BRICKS,

                        ModBlocks.COPPERSTONE_HEATER,
                        ModBlocks.COPPER_TANK,
                        ModBlocks.COPPERSTONE_FORGE_CONTROLLER
                        );

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.DEEPSLATE_SILVER_ORE
                );

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL);

        getOrCreateTagBuilder(BlockTags.WALLS);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN);
    }
}

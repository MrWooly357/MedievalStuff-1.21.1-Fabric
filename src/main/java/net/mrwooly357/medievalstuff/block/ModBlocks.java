package net.mrwooly357.medievalstuff.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.custom.heaters.CopperstoneHeaterBlock;
import net.mrwooly357.medievalstuff.block.custom.tanks.CopperTankBlock;

public class ModBlocks {

    public static final Block COPPERSTONE_BRICKS = registerBlock("copperstone_bricks",
            new Block(AbstractBlock.Settings.create()
                    .strength(2.25F, 3.0F)
                    .sounds(BlockSoundGroup.STONE)
                    .requiresTool()));


    public static final Block RAW_SILVER_BLOCK = registerBlock("raw_silver_block",
            new Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.STONE)
                    .strength(4.5f).requiresTool()));

    public static final Block SILVER_BLOCK = registerBlock("silver_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4.5f)
                    .sounds(BlockSoundGroup.METAL)
                    .requiresTool()));

    //Ores
    public static final Block SILVER_ORE = registerBlock("silver_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 3),
                    AbstractBlock.Settings.create()
                            .sounds(BlockSoundGroup.STONE)
                            .strength(2.5f)
                            .requiresTool()));

    public static final Block DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 3), AbstractBlock.Settings.create()
                            .sounds(BlockSoundGroup.DEEPSLATE)
                            .strength(4f)
                            .requiresTool()));


    //Advanced blocks
    public static final Block COPPERSTONE_HEATER = registerBlock("copperstone_heater",
            new CopperstoneHeaterBlock(AbstractBlock.Settings.create()
                    .strength(3.0F, 4.0F)
                    .requiresTool()
                    .luminance(Blocks.createLightLevelFromLitBlockState(6))
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block COPPER_TANK = registerBlock("copper_tank",
            new CopperTankBlock(AbstractBlock.Settings.create()
                    .strength(2.0F, 1.5F)
                    .requiresTool()
                    .luminance(state -> state.get(CopperTankBlock.LIGHT_LEVEL))
                    .sounds(BlockSoundGroup.COPPER)
                    .nonOpaque()
                    .suffocates(Blocks::never)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MedievalStuff.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MedievalStuff.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MedievalStuff.LOGGER.info("Registering Mod Blocks for " + MedievalStuff.MOD_ID);
    }
}

package net.mrwooly357.medievalstuff.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup MEDIEVALSTUFF_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_items"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_items"))
                    .icon(() -> new ItemStack(ModItems.SILVER_INGOT)).entries((displayContext, entries) -> {
                        //Common items
                        entries.add(ModItems.RAW_SILVER);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);
                        entries.add(ModItems.JAR);
                        entries.add(ModItems.SACRED_ANCIENT_MANUSCRIPT);

                        //Food
                        entries.add(ModItems.PIECE_OF_JELLY);
                        entries.add(ModItems.JAR_OF_JELLY);

                        //Spawn items
                        entries.add(ModItems.JELLY_SPAWN_EGG);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_EQUIPMENT = Registry.register(Registries.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_equipment"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_equipment"))
                    .icon(() -> new ItemStack(ModItems.SILVER_DAGGER)).entries((displayContext, entries) -> {
                        //Common tools and weapons
                        entries.add(ModItems.SILVER_SWORD);
                        entries.add(ModItems.SILVER_AXE);
                        entries.add(ModItems.SILVER_PICKAXE);
                        entries.add(ModItems.SILVER_HOE);
                        entries.add(ModItems.SILVER_SHOVEL);


                        //Advanced tools and weapons
                        entries.add(ModItems.SILVER_DAGGER);
                        entries.add(ModItems.WEIGHTLESS_DAGGER);
                        entries.add(ModItems.WEIGHTLESS_DAGGER_TIER_2);

                        entries.add(ModItems.THE_GREAT_PALADINS_CLAYMORE);
                        entries.add(ModItems.SACRED_ALLOY_HAMMER);
                        entries.add(ModItems.SACRED_ALLOY_TREECHOPPER);


                        //Armor
                        entries.add(ModItems.SILVER_HELMET);
                        entries.add(ModItems.SILVER_CHESTPLATE);
                        entries.add(ModItems.SILVER_LEGGINGS);
                        entries.add(ModItems.SILVER_BOOTS);

                        entries.add(ModItems.THE_GREAT_PALADINS_HELMET);
                        entries.add(ModItems.THE_GREAT_PALADINS_CHESTPLATE);
                        entries.add(ModItems.THE_GREAT_PALADINS_LEGGINGS);
                        entries.add(ModItems.THE_GREAT_PALADINS_BOOTS);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_BLOCKS = Registry.register(Registries.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_blocks"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.medievalstuff_blocks")).icon(() -> new ItemStack(ModBlocks.SILVER_ORE))
                    .entries((displayContext, entries) -> {
                        //Building blocks
                        entries.add(ModBlocks.RAW_SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_BLOCK);
                        entries.add(ModBlocks.GLOOMY_STONE_STAIRS);
                        entries.add(ModBlocks.GLOOMY_STONE_SLAB);
                        entries.add(ModBlocks.GLOOMY_STONE_PRESSURE_PLATE);
                        entries.add(ModBlocks.GLOOMY_STONE_BUTTON);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICKS);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICK_STAIRS);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICK_SLAB);
                        entries.add(ModBlocks.GLOOMY_STONE_BRICK_WALL);
                        entries.add(ModBlocks.COPPERSTONE_BRICKS);


                        //Natural blocks
                        entries.add(ModBlocks.SILVER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_SILVER_ORE);

                        entries.add(ModBlocks.LUMISHROOM_LOG);
                        entries.add(ModBlocks.LUMISHROOM_WOOD);
                        entries.add(ModBlocks.STRIPPED_LUMISHROOM_LOG);
                        entries.add(ModBlocks.STRIPPED_LUMISHROOM_WOOD);
                        entries.add(ModBlocks.LUMISHROOM_PLANKS);
                        entries.add(ModBlocks.LUMISHROOM_CAP);

                        entries.add(ModBlocks.GLOOMY_STONE);

                        entries.add(ModBlocks.LUMISHROOM);


                        //Functional blocks
                        entries.add(ModBlocks.COPPERSTONE_HEATER);
                    }).build());

    public static void registerItemGroups() {
        MedievalStuff.LOGGER.info("Registering Mod Item Groups for " + MedievalStuff.MOD_ID);
    }
}

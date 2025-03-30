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
import net.mrwooly357.medievalstuff.util.ModTags;

public class ModItemGroups {

    public static final ItemGroup MEDIEVALSTUFF_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_items"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff_items"))
                    .icon(() -> new ItemStack(ModItems.RAW_SILVER))
                    .entries((displayContext, entries) -> {
                        //Common items
                        entries.add(ModItems.RAW_SILVER);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);
                        entries.add(ModItems.JAR);
                        entries.add(ModItems.SACRED_ANCIENT_MANUSCRIPT);

                        //Spawn items
                        entries.add(ModItems.JELLY_SPAWN_EGG);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_FOOD_AND_DRINKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_food_and_drinks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff_food_and_drinks"))
                    .icon(() -> new ItemStack(ModItems.PIECE_OF_JELLY))
                    .entries((displayContext, entries) -> {
                        //Food
                        entries.add(ModItems.PIECE_OF_JELLY);
                        entries.add(ModItems.JAR_OF_JELLY);

                        //Drinks
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_EQUIPMENT = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_equipment"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff_equipment"))
                    .icon(() -> new ItemStack(ModItems.WEIGHTLESS_DAGGER))
                    .entries((displayContext, entries) -> {
                        //Advanced weapons
                        entries.add(ModItems.WEIGHTLESS_DAGGER);
                        entries.add(ModItems.WEIGHTLESS_DAGGER_TIER_2);
                        entries.add(ModItems.THE_GREAT_PALADINS_CLAYMORE);

                        //Common weapons
                        entries.add(ModItems.SILVER_DAGGER);
                        entries.add(ModItems.SILVER_SWORD);

                        //Advanced tools
                        entries.add(ModItems.SILVER_AXE);
                        entries.add(ModItems.SILVER_PICKAXE);
                        entries.add(ModItems.SILVER_HOE);
                        entries.add(ModItems.SILVER_SHOVEL);

                        //Common tools
                        entries.add(ModItems.SACRED_ALLOY_HAMMER);
                        entries.add(ModItems.SACRED_ALLOY_TREECHOPPER);

                        //Advanced armor
                        entries.add(ModItems.THE_GREAT_PALADINS_HELMET);
                        entries.add(ModItems.THE_GREAT_PALADINS_CHESTPLATE);
                        entries.add(ModItems.THE_GREAT_PALADINS_LEGGINGS);
                        entries.add(ModItems.THE_GREAT_PALADINS_BOOTS);

                        //Common armor
                        entries.add(ModItems.SILVER_HELMET);
                        entries.add(ModItems.SILVER_CHESTPLATE);
                        entries.add(ModItems.SILVER_LEGGINGS);
                        entries.add(ModItems.SILVER_BOOTS);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.RAW_SILVER_BLOCK))
                    .entries((displayContext, entries) -> {
                        //Building blocks
                        entries.add(ModBlocks.RAW_SILVER_BLOCK);
                        entries.add(ModBlocks.SILVER_BLOCK);
                        entries.add(ModBlocks.COPPERSTONE_BRICKS);

                        //Natural blocks
                        entries.add(ModBlocks.SILVER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_SILVER_ORE);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_FUNCTIONAL_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_functional_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff_functional_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.COPPERSTONE_HEATER))
                    .entries((displayContext, entries) -> {
                        //Copper
                        entries.add(ModBlocks.COPPERSTONE_HEATER);
                        entries.add(ModBlocks.COPPER_TANK);
                    }).build());

    public static void registerItemGroups() {
        MedievalStuff.LOGGER.info("Registering Mod Item Groups for " + MedievalStuff.MOD_ID);
    }
}

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
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff.medievalstuff_items"))
                    .icon(() -> new ItemStack(ModItems.RAW_SILVER))
                    .entries((displayContext, entries) -> {
                        //Common items
                        entries.add(ModItems.RAW_SILVER);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);
                        entries.add(ModItems.JAR);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_FOOD_AND_DRINKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_food_and_drinks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff.medievalstuff_food_and_drinks"))
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
                    .displayName(Text.translatable("itemgroup.medievalstuff.medievalstuff_equipment"))
                    .icon(() -> new ItemStack(ModItems.WEIGHTLESS_DAGGER_TIER_1))
                    .entries((displayContext, entries) -> {
                        //Common tools

                        //Advanced tools
                        entries.add(ModItems.SILVER_AXE);
                        entries.add(ModItems.SILVER_PICKAXE);
                        entries.add(ModItems.SILVER_HOE);
                        entries.add(ModItems.SILVER_SHOVEL);



                        //Common melee weapons
                        entries.add(ModItems.SILVER_SWORD);
                        entries.add(ModItems.SILVER_DAGGER);

                        //Advanced melee weapons
                        entries.add(ModItems.WEIGHTLESS_DAGGER_TIER_1);
                        entries.add(ModItems.WEIGHTLESS_DAGGER_TIER_2);


                        //Common hybrid weapons
                        entries.add(ModItems.COPPER_KHOPESH);

                        //Advanced hybrid weapons


                        //Common ranged weapons
                        entries.add(ModItems.SHORT_COPPER_BOW);

                        //Advanced ranged weapons
                        entries.add(ModItems.TWOBOW);



                        //Common armor
                        entries.add(ModItems.SILVER_HELMET);
                        entries.add(ModItems.SILVER_CHESTPLATE);
                        entries.add(ModItems.SILVER_LEGGINGS);
                        entries.add(ModItems.SILVER_BOOTS);

                        //Advanced armor
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_BLOCKS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_blocks"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff.medievalstuff_blocks"))
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
                    .displayName(Text.translatable("itemgroup.medievalstuff.functional_blocks"))
                    .icon(() -> new ItemStack(ModBlocks.COPPERSTONE_HEATER))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.COPPERSTONE_HEATER);
                        entries.add(ModBlocks.COPPER_TANK);
                        entries.add(ModBlocks.COPPERSTONE_FORGE_CONTROLLER);
                    }).build());

    public static final ItemGroup MEDIEVALSTUFF_SPAWN_ITEMS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_spawn_items"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.medievalstuff.spawn_items"))
                    .icon(() -> new ItemStack(ModItems.JELLY_SPAWN_EGG))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.JELLY_SPAWN_EGG);
                    }).build());


    public static void registerItemGroups() {
        MedievalStuff.LOGGER.info("Registering mod item groups for " + MedievalStuff.MOD_ID);
    }
}

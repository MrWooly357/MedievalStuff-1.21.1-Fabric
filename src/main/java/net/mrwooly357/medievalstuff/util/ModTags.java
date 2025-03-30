package net.mrwooly357.medievalstuff.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_SILVER_TOOL = createTag("needs_silver_tool");
        public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = createTag("incorrect_for_silver_tool");

        public static final TagKey<Block> NEEDS_SACRED_TOOL = createTag("needs_sacred_tool");
        public static final TagKey<Block> INCORRECT_FOR_SACRED_TOOL = createTag("incorrect_for_sacred_tool");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HEATER_FUEL_EXCEPTIONS = createTag("heater_fuel_exceptions");
        public static final TagKey<Item> HEATER_ARSONISTS = createTag("heater_arsonists");
        public static final TagKey<Item> HEATER_CRAFTING_RECIPE_FUEL = createTag("heater_crafting_recipe_fuel");


        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }
}

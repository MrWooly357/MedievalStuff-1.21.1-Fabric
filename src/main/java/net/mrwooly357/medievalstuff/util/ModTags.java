package net.mrwooly357.medievalstuff.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.mrwooly357.medievalstuff.MedievalStuff;

public class ModTags {

    public static class Items {

        public static final TagKey<Item> BYPASSES_DEFAULT_INTERACTION = createItemTag("bypasses_default_interaction");
        public static final TagKey<Item> CUSTOM_BOWS = createItemTag("custom_bows");
        public static final TagKey<Item> HEATER_FUEL_EXCEPTIONS = createItemTag("heater_fuel_exceptions");
        public static final TagKey<Item> HEATER_ARSONISTS = createItemTag("heater_arsonists");
        public static final TagKey<Item> HEATER_CRAFTING_RECIPE_FUEL = createItemTag("heater_crafting_recipe_fuel");
        public static final TagKey<Item> DOOR_BREAKING = createItemTag("door_breaking");


        private static TagKey<Item> createItemTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }

    public static class Blocks {

        public static final TagKey<Block> NEEDS_SILVER_TOOL = createBlockTag("needs_silver_tool");
        public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = createBlockTag("incorrect_for_silver_tool");
        public static final TagKey<Block> FLUIDS = createBlockTag("fluids");
        public static final TagKey<Block> WOODEN_DOORS = createBlockTag("wooden_doors");
        public static final TagKey<Block> METAL_DOORS = createBlockTag("metal_doors");

        private static TagKey<Block> createBlockTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }

    public static class EntityTypes {

        public static final TagKey<EntityType<?>> SOULFUL = create("soulful");
        public static final TagKey<EntityType<?>> SOULLESS = create("soulless");


        private static TagKey<EntityType<?>> create(String name) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> FALLEN_KNIGHT_SPAWNABLE = of("fallen_knight_spawnable");


        private static TagKey<Biome> of(String name) {
            return TagKey.of(RegistryKeys.BIOME, Identifier.of(MedievalStuff.MOD_ID, name));
        }
    }
}

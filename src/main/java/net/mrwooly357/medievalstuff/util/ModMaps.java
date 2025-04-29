package net.mrwooly357.medievalstuff.util;

import com.google.common.collect.Maps;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.Map;

public class ModMaps {

    public static class TankBlocks {

        public static Map<Item, Fluid> createItemFluidMap() {
            Map<Item, Fluid> map = Maps.newLinkedHashMap();

            map.put(Items.WATER_BUCKET, Fluids.WATER);
            map.put(Items.LAVA_BUCKET, Fluids.LAVA);

            return map;
        }

        public static Map<Item, Long> createItemFluidAmountMap() {
            Map<Item, Long> map = Maps.newLinkedHashMap();

            map.put(Items.WATER_BUCKET, 81000L);
            map.put(Items.LAVA_BUCKET, 81000L);

            return map;
        }

        public static Map<Fluid, Item> createFluidBucketItemMap() {
            Map<Fluid, Item> map = Maps.newLinkedHashMap();

            map.put(Fluids.WATER, Items.WATER_BUCKET);
            map.put(Fluids.LAVA, Items.LAVA_BUCKET);

            return map;
        }

        public static Map<Item, Map<Fluid, Item>> createFluidItemMapsMap() {
            Map<Item, Map<Fluid, Item>> map = Maps.newLinkedHashMap();

            map.put(Items.BUCKET, createFluidBucketItemMap());

            return map;
        }

        public static Map<Fluid, Integer> createFluidLightMap() {
            Map<Fluid, Integer> map = Maps.newLinkedHashMap();

            map.put(Fluids.WATER, 0);
            map.put(Fluids.LAVA, 15);

            return map;
        }

        public static Map<Item, Item> createExchangeItemMap() {
            Map<Item, Item> map = Maps.newLinkedHashMap();

            map.put(Items.WATER_BUCKET, Items.BUCKET);
            map.put(Items.LAVA_BUCKET, Items.BUCKET);

            return map;
        }

        public static Map<Item, SoundEvent> createInsertSoundMap() {
            Map<Item, SoundEvent> map = Maps.newLinkedHashMap();

            map.put(Items.WATER_BUCKET, SoundEvents.ITEM_BUCKET_FILL);
            map.put(Items.LAVA_BUCKET, SoundEvents.ITEM_BUCKET_FILL_LAVA);

            return map;
        }

        public static Map<Item, SoundEvent> createExtractSoundMap() {
            Map<Item, SoundEvent> map = Maps.newLinkedHashMap();

            map.put(Items.WATER_BUCKET, SoundEvents.ITEM_BUCKET_EMPTY);
            map.put(Items.LAVA_BUCKET, SoundEvents.ITEM_BUCKET_EMPTY_LAVA);

            return map;
        }
    }
}

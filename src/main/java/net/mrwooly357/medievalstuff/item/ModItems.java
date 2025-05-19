package net.mrwooly357.medievalstuff.item;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.item.custom.*;
import net.mrwooly357.medievalstuff.item.custom.JarOfJellyItem;
import net.mrwooly357.medievalstuff.item.custom.AdvancedMiningToolItem;
import net.mrwooly357.medievalstuff.item.custom.HammerItem;
import net.mrwooly357.medievalstuff.item.custom.TreechopperItem;
import net.mrwooly357.medievalstuff.item.custom.AdvancedSweepMeleeWeaponItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClasses;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterials;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.sword_like.khopesh.CopperKhopeshItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.RangedWeaponMaterials;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.bows.TwobowItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.bows.advanced_bows.short_bows.ShortBowItem;

public class ModItems {

    //Common items
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new Item.Settings()));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));
    public static final Item SACRED_ALLOY_INGOT = registerItem("sacred_alloy_ingot", new Item(new Item.Settings()));
    public static final Item JAR = registerItem("jar", new Item(new Item.Settings().maxCount(16)));


    //Food
    public static final Item PIECE_OF_JELLY = registerItem("piece_of_jelly", new PieceOfJellyItem(new Item.Settings().food(ModFoodComponents.PIECE_OF_JELLY)));
    public static final Item JAR_OF_JELLY = registerItem("jar_of_jelly", new JarOfJellyItem(
            new Item.Settings()
                    .food(ModFoodComponents.JAR_OF_JELLY)
                    .recipeRemainder(ModItems.JAR).maxCount(16)
            )
    );


    //Common weapons
    public static final Item SILVER_SWORD = registerItem("silver_sword", new SwordItem(
            ModToolMaterials.SILVER, new Item.Settings()
                            .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SILVER, 3, -2.3f))
            )
    );
    public static final Item SILVER_DAGGER = registerItem("silver_dagger", new AdvancedSweepMeleeWeaponItem(
            ModToolMaterials.SILVER, new Item.Settings()
            .attributeModifiers(AdvancedSweepMeleeWeaponItem.createAttributeModifiers(ModToolMaterials.SILVER, 1, -2F, -1, -1))
            )
    );
    public static final Item COPPER_KHOPESH = registerItem("copper_khopesh", new CopperKhopeshItem(
            new Item.Settings()
                    .attributeModifiers(ExtendedHybridWeaponItem.createAttributeModifiers(HybridWeaponMaterials.COPPER, HybridWeaponClasses.KHOPESHES)),
            HybridWeaponMaterials.COPPER,
            2.0F
            )
    );


    //Advanced melee weapons
    public static final Item WEIGHTLESS_DAGGER_TIER_1 = registerItem("weightless_dagger_tier_1", new WeightlessDaggerItem(
            ModToolMaterials.SILVER, new Item.Settings()
                    .attributeModifiers(AdvancedSweepMeleeWeaponItem.createAttributeModifiers(ModToolMaterials.SILVER, 1, -2F, -1, -1)),
            StatusEffects.LEVITATION
            )
    );
    public static final Item WEIGHTLESS_DAGGER_TIER_2 = registerItem("weightless_dagger_tier_2", new WeightlessDaggerTier2Item(
            ModToolMaterials.SILVER, new Item.Settings()
                    .attributeModifiers(AdvancedSweepMeleeWeaponItem.createAttributeModifiers(ModToolMaterials.SILVER, 1, -1.9F, -1, -1)),
            StatusEffects.LEVITATION
            )
    );
    public static final Item THE_GREAT_PALADINS_CLAYMORE = registerItem("the_great_paladins_claymore", new TheGreatPaladinsClaymoreItem(
            ModToolMaterials.SACRED, new Item.Settings()
            .attributeModifiers(AdvancedSweepMeleeWeaponItem.createAttributeModifiers(ModToolMaterials.SACRED, 4, -2.8F, 1, 1))
            )
    );


    //Common tools
    public static final Item SILVER_PICKAXE = registerItem("silver_pickaxe", new PickaxeItem(
            ModToolMaterials.SILVER, new Item.Settings()
                    .attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.SILVER, 1f, -2.7f))
            )
    );
    public static final Item SILVER_AXE = registerItem("silver_axe", new AxeItem(
            ModToolMaterials.SILVER, new Item.Settings()
                    .attributeModifiers(AxeItem.createAttributeModifiers(
                            ModToolMaterials.SILVER, 6f, -3f))
            )
    );
    public static final Item SILVER_SHOVEL = registerItem("silver_shovel", new ShovelItem(ModToolMaterials.SILVER, new Item.Settings()
                    .attributeModifiers(ShovelItem.createAttributeModifiers(
                            ModToolMaterials.SILVER, 1.5f, -2.9f))
            )
    );
    public static final Item SILVER_HOE = registerItem("silver_hoe", new HoeItem(
            ModToolMaterials.SILVER, new Item.Settings()
                    .attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.SILVER, -2.0f, -0.9f))
            )
    );


    //Advanced tools
    public static final Item SACRED_ALLOY_HAMMER = registerItem("sacred_alloy_hammer", new HammerItem(
            ModToolMaterials.SACRED, 1, new Item.Settings()
            .attributeModifiers(AdvancedMiningToolItem.createAttributeModifiers(ModToolMaterials.SACRED, 4, -3.3F, 1, 1))
            )
    );
    public static final Item SACRED_ALLOY_TREECHOPPER = registerItem("sacred_alloy_treechopper", new TreechopperItem(
            ModToolMaterials.SACRED, 8, new Item.Settings()
            .attributeModifiers(AdvancedMiningToolItem.createAttributeModifiers(ModToolMaterials.SACRED, 4, -3.2F, 1, 1))
            )
    );


    public static final Item TWOBOW = registerItem("twobow",
            new TwobowItem(new Item.Settings().maxDamage(512).maxCount(1).rarity(Rarity.UNCOMMON)));

    public static final Item SHORT_COPPER_BOW = registerItem("short_copper_bow", new ShortBowItem(
            new Item.Settings(), RangedWeaponMaterials.COPPER
    ));


    //Armor
    public static final Item SILVER_HELMET = registerItem("silver_helmet",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings()
                    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(16))));

    public static final Item SILVER_CHESTPLATE = registerItem("silver_chestplate",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings()
                    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(16))));

    public static final Item SILVER_LEGGINGS = registerItem("silver_leggings",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(16))));

    public static final Item SILVER_BOOTS = registerItem("silver_boots",
            new ArmorItem(ModArmorMaterials.SILVER_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings()
                    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(16))));


    //Spawn items
   public static final Item JELLY_SPAWN_EGG = registerItem("jelly_spawn_egg",
           new SpawnEggItem(ModEntities.JELLY, 0x465ae0, 0x545978, new Item.Settings()));


   public static Item registerItem(String name, Item item) {
       return Registry.register(Registries.ITEM, Identifier.of(MedievalStuff.MOD_ID, name), item);
   }

   public static void registerModItems() {
       MedievalStuff.LOGGER.info("Registering Mod Items for " + MedievalStuff.MOD_ID);
   }
}

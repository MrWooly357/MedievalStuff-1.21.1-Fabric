package net.mrwooly357.medievalstuff.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClass;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial;

import java.util.concurrent.CompletableFuture;

public class ModEnUSTranslationProvider extends FabricLanguageProvider {

    public ModEnUSTranslationProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }


    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        //Items
        translationBuilder.add(ModItems.RAW_SILVER, "Raw Silver");
        translationBuilder.add(ModItems.SILVER_INGOT, "Silver Ingot");
        translationBuilder.add(ModItems.SILVER_NUGGET, "Silver Nugget");
        translationBuilder.add(ModItems.JAR, "Jar");

        translationBuilder.add(ModItems.PIECE_OF_JELLY, "Piece of Jelly");
        translationBuilder.add(ModItems.JAR_OF_JELLY, "Jar of Jelly");

        translationBuilder.add(ModItems.SILVER_PICKAXE, "Silver Pickaxe");
        translationBuilder.add(ModItems.SILVER_AXE, "Silver Axe");
        translationBuilder.add(ModItems.SILVER_SHOVEL, "Silver Shovel");
        translationBuilder.add(ModItems.SILVER_HOE, "Silver Hoe");

        translationBuilder.add(ModItems.SILVER_SWORD, "Silver Sword");
        translationBuilder.add(ModItems.SILVER_DAGGER, "Silver Dagger");
        translationBuilder.add(ModItems.COPPER_KHOPESH, "Copper Khopesh");
        translationBuilder.add(ModItems.WEIGHTLESS_DAGGER_TIER_1, "Weightless Dagger");
        translationBuilder.add(ModItems.WEIGHTLESS_DAGGER_TIER_2, "Weightless Dagger");

        translationBuilder.add(ModItems.SHORT_COPPER_BOW, "Short Copper Bow");
        translationBuilder.add(ModItems.TWOBOW, "Twobow");

        translationBuilder.add(ModItems.SILVER_HELMET, "Silver Helmet");
        translationBuilder.add(ModItems.SILVER_CHESTPLATE, "Silver Chestplate");
        translationBuilder.add(ModItems.SILVER_LEGGINGS, "Silver Leggings");
        translationBuilder.add(ModItems.SILVER_BOOTS, "Silver Boots");

        translationBuilder.add(ModItems.JELLY_SPAWN_EGG, "Jelly Spawn Egg");
        translationBuilder.add(ModItems.FALLEN_KNIGHT_SPAWN_EGG, "Fallen Knight Spawn Egg");


        //Item groups
        translationBuilder.add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_items")), "Medieval Stuff: Items");
        translationBuilder.add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_food_and_drinks")), "Medieval Stuff: Food and Drinks");
        translationBuilder.add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_equipment")), "Medieval Stuff: Equipment");
        translationBuilder.add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_blocks")), "Medieval Stuff: Blocks");
        translationBuilder.add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_functional_blocks")), "Medieval Stuff: Functional Blocks");
        translationBuilder.add(RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(MedievalStuff.MOD_ID, "medievalstuff_spawn_items")), "Medieval Stuff: Spawn Items");


        //Blocks
        translationBuilder.add(ModBlocks.SILVER_ORE, "Silver Ore");
        translationBuilder.add(ModBlocks.DEEPSLATE_SILVER_ORE, "Deepslate Silver Ore");
        translationBuilder.add(ModBlocks.RAW_SILVER_BLOCK, "Raw Silver Block");
        translationBuilder.add(ModBlocks.SILVER_BLOCK, "Silver Block");
        translationBuilder.add(ModBlocks.COPPERSTONE_BRICKS, "Copperstone Bricks");

        translationBuilder.add(ModBlocks.COPPERSTONE_HEATER, "Copperstone Heater");
        translationBuilder.add(ModBlocks.COPPER_TANK, "Copper Tank");
        translationBuilder.add(ModBlocks.COPPERSTONE_FORGE_CONTROLLER, "Copperstone Forge Controller");


        //Entities
        translationBuilder.add(ModEntityTypes.JELLY, "Jelly");
        translationBuilder.add(ModEntityTypes.THROWN_COPPER_KHOPESH, "Copper Khopesh");
        translationBuilder.add(ModEntityTypes.FALLEN_KNIGHT, "Fallen Knight");


        //Effects
        translationBuilder.add(ModStatusEffects.REACH.value(), "Reach");


        //GUIs
        addGUI("copperstone_heater", "Copperstone Heater", translationBuilder);
        addGUI("copperstone_forge_controller", "Copperstone Forge Controller", translationBuilder);


        //Tooltips
        addTooltip("keyboard_shift", "§8Press§r §7Shift §8to see the characteristics", translationBuilder);
        addTooltip("keyboard_control", "§8Press§r §7Control §8to see the description", translationBuilder);
        addTooltip("jelly.description", "§7When eaten might give §5levitation§7 effect for", translationBuilder);
        addTooltip("jar_of_jelly.description", "§7When eaten might give §5levitation§7 effect for", translationBuilder);
        addTooltip("weightless_dagger_tier_1.tooltip", "§3Tier 1", translationBuilder);
        addTooltip("weightless_dagger_tier_1.description", "§8You feel something while keeping dagger in hands.", translationBuilder);
        addTooltip("weightless_dagger_tier_2.tooltip", "§3 Tier 2", translationBuilder);
        addTooltip("weightless_dagger_tier_2.additional_tooltip_1", "§3 Tier 2", translationBuilder);
        addTooltip("weightless_dagger_tier_2.description", "§8The feeling is stronger. You start to feel a bit uncomfortable. The dagger wants something...", translationBuilder);

        //Death messages
        addDeathMessage("prickle", "%1$s died by self-prickling too much", translationBuilder);
        addDeathMessage("prickle.player", "%1$s died by self-prickling too much while trying to escape %2$s", translationBuilder);
    }

    public void addGUI(String name, String translation, TranslationBuilder translationBuilder) {
        translationBuilder.add("gui" + "." + MedievalStuff.MOD_ID + "." + name, translation);
    }

    public void addTooltip(String name, String translation, TranslationBuilder translationBuilder) {
        translationBuilder.add("tooltip" + "." + MedievalStuff.MOD_ID + "." + name, translation);
    }

    public void addDeathMessage(String name, String translation, TranslationBuilder translationBuilder) {
        translationBuilder.add("death" + "." + "attack" + "." + name, translation);
    }

    public void addHybridWeaponMaterial(HybridWeaponMaterial hybridWeaponMaterial, String translation, TranslationBuilder translationBuilder) {
        translationBuilder.add(hybridWeaponMaterial.getTranslationKey(), translation);
    }

    public void addHybridWeaponFamily(HybridWeaponFamily hybridWeaponFamily, String translation, TranslationBuilder translationBuilder) {
        translationBuilder.add(hybridWeaponFamily.getTranslationKey(MedievalStuff.MOD_ID), translation);
    }

    public void addHybridWeaponClass(HybridWeaponClass hybridWeaponClass, String translation, TranslationBuilder translationBuilder) {
        translationBuilder.add(hybridWeaponClass.getTranslationKey(), translation);
    }
}

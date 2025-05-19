package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.registry.ModRegistries;

public class HybridWeaponMaterials {

    public static final HybridWeaponMaterial EMPTY = registerHybridWeaponMaterial(
            "empty", new HybridWeaponMaterial()
    );

    public static final HybridWeaponMaterial COPPER = registerHybridWeaponMaterial(
            "copper", new HybridWeaponMaterial()
                    .attackDamage(2.0F)
                    .attackSpeed(1.3F)
                    .additionalAttackKnockback(0.1F)
                    .additionalAttackRange(0.0F)
                    .chargeTime(20)
                    .throwPower(1.2F)
                    .throwAccuracy(0.7F)
                    .projectileGravity(0.05F)
                    .durability(128)
                    .enchantability(5)
                    .repairIngredient(() -> Ingredient.ofItems(Items.COPPER_INGOT))
    );


    protected static HybridWeaponMaterial registerHybridWeaponMaterial(String name, HybridWeaponMaterial hybridWeaponMaterial) {
        hybridWeaponMaterial.name(name);
        hybridWeaponMaterial.translationModId(MedievalStuff.MOD_ID);

        return Registry.register(ModRegistries.HYBRID_WEAPON_MATERIAL, Identifier.of(MedievalStuff.MOD_ID, name), hybridWeaponMaterial);
    }

    public static void registerHybridWeaponMaterials() {
        MedievalStuff.LOGGER.info("Registering hybrid weapon materials for " + MedievalStuff.MOD_ID);
    }
}

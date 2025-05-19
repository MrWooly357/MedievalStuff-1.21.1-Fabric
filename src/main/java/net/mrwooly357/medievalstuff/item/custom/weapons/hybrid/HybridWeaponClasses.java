package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.registry.ModRegistries;

public class HybridWeaponClasses {

    public static final HybridWeaponClass EMPTY = registerHybridWeaponClass(
            "empty", new HybridWeaponClass()
    );

    public static final HybridWeaponClass KHOPESHES = registerHybridWeaponClass(
            "khopeshes", new HybridWeaponClass()
                    .attackDamage(1.0F)
                    .attackSpeed(0.2F)
                    .additionalAttackKnockback(0.0F)
                    .additionalAttackRange(0.0F)
                    .chargeTime(5)
                    .throwPower(0.3F)
                    .throwAccuracy(0.1F)
                    .projectileGravity(0.01F)
                    .durability(16)
                    .hybridWeaponFamily(HybridWeaponFamilies.SWORD_LIKE)
    );


    public static HybridWeaponClass registerHybridWeaponClass(String name, HybridWeaponClass hybridWeaponClass) {
        hybridWeaponClass.name(name);
        hybridWeaponClass.translationModId(MedievalStuff.MOD_ID);

        return Registry.register(ModRegistries.HYBRID_WEAPON_CLASS, Identifier.of(MedievalStuff.MOD_ID, name), hybridWeaponClass);
    }

    public static void registerHybridWeaponClasses() {
        MedievalStuff.LOGGER.info("Registering hybrid weapon classes for " + MedievalStuff.MOD_ID);
    }
}

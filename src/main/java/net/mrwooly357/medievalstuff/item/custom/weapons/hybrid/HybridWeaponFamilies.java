package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.registry.ModRegistries;

public class HybridWeaponFamilies {

    public static final HybridWeaponFamily EMPTY = registerHybridWeaponFamily(
            "empty", new HybridWeaponFamily()
    );

    public static final HybridWeaponFamily SWORD_LIKE = registerHybridWeaponFamily(
            "sword_like", new HybridWeaponFamily()
                    .attackDamage(1.0F)
                    .attackSpeed(0.2F)
                    .additionalAttackKnockback(0.1F)
                    .additionalAttackRange(0.0F)
                    .chargeTime(10)
                    .throwPower(0.4F)
                    .throwAccuracy(0.1F)
                    .projectileGravity(0.02F)
                    .durability(32)
    );


    public static HybridWeaponFamily registerHybridWeaponFamily(String name, HybridWeaponFamily hybridWeaponFamily) {
        hybridWeaponFamily.name(name);
        hybridWeaponFamily.getTranslationKey(MedievalStuff.MOD_ID);

        return Registry.register(ModRegistries.HYBRID_WEAPON_FAMILY, Identifier.of(MedievalStuff.MOD_ID, name), hybridWeaponFamily);
    }

    public static void registerHybridWeaponFamilies() {
        MedievalStuff.LOGGER.info("Registering hybrid weapon families for " + MedievalStuff.MOD_ID);
    }
}

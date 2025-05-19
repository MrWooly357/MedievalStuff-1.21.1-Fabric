package net.mrwooly357.medievalstuff.registry;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.compound.Compound;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClass;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponFamily;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial;

public class ModRegistryKeys extends RegistryKeys {

    public static final RegistryKey<Registry<Compound>> COMPOUND = of(
            "compound"
    );
    public static final RegistryKey<Registry<HybridWeaponMaterial>> HYBRID_WEAPON_MATERIAL = of(
            "hybrid_weapon_material"
    );
    public static final RegistryKey<Registry<HybridWeaponFamily>> HYBRID_WEAPON_FAMILY = of(
            "hybrid_weapon_family"
    );
    public static final RegistryKey<Registry<HybridWeaponClass>> HYBRID_WEAPON_CLASS = of(
            "hybrid_weapon_class"
    );


    private static <T> RegistryKey<Registry<T>> of(String name) {
        return RegistryKey.ofRegistry(Identifier.of(MedievalStuff.MOD_ID, name));
    }
}

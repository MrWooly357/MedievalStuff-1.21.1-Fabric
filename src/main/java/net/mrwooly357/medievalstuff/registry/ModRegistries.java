package net.mrwooly357.medievalstuff.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.compound.Compound;
import net.mrwooly357.medievalstuff.compound.Compounds;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.*;

public class ModRegistries extends Registries {

    public static final Registry<HybridWeaponMaterial> HYBRID_WEAPON_MATERIAL = create(
            ModRegistryKeys.HYBRID_WEAPON_MATERIAL, registry -> HybridWeaponMaterials.EMPTY
    );
    public static final Registry<HybridWeaponFamily> HYBRID_WEAPON_FAMILY = create(
            ModRegistryKeys.HYBRID_WEAPON_FAMILY, registry -> HybridWeaponFamilies.EMPTY
    );
    public static final Registry<HybridWeaponClass> HYBRID_WEAPON_CLASS = create(
            ModRegistryKeys.HYBRID_WEAPON_CLASS, registry -> HybridWeaponClasses.EMPTY
    );
    public static final Registry<Compound> COMPOUND = create(
            ModRegistryKeys.COMPOUND, registry -> Compounds.EMPTY
    );


    public static void registerModRegistries() {
        MedievalStuff.LOGGER.info("Registering mod registries for " + MedievalStuff.MOD_ID);
    }
}

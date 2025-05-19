package net.mrwooly357.medievalstuff.entity.damage;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

public interface ModDamageTypes extends DamageTypes {

    RegistryKey<DamageType> PRICKLE = of("prickle");


    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MedievalStuff.MOD_ID, name));
    }
}

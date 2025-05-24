package net.mrwooly357.medievalstuff.entity.damage;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

public interface ModDamageTypes {

    RegistryKey<DamageType> PRICKLE = of("prickle");
    RegistryKey<DamageType> SOUL_DECAY = of("soul_decay");
    RegistryKey<DamageType> FALLEN_KNIGHT_CHARGE = of("fallen_knight_charge");


    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MedievalStuff.MOD_ID, name));
    }
}

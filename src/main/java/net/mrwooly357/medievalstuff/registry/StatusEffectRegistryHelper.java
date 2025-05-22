package net.mrwooly357.medievalstuff.registry;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;

/**
 * A helper class used to ease the process of registering custom status effects. I highly recommend looking at {@link ModStatusEffects} to see how it's used.
 */
public interface StatusEffectRegistryHelper {

    /**
     * A helper method used as an extra layer for custom status effects.
     * @param id the {@link Identifier}.
     * @param statusEffect the {@link StatusEffect}.
     * @return a registered status effect.
     */
    static RegistryEntry<StatusEffect> register(Identifier id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, id, statusEffect);
    }
}

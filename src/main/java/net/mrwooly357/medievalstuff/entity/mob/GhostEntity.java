package net.mrwooly357.medievalstuff.entity.mob;

import net.minecraft.entity.mob.MobEntity;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;

/**
 * Represents that an entity is a some type of ghost.
 */
public interface GhostEntity {

    default boolean isAffectedBySun() {
        return true;
    }

    default float sunlightProtection() {
        return 0;
    };

    default boolean isAffectedBySoulDecay() {
        return true;
    }

    default boolean canPhase() {
        return false;
    }

    boolean isShaking();

    default boolean isShaking(MobEntity mobEntity) {
        return mobEntity.hasStatusEffect(ModStatusEffects.SOUL_DECAY) && !mobEntity.hasStatusEffect(ModStatusEffects.SOUL_PROTECTION);
    }
}

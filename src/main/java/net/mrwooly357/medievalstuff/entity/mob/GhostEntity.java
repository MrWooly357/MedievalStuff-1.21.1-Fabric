package net.mrwooly357.medievalstuff.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;

/**
 * Represents that an entity is a some type of ghost.
 */
public interface GhostEntity {

    default boolean isAffectedBySun() {
        return true;
    }

    int defaultSunburnTime();

    default float sunlightProtection() {
        return 1.0F;
    }

    default boolean isAffectedBySoulDecay() {
        return true;
    }

    default boolean canPhase() {
        return false;
    }

    boolean isShaking();

    default boolean isShaking(MobEntity mobEntity) {
        return mobEntity.hasStatusEffect(ModStatusEffects.SOUL_DECAY) && !mobEntity.hasStatusEffect(ModStatusEffects.SOUL_PROTECTION) || mobEntity.isOnFire();
    }

    default void additionalBurnBehavior(Entity entity, float damage) {
        if (entity.isOnFire()) {

            if (entity.age % 20 == 0) {
                entity.damage(entity.getDamageSources().onFire(), damage);
            }
        }
    }
}

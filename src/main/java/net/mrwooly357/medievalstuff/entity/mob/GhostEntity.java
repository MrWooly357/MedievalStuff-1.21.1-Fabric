package net.mrwooly357.medievalstuff.entity.mob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.mrwooly357.medievalstuff.entity.effect.ModStatusEffects;

/**
 * Represents that an entity is a some type of ghost.
 */
public interface GhostEntity {

    default boolean isAffectedBySun(Entity entity) {
        return entity.getWorld().isDay() && !entity.getWorld().isRaining() && !entity.getWorld().isThundering() && entity.getWorld().isSkyVisible(entity.getBlockPos());
    }

    int defaultSunburnTime();

    default float sunlightProtection() {
        return 1.0F;
    }

    default boolean isAffectedBySoulDecay() {
        return true;
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

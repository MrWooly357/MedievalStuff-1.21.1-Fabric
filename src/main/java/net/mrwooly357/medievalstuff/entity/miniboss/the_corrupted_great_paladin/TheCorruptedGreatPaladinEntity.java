package net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;

public class TheCorruptedGreatPaladinEntity extends HostileEntity implements RangedAttackMob {
    private static boolean isRaged;

    public TheCorruptedGreatPaladinEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static DefaultAttributeContainer.Builder createTheCorruptedGreatPaladinEntityAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100)

                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2)

                .add(EntityAttributes.GENERIC_ARMOR, 3)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 4)

                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24)

                .add(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 8)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 2)
                .add(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 2);
    }

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
    }
}

package net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.entity.mob.GhostEntity;
import org.jetbrains.annotations.Nullable;

public class FallenKnightEntity extends HostileEntity implements GhostEntity {

    public FallenKnightEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20F)
                .add(EntityAttributes.GENERIC_ARMOR, 3F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3F)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.5F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3F)
                .build();
    }

    @Override
    protected void initGoals() {
        super.initGoals();
    }

    @Override
    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        super.initEquipment(random, localDifficulty);
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    public float sunlightProtection() {
        return 1.0F;
    }

    @Override
    public boolean isShaking() {
        return isShaking(this);
    }
}

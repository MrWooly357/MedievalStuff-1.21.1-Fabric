package net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.entity.mob.GhostEntity;
import org.jetbrains.annotations.Nullable;

public class FallenKnightEntity extends HostileEntity implements GhostEntity {

    Random random = Random.create();

    public FallenKnightEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    public static DefaultAttributeContainer createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20F)
                .add(EntityAttributes.GENERIC_ARMOR, 3F)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3F)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.5F)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F)
                .build();
    }

    @Override
    protected void initGoals() {
        super.initGoals();

        targetSelector.add(0, new ActiveTargetGoal<>(this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(50));
        goalSelector.add(0, new MeleeAttackGoal(this, 1.25F, true));
        goalSelector.add(2, new WanderAroundGoal(this, 1.0F));
        goalSelector.add(3, new WanderAroundFarGoal(this, 1.0F));
        goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 16.0F));
        goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void tick() {
        tryBurn();
        additionalBurnBehavior(this, 4.0F);

        super.tick();
    }

    protected boolean shouldPlayAdditionalSound() {
        return !getEquippedStack(EquipmentSlot.HEAD).isEmpty() || !getEquippedStack(EquipmentSlot.CHEST).isEmpty() || !getEquippedStack(EquipmentSlot.LEGS).isEmpty() || !getEquippedStack(EquipmentSlot.FEET).isEmpty();
    }

    @Override
    protected float getSoundVolume() {
        return MathHelper.nextFloat(random, 0.8F, 1.0F);
    }

    @Override
    public float getSoundPitch() {
        return MathHelper.nextFloat(random, 0.8F, 1.0F);
    }

    protected float getAdditionalSoundVolume() {
        float min = 0.9F + calculateArmorSoundInfluence() / 2;
        float max = min + 0.2F;

        return MathHelper.nextFloat(random, min, max);
    }

    protected float getAdditionalSoundPitch() {
        float min = 0.9F - calculateArmorSoundInfluence() / 2;

        float max = min + 0.2F;

        return MathHelper.nextFloat(random, min, max);
    }

    private float calculateArmorSoundInfluence() {
        float helmet = getEquippedStack(EquipmentSlot.HEAD).isEmpty() ? 0.0F : 0.2F;
        float chestplate = getEquippedStack(EquipmentSlot.CHEST).isEmpty() ? 0.0F : 0.3F;
        float leggings = getEquippedStack(EquipmentSlot.LEGS).isEmpty() ? 0.0F : 0.2F;
        float boots = getEquippedStack(EquipmentSlot.FEET).isEmpty() ? 0.0F : 0.1F;

        return helmet + chestplate + leggings + boots;
    }

    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    protected SoundEvent getAdditionalAmbientSound() {
        return SoundEvents.BLOCK_CHAIN_PLACE;
    }

    protected SoundEvent getSteppingSound() {
        return SoundEvents.ENTITY_SKELETON_STEP;
    }

    protected SoundEvent getAdditionalSteppingSound() {
        return SoundEvents.BLOCK_CHAIN_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    protected SoundEvent getAdditionalHurtSound() {
        return SoundEvents.BLOCK_CHAIN_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SKELETON_DEATH;
    }

    @Override
    public void playAmbientSound() {
        if (shouldPlayAdditionalSound()) {
            playSound(getAdditionalAmbientSound(), getAdditionalSoundVolume(), getAdditionalSoundPitch());
        }

        super.playAmbientSound();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        float volume = MathHelper.nextFloat(random, 0.2F, 0.4F);

        playSound(getSteppingSound(), getSoundVolume(), getSoundPitch());

        if (shouldPlayAdditionalSound()) {
            playSound(getAdditionalSteppingSound(), volume, getAdditionalSoundPitch());
        }

        super.playStepSound(pos, state);
    }

    @Override
    protected void playHurtSound(DamageSource damageSource) {
        if (shouldPlayAdditionalSound()) {
            playSound(getAdditionalHurtSound(), getAdditionalSoundVolume(), getAdditionalSoundPitch());
        }

        super.playHurtSound(damageSource);
    }

    @Override
    public boolean isAffectedBySun() {
        return getWorld().isDay() && !getWorld().isRaining() && !getWorld().isThundering() && getWorld().isSkyVisible(getBlockPos());
    }

    @Override
    public boolean isShaking() {
        return isShaking(this);
    }

    @Override
    public int defaultSunburnTime() {
        return 50;
    }

    private void tryBurn() {
        if (isAlive()) {

            if (isAffectedBySun()) {
                ItemStack itemStack = getEquippedStack(EquipmentSlot.HEAD);

                if (!itemStack.isEmpty()) {

                    if (itemStack.isDamageable()) {
                        Item item = itemStack.getItem();
                        int damage = MathHelper.nextInt(Random.create(), 1, 2);

                        if (age % 20 == 0) {
                            itemStack.damage(damage, this, EquipmentSlot.HEAD);

                            if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                                sendEquipmentBreakStatus(item, EquipmentSlot.HEAD);
                                equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                            }
                        }
                    }
                } else if (!isOnFire() || getFireTicks() == 1) {
                    setOnFireForTicks((int) (defaultSunburnTime() / sunlightProtection()));
                }
            }
        }
    }

    @Override
    public boolean isFireImmune() {
        return hasStatusEffect(StatusEffects.FIRE_RESISTANCE);
    }
}

package net.mrwooly357.medievalstuff.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.s2c.play.EntityAnimationS2CPacket;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.melee.ExtendedMeleeWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "attack", at = @At(value = "HEAD"), cancellable = true)
    public void attack(Entity target, CallbackInfo callbackInfo) {
        ItemStack stackInHand = getStackInHand(Hand.MAIN_HAND);
        Item itemInHand = stackInHand.getItem();

        if (target.isAttackable() && itemInHand instanceof ExtendedHybridWeaponItem extendedHybridWeaponItem) {

            if (!target.handleAttack(this)) {
                float soundVolume = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);
                float soundPitch = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);
                float baseDamage = this.isUsingRiptide() ? riptideAttackDamage : (float) getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                ItemStack weaponStack = getWeaponStack();
                DamageSource damageSource = getDamageSources().playerAttack(((PlayerEntity) ((Object) this)));
                float damageAgainst = getDamageAgainst(target, baseDamage, damageSource) - baseDamage;
                float attackCooldownProgress = getAttackCooldownProgress(0.5F);
                baseDamage *= 0.2F + attackCooldownProgress * attackCooldownProgress * 0.8F;
                damageAgainst *= attackCooldownProgress;

                resetLastAttackedTicks();

                if (target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE) && target instanceof ProjectileEntity projectileEntity && projectileEntity.deflect(ProjectileDeflection.REDIRECTED, ((PlayerEntity) ((Object) this)), ((PlayerEntity) ((Object) this)), true)) {
                    getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, getSoundCategory());

                    return;
                }

                if (baseDamage > 0.0F || damageAgainst > 0.0F) {
                    boolean bl = attackCooldownProgress > 0.9F;
                    boolean bl1;

                    if (isSprinting() && bl) {
                        getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, this.getSoundCategory(), soundVolume, soundPitch);

                        bl1 = true;
                    } else {
                        bl1 = false;
                    }

                    baseDamage += itemInHand.getBonusAttackDamage(target, baseDamage, damageSource);
                    baseDamage += extendedHybridWeaponItem.getBonusAttackDamage(((PlayerEntity) ((Object) this)), target);
                    boolean bl2 = bl && fallDistance > 0.0F && !isOnGround() && !isClimbing() && !isTouchingWater() && !hasStatusEffect(StatusEffects.BLINDNESS) && !hasVehicle() && target instanceof LivingEntity && !isSprinting();

                    if (bl2) {
                        baseDamage *= 1.5F;
                    }

                    float i = baseDamage + damageAgainst;
                    boolean bl3 = false;
                    double a = horizontalSpeed - prevHorizontalSpeed;

                    if (bl && !bl2 && !bl1 && isOnGround() && a < (double) getMovementSpeed()) {

                        if (extendedHybridWeaponItem.hasSweepingAttack()) {
                            bl3 = true;
                        }
                    }

                    float targetHealth = 0.0F;

                    if (target instanceof LivingEntity livingEntity) {
                        targetHealth = livingEntity.getHealth();
                    }

                    Vec3d targetVelocity = target.getVelocity();
                    boolean bl4 = target.damage(damageSource, i);

                    if (bl4) {
                        float knockbackStrength = getKnockbackAgainst(target, damageSource) + (bl1 ? 1.0F : 0.0F);

                        if (knockbackStrength > 0.0F) {

                            if (target instanceof LivingEntity livingEntity) {
                                livingEntity.takeKnockback(
                                        knockbackStrength * 0.5F,
                                        MathHelper.sin(getYaw() * (float) (Math.PI / 180.0)),
                                        -MathHelper.cos(getYaw() * (float) (Math.PI / 180.0))
                                );
                            } else {
                                target.addVelocity(
                                        -MathHelper.sin(getYaw() * (float) (Math.PI / 180.0)) * knockbackStrength * 0.5F,
                                        0.1,
                                        MathHelper.cos(getYaw() * (float) (Math.PI / 180.0)) * knockbackStrength * 0.5F
                                );
                            }

                            setVelocity(getVelocity().multiply(0.6, 1.0, 0.6));
                            setSprinting(false);
                        }

                        if (bl3) {
                            float l = 1.0F + (float) getAttributeValue(EntityAttributes.PLAYER_SWEEPING_DAMAGE_RATIO) * baseDamage;

                            for (LivingEntity livingEntity : getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0))) {

                                if (livingEntity != this && livingEntity != target && !isTeammate(livingEntity) && (!(livingEntity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingEntity).isMarker()) && squaredDistanceTo(livingEntity) < 9.0) {
                                    float m = getDamageAgainst(livingEntity, l, damageSource) * attackCooldownProgress;

                                    livingEntity.takeKnockback(0.4F, MathHelper.sin(getYaw() * (float) (Math.PI / 180.0)), -MathHelper.cos(getYaw() * (float) (Math.PI / 180.0)));
                                    livingEntity.damage(damageSource, m);

                                    if (getWorld() instanceof ServerWorld serverWorld) {
                                        EnchantmentHelper.onTargetDamaged(serverWorld, livingEntity, damageSource);
                                    }
                                }
                            }

                            getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, getSoundCategory(), soundVolume, soundPitch);
                            spawnSweepAttackParticles();
                        }

                        if (target instanceof ServerPlayerEntity && target.velocityModified) {
                            ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));

                            target.velocityModified = false;

                            target.setVelocity(targetVelocity);
                        }

                        if (bl2) {
                            getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, getSoundCategory(), soundVolume, soundPitch);
                            addCritParticles(target);
                        }

                        if (!bl2 && !bl3) {

                            if (bl) {
                                getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, getSoundCategory(), soundVolume, soundPitch);
                            } else {
                                getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, getSoundCategory(), soundVolume, soundPitch);
                            }
                        }

                        if (damageAgainst > 0.0F) {
                            addEnchantedHitParticles(target);
                        }

                        onAttacking(target);

                        Entity entity = target;

                        if (target instanceof EnderDragonPart) {
                            entity = ((EnderDragonPart) target).owner;
                        }

                        boolean bl5 = false;

                        if (getWorld() instanceof ServerWorld serverWorld) {

                            if (entity instanceof LivingEntity livingEntity3x) {
                                bl5 = weaponStack.postHit(livingEntity3x, ((PlayerEntity) ((Object) this)));
                            }

                            EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
                        }

                        if (!getWorld().isClient && !weaponStack.isEmpty() && entity instanceof LivingEntity) {

                            if (bl5) {
                                weaponStack.postDamageEntity((LivingEntity)entity, ((PlayerEntity) ((Object) this)));
                            }

                            if (weaponStack.isEmpty()) {

                                if (weaponStack == getMainHandStack()) {
                                    setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                                } else {
                                    setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                                }
                            }
                        }

                        if (target instanceof LivingEntity) {
                            float n = targetHealth - ((LivingEntity) target).getHealth();

                            increaseStat(Stats.DAMAGE_DEALT, Math.round(n * 10.0F), ((ServerPlayerEntity) ((Object) this)));

                            if (getWorld() instanceof ServerWorld && n > 2.0F) {

                                int o = (int) ((double) n * 0.5);

                                ((ServerWorld) getWorld()).spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY(0.5), target.getZ(), o, 0.1, 0.0, 0.1, 0.2);
                            }
                        }

                        addExhaustion(0.1F, ((PlayerEntity) ((Object) this)));
                    } else {
                        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, getSoundCategory(), soundVolume, soundPitch);
                    }
                }
            }

        } else  {
            if (target.isAttackable()) {

                if (!target.handleAttack(this)) {
                    float soundVolume = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);
                    float soundPitch = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);
                    float baseDamage = this.isUsingRiptide() ? riptideAttackDamage : (float) getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                    ItemStack weaponStack = getWeaponStack();
                    DamageSource damageSource = getDamageSources().playerAttack(((PlayerEntity) ((Object) this)));
                    float damageAgainst = getDamageAgainst(target, baseDamage, damageSource) - baseDamage;
                    float attackCooldownProgress = getAttackCooldownProgress(0.5F);
                    baseDamage *= 0.2F + attackCooldownProgress * attackCooldownProgress * 0.8F;
                    damageAgainst *= attackCooldownProgress;

                    resetLastAttackedTicks();

                    if (target.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE) && target instanceof ProjectileEntity projectileEntity && projectileEntity.deflect(ProjectileDeflection.REDIRECTED, ((PlayerEntity) ((Object) this)), ((PlayerEntity) ((Object) this)), true)) {
                        getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, getSoundCategory());

                        return;
                    }

                    if (baseDamage > 0.0F || damageAgainst > 0.0F) {
                        boolean bl = attackCooldownProgress > 0.9F;
                        boolean bl1;

                        if (isSprinting() && bl) {
                            getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, this.getSoundCategory(), soundVolume, soundPitch);

                            bl1 = true;
                        } else {
                            bl1 = false;
                        }

                        baseDamage += itemInHand.getBonusAttackDamage(target, baseDamage, damageSource);
                        boolean bl2 = bl && fallDistance > 0.0F && !isOnGround() && !isClimbing() && !isTouchingWater() && !hasStatusEffect(StatusEffects.BLINDNESS) && !hasVehicle() && target instanceof LivingEntity && !isSprinting();

                        if (bl2) {
                            baseDamage *= 1.5F;
                        }

                        float i = baseDamage + damageAgainst;
                        boolean bl3 = false;
                        double a = horizontalSpeed - prevHorizontalSpeed;

                        if (bl && !bl2 && !bl1 && isOnGround() && a < (double) getMovementSpeed()) {

                            if (itemInHand instanceof SwordItem) {
                                bl3 = true;
                            }
                        }

                        float targetHealth = 0.0F;

                        if (target instanceof LivingEntity livingEntity) {
                            targetHealth = livingEntity.getHealth();
                        }

                        Vec3d targetVelocity = target.getVelocity();
                        boolean bl4 = target.damage(damageSource, i);

                        if (bl4) {
                            float knockbackStrength = getKnockbackAgainst(target, damageSource) + (bl1 ? 1.0F : 0.0F);

                            if (knockbackStrength > 0.0F) {

                                if (target instanceof LivingEntity livingEntity) {
                                    livingEntity.takeKnockback(
                                            knockbackStrength * 0.5F,
                                            MathHelper.sin(getYaw() * (float) (Math.PI / 180.0)),
                                            -MathHelper.cos(getYaw() * (float) (Math.PI / 180.0))
                                    );
                                } else {
                                    target.addVelocity(
                                            -MathHelper.sin(getYaw() * (float) (Math.PI / 180.0)) * knockbackStrength * 0.5F,
                                            0.1,
                                            MathHelper.cos(getYaw() * (float) (Math.PI / 180.0)) * knockbackStrength * 0.5F
                                    );
                                }

                                setVelocity(getVelocity().multiply(0.6, 1.0, 0.6));
                                setSprinting(false);
                            }

                            if (bl3) {
                                float l = 1.0F + (float) getAttributeValue(EntityAttributes.PLAYER_SWEEPING_DAMAGE_RATIO) * baseDamage;

                                for (LivingEntity livingEntity : getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0))) {

                                    if (livingEntity != this && livingEntity != target && !isTeammate(livingEntity) && (!(livingEntity instanceof ArmorStandEntity) || !((ArmorStandEntity) livingEntity).isMarker()) && squaredDistanceTo(livingEntity) < 9.0) {
                                        float m = getDamageAgainst(livingEntity, l, damageSource) * attackCooldownProgress;

                                        livingEntity.takeKnockback(0.4F, MathHelper.sin(getYaw() * (float) (Math.PI / 180.0)), -MathHelper.cos(getYaw() * (float) (Math.PI / 180.0)));
                                        livingEntity.damage(damageSource, m);

                                        if (getWorld() instanceof ServerWorld serverWorld) {
                                            EnchantmentHelper.onTargetDamaged(serverWorld, livingEntity, damageSource);
                                        }
                                    }
                                }

                                getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, getSoundCategory(), soundVolume, soundPitch);
                                spawnSweepAttackParticles();
                            }

                            if (target instanceof ServerPlayerEntity && target.velocityModified) {
                                ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));

                                target.velocityModified = false;

                                target.setVelocity(targetVelocity);
                            }

                            if (bl2) {
                                getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, getSoundCategory(), soundVolume, soundPitch);
                                addCritParticles(target);
                            }

                            if (!bl2 && !bl3) {

                                if (bl) {
                                    getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, getSoundCategory(), soundVolume, soundPitch);
                                } else {
                                    getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, getSoundCategory(), soundVolume, soundPitch);
                                }
                            }

                            if (damageAgainst > 0.0F) {
                                addEnchantedHitParticles(target);
                            }

                            onAttacking(target);

                            Entity entity = target;

                            if (target instanceof EnderDragonPart) {
                                entity = ((EnderDragonPart) target).owner;
                            }

                            boolean bl5 = false;

                            if (getWorld() instanceof ServerWorld serverWorld) {

                                if (entity instanceof LivingEntity livingEntity3x) {
                                    bl5 = weaponStack.postHit(livingEntity3x, ((PlayerEntity) ((Object) this)));
                                }

                                EnchantmentHelper.onTargetDamaged(serverWorld, target, damageSource);
                            }

                            if (!getWorld().isClient && !weaponStack.isEmpty() && entity instanceof LivingEntity) {

                                if (bl5) {
                                    weaponStack.postDamageEntity((LivingEntity)entity, ((PlayerEntity) ((Object) this)));
                                }

                                if (weaponStack.isEmpty()) {

                                    if (weaponStack == getMainHandStack()) {
                                        setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                                    } else {
                                        setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                                    }
                                }
                            }

                            if (target instanceof LivingEntity) {
                                float n = targetHealth - ((LivingEntity) target).getHealth();

                                increaseStat(Stats.DAMAGE_DEALT, Math.round(n * 10.0F), ((ServerPlayerEntity) ((Object) this)));

                                if (getWorld() instanceof ServerWorld && n > 2.0F) {

                                    int o = (int) ((double) n * 0.5);

                                    ((ServerWorld) getWorld()).spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY(0.5), target.getZ(), o, 0.1, 0.0, 0.1, 0.2);
                                }
                            }

                            addExhaustion(0.1F, ((PlayerEntity) ((Object) this)));
                        } else {
                            getWorld().playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, getSoundCategory(), soundVolume, soundPitch);
                        }
                    }
                }

            }
        }

        callbackInfo.cancel();
    }

    public float getDamageAgainst(Entity target, float baseDamage, DamageSource damageSource) {

        if (getWorld() instanceof ServerWorld serverWorld) {
            return EnchantmentHelper.getDamage(serverWorld, getWeaponStack(), target, damageSource, baseDamage);
        } else {
            return baseDamage;
        }
    }

    public float getAttackCooldownProgress(float baseTime) {
        return MathHelper.clamp(((float) lastAttackedTicks + baseTime) / getAttackCooldownProgressPerTick(), 0.0F, 1.0F);
    }

    public float getAttackCooldownProgressPerTick() {
        return (float) (1.0 / getAttributeValue(EntityAttributes.GENERIC_ATTACK_SPEED) * 20.0);
    }

    public void resetLastAttackedTicks() {
        lastAttackedTicks = 0;
    }

    public void spawnSweepAttackParticles() {
        double d = -MathHelper.sin(this.getYaw() * (float) (Math.PI / 180.0));
        double e = MathHelper.cos(this.getYaw() * (float) (Math.PI / 180.0));

        if (getWorld() instanceof ServerWorld) {
            ((ServerWorld) getWorld()).spawnParticles(ParticleTypes.SWEEP_ATTACK, getX() + d, getBodyY(0.5), getZ() + e, 0, d, 0.0, e, 0.0);
        }
    }

    public void addCritParticles(Entity target) {

        if (getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.getChunkManager().sendToNearbyPlayers(this, new EntityAnimationS2CPacket(target, EntityAnimationS2CPacket.CRIT));
        }
    }

    public void addEnchantedHitParticles(Entity target) {

        if (getWorld() instanceof ServerWorld serverWorld) {
            serverWorld.getChunkManager().sendToNearbyPlayers(this, new EntityAnimationS2CPacket(target, EntityAnimationS2CPacket.ENCHANTED_HIT));
        }
    }

    public void increaseStat(Identifier stat, int amount, ServerPlayerEntity serverPlayer) {
        increaseStat(Stats.CUSTOM.getOrCreateStat(stat), amount, serverPlayer);
    }

    public void increaseStat(Stat<?> stat, int amount, ServerPlayerEntity serverPlayer) {
        serverPlayer.getStatHandler().increaseStat(((PlayerEntity) ((Object) this)), stat, amount);
        serverPlayer.getScoreboard().forEachScore(stat, this, score -> score.incrementScore(amount));
    }

    public void addExhaustion(float exhaustion, PlayerEntity player) {
        if (!player.getAbilities().invulnerable) {

            if (!this.getWorld().isClient) {
                player.getHungerManager().addExhaustion(exhaustion);
            }
        }
    }
}

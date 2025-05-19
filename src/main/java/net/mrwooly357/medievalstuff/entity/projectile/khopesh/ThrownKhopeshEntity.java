package net.mrwooly357.medievalstuff.entity.projectile.khopesh;

import net.minecraft.entity.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.sword_like.khopesh.KhopeshItem;
import org.jetbrains.annotations.Nullable;

public abstract class ThrownKhopeshEntity extends PersistentProjectileEntity {

    float throwPower;
    boolean critical;
    boolean discardAfterHit;

    @Nullable ItemStack thrownStack;

    protected ThrownKhopeshEntity(EntityType<? extends PersistentProjectileEntity> type, LivingEntity owner, World world, ItemStack stack, @Nullable ItemStack shotFrom, ItemStack thrownStack) {
        super(type, owner, world, stack, shotFrom);
        this.thrownStack = thrownStack;
    }

    protected ThrownKhopeshEntity(EntityType<? extends PersistentProjectileEntity> type, double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(type, x, y, z, world, stack, weapon);
    }

    protected ThrownKhopeshEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    public void tick() {
        if (age > 100 && !inGround && !isOnGround()) {
            addVelocity(new Vec3d(0, -getGravity() / 2, 0));
        }

        super.tick();
    }

    @Override
    protected double getGravity() {
        double gravity = 0.00F;
        Item thrownItem;

        if (thrownStack != null) {
            thrownItem = thrownStack.getItem();

            if (thrownItem instanceof KhopeshItem khopeshItem) {
                gravity = khopeshItem.getProjectileGravity();
            }
        }

        return gravity;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        Item thrownItem = getThrownStack() == null ? getDefaultItemStack().getItem() : getThrownStack().getItem();
        float damage;

        if (thrownItem instanceof KhopeshItem khopeshItem) {
            float damageHelper = khopeshItem.getAttackDamage() / khopeshItem.getThrowPower();
            damage = damageHelper * getThrowPower();

            if (isCritical()) {
                damage *= 1.5F;
            }

            if (entity.getType() != EntityType.ENDERMAN) {
                entity.damage(getDamageSources().thrown(this, getOwner()), damage);

                if (entity instanceof LivingEntity livingEntity) {
                    knockback(livingEntity, khopeshItem);
                }
            }
        }

        if (shouldDiscardAfterHit() && !getWorld().isClient) {
            getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            discard();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);

        if (shouldDiscardAfterHit() && !getWorld().isClient) {
            getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            discard();
        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = getParticleParameters();

            for (int i = 0; i < 8; i++) {
                getWorld().addParticle(particleEffect, getX(), getY(), getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = getThrownStack();

        return !itemStack.isEmpty() && !itemStack.isOf(getDefaultItemStack().getItem()) ? new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack) : (ParticleEffect) ParticleTypes.ITEM;
    }

    public float getThrowPower() {
        return throwPower;
    }

    public boolean isCritical() {
        return critical;
    }

    public boolean shouldDiscardAfterHit() {
        return discardAfterHit;
    }

    public @Nullable ItemStack getThrownStack() {
        return thrownStack;
    }

    public void setThrowPower(float throwPower) {
        this.throwPower = throwPower;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public void setDiscardAfterHit(boolean discardAfterHit) {
        this.discardAfterHit = discardAfterHit;
    }

    protected void knockback(LivingEntity target, KhopeshItem khopeshItem) {
        float knockback = khopeshItem.getAdditionalAttackKnockback();

        if (knockback > 0.0) {
            double a = Math.max(0.0, 1.0 - target.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE));
            Vec3d vec3d = getVelocity().multiply(1.0, 0.0, 1.0).normalize().multiply(knockback * 0.6 * a);

            if (vec3d.lengthSquared() > 0.0) {
                target.addVelocity(vec3d.x, 0.1, vec3d.z);
            }
        }
    }

    @Override
    protected boolean tryPickup(PlayerEntity player) {
        return switch (pickupType) {
            case DISALLOWED -> false;
            case ALLOWED -> player.getInventory().insertStack(getThrownStack());
            case CREATIVE_ONLY -> player.isInCreativeMode();
        };
    }
}

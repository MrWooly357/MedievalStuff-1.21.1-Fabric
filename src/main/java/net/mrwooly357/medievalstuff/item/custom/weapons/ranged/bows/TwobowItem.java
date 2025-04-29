package net.mrwooly357.medievalstuff.item.custom.weapons.ranged.bows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

import static net.mrwooly357.medievalstuff.config.ModConfigValues.Items.Weapons.Ranged.Bows.Twobow.*;

public class TwobowItem extends RangedWeaponItem {

    public TwobowItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        boolean isProjectileItemStackEmpty = !user.getProjectileType(stackInHand).isEmpty();

        if (!user.isInCreativeMode() && !isProjectileItemStackEmpty) {
            return TypedActionResult.fail(stackInHand);
        } else {
            user.setCurrentHand(hand);

            return TypedActionResult.consume(stackInHand);
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            ItemStack projectileItemStack = player.getProjectileType(stack);

            if (!projectileItemStack.isEmpty()) {
                int useTime = getMaxUseTime(stack, user) - remainingUseTicks;
                float pullProgress = getPullProgress(useTime);

                if (!((double) pullProgress < 0.1F)) {
                    float twobowUseSoundVolume = TWOBOW_USE_SOUND_VOLUME + pullProgress;
                    float twobowUseSoundPitch = USE_SOUND_PITCH + pullProgress / 2.5F;
                    List<ItemStack> list = load(stack, projectileItemStack, player);

                    if (world instanceof ServerWorld serverWorld && !list.isEmpty()) {
                        float projectileSpeed = pullProgress * PROJECTILE_SPEED_MULTIPLIER;
                        float projectileDivergence = PROJECTILE_DIVERGENCE - pullProgress / 2;


                        shootAll(serverWorld, player, player.getActiveHand(), stack, list, projectileSpeed, projectileDivergence, pullProgress >= 0.75, null);

                        if (pullProgress >= 0.75) {
                            float additionalProjectileSpeed = pullProgress * ADDITIONAL_PROJECTILE_SPEED_MULTIPLIER;
                            float additionalProjectileDivergence = ADDITIONAL_PROJECTILE_DIVERGENCE - pullProgress / 2;


                            shootAdditional(
                                    serverWorld,
                                    player,
                                    stack,
                                    projectileItemStack,
                                    additionalProjectileSpeed,
                                    additionalProjectileDivergence,
                                    -ADDITIONAL_PROJECTILE_ADDITIONAL_PITCH,
                                    0,
                                    pullProgress == 1.0F);
                        }
                    }

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), USE_SOUND, SoundCategory.PLAYERS, twobowUseSoundVolume, twobowUseSoundPitch);
                }
            }
        }
    }

    protected void shootAdditional(
            ServerWorld world,
            LivingEntity shooter,
            ItemStack weaponStack,
            ItemStack projectileStack,
            float speed,
            float divergence,
            float pitch,
            float yaw,
            boolean critical) {
        ProjectileEntity projectile = createArrowEntity(world, shooter, weaponStack, projectileStack, critical);
        projectile.setVelocity(shooter, shooter.getPitch() + pitch, shooter.getYaw() + yaw, 0.0F, speed, divergence);

        if (projectile instanceof PersistentProjectileEntity persistentProjectile) {
            persistentProjectile.pickupType = PersistentProjectileEntity.PickupPermission.DISALLOWED;
            world.spawnEntity(persistentProjectile);
        } else {
            world.spawnEntity(projectile);
        }
    }

    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed, divergence);
    }

    public static float getPullProgress(int useTicks) {
        float pullProgress = (float) useTicks / TICKS_PER_SECOND;
        pullProgress = (pullProgress * pullProgress + pullProgress * 2.0F) / 3.0F;

        if (pullProgress > 1.0F) {
            pullProgress = 1.0F;
        }

        return pullProgress;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return USE_ANIMATION;
    }

    @Override
    public int getRange() {
        return RANGE;
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }
}

package net.mrwooly357.medievalstuff.item.custom.weapons.ranged.bows;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.*;

import java.util.function.Predicate;

public abstract class ExtendedBowItem extends ExtendedRangedWeaponItem {

    public ExtendedBowItem(Settings settings, RangedWeaponSubclass weaponSubclass, RangedWeaponMaterial weaponMaterial) {
        super(settings, weaponSubclass, weaponMaterial);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        boolean isProjectileItemStackNotEmpty = !user.getProjectileType(stackInHand).isEmpty();

        if (!user.isInCreativeMode() && !isProjectileItemStackNotEmpty) {
            return TypedActionResult.fail(stackInHand);
        } else {
            user.setCurrentHand(hand);

            return TypedActionResult.consume(stackInHand);
        }
    }

    public static float getPullProgress(int useTicks, ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        float pullProgress = (float) useTicks / 20;
        pullProgress = (pullProgress * pullProgress + pullProgress * 2.0F) / 3.0F;

        return Math.min(pullProgress, getChargeTime(extendedRangedWeaponItem));
    }

    @Override
    protected void shoot(LivingEntity shooter, ServerWorld serverWorld, ItemStack weaponItemStack, ItemStack projectileItemStack, int index, float speed, float divergence, boolean critical, float pitch, float yaw) {
        shootArrow(shooter, serverWorld, weaponItemStack, projectileItemStack, speed, divergence, critical, pitch, yaw);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}

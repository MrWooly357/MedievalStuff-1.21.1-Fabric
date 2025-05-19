package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.sword_like.khopesh;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.ExtendedHybridWeaponItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponClasses;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial;
import net.mrwooly357.medievalstuff.util.ModUtil;

public abstract class KhopeshItem extends ExtendedHybridWeaponItem {

    float maxBonusDamage;
    float throwPower;
    boolean projectileBreakAfterHit;

    public KhopeshItem(Item.Settings settings, HybridWeaponMaterial hybridWeaponMaterial, float maxBonusDamage) {
        super(settings, hybridWeaponMaterial, HybridWeaponClasses.KHOPESHES);
        this.maxBonusDamage = maxBonusDamage;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        projectileBreakAfterHit = shouldProjectileBreakAfterHit(stackInHand);

        user.setCurrentHand(hand);

        return TypedActionResult.consume(stackInHand);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            int useTicks = getMaxUseTime(stack, player) - remainingUseTicks;

            if (useTicks >= 5) {
                ItemStack thrownStack = stack.copy();
                thrownStack.damage(throwDurabilityRequirement(), player, getEquipmentSlot(player));

                setThrowPower(Math.min((getThrowPower() / getChargeTime()) * useTicks, getThrowPower()));
                createAndSpawnProjectile(world, player, thrownStack);

                stack.decrementUnlessCreative(1, player);
            }
        }
    }

    @Override
    public float getBonusAttackDamage(PlayerEntity attacker, Entity target) {
        float distance = (float) ModUtil.getDistanceBetween(target.getX(), target.getY(), target.getZ(), attacker.getX(), attacker.getY(), attacker.getZ());

        if (distance < maxBonusDamage + 1.5F) {
            float damage = maxBonusDamage - distance + 1.5F;

            return Math.min(damage, maxBonusDamage);
        } else {
            return 0;
        }
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public boolean hasSweepingAttack() {
        return true;
    }

    public boolean shouldProjectileBreakAfterHit() {
        return projectileBreakAfterHit;
    }

    public boolean shouldProjectileBreakAfterHit(ItemStack weaponStack) {
        return weaponStack.getMaxDamage() - weaponStack.getDamage() < throwDurabilityRequirement();
    }

    public void setThrowPower(float throwPower) {
        this.throwPower = throwPower;
    }

    public abstract void createAndSpawnProjectile(World world, PlayerEntity player, ItemStack thrownStack);

    public EquipmentSlot getEquipmentSlot(PlayerEntity player) {
        return switch (player.getActiveHand()) {
            case MAIN_HAND -> EquipmentSlot.MAINHAND;
            case OFF_HAND -> EquipmentSlot.OFFHAND;
        };
    }
}

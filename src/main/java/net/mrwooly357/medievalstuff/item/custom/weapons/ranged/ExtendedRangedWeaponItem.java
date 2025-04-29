package net.mrwooly357.medievalstuff.item.custom.weapons.ranged;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ExtendedRangedWeaponItem extends RangedWeaponItem {
    RangedWeaponFamily weaponFamily;
    RangedWeaponClass weaponClass;
    RangedWeaponSubclass weaponSubclass;
    RangedWeaponMaterial weaponMaterial;

    public ExtendedRangedWeaponItem(Settings settings, RangedWeaponSubclass weaponSubclass, RangedWeaponMaterial weaponMaterial) {
        super(settings.maxDamage(
                weaponSubclass.getRangedWeaponClass().getRangedWeaponFamily().getExtraDurability()
                + weaponSubclass.getRangedWeaponClass().getExtraDurability()
                + weaponSubclass.getExtraDurability()
                + weaponMaterial.getBaseDurability()));
        this.weaponFamily = weaponSubclass.getRangedWeaponClass().getRangedWeaponFamily();
        this.weaponClass = weaponSubclass.getRangedWeaponClass();
        this.weaponSubclass = weaponSubclass;
        this.weaponMaterial = weaponMaterial;
    }


    protected abstract void shoot(LivingEntity shooter, ServerWorld serverWorld, ItemStack weaponItemStack, ItemStack projectileItemStack, int index, float speed, float divergence, boolean critical, float pitch, float yaw);

    protected void shootArrow(LivingEntity shooter, ServerWorld serverWorld, ItemStack weaponItemStack, ItemStack projectileItemStack, float speed, float divergence, boolean critical, float pitch, float yaw) {
        ProjectileEntity projectile;

        if (critical) {
            projectile = createArrowEntity(serverWorld, shooter, weaponItemStack, projectileItemStack, true);
        } else {
            projectile = createArrowEntity(serverWorld, shooter, weaponItemStack, projectileItemStack, isCritical((ExtendedRangedWeaponItem) weaponItemStack.getItem()));
        }

        projectile.setVelocity(shooter, shooter.getPitch() - pitch, shooter.getYaw() - yaw, 0.0F, speed, divergence);
        serverWorld.spawnEntity(projectile);
    }

    @Override
    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
        projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed, divergence);
    }

    protected boolean isCritical(ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        float chance = extendedRangedWeaponItem.getCriticalChance(extendedRangedWeaponItem);
        float randomizer = MathHelper.nextFloat(Random.create(), 0.0F, 1.0F);

        return randomizer <= chance;
    }

    @Override
    public int getRange() {
        return  weaponFamily.getExtraRange() + weaponClass.getExtraRange() + weaponSubclass.getExtraRange() + weaponMaterial.getBaseRange();
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return weaponMaterial.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    public static int getDurability(ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        return extendedRangedWeaponItem.weaponFamily.getExtraDurability()
                + extendedRangedWeaponItem.weaponClass.getExtraDurability()
                + extendedRangedWeaponItem.weaponSubclass.getExtraDurability()
                + extendedRangedWeaponItem.weaponMaterial.getBaseDurability();
    }

    public static float getChargeTime(ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        return extendedRangedWeaponItem.weaponFamily.getExtraChargeTime()
                + extendedRangedWeaponItem.weaponClass.getExtraChargeTime()
                + extendedRangedWeaponItem.weaponSubclass.getExtraChargeTime()
                + extendedRangedWeaponItem.weaponMaterial.getBaseChargeTime();
    }

    public static float getShotPower(ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        return extendedRangedWeaponItem.weaponFamily.getExtraShotPower()
                + extendedRangedWeaponItem.weaponClass.getExtraShotPower()
                + extendedRangedWeaponItem.weaponSubclass.getExtraShotPower()
                + extendedRangedWeaponItem.weaponMaterial.getBaseShotPower();
    }

    public static float getAccuracy(ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        return extendedRangedWeaponItem.weaponFamily.getExtraAccuracy()
                + extendedRangedWeaponItem.weaponClass.getExtraAccuracy()
                + extendedRangedWeaponItem.weaponSubclass.getExtraAccuracy()
                + extendedRangedWeaponItem.weaponMaterial.getBaseAccuracy();
    }

    public float getCriticalChance(ExtendedRangedWeaponItem extendedRangedWeaponItem) {
        return extendedRangedWeaponItem.weaponFamily.getExtraCriticalChance()
                + extendedRangedWeaponItem.weaponClass.getExtraCriticalChance()
                + extendedRangedWeaponItem.weaponSubclass.getExtraCriticalChance()
                + extendedRangedWeaponItem.weaponMaterial.getBaseCriticalChance();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ExtendedRangedWeaponItem item = (ExtendedRangedWeaponItem) stack.getItem();
        int durability = getDurability(item) - stack.getDamage();

        if (Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.charge_time").append(Text.literal(": " + getChargeTime(item))));
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.shot_power").append(Text.literal(": " + getShotPower(item))));
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.accuracy").append(Text.literal(": " + getAccuracy(item))));
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.critical_chance").append(Text.literal(": " + getCriticalChance(item))));
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.durability").append(Text.literal(": " + durability + "/" + getDurability(item))));

            if (Screen.hasControlDown()) {
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.type").append(Text.literal(": ")).append(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.type_name")));
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.family").append(Text.literal(": ")).append(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.family_" + item.weaponFamily)));
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.class").append(Text.literal(": ")).append(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.class_" + item.weaponClass)));
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.subclass").append(Text.literal(": ")).append(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.subclass_" + item.weaponSubclass)));
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.material").append(Text.literal(": ")).append(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.material_" + item.weaponMaterial)));
            } else {
                tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.keyboard_control"));
            }
        } else {
            tooltip.add(Text.translatable("tooltip.medievalstuff.extended_ranged_weapon_item.shift"));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}

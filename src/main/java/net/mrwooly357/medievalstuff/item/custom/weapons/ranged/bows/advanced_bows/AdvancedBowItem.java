package net.mrwooly357.medievalstuff.item.custom.weapons.ranged.bows.advanced_bows;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.ExtendedRangedWeaponItem;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.RangedWeaponMaterial;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.RangedWeaponSubclass;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.bows.ExtendedBowItem;

import java.util.List;

import static net.mrwooly357.medievalstuff.config.ModConfigValues.Items.Weapons.Ranged.Bows.AdvancedBows.AdvancedBow.*;

public abstract class AdvancedBowItem extends ExtendedBowItem {

    public AdvancedBowItem(Settings settings, RangedWeaponSubclass weaponSubclass, RangedWeaponMaterial weaponMaterial) {
        super(settings, weaponSubclass, weaponMaterial);
    }


    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            ItemStack projectileItemStack = player.getProjectileType(stack);
            ExtendedRangedWeaponItem item = (ExtendedRangedWeaponItem) stack.getItem();

            if (!projectileItemStack.isEmpty()) {
                int useTime = getMaxUseTime(stack, user) - remainingUseTicks;
                float pullProgress = getPullProgress(useTime, item);

                if ((pullProgress > MIN_PULL_REQUIREMENT)) {
                    float useSoundVolume = USE_SOUND_VOLUME + pullProgress / 2.0F;
                    float useSoundPitch = USE_SOUND_PITCH + pullProgress / 2.5F;
                    List<ItemStack> list = load(stack, projectileItemStack, player);

                    if (world instanceof ServerWorld serverWorld && !list.isEmpty()) {
                        float projectileSpeed = getShotPower(item) * pullProgress;
                        float projectileDivergence = BASE_DIVERGENCE - (getAccuracy(item) + pullProgress / 2);

                        shoot(user, serverWorld, stack, projectileItemStack, 0, projectileSpeed, projectileDivergence, false, 0, 0);

                        if (!user.isInCreativeMode()) {
                            stack.damage(1, user, EquipmentSlot.MAINHAND);
                        }
                    }

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), USE_SOUND, USE_SOUND_CATEGORY, useSoundVolume, useSoundPitch);
                }
            }
        }
    }
}

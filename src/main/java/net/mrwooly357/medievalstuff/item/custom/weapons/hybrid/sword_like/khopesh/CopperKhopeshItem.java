package net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.sword_like.khopesh;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.entity.projectile.khopesh.ThrownCopperKhopeshEntity;
import net.mrwooly357.medievalstuff.item.custom.weapons.hybrid.HybridWeaponMaterial;

public class CopperKhopeshItem extends KhopeshItem {

    public CopperKhopeshItem(Item.Settings settings, HybridWeaponMaterial hybridWeaponMaterial, float maxBonusDamage) {
        super(settings, hybridWeaponMaterial, maxBonusDamage);
    }


    @Override
    public void createAndSpawnProjectile(World world, PlayerEntity player, ItemStack thrownStack) {
        ThrownCopperKhopeshEntity thrownCopperKhopeshEntity = new ThrownCopperKhopeshEntity(world, player, thrownStack);

        thrownCopperKhopeshEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, throwPower, getThrowAccuracy());
        thrownCopperKhopeshEntity.setThrowPower(throwPower);
        thrownCopperKhopeshEntity.setDiscardAfterHit(shouldProjectileBreakAfterHit());

        if (MathHelper.nextFloat(Random.create(), 0.01F, getThrowCriticalChance()) <= getThrowCriticalChance()) {
            thrownCopperKhopeshEntity.setCritical(true);
        }

        if (player.isInCreativeMode()) {
            thrownCopperKhopeshEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        }

        world.spawnEntity(thrownCopperKhopeshEntity);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        ThrownCopperKhopeshEntity thrownCopperKhopeshEntity = new ThrownCopperKhopeshEntity(world);
        thrownCopperKhopeshEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;

        return thrownCopperKhopeshEntity;
    }
}

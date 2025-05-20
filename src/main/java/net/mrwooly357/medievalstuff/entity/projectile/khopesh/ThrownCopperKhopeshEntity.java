package net.mrwooly357.medievalstuff.entity.projectile.khopesh;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;
import net.mrwooly357.medievalstuff.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class ThrownCopperKhopeshEntity extends ThrownKhopeshEntity {

    public ThrownCopperKhopeshEntity(World world) {
        super(ModEntityTypes.THROWN_COPPER_KHOPESH, world);
    }

    public ThrownCopperKhopeshEntity(double x, double y, double z, World world, ItemStack stack, @Nullable ItemStack weapon) {
        super(ModEntityTypes.THROWN_COPPER_KHOPESH, x, y, z, world, stack, weapon);
    }

    public ThrownCopperKhopeshEntity(World world, PlayerEntity player, @Nullable ItemStack thrownStack) {
        super(ModEntityTypes.THROWN_COPPER_KHOPESH, player, world, new ItemStack(ModItems.COPPER_KHOPESH), null, thrownStack);
    }

    public ThrownCopperKhopeshEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModItems.COPPER_KHOPESH);
    }
}

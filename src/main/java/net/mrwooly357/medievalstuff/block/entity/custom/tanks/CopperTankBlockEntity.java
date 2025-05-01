package net.mrwooly357.medievalstuff.block.entity.custom.tanks;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;

public class CopperTankBlockEntity extends TankBlockEntity {

    public CopperTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPER_TANK_BE, pos, state);

        SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
            @Override
            protected FluidVariant getBlankVariant() {
                return FluidVariant.blank();
            }

            @Override
            protected long getCapacity(FluidVariant variant) {
                return FluidConstants.BUCKET + 40500;
            }

            @Override
            protected void onFinalCommit() {
                markDirty();
                getWorld().updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        };

        setFluidStorage(fluidStorage);
    }
}

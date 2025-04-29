package net.mrwooly357.medievalstuff.block.entity.custom.tanks;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.tanks.TankBlock;
import org.jetbrains.annotations.Nullable;

import static net.mrwooly357.medievalstuff.block.custom.tanks.TankBlock.calculateLightLevel;

public abstract class TankBlockEntity extends BlockEntity {

    SingleVariantStorage<FluidVariant> fluidStorage;
    int currentTick;

    public TankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        SingleVariantStorage.readNbt(getFluidStorage(), FluidVariant.CODEC, FluidVariant::blank, nbt, registryLookup);
        nbt.getInt("currentTick");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        SingleVariantStorage.writeNbt(getFluidStorage(), FluidVariant.CODEC, nbt, registryLookup);
        nbt.putInt("currentTick", currentTick);
    }

    public SingleVariantStorage<FluidVariant> getFluidStorage() {
        return fluidStorage;
    }

    public abstract void setFluidStorage();

    public static void tick(World world, BlockPos pos, BlockState state, TankBlockEntity entity) {

        if (entity.currentTick < 20) {
            entity.currentTick++;
        }

        if (entity.currentTick == 5) {

            if (state.get(TankBlock.BOTTOM_CONNECTED)) {
                SingleVariantStorage<FluidVariant> fluidStorage = entity.getFluidStorage();
                BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                BlockState state1 = world.getBlockState(pos1);

                if (state1.getBlock() instanceof TankBlock && state1.get(TankBlock.TOP_CONNECTED)) {
                    BlockEntity entity1 = world.getBlockEntity(pos1);

                    if (entity1 instanceof TankBlockEntity tankBlockEntity) {
                        SingleVariantStorage<FluidVariant> fluidStorage1 = tankBlockEntity.getFluidStorage();
                        long emptySpace = fluidStorage1.getCapacity() - fluidStorage1.getAmount();
                        FluidVariant variant = fluidStorage.variant;

                        if (emptySpace > 0 && variant != FluidVariant.of(Fluids.EMPTY)) {
                            long fluidTransferAmount = getFluidTransferAmount(fluidStorage, emptySpace);

                            try (Transaction transaction = Transaction.openOuter()) {
                                fluidStorage.extract(variant, fluidTransferAmount, transaction);
                                transaction.commit();

                                int lightLevel = calculateLightLevel(tankBlockEntity, variant.getFluid());

                                world.setBlockState(pos, state.with(TankBlock.LIGHT_LEVEL, lightLevel));
                            }

                            try (Transaction transaction = Transaction.openOuter()) {
                                fluidStorage1.insert(variant, fluidTransferAmount, transaction);
                                transaction.commit();

                                int lightLevel = calculateLightLevel(tankBlockEntity, fluidStorage1.variant.getFluid());

                                world.setBlockState(pos1, state1.with(TankBlock.LIGHT_LEVEL, lightLevel));
                            }
                        }
                    }
                }
            }

            entity.currentTick = 0;
        }
    }

    private static long getFluidTransferAmount(SingleVariantStorage<FluidVariant> fluidStorage, long emptySpace) {
        long fluidTransferAmount;
        long amount = fluidStorage.getAmount();

        if (emptySpace >= fluidStorage.getAmount()) {
            fluidTransferAmount = amount;
        } else {
            fluidTransferAmount = emptySpace - amount;

            if (fluidTransferAmount < 0) {
                fluidTransferAmount = -fluidTransferAmount;
            }
        }
        return fluidTransferAmount;
    }
}

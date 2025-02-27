package net.mrwooly357.medievalstuff.block.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.AbstractHeaterBlock;

public abstract class AbstractHeaterLevel1BlockEntity extends AbstractHeaterBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public AbstractHeaterLevel1BlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public ItemStack getStack(int slot) {
        markDirty();
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public ItemStack removeStack(int slot) {
        markDirty();
        return Inventories.removeStack(inventory, slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        markDirty();
        inventory.set(slot, stack);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        burnTime = nbt.getShort("BurnTime");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putShort("BurnTime", (short) burnTime);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, AbstractHeaterLevel1BlockEntity blockEntity) {
        boolean shouldMarkDirty = false;
        boolean isBurning = blockEntity.isBurning();
        ItemStack itemStackInFirstSlot = blockEntity.inventory.getFirst();

        if (blockEntity.isBurning()) {
            blockEntity.burnTime--;
        }

        if (!blockEntity.isBurning() && world.getBlockState(blockPos).get(AbstractHeaterBlock.LIT)) {
            blockEntity.burnTime = blockEntity.getFuelTime(itemStackInFirstSlot);

            if (blockEntity.isBurning()) {
                shouldMarkDirty = true;
                itemStackInFirstSlot.decrement(1);
            }
        }


        if (isBurning != blockEntity.isBurning()) {
            shouldMarkDirty = true;
            blockState = blockState.with(AbstractHeaterBlock.LIT, blockEntity.isBurning());
            world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
        }

        if (shouldMarkDirty) {
            markDirty(world, blockPos, blockState);
        }
    }
}
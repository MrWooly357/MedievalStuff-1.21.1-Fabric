package net.mrwooly357.medievalstuff.block.entity.custom.heaters;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.heaters.AbstractHeaterBlock;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreenHandler;
import org.jetbrains.annotations.Nullable;

public class CopperstoneHeaterBlockEntity extends AbstractHeaterBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public CopperstoneHeaterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPERSTONE_HEATER_BE, pos, state, 1.0F);
    }


    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.medievalstuff.copperstone_heater");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CopperstoneHeaterScreenHandler(syncId, playerInventory, this.pos);
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

        burnTime = nbt.getInt("BurnTime");

        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        nbt.putInt("BurnTime", burnTime);

        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, CopperstoneHeaterBlockEntity blockEntity) {
        boolean shouldMarkDirty = false;
        boolean isBurning = blockEntity.isBurning();
        ItemStack itemStackInFirstSlot = blockEntity.inventory.getFirst();

        if (blockEntity.isBurning()) {
            blockEntity.burnTime--;
        }

        if (!blockEntity.isBurning() && world.getBlockState(blockPos).get(AbstractHeaterBlock.LIT)) {
            blockEntity.burnTime = blockEntity.getFuelBurnTime(itemStackInFirstSlot);

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

package net.mrwooly357.medievalstuff.block.entity.custom.heaters;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.heaters.HeaterBlock;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class HeaterBlockEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory<BlockPos> {

    int burnTime;
    float fuelEfficiency;
    @Nullable
    private static final Map<Item, Integer> fuelTimes = AbstractFurnaceBlockEntity.createFuelTimeMap();
    DefaultedList<ItemStack> inventory;

    public HeaterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, float fuelEfficiency) {
        super(type, pos, state);
        this.fuelEfficiency = fuelEfficiency;
    }


    public static Map<Item, Integer> createFuelTimeMap() {
        return fuelTimes;
    }

    protected boolean isBurning() {
        return burnTime > 0;
    }

    public void setBurnTime(int burnTime) {
        this.burnTime = burnTime;
    }

    @Override
    public boolean isEmpty() {
        for (int slot = 0; slot < size(); slot++) {
            ItemStack itemStack = getStack(slot);
            if (!itemStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    protected int getFuelBurnTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            int fuelBurnTime = createFuelTimeMap().getOrDefault(item, 0) / 4;

            return (int) (fuelBurnTime * fuelEfficiency);
        }
    }

    protected void setInventory(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
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

        burnTime = nbt.getInt("BurnTime");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("BurnTime", burnTime);
    }

    public void tick(World world, BlockPos blockPos, BlockState blockState) {
        BlockEntity entity = world.getBlockEntity(pos);

        if (entity instanceof HeaterBlockEntity heaterBlockEntity) {
            boolean shouldMarkDirty = false;
            boolean isBurning = heaterBlockEntity.isBurning();
            ItemStack stackInSlot = ItemStack.EMPTY;

            for (int slot = 0; slot < inventory.size(); slot++) {
                stackInSlot = heaterBlockEntity.inventory.get(slot);

                if (!stackInSlot.isEmpty()) {
                    break;
                }
            }

            if (heaterBlockEntity.isBurning()) {
                heaterBlockEntity.burnTime--;
            }

            if (!heaterBlockEntity.isBurning() && world.getBlockState(blockPos).get(HeaterBlock.LIT)) {
                heaterBlockEntity.burnTime = heaterBlockEntity.getFuelBurnTime(stackInSlot);

                if (heaterBlockEntity.isBurning()) {
                    shouldMarkDirty = true;

                    stackInSlot.decrement(1);
                }
            }


            if (isBurning != heaterBlockEntity.isBurning()) {
                shouldMarkDirty = true;
                blockState = blockState.with(HeaterBlock.LIT, heaterBlockEntity.isBurning());

                world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
            }

            if (shouldMarkDirty) {
                markDirty(world, blockPos, blockState);
            }
        }
    }
}

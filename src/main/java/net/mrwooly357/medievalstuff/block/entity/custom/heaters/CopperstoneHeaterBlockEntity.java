package net.mrwooly357.medievalstuff.block.entity.custom.heaters;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.heaters.CopperstoneHeaterBlock;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class CopperstoneHeaterBlockEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory<BlockPos> {
    private static final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    protected static volatile Map<Item, Integer> fuelTimes;
    static int burnTime;

    public CopperstoneHeaterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPERSTONE_HEATER_BE, pos, state);
    }


    @Override
    public int size() {
        return inventory.size();
    }

    @Override
    public boolean isEmpty() {
        for (int slot = 0; slot < size(); slot++) {

            if (!getStack(slot).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        markDirty();
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        markDirty();

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
        inventory.set(slot, stack.copy());
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    public void clear() {
        inventory.clear();
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        nbt.putInt("BurnTime", burnTime);

        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        nbt.getInt("BurnTime");

        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.medievalstuff.copperstone_heater");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CopperstoneHeaterScreenHandler(syncId, playerInventory, this.pos);
    }

    public static void tick(World world, BlockPos blockPos, BlockState blockState, CopperstoneHeaterBlockEntity blockEntity) {
        boolean shouldMarkDirty = false;
        boolean isBurning = isBurning();
        ItemStack itemStackInFirstSlot = inventory.getFirst();

        if (isBurning()) {
            burnTime--;
        }

        if (!isBurning() && world.getBlockState(blockPos).get(CopperstoneHeaterBlock.LIT)) {
            burnTime = blockEntity.getFuelTime(itemStackInFirstSlot);

            if (isBurning()) {
                shouldMarkDirty = true;
                itemStackInFirstSlot.decrement(1);
            }
        }


        if (isBurning != isBurning()) {
            shouldMarkDirty = true;
            blockState = blockState.with(CopperstoneHeaterBlock.LIT, isBurning());
            world.setBlockState(blockPos, blockState, Block.NOTIFY_ALL);
        }

        if (shouldMarkDirty) {
            markDirty(world, blockPos, blockState);
        }
    }

    public static boolean isBurning() {
        return burnTime > 0;
    }

    public static void setBurnTime(int burnTime) {
        CopperstoneHeaterBlockEntity.burnTime = burnTime;
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return createFuelTimes().getOrDefault(item, 0);
        }
    }

    public static Map<Item, Integer> createFuelTimes() {
        Map<Item, Integer> map1 = fuelTimes;

        if (map1 != null) {
            return map1;
        } else {
            Map<Item, Integer> map2 = Maps.newLinkedHashMap();

            map2.put(Items.COAL, 200);
            map2.put(Items.CHARCOAL, 200);

            fuelTimes = map2;

            return map2;
        }
    }
}

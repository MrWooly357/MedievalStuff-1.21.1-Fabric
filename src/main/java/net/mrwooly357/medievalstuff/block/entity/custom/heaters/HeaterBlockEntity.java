package net.mrwooly357.medievalstuff.block.entity.custom.heaters;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class HeaterBlockEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory<BlockPos> {
    int burnTime;
    float fuelEfficiency;
    @Nullable
    private static final Map<Item, Integer> fuelTimes = AbstractFurnaceBlockEntity.createFuelTimeMap();

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
}

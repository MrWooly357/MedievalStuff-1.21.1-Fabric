package net.mrwooly357.medievalstuff.block.entity.custom.heaters;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class AbstractHeaterBlockEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory<BlockPos> {
    protected static int burnTime;
    @Nullable
    private static volatile Map<Item, Integer> fuelTimes;

    public AbstractHeaterBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    public static Map<Item, Integer> createFuelTimeMap() {
        Map<Item, Integer> map1 = fuelTimes;
        if (map1 != null) {
            return map1;
        } else {
            Map<Item, Integer> map2 = Maps.newLinkedHashMap();
            addFuel(map2, Items.COAL, 400);
            addFuel(map2, Items.CHARCOAL, 400);
            fuelTimes = map2;
            return map2;
        }
    }

    protected static void addFuel(Map<Item, Integer> fuelTimes, ItemConvertible item, int fuelTime) {
        fuelTimes.put(item.asItem(), fuelTime);
    }

    protected static boolean isBurning() {
        return burnTime > 0;
    }

    protected int getFuelTime(ItemStack fuel) {
        if (fuel.isEmpty()) {
            return 0;
        } else {
            Item item = fuel.getItem();
            return createFuelTimeMap().getOrDefault(item, 0);
        }
    }

    public static void setBurnTime(int burnTime) {
        AbstractHeaterBlockEntity.burnTime = burnTime;
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return this.pos;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }
}

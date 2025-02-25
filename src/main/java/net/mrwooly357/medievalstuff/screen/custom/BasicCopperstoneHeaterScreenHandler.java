package net.mrwooly357.medievalstuff.screen.custom;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlers;

public class BasicCopperstoneHeaterScreenHandler extends AbstractBasicHeaterScreenHandler {

    public BasicCopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(blockPos));
    }

    public BasicCopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlers.BASIC_COPPERSTONE_HEATER_SCREEN_HANDLER, syncId, playerInventory);
        checkSize((Inventory) blockEntity, 1);
        Inventory inventory = (Inventory) blockEntity;

        this.addSlot(new Slot(inventory, 0, 80, 35));
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }
}
package net.mrwooly357.medievalstuff.screen.custom.heaters;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.HeaterBlockEntity;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlerTypes;
import net.mrwooly357.medievalstuff.util.ModTags;

public class CopperstoneHeaterScreenHandler extends HeaterScreenHandler {
    private final Inventory inventory;

    public CopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(pos));
    }

    public CopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreenHandlerTypes.COPPERSTONE_HEATER_SCREEN_HANDLER, syncId, playerInventory);
        checkSize((Inventory) blockEntity, 1);
        this.inventory = (Inventory) blockEntity;

        this.addSlot(new Slot(inventory, 0, 80, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return HeaterBlockEntity.createFuelTimeMap().containsKey(stack.getItem()) && !stack.isIn(ModTags.Items.HEATER_FUEL_EXCEPTIONS);
            }
        });
    }


    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }
}

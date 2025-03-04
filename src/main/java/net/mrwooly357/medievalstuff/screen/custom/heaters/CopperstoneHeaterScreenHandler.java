package net.mrwooly357.medievalstuff.screen.custom.heaters;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlers;
import net.mrwooly357.medievalstuff.util.ModTags;

public class CopperstoneHeaterScreenHandler extends AbstractHeaterScreenHandler {
    private final Inventory inventory;

    public CopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        super(ModScreenHandlers.COPPERSTONE_HEATER_SCREEN_HANDLER, syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(blockPos));
        this.inventory = (Inventory) playerInventory.player.getWorld().getBlockEntity(blockPos);

        this.addSlot(new Slot(inventory, 0, 80, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.HEATER_FUEL);
            }
        });

        assert inventory != null;
        checkSize(inventory, 1);
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
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
}

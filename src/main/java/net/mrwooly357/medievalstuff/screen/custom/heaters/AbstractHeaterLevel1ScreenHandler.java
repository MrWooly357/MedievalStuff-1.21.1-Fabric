package net.mrwooly357.medievalstuff.screen.custom.heaters;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.AbstractHeaterLevel1BlockEntity;
import net.mrwooly357.medievalstuff.util.ModTags;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHeaterLevel1ScreenHandler extends AbstractHeaterScreenHandler {
    private final Inventory inventory;
    public final AbstractHeaterLevel1BlockEntity blockEntity;

    protected AbstractHeaterLevel1ScreenHandler(
            @Nullable ScreenHandlerType<?> screenHandlerType,
            int syncId,
            PlayerInventory playerInventory,
            BlockPos blockPos
    ) {
        this(screenHandlerType, syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(blockPos),  new ArrayPropertyDelegate(1));
    }

    protected AbstractHeaterLevel1ScreenHandler(
            @Nullable ScreenHandlerType<?> screenHandlerType,
            int syncId,
            PlayerInventory playerInventory,
            BlockEntity blockEntity,
            PropertyDelegate propertyDelegate
    ) {
        super(screenHandlerType, syncId, playerInventory, blockEntity, propertyDelegate);
        this.inventory = (Inventory) blockEntity;
        this.blockEntity = (AbstractHeaterLevel1BlockEntity) blockEntity;

        checkSize((Inventory) blockEntity, 1);
        checkDataCount(propertyDelegate, 1);

        this.addSlot(new Slot(inventory, 0, 80, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isIn(ModTags.Items.HEATER_FUEL);
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
}

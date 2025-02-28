package net.mrwooly357.medievalstuff.screen.custom.heaters;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.MathHelper;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.AbstractHeaterLevel1BlockEntity;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHeaterScreenHandler extends ScreenHandler {
    private final PropertyDelegate propertyDelegate;
    private final AbstractHeaterLevel1BlockEntity blockEntity;

    protected AbstractHeaterScreenHandler(
            @Nullable ScreenHandlerType<?> screenHandlerType,
            int syncId,
            PlayerInventory playerInventory,
            BlockEntity blockEntity,
            PropertyDelegate propertyDelegate
    ) {
        super(screenHandlerType, syncId);
        this.propertyDelegate = propertyDelegate;
        this.blockEntity = (AbstractHeaterLevel1BlockEntity) blockEntity;

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }


    protected void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    protected void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }

    public float getFuelProgress() {
        int i = this.propertyDelegate.get(0);
        if (i == 0) {
            i = 200;
        }

        return MathHelper.clamp((float)this.propertyDelegate.get(0) / (float)i, 0.0F, 1.0F);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return blockEntity.canPlayerUse(player);
    }
}

package net.mrwooly357.medievalstuff.screen.custom.heaters;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlers;

public class CopperstoneHeaterScreenHandler extends AbstractHeaterLevel1ScreenHandler {


    public CopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        super(ModScreenHandlers.COPPERSTONE_HEATER_SCREEN_HANDLER, syncId, playerInventory, blockPos);
    }

    public CopperstoneHeaterScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.COPPERSTONE_HEATER_SCREEN_HANDLER, syncId, playerInventory, blockEntity, propertyDelegate);
    }
}
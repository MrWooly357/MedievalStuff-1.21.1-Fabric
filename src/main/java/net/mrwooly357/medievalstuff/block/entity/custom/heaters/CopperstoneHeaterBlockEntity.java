package net.mrwooly357.medievalstuff.block.entity.custom.heaters;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreenHandler;
import org.jetbrains.annotations.Nullable;

public class CopperstoneHeaterBlockEntity extends HeaterBlockEntity {

    public CopperstoneHeaterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPERSTONE_HEATER_BE, pos, state, 1.0F);

        DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

        setInventory(inventory);
    }


    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.medievalstuff.copperstone_heater");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CopperstoneHeaterScreenHandler(syncId, playerInventory, this.pos);
    }
}

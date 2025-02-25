package net.mrwooly357.medievalstuff.block.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.screen.custom.BasicCopperstoneHeaterScreenHandler;
import org.jetbrains.annotations.Nullable;

public class BasicCopperstoneHeaterBlockEntity extends AbstractBasicHeaterBlockEntity {

    public BasicCopperstoneHeaterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BASIC_COPPERSTONE_HEATER_BE, pos, state);
    }


    /* Screen stuff */

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
        return new BasicCopperstoneHeaterScreenHandler(syncId, playerInventory, this.pos);
    }
}
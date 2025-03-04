package net.mrwooly357.medievalstuff.block.custom.heaters;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.CopperstoneHeaterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class CopperstoneHeaterBlock extends AbstractHeaterBlock {
    private final MapCodec<CopperstoneHeaterBlock> CODEC = CopperstoneHeaterBlock.createCodec(CopperstoneHeaterBlock::new);

    public CopperstoneHeaterBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected void openScreen(World world, BlockPos blockPos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(blockPos);

        if (blockEntity instanceof CopperstoneHeaterBlockEntity copperstoneHeaterBlockEntity) {
            player.openHandledScreen((NamedScreenHandlerFactory) copperstoneHeaterBlockEntity);
        }
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CopperstoneHeaterBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, ModBlockEntities.COPPERSTONE_HEATER_BE, CopperstoneHeaterBlockEntity::tick);
    }
}

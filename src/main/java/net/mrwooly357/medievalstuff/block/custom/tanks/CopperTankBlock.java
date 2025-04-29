package net.mrwooly357.medievalstuff.block.custom.tanks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.CopperTankBlockEntity;
import org.jetbrains.annotations.Nullable;

public class CopperTankBlock extends TankBlock {

    public static final MapCodec<CopperTankBlock> CODEC = createCodec(CopperTankBlock::new);

    public CopperTankBlock(Settings settings) {
        super(settings);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CopperTankBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }
}

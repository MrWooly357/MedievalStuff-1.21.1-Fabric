package net.mrwooly357.medievalstuff.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.custom.AbstractHeaterBlockEntity;
import net.mrwooly357.medievalstuff.block.entity.custom.CopperstoneHeaterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class CopperstoneHeaterBlock extends AbstractHeaterBlock {
    private static final MapCodec<CopperstoneHeaterBlock> CODEC = CopperstoneHeaterBlock.createCodec(CopperstoneHeaterBlock::new);


    public CopperstoneHeaterBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CopperstoneHeaterBlockEntity(pos, state);
    }
}
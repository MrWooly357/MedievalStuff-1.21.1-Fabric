package net.mrwooly357.medievalstuff.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.entity.custom.BasicCopperstoneHeaterBlockEntity;
import org.jetbrains.annotations.Nullable;

public class BasicCopperstoneHeaterBlock extends AbstractBasicHeaterBlock {
    private static final MapCodec<BasicCopperstoneHeaterBlock> CODEC = BasicCopperstoneHeaterBlock.createCodec(BasicCopperstoneHeaterBlock::new);

    public BasicCopperstoneHeaterBlock(Settings settings, int inventorySize) {
        super(settings, inventorySize);
    }

    public BasicCopperstoneHeaterBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BasicCopperstoneHeaterBlockEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.getBlockEntity(pos) instanceof BasicCopperstoneHeaterBlockEntity basicCopperstoneHeaterBlockEntity) {
            if (!world.isClient) {
                player.openHandledScreen(basicCopperstoneHeaterBlockEntity);
            }
        }
        return ActionResult.SUCCESS;
    }
}
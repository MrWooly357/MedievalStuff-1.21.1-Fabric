package net.mrwooly357.medievalstuff.block.custom.heaters;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.CopperstoneHeaterBlockEntity;
import net.mrwooly357.medievalstuff.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class CopperstoneHeaterBlock extends HeaterBlock {
    private static final MapCodec<CopperstoneHeaterBlock> CODEC = createCodec(CopperstoneHeaterBlock::new);

    public CopperstoneHeaterBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack stackInHand = player.getStackInHand(Hand.MAIN_HAND);
        if (!stackInHand.isIn(ModTags.Items.HEATER_ARSONISTS) || !stackInHand.isIn(ItemTags.SHOVELS)) {
            if (world.getBlockEntity(pos) instanceof CopperstoneHeaterBlockEntity copperstoneHeaterBlockEntity) {
                if (!world.isClient) {
                    player.openHandledScreen(copperstoneHeaterBlockEntity);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CopperstoneHeaterBlockEntity(pos, state);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, ModBlockEntities.COPPERSTONE_HEATER_BE, CopperstoneHeaterBlockEntity::tick);
    }
}

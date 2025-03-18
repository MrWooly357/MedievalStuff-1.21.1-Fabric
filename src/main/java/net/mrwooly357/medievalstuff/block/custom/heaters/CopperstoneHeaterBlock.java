package net.mrwooly357.medievalstuff.block.custom.heaters;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.CopperstoneHeaterBlockEntity;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreenHandler;
import net.mrwooly357.medievalstuff.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class CopperstoneHeaterBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;
    public static final MapCodec<CopperstoneHeaterBlock> CODEC = CopperstoneHeaterBlock.createCodec(CopperstoneHeaterBlock::new);

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

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof CopperstoneHeaterBlockEntity) {
                ItemScatterer.spawn(world, pos, ((CopperstoneHeaterBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && entity instanceof LivingEntity && state.get(LIT)) {
            entity.damage(entity.getDamageSources().inFire(), 1.0F);
        }
    }

    protected void openScreen(World world, BlockPos blockPos, PlayerEntity player) {
        if (world.getBlockEntity(blockPos) instanceof CopperstoneHeaterBlockEntity copperstoneHeaterBlockEntity) {

            if (!world.isClient) {
                player.openHandledScreen(copperstoneHeaterBlockEntity);
            }
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!state.get(LIT)) {

            if (stack.isIn(ModTags.Items.HEATER_ARSONISTS)) {

                if (world.getBlockEntity(pos) instanceof CopperstoneHeaterBlockEntity blockEntity) {
                    for (int slot = 0; slot < blockEntity.size(); slot++) {

                        if (!blockEntity.getStack(slot).isEmpty()) {
                            world.setBlockState(pos, state.with(LIT, true));

                            if (stack.isOf(Items.FLINT_AND_STEEL)) {
                                stack.damage(1, player, EquipmentSlot.MAINHAND);

                            } else {
                                stack.decrementUnlessCreative(1, player);
                            }

                            world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);

                            return ItemActionResult.SUCCESS;
                        }
                    }
                }
            }
        } else if (stack.isIn(ItemTags.SHOVELS)) {

            stack.damage(1, player, EquipmentSlot.MAINHAND);
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);

            if (world.getBlockEntity(pos) instanceof CopperstoneHeaterBlockEntity) {
                CopperstoneHeaterBlockEntity.setBurnTime(0);
            }
            return ItemActionResult.SUCCESS;
        }
        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        openScreen(world, pos, player);
        return ActionResult.SUCCESS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, ModBlockEntities.COPPERSTONE_HEATER_BE, CopperstoneHeaterBlockEntity::tick);
    }


    /* FACING */

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LIT, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
}

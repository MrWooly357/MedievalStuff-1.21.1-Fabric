package net.mrwooly357.medievalstuff.block.custom.heaters;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.AbstractHeaterBlockEntity;
import net.mrwooly357.medievalstuff.util.ModTags;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHeaterBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = Properties.LIT;

    protected AbstractHeaterBlock(AbstractBlock.Settings settings) {
        super(settings);
    }


    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        float soundRandomizer = MathHelper.nextFloat(Random.create(), -0.25F, 0.25F);

        if (!state.get(LIT)) {

            if (stack.isIn(ModTags.Items.HEATER_ARSONISTS)) {

                if (world.getBlockEntity(pos) instanceof AbstractHeaterBlockEntity blockEntity) {

                    for (int slot = 0; slot < blockEntity.size(); slot++) {

                        if (!blockEntity.getStack(slot).isEmpty() && !blockEntity.getStack(slot).isIn(ModTags.Items.HEATER_FUEL_EXCEPTIONS)) {

                            world.setBlockState(pos, state.with(LIT, true));
                            world.playSound(null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F + soundRandomizer, 1.0F + soundRandomizer);

                            if (stack.isOf(Items.FLINT_AND_STEEL)) {
                                stack.damage(1, player, EquipmentSlot.MAINHAND);

                            } else {
                                stack.decrementUnlessCreative(1, player);
                            }



                            return ItemActionResult.SUCCESS;
                        }
                    }
                }
            }
        } else if (stack.isIn(ItemTags.SHOVELS)) {
            int largeSmokeAmountRandomizer = MathHelper.nextInt(Random.create(), 1, 3);
            double randomHelper = MathHelper.nextDouble(Random.create(), -0.15, 0.15);
            double largeSmokeX = pos.getX() + 0.5 + randomHelper;
            double largeSmokeY = pos.getY() + 1.1 + randomHelper;
            double largeSmokeZ = pos.getZ() + 0.5 + randomHelper;

            world.setBlockState(pos, state.with(LIT, false));
            stack.damage(1, player, EquipmentSlot.MAINHAND);

            for (int largeSmokeAmount = 0; largeSmokeAmount < largeSmokeAmountRandomizer; largeSmokeAmount++) {
                double largeSmokeXVelocity = MathHelper.nextDouble(Random.create(), -0.03, 0.03);
                double largeSmokeYVelocity = MathHelper.nextDouble(Random.create(), -0.03, 0.03);
                double largeSmokeZVelocity = MathHelper.nextDouble(Random.create(), -0.03, 0.03);

                world.addParticle(ParticleTypes.LARGE_SMOKE, largeSmokeX, largeSmokeY, largeSmokeZ, largeSmokeXVelocity, largeSmokeYVelocity, largeSmokeZVelocity);
            }

            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F + soundRandomizer, 1.0F + soundRandomizer);

            if (world.getBlockEntity(pos) instanceof AbstractHeaterBlockEntity blockEntity) {
                blockEntity.setBurnTime(0);
            }
            return ItemActionResult.SUCCESS;
        }

        return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof AbstractHeaterBlockEntity) {
                ItemScatterer.spawn(world, pos, ((AbstractHeaterBlockEntity) blockEntity));
                world.updateComparators(pos, this);
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LIT, false);
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && entity instanceof LivingEntity && state.get(LIT)) {
            entity.damage(entity.getDamageSources().inFire(), 1.0F);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            float soundRandomizer = MathHelper.nextFloat(random, -0.25F, 0.25F);
            double offset = random.nextDouble() * 0.6 - 0.3;
            double randomHelper1 = MathHelper.nextDouble(random, -0.015, 0.015);
            double randomHelper2 = random.nextDouble() * 0.2;
            double randomHelper3 = MathHelper.nextDouble(random, -0.2, 0.2);
            double randomHelper4 = MathHelper.nextDouble(random, -0.2, 0.2);
            double randomHelper5 = MathHelper.nextDouble(random, -0.2, 0.2);

            double x = pos.getX() + 0.5;
            double y = pos.getY();
            double z = pos.getZ() + 0.5;


            if (random.nextDouble() < 0.1) {
                world.playSound(x, y, z, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F + soundRandomizer, 1.0F + soundRandomizer, false);
            }

            Direction facingDirection = state.get(FACING);
            Direction.Axis axis = facingDirection.getAxis();

            double flameXHelper = axis == Direction.Axis.X ? facingDirection.getOffsetX() * 0.6 : offset;
            double flameYHelper = random.nextDouble() * 6.0 / 16.0;
            double flameZHelper = axis == Direction.Axis.Z ? facingDirection.getOffsetZ() * 0.6 : offset;

            double flameX = x + flameXHelper + randomHelper1;
            double flameY = y + flameYHelper + randomHelper2 + 0.6;
            double flameZ = z + flameZHelper + randomHelper1;

            double smokeX = x + randomHelper3;
            double smokeY = y + randomHelper4 + 1.2;
            double smokeZ = z + randomHelper5;

            double smokeXVelocity = MathHelper.nextDouble(random, -0.025, 0.025);
            double smokeYVelocity = MathHelper.nextDouble(random, -0.025, 0.025);
            double smokeZVelocity = MathHelper.nextDouble(random, -0.025, 0.025);

            world.addParticle(ParticleTypes.FLAME, flameX, flameY, flameZ, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.SMOKE, smokeX, smokeY, smokeZ, smokeXVelocity, smokeYVelocity, smokeZVelocity);
        }

        super.randomDisplayTick(state, world, pos, random);
    }
}

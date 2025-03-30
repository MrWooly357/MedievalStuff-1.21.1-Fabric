package net.mrwooly357.medievalstuff.block.custom.tanks;

import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.CopperTankBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class CopperTankBlock extends BlockWithEntity {
    public static final BooleanProperty BOTTOM_CONNECTED = BooleanProperty.of("bottom_connected");
    public static final BooleanProperty TOP_CONNECTED = BooleanProperty.of("top_connected");
    public static final BooleanProperty BOTTOM_AND_TOP_CONNECTED = BooleanProperty.of("bottom_and_top_connected");
    public static final IntProperty LIGHT_LEVEL = IntProperty.of("light_level", 0, 15);
    public static final MapCodec<CopperTankBlock> CODEC = createCodec(CopperTankBlock::new);
    @Nullable
    private static volatile Map<Item, Fluid> fluidsMap;
    @Nullable
    private static volatile Map<Fluid, Integer> fluidsLightMap;
    @Nullable
    private static volatile Map<Item, SoundEvent> emptySoundsMap;
    @Nullable
    private static volatile Map<Item, SoundEvent> fillSoundsMap;

    public CopperTankBlock(Settings settings) {
        super(settings);
    }


    /*@Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!world.isClient()) {

            if (neighborPos.getX() == pos.getX() && neighborPos.getZ() == pos.getZ()) {

                if (neighborPos.getY() != pos.getY()) {

                    if (neighborPos.getY() + 1 == pos.getY()) {

                        if (neighborState.isOf(ModBlocks.COPPER_TANK)) {

                            if (!state.get(BOTTOM_CONNECTED)) {
                                world.setBlockState(pos, state.with(BOTTOM_CONNECTED, true), Block.NOTIFY_ALL);

                                if (!neighborState.get(TOP_CONNECTED)) {
                                    world.setBlockState(neighborPos, neighborState.with(TOP_CONNECTED, true), Block.NOTIFY_ALL);
                                }
                            }
                        } else {
                            world.setBlockState(pos, state.with(BOTTOM_CONNECTED, false), Block.NOTIFY_ALL);

                            if (state.get(BOTTOM_AND_TOP_CONNECTED)) {
                                world.setBlockState(pos, state.with(BOTTOM_AND_TOP_CONNECTED, false), Block.NOTIFY_ALL);
                            }
                        }
                    } else if (neighborPos.getY() - 1 == pos.getY()) {

                        if (neighborState.isOf(ModBlocks.COPPER_TANK)) {

                            if (!state.get(TOP_CONNECTED)) {
                                world.setBlockState(pos, state.with(TOP_CONNECTED, true), Block.NOTIFY_ALL);

                                if (!neighborState.get(BOTTOM_CONNECTED)) {
                                    world.setBlockState(neighborPos, neighborState.with(BOTTOM_CONNECTED, true), Block.NOTIFY_ALL);
                                }
                            } else {
                                world.setBlockState(pos, state.with(TOP_CONNECTED, false), Block.NOTIFY_ALL);

                                if (state.get(BOTTOM_AND_TOP_CONNECTED)) {
                                    world.setBlockState(pos, state.with(BOTTOM_AND_TOP_CONNECTED, false), Block.NOTIFY_ALL);
                                }
                            }
                        }
                    }
                }
            }
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }*/

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!ctx.getPlayer().isSneaking()) {
            World world = ctx.getWorld();
            BlockPos placementPos = ctx.getBlockPos();
            BlockPos checkPosBelow = new BlockPos(placementPos.getX(), placementPos.getY() - 1, placementPos.getZ());
            BlockPos checkPosAbove = new BlockPos(placementPos.getX(), placementPos.getY() + 1, placementPos.getZ());
            BlockState stateBelow = world.getBlockState(checkPosBelow);
            BlockState stateAbove = world.getBlockState(checkPosAbove);

            if (!stateBelow.isOf(ModBlocks.COPPER_TANK) && !stateAbove.isOf(ModBlocks.COPPER_TANK)) {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(TOP_CONNECTED, false).with(BOTTOM_AND_TOP_CONNECTED, false);

            } else if (stateBelow.isOf(ModBlocks.COPPER_TANK) && !stateAbove.isOf(ModBlocks.COPPER_TANK)) {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, true).with(TOP_CONNECTED, false).with(BOTTOM_AND_TOP_CONNECTED, false);

            } else if (!stateBelow.isOf(ModBlocks.COPPER_TANK) && stateAbove.isOf(ModBlocks.COPPER_TANK)) {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(TOP_CONNECTED, true).with(BOTTOM_AND_TOP_CONNECTED, false);

            } else if (stateBelow.isOf(ModBlocks.COPPER_TANK) && stateAbove.isOf(ModBlocks.COPPER_TANK)) {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, true).with(TOP_CONNECTED, true).with(BOTTOM_AND_TOP_CONNECTED, true);
            }
        }
        return super.getPlacementState(ctx);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof CopperTankBlockEntity copperTankBlockEntity) {
            SingleVariantStorage<FluidVariant> fluidStorage = copperTankBlockEntity.fluidStorage;
            Item itemInStack = stack.getItem();

            if (createFluidsMap().containsKey(itemInStack)) {
                long freeSpaceRequirement = copperTankBlockEntity.fluidStorage.getCapacity() - 1000;

                if (fluidStorage.getAmount() <= freeSpaceRequirement) {

                    if (copperTankBlockEntity.getFluid().isOf(createFluidsMap().get(itemInStack)) || fluidStorage.isResourceBlank()) {

                        try (Transaction transaction = Transaction.openOuter()) {
                            SoundEvent useSound = createEmptySoundsMap().get(itemInStack);
                            float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.75F, 1.25F);
                            float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.75F, 1.25F);

                            fluidStorage.insert(FluidVariant.of(createFluidsMap().get(stack.getItem())), 1000, transaction);
                            stack.decrementUnlessCreative(1, player);

                            if (player.getStackInHand(hand).isEmpty()) {
                                ItemStack newStack = new ItemStack(Items.BUCKET, 1);

                                player.setStackInHand(hand, newStack);
                            }

                            transaction.commit();
                            int lightLevel = calculateLightLevel(copperTankBlockEntity, fluidStorage.variant.getFluid());
                            world.setBlockState(
                                    pos, state.with(BOTTOM_CONNECTED, state.get(BOTTOM_CONNECTED))
                                            .with(TOP_CONNECTED, state.get(TOP_CONNECTED))
                                            .with(BOTTOM_AND_TOP_CONNECTED, state.get(BOTTOM_AND_TOP_CONNECTED))
                                            .with(LIGHT_LEVEL, lightLevel));
                            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), useSound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);
                        }
                    }
                }
            } else {
                if (stack.isOf(Items.BUCKET)) {

                    if (fluidStorage.getAmount() >= 1000) {
                        try(Transaction transaction = Transaction.openOuter()) {
                            ItemStack newStack = new ItemStack(fluidStorage.variant.getFluid().getBucketItem(), 1);
                            SoundEvent useSound = createFillSoundsMap2().get(newStack.getItem());
                            float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.75F, 1.25F);
                            float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.75F, 1.25F);

                            stack.decrement(1);

                            if (!player.getInventory().insertStack(newStack)) {
                                player.dropItem(newStack, false);
                            }

                            fluidStorage.extract(fluidStorage.variant, 1000, transaction);
                            transaction.commit();
                            int lightLevel = calculateLightLevel(copperTankBlockEntity, fluidStorage.variant.getFluid());
                            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), useSound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);
                            world.setBlockState(
                                    pos, state.with(BOTTOM_CONNECTED, state.get(BOTTOM_CONNECTED))
                                            .with(TOP_CONNECTED, state.get(TOP_CONNECTED))
                                            .with(BOTTOM_AND_TOP_CONNECTED, state.get(BOTTOM_AND_TOP_CONNECTED))
                                            .with(LIGHT_LEVEL, lightLevel));
                        }
                    }
                } else {
                    return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
                }
            }
        }
        return ItemActionResult.SUCCESS;
    }

    public static Map<Item, Fluid> createFluidsMap() {
        Map<Item, Fluid> map = fluidsMap;
        if (map != null) {
            return map;
        } else {
            Map<Item, Fluid> map2 = Maps.newLinkedHashMap();

            map2.put(Items.WATER_BUCKET, Fluids.WATER);
            map2.put(Items.LAVA_BUCKET, Fluids.LAVA);

            fluidsMap = map2;
            return map2;
        }
    }

    public static Map<Fluid, Integer> createFluidsLightMap() {
        Map<Fluid, Integer> map = fluidsLightMap;
        if (map != null) {
            return map;
        } else {
            Map<Fluid, Integer> map2 = Maps.newLinkedHashMap();

            map2.put(Fluids.WATER, 0);
            map2.put(Fluids.LAVA, 15);

            fluidsLightMap = map2;
            return map2;
        }
    }

    public static int calculateLightLevel(CopperTankBlockEntity copperTankBlockEntity, Fluid fluid) {
        if (createFluidsLightMap().containsKey(fluid) && createFluidsLightMap().get(fluid) > 0) {
            long fluidInTank = copperTankBlockEntity.fluidStorage.getAmount();
            long lightLevelHelper = copperTankBlockEntity.fluidStorage.getCapacity() / createFluidsLightMap().get(fluid);

            return (int) (fluidInTank / lightLevelHelper);
        } else {
            return 0;
        }
    }

    public static Map<Item, SoundEvent> createEmptySoundsMap() {
        Map<Item, SoundEvent> map = emptySoundsMap;
        if (map != null) {
            return map;
        } else {
            Map<Item, SoundEvent> map2 = Maps.newLinkedHashMap();

            map2.put(Items.WATER_BUCKET, SoundEvents.ITEM_BUCKET_EMPTY);
            map2.put(Items.LAVA_BUCKET, SoundEvents.ITEM_BUCKET_EMPTY_LAVA);

            emptySoundsMap = map2;
            return map2;
        }
    }

    public static Map<Item, SoundEvent> createFillSoundsMap2() {
        Map<Item, SoundEvent> map = fillSoundsMap;
        if (map != null) {
            return map;
        } else {
            Map<Item, SoundEvent> map2 = Maps.newLinkedHashMap();

            map2.put(Items.WATER_BUCKET, SoundEvents.ITEM_BUCKET_FILL);
            map2.put(Items.LAVA_BUCKET, SoundEvents.ITEM_BUCKET_FILL_LAVA);

            fillSoundsMap = map2;
            return map2;
        }
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

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(TOP_CONNECTED, BOTTOM_CONNECTED, BOTTOM_AND_TOP_CONNECTED, LIGHT_LEVEL);
    }
}

package net.mrwooly357.medievalstuff.block.custom.tanks;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.CopperTankBlockEntity;
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.TankBlockEntity;
import net.mrwooly357.medievalstuff.util.ItemStackUtils;
import net.mrwooly357.medievalstuff.util.ModTags;
import org.jetbrains.annotations.Nullable;

import static net.mrwooly357.medievalstuff.util.ModMaps.TankBlocks.*;

public abstract class TankBlock extends BlockWithEntity {

    public static final BooleanProperty BOTTOM_CONNECTED = BooleanProperty.of("bottom_connected");
    public static final BooleanProperty BOTTOM_BLOCKED = BooleanProperty.of("bottom_blocked");
    public static final BooleanProperty TOP_CONNECTED = BooleanProperty.of("top_connected");
    public static final  BooleanProperty TOP_BLOCKED = BooleanProperty.of("top_blocked");
    public static final IntProperty LIGHT_LEVEL = IntProperty.of("light_level", 0, 15);

    protected TankBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        Item itemInStack = stack.getItem();
        long fluidAmount;
        ItemStack exchangeStack;
        SoundEvent sound;
        boolean bl = false;

        if (createItemFluidMap().containsKey(itemInStack)) {
            Fluid fluid = createItemFluidMap().get(itemInStack);
            fluidAmount = createItemFluidAmountMap().get(itemInStack);
            exchangeStack = new ItemStack(createExchangeItemMap().get(itemInStack), 1);
            sound = createExtractSoundMap().get(itemInStack);
            bl = tryInsert(world, pos, fluid, fluidAmount, sound, stack, exchangeStack, player);

        } else if (createExchangeItemMap().containsValue(itemInStack)) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof TankBlockEntity tankBlockEntity) {
                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();

                if (!state.get(BOTTOM_CONNECTED) && !state.get(TOP_CONNECTED) && !fluidStorage.isResourceBlank()) {
                    Item item = createFluidItemMapsMap().get(itemInStack).get(fluidStorage.variant.getFluid());
                    fluidAmount = createItemFluidAmountMap().get(item);
                    exchangeStack = new ItemStack(item, 1);
                    sound = createInsertSoundMap().get(item);
                    bl = tryExtract(world, pos, fluidAmount, sound, stack, exchangeStack, player);

                } else {
                    for (int a = pos.getY(); a > world.getBottomY(); a--) {
                        BlockPos posToCheck = new BlockPos(pos.getX(), a, pos.getZ());
                        BlockState stateToCheck = world.getBlockState(posToCheck);

                        if (stateToCheck.getBlock() instanceof CopperTankBlock) {

                            if (stateToCheck.get(TOP_CONNECTED)) {
                                BlockEntity blockEntityToCheck = world.getBlockEntity(posToCheck);

                                if (blockEntityToCheck instanceof TankBlockEntity tankBlockEntity1) {

                                    SingleVariantStorage<FluidVariant> fluidStorage1 = tankBlockEntity1.getFluidStorage();

                                    if (!fluidStorage1.isResourceBlank()) {
                                        Item item = createFluidItemMapsMap().get(itemInStack).get(fluidStorage1.variant.getFluid());
                                        fluidAmount = createItemFluidAmountMap().get(item);
                                        exchangeStack = new ItemStack(item, 1);
                                        sound = createInsertSoundMap().get(item);
                                        bl = tryExtract(world, pos, fluidAmount, sound, stack, exchangeStack, player);

                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

        } else {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }

        return bl ? ItemActionResult.SUCCESS : ItemActionResult.CONSUME;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {

        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);

            if (blockEntity instanceof TankBlockEntity tankBlockEntity) {
                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();

                if (fluidStorage.getAmount() >= 81000) {
                    placeFluid(null, world, pos, null, fluidStorage);
                }
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos placementPos = ctx.getBlockPos();
        PlayerEntity player = ctx.getPlayer();
        HitResult hitResult = player.raycast(32, 0, false);
        BlockHitResult blockHitResult = (BlockHitResult) hitResult;
        Direction side = blockHitResult.getSide();
        BlockPos checkPosBelow = new BlockPos(placementPos.getX(), placementPos.getY() - 1, placementPos.getZ());
        BlockPos checkPosAbove = new BlockPos(placementPos.getX(), placementPos.getY() + 1, placementPos.getZ());
        BlockState stateBelow = world.getBlockState(checkPosBelow);
        BlockState stateAbove = world.getBlockState(checkPosAbove);

        if (player.isSneaking()) {

            if (!(stateBelow.getBlock() instanceof TankBlock) && !(stateAbove.getBlock() instanceof TankBlock)) {

                if (side == Direction.DOWN) {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);

                } else if (side == Direction.UP) {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);

                } else {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                }

            } else if (stateBelow.getBlock() instanceof TankBlock && !(stateAbove.getBlock() instanceof TankBlock)) {

                if (side == Direction.DOWN) {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);

                } else if (side == Direction.UP) {

                    if (!stateBelow.get(TOP_BLOCKED)) {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, true).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);

                    } else {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);
                    }

                } else {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                }

            } else if (!(stateBelow.getBlock() instanceof TankBlock) && stateAbove.getBlock() instanceof TankBlock) {

                if (side == Direction.DOWN) {

                    if (!stateAbove.get(BOTTOM_BLOCKED)) {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, true).with(TOP_BLOCKED, false);

                    } else {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                    }

                } else if (side == Direction.UP) {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);

                } else {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                }

            } else if (stateBelow.getBlock() instanceof TankBlock && stateAbove.getBlock() instanceof TankBlock) {

                if (side == Direction.DOWN) {

                    if (!stateAbove.get(BOTTOM_BLOCKED)) {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, true).with(TOP_BLOCKED, false);

                    } else {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                    }

                } else if (side == Direction.UP) {

                    if (!stateBelow.get(TOP_BLOCKED)) {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, true).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);

                    } else {
                        return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);
                    }

                } else {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                }
            }

        } else {

            if (stateBelow.getBlock() instanceof TankBlock && !stateBelow.get(TOP_BLOCKED) && !(stateAbove.getBlock() instanceof TankBlock)) {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, true).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);

            } else if (!(stateBelow.getBlock() instanceof TankBlock) && stateAbove.getBlock() instanceof TankBlock && !stateAbove.get(BOTTOM_BLOCKED)) {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, true).with(TOP_BLOCKED, false);

            } else if (stateBelow.getBlock() instanceof TankBlock && !stateBelow.get(TOP_BLOCKED) && stateAbove.getBlock() instanceof TankBlock && !stateAbove.get(BOTTOM_BLOCKED)) {

                if (shouldConnectWhenBetween(world, placementPos)) {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, true).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, true).with(TOP_BLOCKED, false);

                } else {
                    return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                }

            } else {
                return super.getPlacementState(ctx).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);
            }
        }

        return super.getPlacementState(ctx);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if (!world.isClient()) {

            if (neighborPos.getX() == pos.getX() && neighborPos.getZ() == pos.getZ()) {

                if (neighborPos.getY() + 1 == pos.getY()) {

                    if (neighborState.getBlock() instanceof TankBlock && !state.get(BOTTOM_BLOCKED) && !neighborState.get(TOP_BLOCKED)) {
                        world.setBlockState(neighborPos, neighborState.with(TOP_CONNECTED, true), Block.NOTIFY_ALL);

                        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(BOTTOM_CONNECTED, true).with(BOTTOM_BLOCKED, false);

                    } else if (!state.get(BOTTOM_BLOCKED)) {
                        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, false);

                    } else {
                        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(BOTTOM_CONNECTED, false).with(BOTTOM_BLOCKED, true);
                    }

                } else if (neighborPos.getY() - 1 == pos.getY()) {

                    if (neighborState.getBlock() instanceof TankBlock && !state.get(TOP_BLOCKED) && !neighborState.get(BOTTOM_BLOCKED)) {
                        world.setBlockState(neighborPos, neighborState.with(BOTTOM_CONNECTED, true), Block.NOTIFY_ALL);

                        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(TOP_CONNECTED, true).with(TOP_BLOCKED, false);

                    } else if (!state.get(TOP_BLOCKED)) {
                        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(TOP_CONNECTED, false).with(TOP_BLOCKED, false);

                    } else {
                        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos).with(TOP_CONNECTED, false).with(TOP_BLOCKED, true);
                    }
                }
            }
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public static int calculateLightLevel(TankBlockEntity entity, Fluid fluid) {
        SingleVariantStorage<FluidVariant> fluidStorage = entity.getFluidStorage();

        if (createFluidLightMap().containsKey(fluid) && createFluidLightMap().get(fluid) > 0) {
            long fluidInTank = fluidStorage.getAmount();
            long lightLevelHelper = fluidStorage.getCapacity() / createFluidLightMap().get(fluid);

            return (int) (fluidInTank / lightLevelHelper);

        } else {
            return 0;
        }
    }

    public boolean placeFluid(@Nullable PlayerEntity player, World world, BlockPos pos, @Nullable BlockHitResult hitResult, SingleVariantStorage<FluidVariant> fluidStorage) {
        BlockEntity entity = world.getBlockEntity(pos);

        if (entity instanceof TankBlockEntity) {
            Fluid fluid = fluidStorage.variant.getFluid();

            if (!(fluid instanceof FlowableFluid flowableFluid)) {
                return false;

            } else {
                Block block;
                boolean bl;
                BlockState blockState;
                boolean var10000;

                label82: {
                    blockState = world.getBlockState(pos);
                    block = blockState.getBlock();
                    bl = blockState.canBucketPlace(fluid);
                    label70:

                    if (!blockState.isAir() && !bl) {

                        if (block instanceof FluidFillable fluidFillable && fluidFillable.canFillWithFluid(player, world, pos, blockState, fluid)) {
                            break label70;
                        }

                        var10000 = false;

                        break label82;
                    }

                    var10000 = true;
                }

                boolean bl2 = var10000;

                if (!bl2) {
                    return hitResult != null && this.placeFluid(player, world, hitResult.getBlockPos().offset(hitResult.getSide()), null, fluidStorage);

                } else if (world.getDimension().ultrawarm() && fluid.matchesType(Fluids.WATER)) {

                    int i = pos.getX();
                    int j = pos.getY();
                    int k = pos.getZ();

                    world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; l++) {
                        world.addParticle(ParticleTypes.LARGE_SMOKE, (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0, 0.0, 0.0);
                    }

                    return true;

                } else {

                    if (block instanceof FluidFillable fluidFillable && fluid == Fluids.WATER) {
                        fluidFillable.tryFillWithFluid(world, pos, blockState, flowableFluid.getStill(false));

                        return true;
                    }

                    if (!world.isClient && bl && !blockState.isIn(ModTags.Blocks.FLUIDS)) {
                        world.breakBlock(pos, true);
                    }

                    return world.setBlockState(pos, fluid.getDefaultState().getBlockState(), Block.NOTIFY_ALL_AND_REDRAW) || blockState.getFluidState().isStill();
                }
            }

        } else {
            return false;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BOTTOM_CONNECTED, BOTTOM_BLOCKED, TOP_CONNECTED, TOP_BLOCKED, LIGHT_LEVEL);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world.isClient ? null : validateTicker(type, ModBlockEntities.COPPER_TANK_BE, TankBlockEntity::tick);
    }

    public static boolean tryInsert(World world, BlockPos pos, Fluid fluid, long amount, SoundEvent sound) {
        return tryInsert(world, pos, fluid, amount, sound, null, null, null);
    }

    public static boolean tryInsert(World world, BlockPos pos, Fluid fluid, long amount, SoundEvent sound, @Nullable ItemStack useStack, @Nullable ItemStack exchangeStack, @Nullable PlayerEntity player) {
        BlockState state = world.getBlockState(pos);
        PlayerInventory inventory = player.getInventory();
        boolean bl = false;

        if (state.get(BOTTOM_CONNECTED) || state.get(TOP_CONNECTED)) {
            boolean shouldContinue = true;

            for (int a = pos.getY(); a > world.getBottomY(); a--) {
                BlockPos posToCheck = new BlockPos(pos.getX(), a, pos.getZ());
                BlockState stateToCheck = world.getBlockState(posToCheck);

                if (stateToCheck.getBlock() instanceof TankBlock) {

                    if (stateToCheck.get(TOP_CONNECTED) || stateToCheck.get(BOTTOM_CONNECTED)) {

                        if (!stateToCheck.get(BOTTOM_CONNECTED)) {
                            long overallAvailableSpace = 0;
                            Fluid overallFluid = Fluids.EMPTY;
                            BlockEntity entity = world.getBlockEntity(posToCheck);

                            if (entity instanceof TankBlockEntity) {

                                for (int b = posToCheck.getY(); b < world.getTopY(); b++) {
                                    BlockPos posToCheck1 = new BlockPos(posToCheck.getX(), b, posToCheck.getZ());
                                    BlockState stateToCheck1 = world.getBlockState(posToCheck1);

                                    if (stateToCheck1.getBlock() instanceof TankBlock) {

                                        if (stateToCheck1.get(BOTTOM_CONNECTED) || stateToCheck1.get(TOP_CONNECTED)) {
                                            BlockEntity blockEntity1 = world.getBlockEntity(posToCheck1);

                                            if (blockEntity1 instanceof TankBlockEntity tankBlockEntity1) {
                                                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity1.getFluidStorage();
                                                overallAvailableSpace += fluidStorage.getCapacity() - fluidStorage.getAmount();

                                                if (fluidStorage.variant != FluidVariant.of(Fluids.EMPTY)) {
                                                    overallFluid = fluidStorage.variant.getFluid();

                                                }

                                                if (!stateToCheck1.get(TOP_CONNECTED)) {

                                                    if (amount <= overallAvailableSpace && fluid == overallFluid || amount <= overallAvailableSpace && overallFluid == Fluids.EMPTY) {

                                                        for (int c = posToCheck.getY(); c < posToCheck1.getY() + 1; c++) {
                                                            BlockPos posToCheck2 = new BlockPos(posToCheck.getX(), c, posToCheck.getZ());
                                                            BlockState state1 = world.getBlockState(posToCheck2);
                                                            BlockEntity blockEntity2 = world.getBlockEntity(posToCheck2);

                                                            if (blockEntity2 instanceof TankBlockEntity tankBlockEntity2) {
                                                                SingleVariantStorage<FluidVariant> fluidStorage1 = tankBlockEntity2.getFluidStorage();
                                                                long possibleTransferAmount = fluidStorage1.getCapacity() - fluidStorage1.getAmount();
                                                                long transferAmount = Math.min(amount, possibleTransferAmount);

                                                                try (Transaction transaction = Transaction.openOuter()) {
                                                                    fluidStorage1.insert(FluidVariant.of(fluid), transferAmount, transaction);
                                                                    transaction.commit();

                                                                    amount -= transferAmount;
                                                                    int lightLevel = calculateLightLevel(tankBlockEntity2, fluidStorage1.variant.getFluid());

                                                                    world.setBlockState(posToCheck2, state1.with(LIGHT_LEVEL, lightLevel));
                                                                }
                                                            }

                                                            if (amount == 0) {
                                                                float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);
                                                                float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);

                                                                if (useStack != null && exchangeStack != null) {

                                                                    if (!ItemStackUtils.insertFluidStorageStack(useStack, exchangeStack, inventory, inventory.selectedSlot)) {
                                                                        player.dropItem(exchangeStack, false);
                                                                    }
                                                                }

                                                                shouldContinue = false;
                                                                bl = true;

                                                                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);

                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        shouldContinue = false;

                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        shouldContinue = false;

                                        break;
                                    }

                                    if (!shouldContinue) {
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }

                if (!shouldContinue) {
                    break;
                }
            }
        } else {
            CopperTankBlockEntity entity = (CopperTankBlockEntity) world.getBlockEntity(pos);
            SingleVariantStorage<FluidVariant> fluidStorage = entity.getFluidStorage();
            long freeSpaceRequirement = fluidStorage.getCapacity() - fluidStorage.getAmount();

            if (amount <= freeSpaceRequirement) {

                if (fluid == fluidStorage.variant.getFluid() || fluidStorage.isResourceBlank()) {

                    try (Transaction transaction = Transaction.openOuter()) {
                        float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);
                        float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);


                        fluidStorage.insert(FluidVariant.of(fluid), amount, transaction);
                        transaction.commit();

                        int lightLevel = calculateLightLevel(entity, fluidStorage.variant.getFluid());

                        world.setBlockState(pos, state.with(LIGHT_LEVEL, lightLevel));
                        world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);

                        if (useStack != null && exchangeStack != null) {

                            if (!ItemStackUtils.insertFluidStorageStack(useStack, exchangeStack, inventory, inventory.selectedSlot)) {
                                player.dropItem(exchangeStack, false);
                            }
                        }

                        bl = true;
                    }
                }
            }
        }

        return bl;
    }

    public static boolean tryExtract(World world, BlockPos pos, long amount, SoundEvent sound) {
        return tryExtract(world, pos, amount, sound, null, null, null);
    }

    public static boolean tryExtract(World world, BlockPos pos, long amount, SoundEvent sound, ItemStack useStack, ItemStack exchangeStack, PlayerEntity player) {
        BlockState state = world.getBlockState(pos);
        PlayerInventory inventory = player.getInventory();
        boolean bl = false;

        if (state.get(BOTTOM_CONNECTED) || state.get(TOP_CONNECTED)) {
            boolean shouldContinue = true;

            for (int a = pos.getY(); a < world.getTopY(); a++) {
                BlockPos posToCheck = new BlockPos(pos.getX(), a, pos.getZ());
                BlockState stateToCheck = world.getBlockState(posToCheck);

                if (stateToCheck.getBlock() instanceof TankBlock) {

                    if (stateToCheck.get(BOTTOM_CONNECTED) || stateToCheck.get(TOP_CONNECTED)) {

                        if (!stateToCheck.get(TOP_CONNECTED)) {
                            long overallAvailableFluid = 0;

                            BlockEntity entity = world.getBlockEntity(posToCheck);

                            if (entity instanceof TankBlockEntity) {

                                for (int b = posToCheck.getY(); b > world.getBottomY(); b--) {
                                    BlockPos posToCheck1 = new BlockPos(posToCheck.getX(), b, posToCheck.getZ());
                                    BlockState stateToCheck1 = world.getBlockState(posToCheck1);

                                    if (stateToCheck1.getBlock() instanceof TankBlock) {

                                        if (stateToCheck1.get(TOP_CONNECTED) || stateToCheck1.get(BOTTOM_CONNECTED)) {
                                            BlockEntity blockEntity1 = world.getBlockEntity(posToCheck1);

                                            if (blockEntity1 instanceof TankBlockEntity tankBlockEntity) {
                                                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                                                overallAvailableFluid += fluidStorage.getAmount();
                                                FluidVariant variant = fluidStorage.variant;

                                                if (!stateToCheck1.get(BOTTOM_CONNECTED)) {

                                                    if (amount <= overallAvailableFluid) {

                                                        for (int c = posToCheck.getY(); c > posToCheck1.getY() - 1; c--) {
                                                            BlockPos posToCheck2 = new BlockPos(posToCheck.getX(), c, posToCheck.getZ());
                                                            BlockState state1 = world.getBlockState(posToCheck2);
                                                            BlockEntity blockEntity2 = world.getBlockEntity(posToCheck2);

                                                            if (blockEntity2 instanceof TankBlockEntity tankBlockEntity1) {
                                                                SingleVariantStorage<FluidVariant> fluidStorage1 = tankBlockEntity1.getFluidStorage();
                                                                long possibleTransferAmount = fluidStorage1.getAmount();
                                                                long transferAmount = Math.min(possibleTransferAmount, amount);

                                                                try (Transaction transaction = Transaction.openOuter()) {
                                                                    fluidStorage1.extract(variant, transferAmount, transaction);
                                                                    transaction.commit();

                                                                    amount -= transferAmount;
                                                                    int lightLevel = calculateLightLevel(tankBlockEntity1, fluidStorage1.variant.getFluid());

                                                                    world.setBlockState(posToCheck2, state1.with(LIGHT_LEVEL, lightLevel));

                                                                }
                                                            }

                                                            if (amount == 0) {
                                                                float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);
                                                                float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);

                                                                if (useStack != null && exchangeStack != null) {

                                                                    if (!ItemStackUtils.insertFluidStorageStack(useStack, exchangeStack, inventory, inventory.selectedSlot)) {
                                                                        player.dropItem(exchangeStack, false);
                                                                    }
                                                                }

                                                                shouldContinue = false;
                                                                bl = true;

                                                                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);

                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        shouldContinue = false;

                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        shouldContinue = false;

                                        break;
                                    }

                                    if (!shouldContinue) {
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }

                if (!shouldContinue) {
                    break;
                }
            }
        } else {
            CopperTankBlockEntity entity = (CopperTankBlockEntity) world.getBlockEntity(pos);
            SingleVariantStorage<FluidVariant> fluidStorage = entity.getFluidStorage();
            FluidVariant variant = fluidStorage.variant;

            if (amount <= fluidStorage.getAmount()) {

                try (Transaction transaction = Transaction.openOuter()) {
                    float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);
                    float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.8F, 1.2F);

                    fluidStorage.extract(variant, amount, transaction);
                    transaction.commit();

                    int lightLevel = calculateLightLevel(entity, variant.getFluid());

                    world.setBlockState(pos, state.with(LIGHT_LEVEL, lightLevel));
                    world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);

                    if (useStack != null && exchangeStack != null) {

                        if (!ItemStackUtils.insertFluidStorageStack(useStack, exchangeStack, inventory, inventory.selectedSlot)) {
                            player.dropItem(exchangeStack, false);
                        }
                    }

                    bl = true;
                }
            }
        }

        return bl;
    }

    public static boolean shouldConnectWhenBetween(World world, BlockPos pos) {
        BlockPos posBelow = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        BlockPos posAbove = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        BlockState stateBelow = world.getBlockState(posBelow);
        BlockState stateAbove = world.getBlockState(posAbove);
        FluidVariant empty = FluidVariant.of(Fluids.EMPTY);
        FluidVariant variantBelow = empty;
        FluidVariant variantAbove = empty;

        if (stateBelow.getBlock() instanceof TankBlock) {

            if (stateBelow.get(BOTTOM_CONNECTED)) {
                boolean shouldContinue = true;
                BlockEntity entity = world.getBlockEntity(posBelow);

                if (entity instanceof TankBlockEntity) {

                    for (int b = posBelow.getY(); b > world.getBottomY(); b--) {
                        BlockPos posToCheck1 = new BlockPos(posBelow.getX(), b, posBelow.getZ());
                        BlockState stateToCheck1 = world.getBlockState(posToCheck1);

                        if (stateToCheck1.getBlock() instanceof TankBlock) {

                            if (stateToCheck1.get(TOP_CONNECTED) || stateToCheck1.get(BOTTOM_CONNECTED)) {
                                BlockEntity blockEntity1 = world.getBlockEntity(posToCheck1);

                                if (blockEntity1 instanceof TankBlockEntity tankBlockEntity1) {
                                    SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity1.getFluidStorage();
                                    FluidVariant variant = fluidStorage.variant;

                                    if (variant != empty) {
                                        variantBelow = variant;
                                    }

                                    if (!stateToCheck1.get(BOTTOM_CONNECTED)) {
                                        shouldContinue = false;
                                    }
                                }
                            }
                        } else {
                            shouldContinue = false;
                        }

                        if (!shouldContinue) {
                            break;
                        }
                    }
                }
            }
        }

        if (stateAbove.getBlock() instanceof TankBlock) {

            if (stateAbove.get(TOP_CONNECTED)) {
                boolean shouldContinue = true;
                BlockEntity entity = world.getBlockEntity(posAbove);

                if (entity instanceof TankBlockEntity) {

                    for (int b = posAbove.getY(); b < world.getTopY(); b++) {
                        BlockPos posToCheck1 = new BlockPos(posAbove.getX(), b, posAbove.getZ());
                        BlockState stateToCheck1 = world.getBlockState(posToCheck1);

                        if (stateToCheck1.getBlock() instanceof TankBlock) {

                            if (stateToCheck1.get(BOTTOM_CONNECTED) || stateToCheck1.get(TOP_CONNECTED)) {
                                BlockEntity blockEntity1 = world.getBlockEntity(posToCheck1);

                                if (blockEntity1 instanceof TankBlockEntity tankBlockEntity1) {
                                    SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity1.getFluidStorage();
                                    FluidVariant variant = fluidStorage.variant;

                                    if (variant != empty) {
                                        variantAbove = variant;
                                    }

                                    if (!stateToCheck1.get(TOP_CONNECTED)) {
                                        shouldContinue = false;
                                    }
                                }
                            }
                        } else {
                            shouldContinue = false;
                        }

                        if (!shouldContinue) {
                            break;
                        }
                    }
                }
            }
        }

        return variantBelow == variantAbove || variantBelow == empty || variantAbove == empty;
    }
}

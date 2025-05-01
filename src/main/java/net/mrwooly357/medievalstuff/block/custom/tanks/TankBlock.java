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
import net.minecraft.util.ActionResult;
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
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.TankBlockEntity;
import net.mrwooly357.medievalstuff.util.ItemStackUtils;
import net.mrwooly357.medievalstuff.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.mrwooly357.medievalstuff.util.ModMaps.TankBlocks.*;

public abstract class TankBlock extends BlockWithEntity {

    public static final BooleanProperty BOTTOM_CONNECTED = BooleanProperty.of("bottom_connected");
    public static final BooleanProperty BOTTOM_BLOCKED = BooleanProperty.of("bottom_blocked");
    public static final BooleanProperty TOP_CONNECTED = BooleanProperty.of("top_connected");
    public static final BooleanProperty TOP_BLOCKED = BooleanProperty.of("top_blocked");
    public static final IntProperty LIGHT_LEVEL = IntProperty.of("light_level", 0, 15);

    protected TankBlock(Settings settings) {
        super(settings);
    }


    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        Item itemInStack = stack.getItem();
        long fluidAmount;
        ItemStack exchangeStack = ItemStack.EMPTY;
        SoundEvent sound;
        boolean bl = false;

        if (createItemFluidMap().containsKey(itemInStack)) {
            Fluid fluid = createItemFluidMap().get(itemInStack);
            fluidAmount = createItemFluidAmountMap().get(itemInStack);
            sound = createExtractSoundMap().get(itemInStack);

            if (!player.isInCreativeMode()) {
                exchangeStack = new ItemStack(createExchangeItemMap().get(itemInStack), 1);
            }

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

                label82:
                {
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
        return validateTicker(type, ModBlockEntities.COPPER_TANK_BE, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    public boolean tryInsert(World world, BlockPos pos, Fluid fluid, long amount, SoundEvent sound) {
        return tryInsert(world, pos, fluid, amount, sound, null, null, null);
    }

    public boolean tryInsert(World world, BlockPos pos, Fluid fluid, long amount, SoundEvent sound, @Nullable ItemStack useStack, @Nullable ItemStack exchangeStack, @Nullable PlayerEntity player) {
        boolean bl = false;
        FluidVariant insertVariant = FluidVariant.of(fluid);

        if (canInsert(amount, world, pos)) {
            FluidVariant variantInMultiblockConstruction = getFluidVariant(world, pos);

            if (variantInMultiblockConstruction == insertVariant || variantInMultiblockConstruction == FluidVariant.of(Fluids.EMPTY)) {
                List<BlockPos> multiblockConstruction = createMultiblockConstruction(world, pos);

                for (BlockPos blockPos : multiblockConstruction) {
                    BlockEntity entity = world.getBlockEntity(blockPos);

                    if (entity instanceof TankBlockEntity tankBlockEntity) {
                        SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                        long possibleTransferAmount = fluidStorage.getCapacity() - fluidStorage.getAmount();

                        if (possibleTransferAmount > 0) {
                            long transferAmount = Math.min(amount, possibleTransferAmount);

                            try (Transaction transaction = Transaction.openOuter()) {
                                BlockState state = world.getBlockState(blockPos);
                                fluidStorage.insert(FluidVariant.of(fluid), transferAmount, transaction);
                                transaction.commit();

                                amount -= transferAmount;
                                int lightLevel = calculateLightLevel(tankBlockEntity, fluid);
                                System.out.println(lightLevel);
                                System.out.println(transferAmount);

                                world.setBlockState(blockPos, state.with(LIGHT_LEVEL, lightLevel));
                            }

                            if (amount == 0) {
                                PlayerInventory inventory = player.getInventory();
                                float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);
                                float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);

                                if (useStack != null && exchangeStack != null) {

                                    if (!ItemStackUtils.insertFluidStorageStack(useStack, exchangeStack, inventory, inventory.selectedSlot)) {
                                        player.dropItem(exchangeStack, false);
                                    }
                                }

                                bl = true;

                                world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);

                                break;
                            }
                        }
                    }
                }
            }
        }

        return bl;
    }

    public boolean tryExtract(World world, BlockPos pos, long amount, SoundEvent sound) {
        return tryExtract(world, pos, amount, sound, null, null, null);
    }

    public boolean tryExtract(World world, BlockPos pos, long amount, SoundEvent sound, @Nullable ItemStack useStack, @Nullable ItemStack exchangeStack, @Nullable PlayerEntity player) {
        boolean bl = false;

        if (canExtract(amount, world, pos)) {
            FluidVariant variantInMultiblockConstruction = getFluidVariant(world, pos);

            List<BlockPos> multiblockConstruction = createMultiblockConstruction(world, pos);

            for (int a = multiblockConstruction.size() - 1; a > -1; a--) {
                BlockPos blockPos = multiblockConstruction.get(a);
                BlockEntity entity = world.getBlockEntity(blockPos);

                if (entity instanceof TankBlockEntity tankBlockEntity) {
                    SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                    long possibleTransferAmount = fluidStorage.getAmount();

                    if (possibleTransferAmount > 0) {
                        long transferAmount = Math.min(amount, possibleTransferAmount);

                        try (Transaction transaction = Transaction.openOuter()) {
                            BlockState state = world.getBlockState(blockPos);
                            fluidStorage.extract(variantInMultiblockConstruction, transferAmount, transaction);
                            transaction.commit();

                            amount -= transferAmount;
                            int lightLevel = calculateLightLevel(tankBlockEntity, variantInMultiblockConstruction.getFluid());
                            System.out.println(lightLevel);

                            world.setBlockState(blockPos, state.with(LIGHT_LEVEL, lightLevel));
                        }

                        if (amount == 0) {
                            PlayerInventory inventory = player.getInventory();
                            float useSoundVolume = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);
                            float useSoundPitch = MathHelper.nextFloat(Random.create(), 0.9F, 1.1F);

                            if (useStack != null && exchangeStack != null) {

                                if (!ItemStackUtils.insertFluidStorageStack(useStack, exchangeStack, inventory, inventory.selectedSlot)) {
                                    player.dropItem(exchangeStack, false);
                                }
                            }

                            bl = true;

                            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), sound, SoundCategory.PLAYERS, useSoundVolume, useSoundPitch);

                            break;
                        }
                    }
                }
            }
        }

        return bl;
    }

    public boolean shouldConnectWhenBetween(World world,BlockPos pos) {
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
            } else {
                BlockEntity entity = world.getBlockEntity(posBelow);

                if (entity instanceof TankBlockEntity tankBlockEntity) {
                    SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                    variantBelow = fluidStorage.variant;
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
            } else {
                BlockEntity entity = world.getBlockEntity(posAbove);

                if (entity instanceof TankBlockEntity tankBlockEntity) {
                    SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                    variantAbove = fluidStorage.variant;
                }
            }
        }

        return variantBelow == variantAbove || variantBelow == empty || variantAbove == empty;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        System.out.println(canInsert(81000, world, pos));
        System.out.println(canExtract(81000, world, pos));

        return super.onUse(state, world, pos, player, hit);
    }

    public FluidVariant getFluidVariant(World world, BlockPos pos) {
        List<BlockPos> multiblockConstruction = createMultiblockConstruction(world, pos);
        FluidVariant variant = FluidVariant.of(Fluids.EMPTY);

        for (BlockPos blockPos : multiblockConstruction) {
            BlockEntity entity = world.getBlockEntity(blockPos);

            if (entity instanceof TankBlockEntity tankBlockEntity) {
                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                FluidVariant variantInStorage = fluidStorage.variant;

                if (variantInStorage != FluidVariant.of(Fluids.EMPTY)) {
                    variant = variantInStorage;

                    break;
                }
            }
        }

        return variant;
    }

    public boolean canInsert(long amount, World world, BlockPos pos) {
        List<BlockPos> multiblockConstruction = createMultiblockConstruction(world, pos);
        boolean bl = false;

        for (BlockPos blockPos : multiblockConstruction) {
            BlockEntity entity = world.getBlockEntity(blockPos);
            long overallFreeSpace = 0;

            if (entity instanceof TankBlockEntity tankBlockEntity) {
                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                overallFreeSpace += fluidStorage.getCapacity() - fluidStorage.getAmount();

                if (overallFreeSpace >= amount) {
                    bl = true;

                    break;
                }
            }
        }

        return bl;
    }

    public boolean canExtract(long amount, World world, BlockPos pos) {
        List<BlockPos> multiblockConstruction = createMultiblockConstruction(world, pos);
        boolean bl = false;

        for (int a = multiblockConstruction.size() - 1; a > -1; a--) {
            BlockEntity entity = world.getBlockEntity(multiblockConstruction.get(a));
            long overallAvailableFluid = 0;

            if (entity instanceof TankBlockEntity tankBlockEntity) {
                SingleVariantStorage<FluidVariant> fluidStorage = tankBlockEntity.getFluidStorage();
                overallAvailableFluid += fluidStorage.getAmount();

                if (overallAvailableFluid >= amount) {
                    bl = true;

                    break;
                }
            }
        }

        return bl;
    }

    protected List<BlockPos> createMultiblockConstruction(World world, BlockPos startPos) {
        List<BlockPos> multiblockConstruction = new ArrayList<>();
        BlockState state = world.getBlockState(startPos);

        if (state.get(BOTTOM_CONNECTED) || state.get(TOP_CONNECTED)) {
            boolean shouldContinue = true;

            for (int a = startPos.getY(); a > world.getBottomY(); a--) {
                BlockPos posToCheck = new BlockPos(startPos.getX(), a, startPos.getZ());
                BlockState stateToCheck = world.getBlockState(posToCheck);

                if (stateToCheck.getBlock() instanceof TankBlock) {

                    if (stateToCheck.get(TOP_CONNECTED) || stateToCheck.get(BOTTOM_CONNECTED)) {

                        if (!stateToCheck.get(BOTTOM_CONNECTED)) {

                            for (int b = posToCheck.getY(); b < world.getTopY(); b++) {
                                BlockPos posToCheck1 = new BlockPos(posToCheck.getX(), b, posToCheck.getZ());
                                BlockState stateToCheck1 = world.getBlockState(posToCheck1);

                                if (stateToCheck1.getBlock() instanceof TankBlock) {

                                    if (stateToCheck1.get(BOTTOM_CONNECTED) || stateToCheck1.get(TOP_CONNECTED)) {
                                        multiblockConstruction.add(posToCheck1);

                                        if (!stateToCheck1.get(TOP_CONNECTED)) {
                                            shouldContinue = false;
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
            multiblockConstruction.add(startPos);
        }

        return multiblockConstruction;
    }
}

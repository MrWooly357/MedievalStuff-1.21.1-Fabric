package net.mrwooly357.medievalstuff.item.custom;

import com.google.common.collect.BiMap;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreechopperItem extends AxeItem {
    private static int additionalBlocksForAbility;

    public TreechopperItem(ToolMaterial material, int additionalBlocksForAbility, Settings settings) {
        super(material, settings);
        TreechopperItem.additionalBlocksForAbility = additionalBlocksForAbility;
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();

        if (shouldCancelStripAttempt(context)) {
            return ActionResult.PASS;
        } else {
            Optional<BlockState> optional = this.tryStrip(world, blockPos, playerEntity, world.getBlockState(blockPos));
            if (optional.isEmpty()) {
                return ActionResult.PASS;
            } else {
                ItemStack itemStack = context.getStack();
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
                }

                assert playerEntity != null;
                for (int a = 0; a != additionalBlocksForAbility + 1; a++) {
                    BlockPos blockToCheckAbove = new BlockPos(blockPos.getX(), blockPos.getY() + a, blockPos.getZ());
                    Optional<BlockState> blockToCheckUpOptional = this.tryStrip(world, blockToCheckAbove, playerEntity, world.getBlockState(blockToCheckAbove));

                    if (blockToCheckUpOptional.isPresent()) {
                        world.setBlockState(blockToCheckAbove, blockToCheckUpOptional.get(), Block.NOTIFY_ALL_AND_REDRAW);
                        world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockToCheckAbove, GameEvent.Emitter.of(playerEntity, blockToCheckUpOptional.get()));
                        itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));

                    } else {
                        for (int b = 0; b != additionalBlocksForAbility + 2 - a; b++) {
                            BlockPos blockToCheckBelow = new BlockPos(blockPos.getX(), blockPos.getY() - b, blockPos.getZ());
                            Optional<BlockState> blockToCheckDownOptional = this.tryStrip(world, blockToCheckBelow, playerEntity, world.getBlockState(blockToCheckBelow));

                            if (blockToCheckDownOptional.isPresent()) {
                                world.setBlockState(blockToCheckBelow, blockToCheckDownOptional.get(), Block.NOTIFY_ALL_AND_REDRAW);
                                world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockToCheckBelow, GameEvent.Emitter.of(playerEntity, blockToCheckDownOptional.get()));
                                itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
                            }
                        }
                        break;
                    }
                }


                return ActionResult.success(world.isClient);
            }
        }
    }


    public static List<BlockPos> getBlocksToDestroy(int range, BlockPos blockPos, PlayerEntity player) {
        List<BlockPos> positionsForChecking = new ArrayList<>();

        HitResult hitResult = player.raycast(16, 0, false);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            for (int a = 0; a != range + 1; a++) {
                BlockPos blockToCheckAbove = new BlockPos(blockPos.getX(), blockPos.getY() + a, blockPos.getZ());

                ItemStack mainHandItem = player.getMainHandStack();
                World world = player.getWorld();

                if (isCorrectForDrop(mainHandItem, world.getBlockState(blockToCheckAbove))) {
                    positionsForChecking.add(blockToCheckAbove);
                } else {
                    for (int b = 0; b != range - a + 2; b++) {
                        BlockPos blockToCheckBelow = new BlockPos(blockPos.getX(), blockPos.getY() - b, blockPos.getZ());

                        if (isCorrectForDrop(mainHandItem, world.getBlockState(blockToCheckBelow))) {
                            positionsForChecking.add(blockToCheckBelow);
                        } else {
                            break;
                        }
                    }
                    break;
                }
            }
        }

        return positionsForChecking;
    }

    private static boolean shouldCancelStripAttempt(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        if (!context.getHand().equals(Hand.MAIN_HAND)) return false;
        assert playerEntity != null;
        return playerEntity.getOffHandStack().isOf(Items.SHIELD) && !playerEntity.shouldCancelInteraction();
    }

    private Optional<BlockState> tryStrip(World world, BlockPos pos, @Nullable PlayerEntity player, BlockState state) {
        Optional<BlockState> optional = this.getStrippedState(state);
        if (optional.isPresent()) {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return optional;
        } else {
            Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(state);
            if (optional2.isPresent()) {
                world.playSound(player, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.syncWorldEvent(player, WorldEvents.BLOCK_SCRAPED, pos, 0);
                return optional2;
            } else {
                Optional<BlockState> optional3 = Optional.ofNullable((Block)((BiMap)HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get()).get(state.getBlock()))
                        .map(block -> block.getStateWithProperties(state));
                if (optional3.isPresent()) {
                    world.playSound(player, pos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.syncWorldEvent(player, WorldEvents.WAX_REMOVED, pos, 0);
                    return optional3;
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    private Optional<BlockState> getStrippedState(BlockState state) {
        return Optional.ofNullable((Block)STRIPPED_BLOCKS.get(state.getBlock()))
                .map(block -> block.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS)));
    }

    public static boolean isCorrectForDrop(ItemStack stack, BlockState state) {
        ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
        return toolComponent != null && toolComponent.isCorrectForDrops(state);
    }

    public static int getAdditionalBlocksForAbility() {
        return TreechopperItem.additionalBlocksForAbility;
    }
}

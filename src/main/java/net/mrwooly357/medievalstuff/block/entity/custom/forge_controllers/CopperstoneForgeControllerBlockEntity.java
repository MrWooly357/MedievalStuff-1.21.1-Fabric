package net.mrwooly357.medievalstuff.block.entity.custom.forge_controllers;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.tanks.TankBlock;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class CopperstoneForgeControllerBlockEntity extends ForgeControllerBlockEntity {

    private DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6);
    private PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> getProgress();
                case 1 -> getAdditiveAmount();
                case 2 -> getCompoundAmount();
                case 3 -> getMaxProgress();
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0: setProgress(value);
                case 1: setAdditiveAmount(value);
                case 2: setCompoundAmount(value);
                case 3: setMaxProgress(value);
            }
        }

        @Override
        public int size() {
            return 4;
        }
    };

    private static final int INGREDIENT_FLUID_INPUT_SLOT = 0;
    private static final int INGREDIENT_FLUID_OUTPUT_SLOT = 1;
    private static final int ADDITIVE_SLOT = 2;
    private static final int COMPOUND_SLOT = 3;
    private static final int RESULT_FLUID_INPUT_SLOT = 4;
    private static final int RESULT_FLUID_OUTPUT_SLOT = 5;

    private BlockPos ingredientFluidTankPos;
    private BlockPos resultFluidTankPos;

    public CopperstoneForgeControllerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COPPERSTONE_FORGE_CONTROLLER_BE, pos, state);

        setInventory(inventory);
        setPropertyDelegate(propertyDelegate);
        setDefaultMaxProgress(200);
    }


    public void tick(World world, BlockPos pos, BlockState state) {
        /*if (hasRecipe() && canInsertResultFluid) {

            if (progress < maxProgress) {
                increaseCraftingProgress();
                markDirty(world, pos, state);
            }

            if (hasCraftingFinished()) {
                craftFluid();
                resetProgress();
            }
        } else {
            resetProgress();
        }*/

        /*BlockState stateToCheck = world.getBlockState(ingredientFluidTankPos);
        BlockState stateToCheck1 = world.getBlockState(ingredientFluidTankPos);

        if (stateToCheck.getBlock() instanceof TankBlock ingredientFluidTank) {

            if (stateToCheck1.getBlock() instanceof TankBlock resultFluidTank) {

                if (resultFluidTank.canInsert(1, world, resultFluidTankPos)) {

                    if (progress < maxProgress) {
                        increaseCraftingProgress();
                        markDirty(world, pos, state);
                    }

                    if (hasCraftingFinished()) {
                        //craftFluid();
                        resetProgress();
                    }
                } else {
                    resetProgress();
                }
            }
        }*/
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("gui.medievalstuff.copperstone_forge_controller");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    private BlockPos getIngredientFluidTankPos() {
        return ingredientFluidTankPos;
    }

    private BlockPos getResultFluidTankPos() {
        return resultFluidTankPos;
    }

    private void setIngredientFluidTankPos(BlockPos pos) {
        ingredientFluidTankPos = pos;
    }

    private void setResultFluidTankPos(BlockPos pos) {
        resultFluidTankPos = pos;
    }
}

package net.mrwooly357.medievalstuff.block.entity.custom.forge_controllers;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.block.entity.custom.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

public abstract class ForgeControllerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    private DefaultedList<ItemStack> inventory;
    private PropertyDelegate propertyDelegate;

    protected int progress;
    protected int additiveAmount;
    protected int compoundAmount;
    protected int maxProgress;

    private int DEFAULT_MAX_PROGRESS;

    public ForgeControllerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    public DefaultedList<ItemStack> getInventory() {
        return inventory;
    }

    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    public int getProgress() {
        return progress;
    }

    public int getAdditiveAmount() {
        return additiveAmount;
    }

    public int getCompoundAmount() {
        return compoundAmount;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public int getDefaultMaxProgress() {
        return DEFAULT_MAX_PROGRESS;
    }

    public void setInventory(DefaultedList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    public void setPropertyDelegate(PropertyDelegate propertyDelegate) {
        this.propertyDelegate = propertyDelegate;
    }

    public void setProgress(int value) {
        progress = value;
    }

    public void setAdditiveAmount(int value) {
        additiveAmount = value;
    }

    public void setCompoundAmount(int value) {
        compoundAmount = value;
    }

    public void setMaxProgress(int value) {
        maxProgress = value;
    }

    public void setDefaultMaxProgress(int value) {
        DEFAULT_MAX_PROGRESS = value;
    }

    protected void increaseCraftingProgress() {
        progress++;
    }

    protected void decreaseCraftingProgress() {
        progress--;
    }

    protected void resetProgress() {
        progress = 0;
        maxProgress = DEFAULT_MAX_PROGRESS;
    }

    protected boolean hasCraftingFinished() {
        return progress == maxProgress;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);

        Inventories.readNbt(nbt, inventory, registryLookup);
        progress = nbt.getInt("Progress");
        additiveAmount = nbt.getInt("AdditiveAmount");
        compoundAmount = nbt.getInt("CompoundAmount");
        maxProgress = nbt.getInt("MaxProgress");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);

        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("Progress", progress);
        nbt.putInt("AdditiveAmount", additiveAmount);
        nbt.putInt("CompoundAmount", compoundAmount);
        nbt.putInt("MaxProgress", maxProgress);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return pos;
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}

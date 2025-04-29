package net.mrwooly357.medievalstuff.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

public class ItemStackUtils {

    public static boolean insertFluidStorageStack(ItemStack useStack, ItemStack exchangeStack, PlayerInventory inventory, int slot) {
        PlayerEntity player = inventory.player;
        int count = useStack.getCount();
        boolean bl = false;
        boolean isExchangeStackEmpty = exchangeStack.isEmpty();
        boolean isPlayerInCreativeMode = player.isInCreativeMode();

        if (count <= 1 && !isExchangeStackEmpty && !isPlayerInCreativeMode) {
            useStack.decrement(1);
            inventory.setStack(slot, exchangeStack);

            bl = true;
        } else if (count > 1 && !isExchangeStackEmpty && !isPlayerInCreativeMode) {
            for (int a = 0; a < inventory.main.size(); a++) {
                if (inventory.main.get(a).isEmpty()) {
                    useStack.decrementUnlessCreative(1, player);
                    inventory.main.set(a, exchangeStack);

                    bl = true;

                    break;
                }
            }
        } else if (!isExchangeStackEmpty) {
            for (int a = 0; a < inventory.main.size(); a++) {
                if (inventory.main.get(a).isOf(exchangeStack.getItem()) || inventory.offHand.getFirst().isOf(exchangeStack.getItem())) {
                    bl = true;

                    break;
                } else if (inventory.getStack(a).isEmpty()) {
                    inventory.main.set(a, exchangeStack);

                    bl = true;

                    break;
                }
            }
        }

        return bl;
    }
}

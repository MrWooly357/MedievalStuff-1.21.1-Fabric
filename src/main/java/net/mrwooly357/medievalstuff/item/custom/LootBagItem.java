package net.mrwooly357.medievalstuff.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class LootBagItem extends Item {
    private static List<Item> lootTable = new ArrayList<>();
    private static List<Integer> lootCountTable = new ArrayList<>();
    private static List<Integer> lootChanceTable = new ArrayList<>();

    public LootBagItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        for (int lootIndex = 0; lootIndex < lootTable.size(); lootIndex++) {

            for (int slot = 0; slot < user.getInventory().size(); slot++) {
                ItemStack stackInSlot = user.getInventory().getStack(slot);

                int lootChance = MathHelper.nextInt(Random.create(), 1, lootChanceTable.get(lootIndex));

                if (lootChance == 1) {
                    ItemStack lootStack = new ItemStack(lootTable.get(lootIndex), lootCountTable.get(lootIndex));

                    if (stackInSlot.isEmpty()) {
                        user.getInventory().setStack(slot, lootStack);

                    } else {
                        user.dropStack(lootStack);
                    }
                }
            }
        }

        ItemStack stackInHand = user.getStackInHand(Hand.MAIN_HAND);

        world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, SoundCategory.PLAYERS, 1.0F, 2.0F);
        stackInHand.decrementUnlessCreative(1, user);

        return TypedActionResult.success(stackInHand, true);
    }

    protected static void addLoot(Item item, int count, int chance, int index) {
        lootTable.add(index, item);
        lootCountTable.add(index, count);
        lootChanceTable.add(index, chance);
    }
}

package net.mrwooly357.medievalstuff.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.item.ModItems;
import net.mrwooly357.medievalstuff.item.custom.weapons.ranged.ExtendedRangedWeaponItem;

public class ModModelPredicates {

    public static void registerModModelPredicates() {
        registerCustomBow(ModItems.TWOBOW);
        registerExtendedRangedWeaponItem((ExtendedRangedWeaponItem) ModItems.SHORT_COPPER_BOW);
    }

    private static void registerCustomBow(Item item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else if (entity.getActiveItem() != stack) {
                return 0.0F;
            } else {
                int useTime = stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft();
                return useTime / 20.0F;
            }
        });
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }

    private static void registerExtendedRangedWeaponItem(ExtendedRangedWeaponItem item) {
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else if (entity.getActiveItem() != stack) {
                return 0.0F;
            } else {
                int useTime = stack.getMaxUseTime(entity) - entity.getItemUseTimeLeft();
                float divisionHelper = 20.0F - ExtendedRangedWeaponItem.getChargeTime(item);

                return useTime / divisionHelper;
            }
        });
        ModelPredicateProviderRegistry.register(item, Identifier.ofVanilla("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }
}

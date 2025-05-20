package net.mrwooly357.medievalstuff.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.item.ModItems;

/**
 * A helper class used to ease the process of registering custom items. I highly recommend looking at {@link ModItems} to see how it's used.
 */
public interface ItemRegistryHelper {

    /**
     * A helper method used as an extra layer for custom items.
     * @param id the {@link Identifier}.
     * @param item the {@link Item}.
     * @return a registered item.
     */
    static Item register(Identifier id, Item item) {
        return Registry.register(Registries.ITEM, id, item);
    }
}

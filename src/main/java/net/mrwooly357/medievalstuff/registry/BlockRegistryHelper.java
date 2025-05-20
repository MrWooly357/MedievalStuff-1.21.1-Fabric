package net.mrwooly357.medievalstuff.registry;

import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * A helper class used to ease the process of registering custom blocks. I highly recommend looking at {@link ModBlocks} to see how it's used.
 */
public interface BlockRegistryHelper {

    /**
     * A helper method used as an extra layer for custom blocks.
     * @param id the {@link Identifier}.
     * @param block the {@link Block}.
     * @return a registered block.
     */
    static Block register(Identifier id, Block block) {
        return Registry.register(Registries.BLOCK, id, block);
    }

    /**
     * A helper method used as an extra layer for items for custom blocks.
     * @param id the {@link Identifier}.
     * @param block the {@link Block}.
     */
    static void registerBlockItem(Identifier id, Block block) {
        Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
    }
}

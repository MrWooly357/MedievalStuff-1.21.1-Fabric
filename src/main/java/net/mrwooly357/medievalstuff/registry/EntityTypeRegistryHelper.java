package net.mrwooly357.medievalstuff.registry;

import net.minecraft.entity.Entity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;

/**
 * A helper class used to ease the process of registering custom entity types. I highly recommend looking at {@link ModEntityTypes} to see how it's used.
 */
public interface EntityTypeRegistryHelper {

    /**
     * A helper method used as an extra layer for custom entity types.
     * @param id the {@link Identifier}.
     * @param entityType the {@link EntityType}.
     * @return a registered entity type.
     */
    static <T extends Entity> EntityType<T> register(Identifier id, EntityType<T> entityType) {
        return Registry.register(Registries.ENTITY_TYPE, id, entityType);
    }
}

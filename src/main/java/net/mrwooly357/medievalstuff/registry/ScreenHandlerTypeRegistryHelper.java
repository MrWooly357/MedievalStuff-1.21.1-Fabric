package net.mrwooly357.medievalstuff.registry;

import net.minecraft.screen.ScreenHandler;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlerTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

/**
 * A helper class used to ease the process of registering custom screen handler types. I highly recommend looking at {@link ModScreenHandlerTypes} to see how it's used.
 */
public interface ScreenHandlerTypeRegistryHelper {

    /**
     * A helper method used as an extra layer for custom screen handlers.
     * @param id the {@link Identifier}.
     * @param screenHandlerType the {@link ScreenHandlerType}.
     * @return a registered screen handler type.
     * @param <T> the type.
     */
    static <T extends ScreenHandler> ScreenHandlerType<T> register(Identifier id, ScreenHandlerType<T> screenHandlerType) {
        return Registry.register(Registries.SCREEN_HANDLER, id, screenHandlerType);
    }
}

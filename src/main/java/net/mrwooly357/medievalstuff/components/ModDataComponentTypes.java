package net.mrwooly357.medievalstuff.components;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {


    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(MedievalStuff.MOD_ID),
                (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerDataComponentTypes() {
        MedievalStuff.LOGGER.info("Registering Data Component Types for " + MedievalStuff.MOD_ID);
    }
}

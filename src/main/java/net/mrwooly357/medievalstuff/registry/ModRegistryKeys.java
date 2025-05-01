package net.mrwooly357.medievalstuff.registry;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.compound.Compound;

public class ModRegistryKeys extends RegistryKeys {

    public static final RegistryKey<Registry<Compound>> COMPOUND = of("compound");


    private static <T> RegistryKey<Registry<T>> of(String name) {
        return RegistryKey.ofRegistry(Identifier.of(MedievalStuff.MOD_ID, name));
    }
}

package net.mrwooly357.medievalstuff.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.compound.Compound;
import net.mrwooly357.medievalstuff.compound.Compounds;

public class ModRegistries extends Registries {

    public static final Registry<Compound> COMPOUND = create(ModRegistryKeys.COMPOUND, Compounds::registerAndGetDefault);


    public static void registerModRegistries() {
        MedievalStuff.LOGGER.info("Registering mod registries for " + MedievalStuff.MOD_ID);
    }
}

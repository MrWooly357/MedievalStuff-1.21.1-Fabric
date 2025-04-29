package net.mrwooly357.medievalstuff.world.gen.structure;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureKeys;
import net.mrwooly357.medievalstuff.MedievalStuff;

public interface ModStructureKeys extends StructureKeys {

    //RegistryKey<Structure> ABANDONED_BUNKER = ofMS("abandoned_bunker");


    private static RegistryKey<Structure> ofMS(String name) {
        return RegistryKey.of(RegistryKeys.STRUCTURE, Identifier.of(MedievalStuff.MOD_ID, name));
    }

    static void RegisterModStructureKeys() {
        MedievalStuff.LOGGER.info("Registering mod structure keys for " + MedievalStuff.MOD_ID);
    }
}

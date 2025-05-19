package net.mrwooly357.medievalstuff.compound;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.registry.ModRegistries;

public class Compounds {

    public static final Compound EMPTY = registerCompound("empty",
            new Compound(new Compound.Properties()
            )
    );

    public static final Compound COAL_DUST = registerCompound("coal_dust",
            new Compound(new Compound.Properties()
                    .weight(10)
                    .type(Compound.Type.COAL)
            )
    );


    public static Compound registerCompound(String name, Compound compound) {
        return Registry.register(ModRegistries.COMPOUND, Identifier.of(MedievalStuff.MOD_ID, name), compound);
    }

    public static void registerCompounds() {
        MedievalStuff.LOGGER.info("Registering compounds for " + MedievalStuff.MOD_ID);
    }
}

package net.mrwooly357.medievalstuff.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.world.ModConfiguredFeatures;

import java.util.Optional;

public class ModSaplingGenerators {
    public static final SaplingGenerator LUMISHROOM = new SaplingGenerator(MedievalStuff.MOD_ID + ":lumishroom",
            Optional.empty(), Optional.of(ModConfiguredFeatures.LUMISHROOM_KEY), Optional.empty());
}

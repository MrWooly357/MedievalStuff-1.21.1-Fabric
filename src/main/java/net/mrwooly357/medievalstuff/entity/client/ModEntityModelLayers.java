package net.mrwooly357.medievalstuff.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

public class ModEntityModelLayers {
    public static final EntityModelLayer JELLY =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "jelly"), "main");
}

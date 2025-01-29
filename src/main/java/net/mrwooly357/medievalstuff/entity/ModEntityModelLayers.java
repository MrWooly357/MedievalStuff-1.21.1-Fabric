package net.mrwooly357.medievalstuff.entity;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;


public class ModEntityModelLayers {

    public static final EntityModelLayer JELLY_NORMAL =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "jelly"), "main");
    public static final EntityModelLayer JELLY_TRANSLUCENT =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "jelly"), "outer");
}

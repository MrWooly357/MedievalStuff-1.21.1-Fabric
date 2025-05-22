package net.mrwooly357.medievalstuff.entity;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;


public class ModEntityModelLayers {

    public static final EntityModelLayer JELLY_NORMAL = create(
            "jelly", "main"
    );
    public static final EntityModelLayer JELLY_TRANSLUCENT = create(
            "jelly", "outer"
    );
    public static final EntityModelLayer THROWN_COPPER_KHOPESH = create(
            "thrown_copper_khopesh", "main"
    );
    public static final EntityModelLayer FALLEN_KNIGHT = create(
            "fallen_knight", "main"
    );
    public static final EntityModelLayer FALLEN_KNIGHT_INNER_ARMOR = createInnerArmor(
            "fallen_knight"
    );
    public static final EntityModelLayer FALLEN_KNIGHT_OUTER_ARMOR = createOuterArmor(
            "fallen_knight"
    );


    private static EntityModelLayer createInnerArmor(String idName) {
        return create(idName, "inner_armor");
    }

    private static EntityModelLayer createOuterArmor(String idName) {
        return create(idName, "outer_armor");
    }

    private static EntityModelLayer create(String idName, String layer) {
        return new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, idName), layer);
    }
}

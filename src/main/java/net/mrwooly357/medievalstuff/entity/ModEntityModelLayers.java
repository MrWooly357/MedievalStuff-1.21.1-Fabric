package net.mrwooly357.medievalstuff.entity;

import com.google.common.collect.Sets;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

import java.util.Set;

@Environment(EnvType.CLIENT)
public class ModEntityModelLayers extends EntityModelLayers {

    private static final Set<EntityModelLayer> LAYERS = Sets.newHashSet();

    public static final EntityModelLayer JELLY_NORMAL =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "jelly"), "main");
    public static final EntityModelLayer JELLY_TRANSLUCENT =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "jelly"), "outer");

    public static final EntityModelLayer THE_CORRUPTED_GREAT_PALADIN =
            new EntityModelLayer(Identifier.of(MedievalStuff.MOD_ID, "the_corrupted_great_paladin"), "main");
    public static final EntityModelLayer THE_CORRUPTED_GREAT_PALADIN_INNER_ARMOR = createInnerArmor("the_corrupted_great_paladin");
    public static final EntityModelLayer THE_CORRUPTED_GREAT_PALADIN_OUTER_ARMOR = createOuterArmor("the_corrupted_great_paladin");


    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(Identifier.ofVanilla(id), layer);
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer createInnerArmor(String id) {
        return register(id, "inner_armor");
    }

    private static EntityModelLayer createOuterArmor(String id) {
        return register(id, "outer_armor");
    }
}

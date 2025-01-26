package net.mrwooly357.medievalstuff.entity.client.render;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.mrwooly357.medievalstuff.entity.client.JellyEntityModel;
import net.mrwooly357.medievalstuff.entity.client.ModEntityModelLayers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public class ModEntityModels {
    public static Map<EntityModelLayer, TexturedModelData> getModels() {
       ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder = ImmutableMap.builder();
       builder.put(ModEntityModelLayers.JELLY_NORMAL, JellyEntityModel.getNormalTexturedModelData());
       builder.put(ModEntityModelLayers.JELLY_TRANSLUCENT, JellyEntityModel.getTranslucentTexturedModelData());

        ImmutableMap<EntityModelLayer, TexturedModelData> immutableMap = builder.build();
        List<EntityModelLayer> list = EntityModelLayers.getLayers()
                .filter(layer -> !immutableMap.containsKey(layer))
                .toList();
        if (!list.isEmpty()) {
            throw new IllegalStateException("Missing layer definitions: " + list);
        } else {
            return immutableMap;
        }
    }
}

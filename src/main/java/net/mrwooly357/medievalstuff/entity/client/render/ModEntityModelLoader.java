package net.mrwooly357.medievalstuff.entity.client.render;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloader;

import java.util.Map;

@Environment(EnvType.CLIENT)
public class ModEntityModelLoader extends EntityModelLoader implements SynchronousResourceReloader {

    @Override
    public void reload(ResourceManager manager) {
        Map<EntityModelLayer, TexturedModelData> modelParts = ImmutableMap.copyOf(ModEntityModels.getModels());
    }
}

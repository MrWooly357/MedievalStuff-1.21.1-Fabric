package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.entity.client.JellyModel;
import net.mrwooly357.medievalstuff.entity.client.JellyRenderer;
import net.mrwooly357.medievalstuff.entity.client.ModEntityModelLayers;

public class MedievalStuffClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LUMISHROOM, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY, JellyModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLY, JellyRenderer::new);
    }
}

package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.client.TheCorruptedGreatPaladinEntityModel;
import net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.client.TheCorruptedGreatPaladinEntityRenderer;
import net.mrwooly357.medievalstuff.entity.mob.jelly.client.JellyEntityModel;
import net.mrwooly357.medievalstuff.entity.mob.jelly.client.JellyEntityRenderer;
import net.mrwooly357.medievalstuff.entity.ModEntityModelLayers;

public class MedievalStuffClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LUMISHROOM, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY_NORMAL, JellyEntityModel::getNormalTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY_TRANSLUCENT, JellyEntityModel::getTranslucentTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLY, JellyEntityRenderer::new);
    }
}

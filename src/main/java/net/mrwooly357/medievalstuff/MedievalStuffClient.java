package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.entity.mob.jelly.client.JellyEntityModel;
import net.mrwooly357.medievalstuff.entity.mob.jelly.client.JellyEntityRenderer;
import net.mrwooly357.medievalstuff.entity.ModEntityModelLayers;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlers;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreen;

public class MedievalStuffClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LUMISHROOM, RenderLayer.getCutout());

        //Entities
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY_NORMAL, JellyEntityModel::getNormalTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY_TRANSLUCENT, JellyEntityModel::getTranslucentTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLY, JellyEntityRenderer::new);

        //Screen handlers
        HandledScreens.register(ModScreenHandlers.COPPERSTONE_HEATER_SCREEN_HANDLER, CopperstoneHeaterScreen::new);
    }
}

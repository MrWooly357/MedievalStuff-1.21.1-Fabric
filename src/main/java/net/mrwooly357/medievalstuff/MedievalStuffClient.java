package net.mrwooly357.medievalstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.ModBlockEntities;
import net.mrwooly357.medievalstuff.block.entity.renderer.TankBlockEntityRenderer;
import net.mrwooly357.medievalstuff.entity.ModEntities;
import net.mrwooly357.medievalstuff.entity.mob.passive.jelly.JellyEntityModel;
import net.mrwooly357.medievalstuff.entity.mob.passive.jelly.JellyEntityRenderer;
import net.mrwooly357.medievalstuff.entity.ModEntityModelLayers;
import net.mrwooly357.medievalstuff.entity.projectile.khopesh.ThrownCopperKhopeshEntityModel;
import net.mrwooly357.medievalstuff.entity.projectile.khopesh.ThrownCopperKhopeshEntityRenderer;
import net.mrwooly357.medievalstuff.screen.ModScreenHandlers;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreen;
import net.mrwooly357.medievalstuff.util.ModModelPredicates;

public class MedievalStuffClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //Blocks
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COPPER_TANK, RenderLayer.getTranslucent());

        //Block entity renderers
        BlockEntityRendererFactories.register(ModBlockEntities.COPPER_TANK_BE, TankBlockEntityRenderer::new);

        //Entities
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY_NORMAL, JellyEntityModel::getNormalTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.JELLY_TRANSLUCENT, JellyEntityModel::getTranslucentTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLY, JellyEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.THROWN_COPPER_KHOPESH, ThrownCopperKhopeshEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.THROWN_COPPER_KHOPESH, ThrownCopperKhopeshEntityRenderer::new);

        //Screen handlers
        HandledScreens.register(ModScreenHandlers.COPPERSTONE_HEATER_SCREEN_HANDLER, CopperstoneHeaterScreen::new);

        //Additional
        ModModelPredicates.registerModModelPredicates();
    }
}

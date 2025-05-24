package net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.ModEntityModelLayers;

public class FallenKnightEntityRenderer<T extends HostileEntity> extends BipedEntityRenderer<T, FallenKnightEntityModel<T>> {

    private static final Identifier TEXTURE = Identifier.of(MedievalStuff.MOD_ID, "textures/entity/fallen_knight/fallen_knight.png");
    private static final RenderLayer EMISSIVE_TRANSLUCENT = RenderLayer.of(
            "emissive_translucent", VertexFormats.POSITION_COLOR_TEXTURE_OVERLAY_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, false, true, RenderLayer.MultiPhaseParameters.builder()
                    .program(new RenderPhase.ShaderProgram(GameRenderer::getRenderTypeEntityTranslucentEmissiveProgram))
                    .texture(new RenderPhase.Texture(TEXTURE, false, false))
                    .transparency(RenderPhase.TRANSLUCENT_TRANSPARENCY)
                    .cull(RenderPhase.DISABLE_CULLING)
                    .lightmap(RenderPhase.ENABLE_LIGHTMAP)
                    .overlay(RenderPhase.ENABLE_OVERLAY_COLOR)
                    .build(false)

    );

    public FallenKnightEntityRenderer(EntityRendererFactory.Context context) {
        this(context, ModEntityModelLayers.FALLEN_KNIGHT, ModEntityModelLayers.FALLEN_KNIGHT_OUTER_ARMOR, ModEntityModelLayers.FALLEN_KNIGHT_INNER_ARMOR);
    }

    public FallenKnightEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer entityModelLayer, EntityModelLayer legArmorLayer, EntityModelLayer bodyArmorLayer) {
        this(context, legArmorLayer, bodyArmorLayer, new FallenKnightEntityModel<>(context.getPart(entityModelLayer)));
    }

    public FallenKnightEntityRenderer(EntityRendererFactory.Context context, EntityModelLayer entityModelLayer, EntityModelLayer entityModelLayer1, FallenKnightEntityModel<T> fallenKnightEntityModel) {
        super(context, fallenKnightEntityModel, 0.5F);

        addFeature(
                new ArmorFeatureRenderer<>(
                        this, new FallenKnightEntityModel<>(context.getPart(entityModelLayer)), new FallenKnightEntityModel<>(context.getPart(entityModelLayer1)
                ),
                        context.getModelManager()
                )
        );
    }


    @Override
    public Identifier getTexture(T entity) {
        return TEXTURE;
    }

    @Override
    protected boolean isShaking(T entity) {
        return entity instanceof FallenKnightEntity fallenKnightEntity && fallenKnightEntity.isShaking();
    }

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity instanceof FallenKnightEntity fallenKnightEntity) {

            if (entity.isAlive()) {
                matrices.push();
                matrices.scale(1.0F, -1.0F, 1.0F);
                matrices.translate(0.0F, -1.5F, 0.0F);
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(EMISSIVE_TRANSLUCENT);
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                model.renderSoul(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

                if (fallenKnightEntity.isCharging()) {
                    model.renderBodyWithTranslucency(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
                }

                RenderSystem.disableBlend();
                matrices.pop();
            }

            if (!fallenKnightEntity.isCharging()) {
                super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
            }
        }
    }
}

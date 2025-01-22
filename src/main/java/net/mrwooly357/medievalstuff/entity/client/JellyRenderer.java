package net.mrwooly357.medievalstuff.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.custom.JellyEntity;

public class JellyRenderer extends MobEntityRenderer<JellyEntity, JellyModel> {
    public JellyRenderer(EntityRendererFactory.Context context) {
        super(context, new JellyModel(context.getPart(ModEntityModelLayers.JELLY)), 0.3f);
    }

    @Override
    public Identifier getTexture(JellyEntity entity) {
        return Identifier.of(MedievalStuff.MOD_ID, "textures/entity/jelly/jelly_blue.png");
    }

    @Override
    public void render(JellyEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}

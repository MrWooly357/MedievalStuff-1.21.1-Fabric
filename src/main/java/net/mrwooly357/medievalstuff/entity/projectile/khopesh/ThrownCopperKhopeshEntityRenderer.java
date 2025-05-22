package net.mrwooly357.medievalstuff.entity.projectile.khopesh;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.ModEntityModelLayers;

public class ThrownCopperKhopeshEntityRenderer extends EntityRenderer<ThrownCopperKhopeshEntity> {

    private static final Identifier TEXTURE = Identifier.of(MedievalStuff.MOD_ID, "textures/entity/thrown_khopesh/copper.png");
    protected ThrownCopperKhopeshEntityModel model;

    public ThrownCopperKhopeshEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model = new ThrownCopperKhopeshEntityModel(ctx.getPart(ModEntityModelLayers.THROWN_COPPER_KHOPESH));
    }


    @Override
    public void render(ThrownCopperKhopeshEntity thrownCopperKhopeshEntity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, thrownCopperKhopeshEntity.prevYaw, thrownCopperKhopeshEntity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, thrownCopperKhopeshEntity.prevPitch, thrownCopperKhopeshEntity.getPitch()) + 90.0F));
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, model.getLayer(getTexture(thrownCopperKhopeshEntity)), false, false);
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();

        super.render(thrownCopperKhopeshEntity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ThrownCopperKhopeshEntity entity) {
        return TEXTURE;
    }
}

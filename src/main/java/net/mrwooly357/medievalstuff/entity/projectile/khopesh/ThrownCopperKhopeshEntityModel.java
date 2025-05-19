package net.mrwooly357.medievalstuff.entity.projectile.khopesh;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class ThrownCopperKhopeshEntityModel extends EntityModel<ThrownCopperKhopeshEntity> {
    private final ModelPart main;

    public ThrownCopperKhopeshEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        main = root.getChild("main");
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 4).cuboid(3.0F, -8.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(2.0F, -7.0F, -1.0F, 6.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 2).cuboid(1.0F, -6.0F, -1.0F, 5.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 6).cuboid(0.0F, -5.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 6).cuboid(-1.0F, -4.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(8, 12).cuboid(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 8).cuboid(-2.0F, -2.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 14).cuboid(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 10).cuboid(-3.0F, 0.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 0).cuboid(-3.0F, 1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 14).cuboid(-5.0F, 1.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 8).cuboid(-5.0F, 2.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 12).cuboid(-5.0F, 3.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 10).cuboid(-6.0F, 4.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(14, 12).cuboid(-8.0F, 4.0F, -1.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(10, 4).cuboid(-8.0F, 5.0F, -1.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(12, 2).cuboid(-8.0F, 6.0F, -1.0F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(6, 14).cuboid(-7.0F, 7.0F, -1.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 32, 32);
    }

    @Override
    public void setAngles(ThrownCopperKhopeshEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        main.render(matrices, vertexConsumer, light, overlay, color);
    }
}

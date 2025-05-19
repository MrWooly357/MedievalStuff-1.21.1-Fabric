package net.mrwooly357.medievalstuff.entity.mob.passive.jelly;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public class JellyEntityModel<T extends Entity> extends SinglePartEntityModel<T> {
    private final ModelPart jelly;

    public JellyEntityModel(ModelPart root) {
        this.jelly = root.getChild("jelly");
    }

    public static TexturedModelData getTranslucentTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData jelly = modelPartData.addChild(
                "jelly", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = jelly.addChild(
                "body", ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4.0F, -11.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData legs = body.addChild(
                "legs", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, -3.0F, 0.0F));

        ModelPartData leg1 = legs.addChild(
                "leg1", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leg1_r1 = leg1.addChild(
                "leg1_r1", ModelPartBuilder.create()
                        .uv(0, 17)
                        .cuboid(0.0F, -1.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 1.0F, 0.25F, -0.3491F, 0.0F, -0.3491F));

        ModelPartData leg2 = legs.addChild(
                "leg2", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leg2_r1 = leg2.addChild(
                "leg2_r1", ModelPartBuilder.create()
                        .uv(9, 17)
                        .cuboid(-2.0F, -1.0F, -2.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 1.0F, 0.25F, -0.3491F, 0.0F, 0.3491F));

        ModelPartData leg3 = legs.addChild(
                "leg3", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData leg3_r1 = leg3.addChild(
                "leg3_r1",
                ModelPartBuilder.create()
                        .uv(18, 17)
                        .cuboid(-1.0F, -1.3F, -0.3F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, 1.0F, 0.25F, 0.3491F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    public static TexturedModelData getNormalTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData jelly = modelPartData.addChild(
                "jelly", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData hat = jelly.addChild(
                "hat", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hat2 = hat.addChild(
                "hat2", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hat4_r1 = hat2.addChild(
                "hat4_r1", ModelPartBuilder.create()
                        .uv(0, 27)
                        .cuboid(-1.0F, 0.0F, 0.75F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -11.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

        ModelPartData hat3_r1 = hat2.addChild(
                "hat3_r1", ModelPartBuilder.create()
                        .uv(22, 27)
                        .cuboid(-3.75F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -10.9F, 0.0F, 0.0F, 0.0F, 0.0873F));

        ModelPartData hat2_r1 = hat2.addChild(
                "hat2_r1", ModelPartBuilder.create()
                        .uv(22, 23)
                        .cuboid(-1.0F, 0.0F, -3.75F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -11.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

        ModelPartData hat1_r1 = hat2.addChild("hat1_r1", ModelPartBuilder.create()
                .uv(27, 20)
                .cuboid(0.75F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -10.9F, 0.0F, 0.0F, 0.0F, -0.1745F));

        ModelPartData hat1 = hat.addChild(
                "hat1", ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData hat4_r2 = hat1.addChild(
                "hat4_r2", ModelPartBuilder.create()
                        .uv(11, 23)
                        .cuboid(-1.0F, 0.1F, 0.75F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -11.0F, 0.0F, 0.2182F, 0.7854F, 0.0F));

        ModelPartData hat3_r2 = hat1.addChild(
                "hat3_r2", ModelPartBuilder.create()
                        .uv(0, 23)
                        .cuboid(-1.0F, 0.1F, -3.75F, 2.0F, 0.0F, 3.0F, new Dilation(0.0F))
                        .uv(27, 17).cuboid(-3.75F, 0.1F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -11.0F, 0.0F, 0.0F, 0.7854F, 0.1745F));

        ModelPartData hat1_r2 = hat1.addChild(
                "hat1_r2", ModelPartBuilder.create()
                        .uv(11, 27)
                        .cuboid(0.75F, 0.1F, -1.0F, 3.0F, 0.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.of(0.0F, -11.0F, 0.0F, 0.0F, 0.7854F, -0.0873F));

        ModelPartData flower = hat.addChild(
                "flower", ModelPartBuilder.create()
                        .uv(11, 30)
                        .cuboid(-1.0F, -11.75F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData core = jelly.addChild(
                "core", ModelPartBuilder.create()
                        .uv(33, 0)
                        .cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)),
                ModelTransform.pivot(0.0F, -7.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHatYaw, float hatPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);

        if (entity instanceof JellyEntity jellyEntity) {
            this.animateMovement(JellyAnimations.jelly_animation, limbSwing, limbSwingAmount, 2f, 2.5f);
            this.updateAnimation(jellyEntity.basicJellyAnimationState, JellyAnimations.jelly_animation, ageInTicks, 1);
        }
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        jelly.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return jelly;
    }
}

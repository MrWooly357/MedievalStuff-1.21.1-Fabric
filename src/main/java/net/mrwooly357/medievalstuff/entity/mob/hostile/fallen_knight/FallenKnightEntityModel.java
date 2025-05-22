package net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.HostileEntity;

public class FallenKnightEntityModel<T extends HostileEntity> extends BipedEntityModel<T> {

    private static final Dilation ARMOR_DILATION = new Dilation(1.0F);
    private static final Dilation HAT_DILATION = new Dilation(0.5F);
    public ModelPart soulInner;
    public ModelPart soulOuter;

    public FallenKnightEntityModel(ModelPart root) {
        super(root);

        soulInner = getTexturedModelData().createModel().getChild("soul_inner");
        soulOuter = getTexturedModelData().createModel().getChild("soul_outer");
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = BipedEntityModel.getModelData(Dilation.NONE, 0.0F);
        ModelPartData modelPartData = modelData.getRoot();

        addLimbs(modelPartData);
        addSoulInner(modelPartData);
        addSoulOuter(modelPartData);

        return TexturedModelData.of(modelData, 64, 32);
    }

    public static TexturedModelData getInnerArmorTexturedModelData() {
        return TexturedModelData.of(ArmorEntityModel.getModelData(ARMOR_DILATION), 64, 32);
    }

    public static TexturedModelData getOuterArmorTexturedModelData() {
        return TexturedModelData.of(ArmorEntityModel.getModelData(HAT_DILATION), 64, 32);
    }

    protected static void addLimbs(ModelPartData modelPartData) {
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_ARM, ModelPartBuilder.create()
                        .uv(40, 16)
                        .cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                ModelTransform.pivot(-5.0F, 2.0F, 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.LEFT_ARM, ModelPartBuilder.create()
                        .uv(40, 16)
                        .mirrored()
                        .cuboid(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                ModelTransform.pivot(5.0F, 2.0F, 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create()
                        .uv(0, 16)
                        .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                ModelTransform.pivot(-2.0F, 12.0F, 0.0F)
        );
        modelPartData.addChild(
                EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create()
                        .uv(0, 16)
                        .mirrored()
                        .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F),
                ModelTransform.pivot(2.0F, 12.0F, 0.0F)
        );
    }

    protected static void addSoulInner(ModelPartData modelPartData) {
        ModelPartData soul_inner = modelPartData.addChild(
                "soul_inner", ModelPartBuilder.create()
                        .uv(60, 30)
                        .cuboid(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, Dilation.NONE),
                ModelTransform.pivot(0.0F, 4.0F, 0.0F)
        );
    }

    protected static void addSoulOuter(ModelPartData modelPartData) {
        ModelPartData soul_outer = modelPartData.addChild(
                "soul_outer", ModelPartBuilder.create()
                        .uv(56, 26)
                        .cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, Dilation.NONE),
                ModelTransform.pivot(0.0F, 4.0F, 0.0F)
        );
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
        super.render(matrices, vertices, light, overlay, color);
    }

    public void renderTranslucent(MatrixStack matrices, VertexConsumer vertices, int light, int overlay) {
        soulOuter.render(matrices, vertices, light, overlay);
        soulInner.render(matrices, vertices, light, overlay);
    }
}

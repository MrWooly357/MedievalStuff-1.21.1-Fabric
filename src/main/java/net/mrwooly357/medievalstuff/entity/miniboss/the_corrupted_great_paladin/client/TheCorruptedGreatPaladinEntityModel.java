package net.mrwooly357.medievalstuff.entity.miniboss.the_corrupted_great_paladin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Arm;

import java.util.Collections;

@Environment(EnvType.CLIENT)
public class TheCorruptedGreatPaladinEntityModel<T extends MobEntity> extends PlayerEntityModel<T> {
	private final ModelPart main;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;


	public TheCorruptedGreatPaladinEntityModel(ModelPart root) {
        super(root, false);
        this.main = root.getChild("main");
		this.head = this.main.getChild("head");
		this.body = this.main.getChild("body");
		this.left_arm = this.main.getChild("left_arm");
		this.right_arm = this.main.getChild("right_arm");
		this.left_leg = this.main.getChild("left_leg");
		this.right_leg = this.main.getChild("right_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData main = modelPartData.addChild(
				"main", ModelPartBuilder.create(),
				ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = main.addChild(
				"head", ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-4.0F, -32.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData body = main.addChild(
				"body", ModelPartBuilder.create()
						.uv(0, 16)
						.cuboid(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_arm = main.addChild(
				"left_arm", ModelPartBuilder.create()
						.uv(16, 32)
						.cuboid(-8.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_arm = main.addChild(
				"right_arm", ModelPartBuilder.create()
						.uv(32, 0)
						.cuboid(4.0F, -24.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData left_leg = main.addChild(
				"left_leg", ModelPartBuilder.create()
						.uv(24, 16)
						.cuboid(-4.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData right_leg = main.addChild(
				"right_leg", ModelPartBuilder.create()
						.uv(0, 32)
						.cuboid(0.0F, -12.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)),
				ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		return TexturedModelData.of(modelData, 48, 48);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		main.render(matrices, vertices, light, overlay, color);
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}
}
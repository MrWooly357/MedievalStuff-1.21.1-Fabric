package net.mrwooly357.medievalstuff.block.entity.renderer;

import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import net.mrwooly357.medievalstuff.block.custom.tanks.TankBlock;
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.TankBlockEntity;

// Credits to TurtyWurty
// Under MIT-License: https://github.com/DaRealTurtyWurty/1.20-Tutorial-Mod?tab=MIT-1-ov-file#readme
// Major Rewrites for Fabric
public class TankBlockEntityRenderer implements BlockEntityRenderer<TankBlockEntity> {

    public TankBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
    }

    @Override
    public void render(TankBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        World world = entity.getWorld();
        FluidVariant fluid = entity.getFluidStorage().variant;
        BlockPos pos = entity.getPos();
        BlockPos additionalPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        BlockEntity additionalEntity = world.getBlockEntity(additionalPos);
        BlockState state = world.getBlockState(pos);
        SingleVariantStorage<FluidVariant> fluidStorage = null;

        if (additionalEntity instanceof TankBlockEntity tankBlockEntity) {
            fluidStorage = tankBlockEntity.getFluidStorage();
        }

        if (state.getBlock() instanceof TankBlock) {

            if (fluid.isBlank())
                return;

            final Sprite sprite = FluidVariantRendering.getSprite(fluid);
            int color = FluidVariantRendering.getColor(fluid);
            FluidState fluidState = fluid.getFluid().getDefaultState();

            float height = (((float) entity.getFluidStorage().getAmount() / entity.getFluidStorage().getCapacity()) * 0.625f) + 0.25f;
            float bottomHeight;

            if (state.get(TankBlock.BOTTOM_CONNECTED)) {
                bottomHeight = 0.0F;

            } else {
                bottomHeight = 0.1F;
            }

            if (fluidStorage != null) {

                if (state.get(TankBlock.TOP_CONNECTED) && !fluidStorage.isResourceBlank()) {
                    height += 0.125F;
                }
            }

            VertexConsumer builder = vertexConsumers.getBuffer(RenderLayers.getFluidLayer(fluidState));

            //Top
            drawQuad(builder, matrices, 0.1f, height, 0.1f, 0.9f, height, 0.9f, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), light, color);
            //Bottom
            drawQuad(builder, matrices, 0.1f, bottomHeight, 0.1f, 0.9f, height, 0.1f, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), light, color);
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
            matrices.translate(0, 0f, -1f);

            drawQuad(builder, matrices, 0.1f, -0.01f, 0.1f, 0.9f, -0.01f, 0.9f, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), light, color);
            matrices.pop();
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
            matrices.translate(-1f, 0, -1.8f);

            drawQuad(builder, matrices, 0.1f, 0, 0.9f, 0.9f, height, 0.9f, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), light, color);
            matrices.pop();
            matrices.push();
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90));
            matrices.translate(-1f, 0, 0);

            drawQuad(builder, matrices, 0.1f, 0, 0.1f, 0.9f, height, 0.1f, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), light, color);
            matrices.pop();
            matrices.push();
            matrices.multiply(RotationAxis.NEGATIVE_Y.rotationDegrees(90));
            matrices.translate(0, 0, -1f);

            drawQuad(builder, matrices, 0.1f, 0, 0.1f, 0.9f, height, 0.1f, sprite.getMinU(), sprite.getMinV(), sprite.getMaxU(), sprite.getMaxV(), light, color);
            matrices.pop();
        }
    }

    private static void drawVertex(VertexConsumer builder, MatrixStack poseStack, float x, float y, float z, float u, float v, int packedLight, int color) {
        builder.vertex(poseStack.peek().copy(), x, y, z)
                .color(color)
                .texture(u, v)
                .light(packedLight)
                .normal(1, 0, 0);
    }

    private static void drawQuad(VertexConsumer builder, MatrixStack poseStack, float x0, float y0, float z0, float x1, float y1, float z1, float u0, float v0, float u1, float v1, int packedLight, int color) {
        drawVertex(builder, poseStack, x0, y0, z0, u0, v0, packedLight, color);
        drawVertex(builder, poseStack, x0, y1, z1, u0, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y1, z1, u1, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y0, z0, u1, v0, packedLight, color);
    }
}

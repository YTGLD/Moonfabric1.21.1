package com.moonfabric.EntiyMl;

import com.moonfabric.Entity.owner_blood;
import com.moonfabric.HasCurio;
import com.moonfabric.MRender;
import com.moonfabric.MoonFabricModClient;
import com.moonfabric.MoonPost;
import com.moonfabric.init.init;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class OwnerBloodRender <T extends owner_blood> extends EntityRenderer<T> {
    public OwnerBloodRender(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public boolean shouldRender(T entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

    public void render(T persistentProjectileEntity, float f, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (persistentProjectileEntity.getOwner()!=null&& !HasCurio.has(init.fire_book, persistentProjectileEntity.getOwner())) {
            MoonPost.renderEffectForNextTick(MoonFabricModClient.POST);


            double x = MathHelper.lerp(tickDelta, persistentProjectileEntity.lastRenderX, persistentProjectileEntity.getX());
            double y = MathHelper.lerp(tickDelta, persistentProjectileEntity.lastRenderY, persistentProjectileEntity.getY());
            double z = MathHelper.lerp(tickDelta, persistentProjectileEntity.lastRenderZ, persistentProjectileEntity.getZ());
            matrixStack.push();
            matrixStack.translate(-x, -y, -z);
            renderTrail(persistentProjectileEntity, tickDelta, matrixStack, vertexConsumerProvider, 1, 0, 0, 240);
            matrixStack.pop();
            renderSphere1(matrixStack, vertexConsumerProvider, 240, 0.65f);

            renderSphere1s(matrixStack, vertexConsumerProvider, 240, 0.6f);
        }
        super.render(persistentProjectileEntity, f, tickDelta, matrixStack, vertexConsumerProvider, i);
    }
    private void renderTrail(owner_blood entityIn, float partialTicks, MatrixStack poseStack, VertexConsumerProvider bufferIn, float trailR, float trailG, float trailB, int packedLightIn) {
        int samples = 0;
        int sampleSize = owner_blood.max;
        float as = 0.3f; // 调整高度
        float trailZRot = 0;
        Vec3d topAngleVec = new Vec3d(as, as, as).rotateZ(trailZRot);
        Vec3d bottomAngleVec = new Vec3d(-as, -as, as).rotateZ(trailZRot);
        Vec3d drawFrom = entityIn.getTrailPosition(0, partialTicks);
        VertexConsumer vertexconsumer = bufferIn.getBuffer(MRender.LIGHTNING);

        while (samples < sampleSize - 1) { // 减少一个采样点以避免访问越界
            Vec3d sample = entityIn.getTrailPosition(samples + 1, partialTicks); // 修改这里的指针偏移量
            float u1 = samples / (float) sampleSize;
            float u2 = u1 + 1 / (float) sampleSize;

            // 计算动态透明度
            float alpha1 = 1 - u1; // 从1到0
            float alpha2 = 1 - u2; // 从1到0

            Vec3d draw1 = drawFrom;

            MatrixStack.Entry posestack$pose = poseStack.peek();
            Matrix4f matrix4f = posestack$pose.getPositionMatrix();

            // 添加四边形的四个顶点
            vertexconsumer.vertex(matrix4f, (float) draw1.x + (float) bottomAngleVec.x, (float) draw1.y + (float) bottomAngleVec.y, (float) draw1.z + (float) bottomAngleVec.z).color(trailR, trailG, trailB, alpha1).texture(0, 0).overlay(OverlayTexture.DEFAULT_UV).light(packedLightIn,240).normal(0.0F, 0.0F, 0.0F);
            vertexconsumer.vertex(matrix4f, (float) sample.x + (float) bottomAngleVec.x, (float) sample.y + (float) bottomAngleVec.y, (float) sample.z + (float) bottomAngleVec.z).color(trailR, trailG, trailB, alpha2).texture(0, 0).overlay(OverlayTexture.DEFAULT_UV).light(packedLightIn,240).normal(0.0F, 0.0F, 0.0F);
            vertexconsumer.vertex(matrix4f, (float) sample.x + (float) topAngleVec.x, (float) sample.y + (float) topAngleVec.y, (float) sample.z + (float) topAngleVec.z).color(trailR, trailG, trailB, alpha2).texture(0, 0).overlay(OverlayTexture.DEFAULT_UV).light(packedLightIn,240).normal(0.0F, 0.0F, 0.0F);
            vertexconsumer.vertex(matrix4f, (float) draw1.x + (float) topAngleVec.x, (float) draw1.y + (float) topAngleVec.y, (float) draw1.z + (float) topAngleVec.z).color(trailR, trailG, trailB, alpha1).texture(0, 0).overlay(OverlayTexture.DEFAULT_UV).light(packedLightIn,240).normal(0.0F, 0.0F, 0.0F);

            samples++;
            drawFrom = sample;
        }
    }
    public void renderSphere1(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
        int stacks = 30; // 垂直方向的分割数
        int slices = 30; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.getBloodOutLine());
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
            }
        }
    }
    public void renderSphere1s(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.getBlood());
        for (int i = 0; i < stacks; ++i) {
            float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
            float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

            for (int j = 0; j < slices; ++j) {
                float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                float x0 = s * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                float y0 = s * (float) Math.cos(phi0);
                float z0 = s * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                float x1 = s * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                float y1 = s * (float) Math.cos(phi0);
                float z1 = s * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                float x2 = s * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                float y2 = s * (float) Math.cos(phi1);
                float z2 = s * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                float x3 = s * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                float y3 = s * (float) Math.cos(phi1);
                float z3 = s * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
                vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(light, light).normal(1, 0, 0);
            }
        }
    }
    @Override
    public Identifier getTexture(T entity) {
        return Identifier.of("moonfabric","textures/entity/flysword.png");
    }


}




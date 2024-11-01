package com.moonfabric.EntiyMl;

import com.moonfabric.Entity.attack_blood;
import com.moonfabric.Entity.owner_blood;
import com.moonfabric.Handler;
import com.moonfabric.MRender;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

public class OwnerBloodRender <T extends owner_blood> extends EntityRenderer<T> {
    public OwnerBloodRender(EntityRendererFactory.Context context) {
        super(context);
    }

    public void render(T persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider
            vertexConsumerProvider, int i) {
        setT(matrixStack,persistentProjectileEntity,vertexConsumerProvider);
        renderSphere1(matrixStack,vertexConsumerProvider,240,0.5f);
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
    private void setT(MatrixStack matrices,
                      T entity,
                      VertexConsumerProvider vertexConsumers){

        matrices.push();
        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3d prevPos = entity.getTrailPositions().get(i - 1);
            Vec3d currPos = entity.getTrailPositions().get(i);
            Vec3d adjustedPrevPos = new Vec3d(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3d adjustedCurrPos = new Vec3d(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            Handler.renderLine(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderLayer.getLightning(),0.25f);

        }
        matrices.pop();

    }
    public void renderSphere1(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
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




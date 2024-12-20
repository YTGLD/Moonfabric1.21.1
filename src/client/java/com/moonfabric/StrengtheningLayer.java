package com.moonfabric;

import com.moonfabric.Entity.nig_test;
import com.moonfabric.Entity.nightmare_giant;
import com.moonfabric.EntiyMl.nig.CircleCubeBoom;
import com.moonfabric.EntiyMl.nig.NigEntityModel;
import com.moonfabric.EntiyMl.ytgld.YtgldEntityModel;
import com.moonfabric.EntiyMl.ytgld.YtgldEntityRenderer;
import com.moonfabric.init.InItEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;

public class StrengtheningLayer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer {
    public StrengtheningLayer(FeatureRendererContext context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       Entity entity,
                       float limbAngle,
                       float limbDistance,
                       float tickDelta,
                       float animationProgress,
                       float headYaw,
                       float headPitch) {
        new Sword(matrices, vertexConsumers, light, entity);

        if (entity instanceof nig_test){
            new CircleCubeBoom(matrices,vertexConsumers,240,entity);
        }
        if (entity.getType()==InItEntity.nightmare_giant) {
            matrices.push();

            renderSphereBlood(matrices, vertexConsumers,0, 0.35f);
            getNig(matrices, vertexConsumers, 240, 0.4f);
            matrices.pop();
        }
        if (entity.getType()==InItEntity.ytgld_) {
            if (this.getContextModel() instanceof YtgldEntityModel<?> model) {


                matrices.push();
                matrices.translate(model.body.pivotX / 16.0F, model.body.pivotY / 16.0F+1.5, model.body.pivotZ / 16.0F);

                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(model.body.pitch));
                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(model.body.yaw));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(model.body.roll));

                getYtgld(matrices, vertexConsumers, 0, 0.35f);
                getYtgldBlood(matrices, vertexConsumers, 240, 0.4f);
                matrices.pop();
            }
        }
    }
    public void renderSphereBlood(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.getBlood_common());
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
    public void getNig(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.getBloodNIG());
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


    public void getYtgldBlood(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
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
    public void getYtgld(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
        int stacks = 20; // 垂直方向的分割数
        int slices = 20; // 水平方向的分割数
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
}

package com.moonfabric.EntiyMl.nig;

import com.moonfabric.MRender;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;


public class CircleCubeBoom {

    public CircleCubeBoom(@NotNull MatrixStack matrices,
                          @NotNull VertexConsumerProvider vertexConsumers,
                          int light,
                          @NotNull Entity entity) {



        float alp =100;
        alp -= entity.age;
        if (alp>10){
            alp -= entity.age*2;
        }
        if (alp <0){
            alp = 0;
        }

        alp /= 100;

        {

            matrices.push();
            renderCircle3(matrices, vertexConsumers, light, 0, 0, 0,1, alp, entity);
            matrices.pop();
        }

    }



    public  void renderCircle3(@NotNull MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight, float x, float y, float z, float radius,float alp , Entity entity) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(MRender.getLightning());
        int verticalSegments = 24; // 垂直段数
        int horizontalSegments = 24; // 水平段数
        poseStack.translate(0 ,-1 ,0);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees((float) (-entity.age*0.1)));
        poseStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((float) (-entity.age * 0.1)));


        for (int i = 0; i < verticalSegments; i++) {
            float vAngle1 = (float) (Math.PI * i / verticalSegments);
            float vAngle2 = (float) (Math.PI * (i + 1) / verticalSegments);

            for (int j = 0; j < horizontalSegments; j++) {
                float hAngle1 = (float) (2 * Math.PI * j / horizontalSegments);
                float hAngle2 = (float) (2 * Math.PI * (j + 1) / horizontalSegments);

                float x1 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle1));
                float y1 = (float) (y + radius * Math.cos(vAngle1));
                float z1 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle1));

                float x2 = (float) (x + radius * Math.sin(vAngle1) * Math.cos(hAngle2));
                float y2 = (float) (y + radius * Math.cos(vAngle1));
                float z2 = (float) (z + radius * Math.sin(vAngle1) * Math.sin(hAngle2));

                float x3 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle1));
                float y3 = (float) (y + radius * Math.cos(vAngle2));
                float z3 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle1));

                float x4 = (float) (x + radius * Math.sin(vAngle2) * Math.cos(hAngle2));
                float y4 = (float) (y + radius * Math.cos(vAngle2));
                float z4 = (float) (z + radius * Math.sin(vAngle2) * Math.sin(hAngle2));

                // 绘制两个三角形
                vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), x1, y1, z1).color(0, 0, 0, alp).light(0, 0).overlay(OverlayTexture.DEFAULT_UV).texture(packedLight,packedLight).normal(1, 0, 0);
                vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), x2, y2, z2).color(0, 0, 0, alp).light(0, 0).overlay(OverlayTexture.DEFAULT_UV).texture(packedLight,packedLight).normal(1, 0, 0);
                vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), x3, y3, z3).color(0, 0, 0, alp).light(0, 0).overlay(OverlayTexture.DEFAULT_UV).texture(packedLight,packedLight).normal(1, 0, 0);

                vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), x2, y2, z2).color(0, 0, 0, alp).light(0, 0).overlay(OverlayTexture.DEFAULT_UV).texture(packedLight,packedLight).normal(1, 0, 0);
                vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), x3, y3, z3).color(0, 0, 0, alp).light(0, 0).overlay(OverlayTexture.DEFAULT_UV).texture(packedLight,packedLight).normal(1, 0, 0);
                vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), x4, y4, z4).color(0, 0, 0, alp).light(0, 0).overlay(OverlayTexture.DEFAULT_UV).texture(packedLight,packedLight).normal(1, 0, 0);
            }
        }
    }


}

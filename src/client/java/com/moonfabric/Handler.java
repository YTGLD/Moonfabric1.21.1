package com.moonfabric;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public class Handler {
    private static void addSquare(VertexConsumer vertexConsumer, MatrixStack poseStack, Vec3d up1, Vec3d up2, Vec3d down1, Vec3d down2, float alpha) {
        // 添加四个顶点来绘制一个矩形
        vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), (float)up1.x, (float)up1.y, (float)up1.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .light(240, 240)
                .normal(0, 0, 1);

        vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), (float)down1.x, (float)down1.y, (float)down1.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .light(240, 240)
                .normal(0, 0, 1);

        vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), (float)down2.x, (float)down2.y, (float)down2.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .light(240, 240)
                .normal(0, 0, 1);

        vertexConsumer.vertex(poseStack.peek().getPositionMatrix(), (float)up2.x, (float)up2.y, (float)up2.z)
                .color(220, 20, 60, (int)(alpha * 255))
                .light(240, 240)
                .normal(0, 0, 1);
    }

    public static void renderLine(MatrixStack poseStack, VertexConsumerProvider bufferSource, Vec3d start, Vec3d end, float a, RenderLayer renderType, float radius) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);

        int segmentCount = 16; // 圆柱横向细分数

        for (int i = 0; i < segmentCount; i++) {
            double angle1 = (2 * Math.PI * i) / segmentCount;
            double angle2 = (2 * Math.PI * (i + 1)) / segmentCount;

            double x1 = Math.cos(angle1) * radius;
            double z1 = Math.sin(angle1) * radius;
            double x2 = Math.cos(angle2) * radius;
            double z2 = Math.sin(angle2) * radius;

            Vec3d up1 = start.add(x1, 0, z1);
            Vec3d up2 = start.add(x2, 0, z2);
            Vec3d down1 = end.add(x1, 0, z1);
            Vec3d down2 = end.add(x2, 0, z2);


            addSquare(vertexConsumer, poseStack, up1, up2, down1, down2, a);
        }
    }
}

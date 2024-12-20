package com.moonfabric;

import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.common.Blood.blood_stones;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.NotNull;
public class Sword {
    public Sword(@NotNull MatrixStack matrices,
                 @NotNull VertexConsumerProvider vertexConsumers,
                 int light,
                 @NotNull Entity entity) {
        MoonPost.renderEffectForNextTick(MoonFabricModClient.POST);
        if (entity instanceof LivingEntity living) {
            if (HasCurio.has(init.blood_stones, living)) {
                TrinketsApi.getTrinketComponent(living).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (stack.get(Data.CUSTOM_DATA) != null) {
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 0) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 20));
                                matrices.translate(0, 0.07, 0.7 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.17f);
                                matrices.pop();
                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 1) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 22));
                                matrices.translate(0, 0, 0.5f * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.14f);
                                matrices.pop();
                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 2) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 10));
                                matrices.translate(0, 0.2, 0.45 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.1f);
                                matrices.pop();
                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 3) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 21));
                                matrices.translate(0, 0.22, 0.23 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.075f);
                                matrices.pop();
                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 4) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 8));
                                matrices.translate(0, 0.25, 0.55 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.12f);
                                matrices.pop();


                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 5) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 6));
                                matrices.translate(0, -0.05, 0.58 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.1f);
                                matrices.pop();
                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 6) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 15));
                                matrices.translate(0, 0.11, 0.6 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.15f);
                                matrices.pop();
                            }
                            if (stack.get(Data.CUSTOM_DATA).getInt(blood_stones.MaxSword) > 7) {
                                matrices.push();
                                matrices.multiply(RotationAxis.NEGATIVE_Y.rotation((float) living.age / 20));
                                matrices.translate(0, 0.11, 0.4 * 1.5);
                                renderSphere1(matrices, vertexConsumers, light, 0.22f);
                                matrices.pop();
                            }
                        }

                    });
                });
            }
        }
    }
    public void renderSphere1(@NotNull MatrixStack matrices, @NotNull VertexConsumerProvider vertexConsumers, int light, float s) {
        {
            float radius = s; // 球体的半径
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.getBlood());
            for (int i = 0; i < stacks; ++i) {
                float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
                float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

                for (int j = 0; j < slices; ++j) {
                    float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                    float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                    float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                    float y0 = radius * (float) Math.cos(phi0);
                    float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                    float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                    float y1 = radius * (float) Math.cos(phi0);
                    float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                    float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                    float y2 = radius * (float) Math.cos(phi1);
                    float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                    float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                    float y3 = radius * (float) Math.cos(phi1);
                    float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                }
            }
        }
        {
            float radius = s*1.25f; // 球体的半径
            int stacks = 20; // 垂直方向的分割数
            int slices = 20; // 水平方向的分割数
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(MRender.getBloodOutLine());
            for (int i = 0; i < stacks; ++i) {
                float phi0 = (float) Math.PI * ((i + 0) / (float) stacks);
                float phi1 = (float) Math.PI * ((i + 1) / (float) stacks);

                for (int j = 0; j < slices; ++j) {
                    float theta0 = (float) (2 * Math.PI) * ((j + 0) / (float) slices);
                    float theta1 = (float) (2 * Math.PI) * ((j + 1) / (float) slices);

                    float x0 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta0);
                    float y0 = radius * (float) Math.cos(phi0);
                    float z0 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta0);

                    float x1 = radius * (float) Math.sin(phi0) * (float) Math.cos(theta1);
                    float y1 = radius * (float) Math.cos(phi0);
                    float z1 = radius * (float) Math.sin(phi0) * (float) Math.sin(theta1);

                    float x2 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta1);
                    float y2 = radius * (float) Math.cos(phi1);
                    float z2 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta1);

                    float x3 = radius * (float) Math.sin(phi1) * (float) Math.cos(theta0);
                    float y3 = radius * (float) Math.cos(phi1);
                    float z3 = radius * (float) Math.sin(phi1) * (float) Math.sin(theta0);

                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x0, y0, z0).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x1, y1, z1).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x2, y2, z2).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                    vertexConsumer.vertex(matrices.peek().getPositionMatrix(), x3, y3, z3).color(1.0f, 1.0f, 1.0f, 1.0f).overlay(OverlayTexture.DEFAULT_UV).light(0, 0).texture(light, light).normal(1, 0, 0);
                }
            }
        }
    }
}

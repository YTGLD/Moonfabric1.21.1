package com.moonfabric.MODEL;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class FeatureRenderer <T extends LivingEntity, M extends EntityModel<T>> extends net.minecraft.client.render.entity.feature.FeatureRenderer<T, M> {

    public FeatureRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T abstractClientPlayerEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (abstractClientPlayerEntity instanceof PlayerEntity player) {
            if (hasCurio.has(init.nanocube, player)) {
                {
                    matrices.push();
                    matrices.scale(0.4f, 0.4f, 0.4f);
                    matrices.translate(0, -2.46, 0);
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180f - (float) abstractClientPlayerEntity.age));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180f - (float) abstractClientPlayerEntity.age));
                    ItemStack ss = new ItemStack(init.thedoomeye_ui1.asItem());
                    MinecraftClient.getInstance().getItemRenderer().renderItem(ss, ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, abstractClientPlayerEntity.getEntityWorld(), 0);
                    matrices.pop();
                }

                {

                    matrices.push();
                    matrices.scale(1, 1, 1);
                    matrices.translate(0, -1, 0);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f + (float) abstractClientPlayerEntity.age*3));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180f + (float) abstractClientPlayerEntity.age*3));
                    ItemStack ss = new ItemStack(init.thedoomeye_ui.asItem());
                    MinecraftClient.getInstance().getItemRenderer().renderItem(ss, ModelTransformationMode.GROUND, 255, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, abstractClientPlayerEntity.getEntityWorld(), 0);
                    matrices.pop();


                }
            }
        }
    }
}

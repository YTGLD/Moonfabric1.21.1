package com.moonfabric.MODEL;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;

public class painRenderer  <T extends LivingEntity, M extends EntityModel<T>> extends net.minecraft.client.render.entity.feature.FeatureRenderer<T, M> {
    public final  EntityModel<?> model ;
    public painRenderer(FeatureRendererContext<T, M> context,EntityModel<?> mod) {
        super(context);
        model=mod;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T abstractClientPlayerEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        if (hasCurio.has(init.pain_box,abstractClientPlayerEntity)){
           if (model instanceof PlayerEntityModel playerEntityModel) {
               if (abstractClientPlayerEntity instanceof AbstractClientPlayerEntity abstractClientPlayerEntity1) {
                   matrices.push();
                   TrinketRenderer.translateToChest(matrices, playerEntityModel, abstractClientPlayerEntity1);

                   matrices.multiply(RotationAxis.NEGATIVE_X.rotationDegrees(180));
                   matrices.translate(0,0.12,-0.33);
                   ItemStack ss = new ItemStack(init.pain_box_ui.asItem());

                   MinecraftClient.getInstance().getItemRenderer().renderItem(ss, ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, abstractClientPlayerEntity.getEntityWorld(), 0);

                   matrices.pop();
               }
           }
        }
    }
}

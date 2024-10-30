package com.moonfabric.test;

import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface Render extends TrinketRenderer {
    @Override
    default void render(final ItemStack stack,
                       SlotReference slotReference,
                       EntityModel<? extends LivingEntity> contextModel,
                       MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers,
                       int light,
                       LivingEntity entity,
                       float limbAngle, float limbDistance,
                       float tickDelta, float animationProgress,
                       float headYaw, float headPitch)
    {
        AbstractClientPlayerEntity player;
        if (entity instanceof AbstractClientPlayerEntity) {
            player = (AbstractClientPlayerEntity)entity;
        } else {
            return;
        }
        align(player, (PlayerEntityModel)contextModel, matrices, headYaw, headPitch);
        ItemStack ss = new ItemStack(init.block_cube.asItem());
        MinecraftClient.getInstance().getItemRenderer().renderItem(ss, ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getEntityWorld(), 0);

    }

    @Environment(EnvType.CLIENT)
    void align(AbstractClientPlayerEntity abstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel, MatrixStack matrixStack, float x, float z);
}
package com.moonfabric.test.BloodOrb;

import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BloodOrbR implements BloodOrbChest {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {
        TrinketRenderer.translateToChest(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity);

        int a = abstractClientPlayerEntity.age;
        float s = (float) Math.sin(a);
        matrixStack.translate(0.1, -0.375 , (s / 25));
        matrixStack.scale(1.2f,1.2f,1.2f);

    }
}


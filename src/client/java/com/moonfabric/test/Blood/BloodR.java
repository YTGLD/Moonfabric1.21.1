package com.moonfabric.test.Blood;

import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class BloodR implements RenderBlood {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {


        TrinketRenderer.translateToFace(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity,headYaw,headPitch);

        matrixStack.translate(0.0D, 0.1, 0.1);
        matrixStack.scale(1.6f,1.6f,1.6f);
    }
}


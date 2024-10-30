package com.moonfabric.test.cursecandle;

import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class cursecandleR implements cursecandleRender {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {


        TrinketRenderer.translateToChest(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity);
        matrixStack.translate(-0.2, 0.4, 0.05);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(135));
        matrixStack.scale(0.775f,1,0.775f);

    }
}


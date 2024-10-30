package com.moonfabric.test.Black;

import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class BlockHandR implements RenderChest {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {


        TrinketRenderer.translateToChest(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity);
        matrixStack.translate(0.2, 0.25, 0.2);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(22));
        matrixStack.scale(1.2f,1.2f,1.2f);

    }
}

package com.moonfabric.test.ORB;

import com.moonfabric.item.common.Mise.blackorb;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class blackorbR implements Renderblackorb {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {


        TrinketRenderer.translateToChest(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(blackorb.anInt));
        matrixStack.translate(0.0D, -0.7, 0.8);

    }
}

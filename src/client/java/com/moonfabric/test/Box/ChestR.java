package com.moonfabric.test.Box;

import com.moonfabric.item.common.CurseOrDoom.twistedstone;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class ChestR implements RenderChest {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {


        TrinketRenderer.translateToChest(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(twistedstone.anInt));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
        matrixStack.translate(0.0D, 0.6, -0.03d);
        matrixStack.scale(1,1,1.33f);

    }
}

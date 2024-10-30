package com.moonfabric.test.o;

import com.moonfabric.item.Ms.origincube;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

public class oR  implements oRender {
    @Override
    public void align(AbstractClientPlayerEntity abstractClientPlayerEntity,
                      PlayerEntityModel<AbstractClientPlayerEntity> playerEntityPlayerEntityModel,
                      MatrixStack matrixStack, float headYaw, float headPitch)
    {


        TrinketRenderer.translateToChest(matrixStack, playerEntityPlayerEntityModel, abstractClientPlayerEntity);
        matrixStack.translate(0.0D, -1.35, 0.1);
        matrixStack.scale(0.5f,0.5f,0.5f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-origincube.anInt));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(origincube.anInt / 1.3f));


    }
}

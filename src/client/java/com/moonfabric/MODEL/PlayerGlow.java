package com.moonfabric.MODEL;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class PlayerGlow <T extends LivingEntity, M extends EntityModel<T>> extends net.minecraft.client.render.entity.feature.FeatureRenderer<T, M> {
    private final Identifier texture;

    public PlayerGlow(LivingEntityRenderer<T, M> context, Identifier texture) {
        super(context);
        this.texture = texture;

    }


    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T wardenEntity, float f, float g, float h, float j, float k, float l) {
        if (!wardenEntity.isInvisible()) {
            if (hasCurio.has(init.pain_candle,wardenEntity)) {
                if (wardenEntity.getHealth() >= wardenEntity.getMaxHealth()) {
                    VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(this.texture));
                    int n = ColorHelper.Argb.getArgb(255, 255, 255, 255);
                    this.getContextModel().render(matrixStack, vertexConsumer, 240, LivingEntityRenderer.getOverlay(wardenEntity, 0.0F), n);
                }
            }
        }
    }

}


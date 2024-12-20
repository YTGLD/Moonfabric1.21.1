package com.moonfabric.EntiyMl.ytgld;

import com.moonfabric.Entity.ytgld;
import com.moonfabric.MoonFabricMod;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class YtgldEntityRenderer extends MobEntityRenderer<ytgld, YtgldEntityModel<ytgld>> {
    private static final Identifier TEXTURE = Identifier.of(MoonFabricMod.MODID, "textures/entity/ytgld.png");


    public YtgldEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new YtgldEntityModel<>(context.getPart(EntityModelLayers.WARDEN)), 0.9F);
        this.addFeature(new YtgldFeatureRenderer<>(this, TEXTURE, (warden, tickDelta, animationProgress) -> {
            return warden.getTendrilPitch(tickDelta);
        }, YtgldEntityModel::getTendrils));
    }

    @Override
    protected void scale(ytgld entity, MatrixStack matrices, float amount) {
        matrices.scale(1.35f, 1.35f, 1.35f);
    }

    @Override
    public void render(ytgld livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }


    public Identifier getTexture(ytgld wardenEntity) {
        return TEXTURE;
    }
}


package com.moonfabric.EntiyMl.nig;

import com.moonfabric.Entity.cell_giant;
import com.moonfabric.Entity.nightmare_giant;
import com.moonfabric.MoonFabricMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class NigEntityRenderer extends MobEntityRenderer<nightmare_giant, NigEntityModel<nightmare_giant>> {
    private static final Identifier TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/nightmare_giant.png");
    private static final Identifier PULSATING_SPOTS_1_TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/nig_boot.png");
    private static final Identifier PULSATING_SPOTS_2_TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/nig_boot_2.png");

    public NigEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new NigEntityModel<>(context.getPart(EntityModelLayers.WARDEN)), 0.9F);
        this.addFeature(new NigFeatureRenderer<>(this, PULSATING_SPOTS_1_TEXTURE, (warden, tickDelta, animationProgress) -> {
            return Math.max(0.0F, MathHelper.cos(animationProgress * 0.045F) * 0.25F);
        }, NigEntityModel::getBodyHeadAndLimbs));
        this.addFeature(new NigFeatureRenderer<>(this, PULSATING_SPOTS_2_TEXTURE, (warden, tickDelta, animationProgress) -> {
            return Math.max(0.0F, MathHelper.cos(animationProgress * 0.045F + 3.1415927F) * 0.25F);
        }, NigEntityModel::getBodyHeadAndLimbs));
        this.addFeature(new NigFeatureRenderer<>(this, TEXTURE, (warden, tickDelta, animationProgress) -> {
            return warden.getTendrilPitch(tickDelta);
        }, NigEntityModel::getTendrils));
    }

    public Identifier getTexture(nightmare_giant wardenEntity) {
        return TEXTURE;
    }
}


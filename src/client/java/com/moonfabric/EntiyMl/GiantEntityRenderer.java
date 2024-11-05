package com.moonfabric.EntiyMl;

import com.moonfabric.Entity.cell_giant;
import com.moonfabric.MoonFabricMod;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GiantEntityRenderer  extends MobEntityRenderer<cell_giant, GiantEntityModel<cell_giant>> {
    private static final Identifier TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/cell_giant.png");
    private static final Identifier HEART_TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/hearth.png");
    private static final Identifier PULSATING_SPOTS_1_TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/cell_giant_spots_1.png");
    private static final Identifier PULSATING_SPOTS_2_TEXTURE = Identifier.of(MoonFabricMod.MODID,"textures/entity/cell_giant_spots_2.png");

    public GiantEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GiantEntityModel<>(context.getPart(EntityModelLayers.WARDEN)), 0.9F);
        this.addFeature(new GiantFeatureRenderer<>(this, PULSATING_SPOTS_1_TEXTURE, (warden, tickDelta, animationProgress) -> {
            return Math.max(0.0F, MathHelper.cos(animationProgress * 0.045F) * 0.25F);
        }, GiantEntityModel::getBodyHeadAndLimbs));
        this.addFeature(new GiantFeatureRenderer<>(this, PULSATING_SPOTS_2_TEXTURE, (warden, tickDelta, animationProgress) -> {
            return Math.max(0.0F, MathHelper.cos(animationProgress * 0.045F + 3.1415927F) * 0.25F);
        }, GiantEntityModel::getBodyHeadAndLimbs));
        this.addFeature(new GiantFeatureRenderer<>(this, TEXTURE, (warden, tickDelta, animationProgress) -> {
            return warden.getTendrilPitch(tickDelta);
        }, GiantEntityModel::getTendrils));
        this.addFeature(new GiantFeatureRenderer<>(this, HEART_TEXTURE, (warden, tickDelta, animationProgress) -> {
            return warden.getHeartPitch(tickDelta);
        }, GiantEntityModel::getBody));
    }

    public Identifier getTexture(cell_giant wardenEntity) {
        return TEXTURE;
    }
}


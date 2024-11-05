package com.moonfabric.EntiyMl;

import net.minecraft.client.render.entity.BipedEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.Identifier;

public class ZombieBaseEntityRenderer<T extends TameableEntity, M extends ZombieEntityModel<T>> extends BipedEntityRenderer<T, M> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/zombie/zombie.png");

    protected ZombieBaseEntityRenderer(EntityRendererFactory.Context ctx, M bodyModel, M legsArmorModel, M bodyArmorModel) {
        super(ctx, bodyModel, 0.5F);
        this.addFeature(new ArmorFeatureRenderer(this, legsArmorModel, bodyArmorModel, ctx.getModelManager()));
    }

    public Identifier getTexture(TameableEntity zombieEntity) {
        return TEXTURE;
    }

    protected boolean isShaking(T zombieEntity) {
        return super.isShaking(zombieEntity);
    }
}

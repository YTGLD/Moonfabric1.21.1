package com.moonfabric.EntiyMl;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.passive.TameableEntity;

public class ZombieEntityRenderer  extends ZombieBaseEntityRenderer<TameableEntity, ZombieEntityModel<TameableEntity>> {
    public ZombieEntityRenderer(EntityRendererFactory.Context context) {
        this(context, EntityModelLayers.ZOMBIE, EntityModelLayers.ZOMBIE_INNER_ARMOR, EntityModelLayers.ZOMBIE_OUTER_ARMOR);
    }

    public ZombieEntityRenderer(EntityRendererFactory.Context ctx, EntityModelLayer layer, EntityModelLayer legsArmorLayer, EntityModelLayer bodyArmorLayer) {
        super(ctx, new ZombieEntityModel<>(ctx.getPart(layer)), new ZombieEntityModel<>(ctx.getPart(legsArmorLayer)), new ZombieEntityModel<>(ctx.getPart(bodyArmorLayer)));
    }
}


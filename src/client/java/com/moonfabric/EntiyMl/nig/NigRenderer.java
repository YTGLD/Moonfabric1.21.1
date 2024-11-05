package com.moonfabric.EntiyMl.nig;

import com.moonfabric.Entity.line;
import com.moonfabric.Entity.nig_test;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class NigRenderer <T extends nig_test> extends EntityRenderer<T> {
    public NigRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(T entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        new CircleCubeBoom(matrices,vertexConsumers,240,entity);

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(T entity) {
        return Identifier.of("moonfabric","textures/entity/flysword.png");
    }
}

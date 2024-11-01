package com.moonfabric.EntiyMl;

import com.moonfabric.Entity.line;
import com.moonfabric.Handler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class LineRender <T extends line> extends EntityRenderer<T> {
    public LineRender(EntityRendererFactory.Context context) {
        super(context);
    }

    public void render(T persistentProjectileEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider
            vertexConsumerProvider, int i) {
        setT(matrixStack,persistentProjectileEntity,vertexConsumerProvider);
        super.render(persistentProjectileEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
    private void setT(MatrixStack matrices,
                      T entity,
                      VertexConsumerProvider vertexConsumers){

        matrices.push();
        for (int i = 1; i < entity.getTrailPositions().size(); i++){
            Vec3d prevPos = entity.getTrailPositions().get(i - 1);
            Vec3d currPos = entity.getTrailPositions().get(i);
            Vec3d adjustedPrevPos = new Vec3d(prevPos.x - entity.getX(), prevPos.y - entity.getY(), prevPos.z - entity.getZ());
            Vec3d adjustedCurrPos = new Vec3d(currPos.x - entity.getX(), currPos.y - entity.getY(), currPos.z - entity.getZ());

            float alpha = (float)(i) / (float)(entity.getTrailPositions().size());

            Handler.renderLine(matrices, vertexConsumers, adjustedPrevPos, adjustedCurrPos, alpha, RenderLayer.getLightning(),0.05f);

        }
        matrices.pop();

    }
    @Override
    public Identifier getTexture(T entity) {
        return Identifier.of("moonfabric","textures/entity/flysword.png");
    }


}


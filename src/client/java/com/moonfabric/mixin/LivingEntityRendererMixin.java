package com.moonfabric.mixin;

import com.moonfabric.MODEL.PlayerGlow;
import com.moonfabric.MODEL.painRenderer;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;



@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements FeatureRendererContext<T, M> {

    @Shadow protected abstract boolean addFeature(FeatureRenderer<T, M> feature);

    protected LivingEntityRendererMixin(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    public void init(EntityRendererFactory.Context ctx, EntityModel<?> model, float shadowRadius, CallbackInfo info) {
        addFeature((FeatureRenderer<T, M>) new com.moonfabric.MODEL.FeatureRenderer<>((LivingEntityRenderer<?, ?>) (Object) this));
        addFeature((FeatureRenderer<T, M>)new PlayerGlow<>((LivingEntityRenderer<?, ?>) (Object) this,Identifier.of("moonfabric", "textures/entity/pain.png")));
        addFeature((FeatureRenderer<T, M>)new painRenderer<>((LivingEntityRenderer<?, ?>) (Object) this,model));


    }

}

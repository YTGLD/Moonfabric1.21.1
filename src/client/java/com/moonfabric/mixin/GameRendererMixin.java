package com.moonfabric.mixin;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow @Final private Camera camera;

    @Shadow private @Nullable PostEffectProcessor blurPostProcessor;

    @Inject(at = @At("RETURN"), method = "render")
    public void init(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        if (camera.getFocusedEntity() instanceof PlayerEntity entity) {
            if (HasCurio.has(init.nightmare_base_black_eye, entity)) {
                float fs = 1;
                if (this.blurPostProcessor != null) {
                    this.blurPostProcessor.setUniforms("Radius", fs);
                    this.blurPostProcessor.render(tickCounter.getLastDuration());
                }
            }
        }
    }
}

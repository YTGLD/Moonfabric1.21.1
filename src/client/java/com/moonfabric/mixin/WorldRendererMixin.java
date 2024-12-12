package com.moonfabric.mixin;

import com.moonfabric.MoonPost;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow @Final private MinecraftClient client;

    @Inject(method = "loadEntityOutlinePostProcessor",
            at = @At("TAIL"))
    private void initOutline(CallbackInfo ci) {
        MoonPost.onInitializeOutline(client);
    }
    @Inject(method = "onResized",
            at = @At("TAIL"))
    private void resize(int x, int y, CallbackInfo ci) {
        MoonPost.resize(x, y);
    }


    @Inject(method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/BufferBuilderStorage;getEntityVertexConsumers()Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;",
                    shift = At.Shift.BEFORE
            ))
    private void renderLevel1(RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci) {
        MoonPost.clearAndBindWrite(this.client.getFramebuffer());
    }

    @Inject(method = "render",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/BufferBuilderStorage;getOutlineVertexConsumers()Lnet/minecraft/client/render/OutlineVertexConsumerProvider;",
                    shift = At.Shift.BEFORE
            ))
    private void renderLevel2(RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci) {
        MoonPost.processEffects(this.client.getFramebuffer());
    }

    @Inject(method = "render",
            at = @At(
                    value = "TAIL"
            ))
    private void renderLevel3(RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci) {
        MoonPost.blitEffects(client);
    }
}

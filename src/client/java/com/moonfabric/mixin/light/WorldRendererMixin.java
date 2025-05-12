package com.moonfabric.mixin.light;

import com.moonfabric.MoonPost;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.LightType;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(method = "getLightmapCoordinates(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/util/math/BlockPos;)I",
            at = @At(
                    value = "TAIL"
            ), cancellable = true)
    private static void getLightmapCoordinates(BlockRenderView world, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if (!world.getBlockState(pos).isSolidRender(level, pos) && LambDynLights.get().config.getDynamicLightsMode().isEnabled())
            cir.setReturnValue(LambDynLights.get().getLightmapWithDynamicLight(level, pos, cir.getReturnValue()));
    }

}

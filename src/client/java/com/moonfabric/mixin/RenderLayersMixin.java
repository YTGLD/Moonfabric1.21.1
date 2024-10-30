package com.moonfabric.mixin;

import com.moonfabric.init.init;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public abstract class RenderLayersMixin {

    @Inject(method = "getItemLayer", at = @At(value = "RETURN"), cancellable = true)
    private static void mf$RenderLayers(ItemStack stack, boolean direct, CallbackInfoReturnable<RenderLayer> cir) {
        if (stack.isOf(init.origincube)){
            cir.setReturnValue(RenderLayer.getEndGateway());
        }
    }
}

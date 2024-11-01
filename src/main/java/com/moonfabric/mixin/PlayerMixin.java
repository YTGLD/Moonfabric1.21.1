package com.moonfabric.mixin;

import com.moonfabric.init.AttReg;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerMixin {
    @Inject(method = "createPlayerAttributes", at = @At(value = "RETURN"), cancellable = true)
    private static void createPlayerAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir){
        cir.setReturnValue(cir.getReturnValue().add(AttReg.heal,1));
    }
}

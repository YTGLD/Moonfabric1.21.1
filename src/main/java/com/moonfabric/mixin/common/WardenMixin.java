package com.moonfabric.mixin.common;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin( WardenEntity.class)
public class WardenMixin {
    @Inject(method = "isValidTarget", at = @At(value = "RETURN"), cancellable = true)
    private void mf$modifyAppliedDamage(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        WardenEntity block = (WardenEntity) (Object) this;

        if (entity instanceof PlayerEntity player){

            if (hasCurio.has(init.nanofruit, player)){

                cir.setReturnValue(false);
            }
        }

    }

}

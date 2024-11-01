package com.moonfabric.mixin.TickMixin;

import com.moonfabric.Ievent.IHurtSizeEvent;
import com.moonfabric.Ievent.IeventAttack;
import com.moonfabric.item.common.Blood.blood_amout;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(LivingEntity.class)
public class LivingEvent {
    @Inject(method = "modifyAppliedDamage", at = @At(value = "RETURN"), cancellable = true)
    private void mf$modifyAppliedDamage_m(DamageSource source, float amount, CallbackInfoReturnable<Float> cir){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        blood_amout.Hurt(livingEntity,source);




        TrinketsApi.getTrinketComponent(livingEntity).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, itemStack) -> {
                float size= IHurtSizeEvent.ON_HURT.invoker().hurt(livingEntity,source,cir.getReturnValue(),itemStack);
                cir.setReturnValue(size);
            });
        });
        if (source != null && source.getAttacker() instanceof LivingEntity living){
            TrinketsApi.getTrinketComponent(living).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, itemStack) -> {
                    float size= IeventAttack.ON_HURT.invoker().hurt(living,source,cir.getReturnValue(),itemStack);
                    cir.setReturnValue(size);
                });
            });
        }
    }
}

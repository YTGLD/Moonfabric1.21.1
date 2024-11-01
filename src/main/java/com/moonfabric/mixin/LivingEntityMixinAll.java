package com.moonfabric.mixin;

import com.moonfabric.init.AttReg;
import com.moonfabric.item.ectoplasm.ectoplasmapple;
import com.moonfabric.item.ectoplasm.ectoplasmhorseshoe;
import com.moonfabric.item.ectoplasm.ectoplasmshild;
import com.moonfabric.item.nightmare.nightmareanchor;
import com.moonfabric.item.nightmare.nightmarestone;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixinAll {
    @Inject(method = "modifyAppliedDamage", at = @At(value = "RETURN"), cancellable = true)
    private void mf$modifyAppliedDamage_m(DamageSource source, float amount, CallbackInfoReturnable<Float> cir){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ectoplasmapple.hurt(livingEntity, source);
        ectoplasmshild.hurt(livingEntity,cir);
        ectoplasmhorseshoe.ectoplasmhorseshoeHurt(livingEntity,source,cir);

        nightmarestone.hurt(livingEntity);
    }
    @Inject(method = "onDeath", at = @At(value = "RETURN"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        nightmareanchor.die(livingEntity);
    }
    @ModifyVariable(method = "heal", at = @At(value = "HEAD"), index = 1, argsOnly = true)
    public float heal(float amout) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof PlayerEntity player) {
            if (player.getAttributeInstance(AttReg.heal)!= null) {
                return (float) (amout * (player.getAttributeInstance(AttReg.heal).getValue()));
            }
        }
        return amout;
    }
}

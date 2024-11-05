package com.moonfabric.mixin;

import com.moonfabric.hasCurio;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.init;
import com.moonfabric.item.AllZombie;
import com.moonfabric.item.dna.dna;
import com.moonfabric.item.ectoplasm.ectoplasmapple;
import com.moonfabric.item.ectoplasm.ectoplasmhorseshoe;
import com.moonfabric.item.ectoplasm.ectoplasmshild;
import com.moonfabric.item.nightmare.nightmareanchor;
import com.moonfabric.item.nightmare.nightmarestone;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.Vec3d;
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
        AllZombie.evils(livingEntity, source,cir);
        dna.hurt(source,livingEntity,cir);
        nightmarestone.hurt(livingEntity,source);
    }
    @Inject(method = "onDeath", at = @At(value = "RETURN"), cancellable = true)
    private void mf$modifyAppliedDamage_m(DamageSource damageSource, CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        AllZombie.evil(livingEntity,damageSource);
        dna.dieD(livingEntity, damageSource);

    }
    @Inject(method = "canWalkOnFluid", at = @At(value = "RETURN"), cancellable = true)
    private void canWalkOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (hasCurio.has(init.ambush,livingEntity)){
            cir.setReturnValue(true);
        }

    }
    @Inject(method = "travel", at = @At(value = "HEAD"))
    private void onDeath(Vec3d movementInput, CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity.isTouchingWater()) {
            if (livingEntity instanceof PlayerEntity player) {
                if (player.getAttributeInstance(AttReg.swiming) != null) {
                    float speed = (float) player.getAttributeInstance(AttReg.swiming).getValue();
                    //1

                    livingEntity.updateVelocity((speed - 1)/2, movementInput);


                }
            }
        }

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

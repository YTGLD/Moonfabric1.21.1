package com.moonfabric.mixin.common;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class  PlayerEntityMixin {

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract float getAttackCooldownProgress(float baseTime);

    @Inject(method = "getHurtSound", at = @At("RETURN"), cancellable = true)
    private void getHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> cir){

        PlayerEntity player = (PlayerEntity) (Object) this;
        if (hasCurio.has(init.nanoheart, player)){
            cir.setReturnValue(SoundEvents.ENTITY_WARDEN_HURT);
        }
    }
    @Inject(method = "getDeathSound", at = @At("RETURN"), cancellable = true)
    private void getDeathSound(CallbackInfoReturnable<SoundEvent> cir){
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (hasCurio.has(init.nanoheart, player)){
            cir.setReturnValue(SoundEvents.ENTITY_WARDEN_DEATH);
        }
    }
    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void moon$getBlockBreakingSpeed(BlockState block, CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (hasCurio.has(init.firecottoncandy, player)) {
            if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                //在地狱或岩浆中：+50% 挖掘速度,速度,伤害,护甲,生命
                if (player.isInLava()){
                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                }else if (player.isTouchingWaterOrRain()) {
                    cir.setReturnValue(cir.getReturnValue() * 0.3f);
                }
            }
        }
        float goldheart_speed = cir.getReturnValue();
        if (hasCurio.has(init.goldheart, player)) {
            goldheart_speed = cir.getReturnValue() * 1.55f;
        }
        cir.setReturnValue(goldheart_speed);
    }
    @Inject(method = "getMovementSpeed", at = @At(value = "RETURN"), cancellable = true)
    private void mf$getMovementSpeed(CallbackInfoReturnable<Float> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof PlayerEntity player) {
            if (hasCurio.has(init.pain_stone, player)){
                cir.setReturnValue(cir.getReturnValue() * 1.35f);

            }
        }
        if (livingEntity instanceof PlayerEntity player){
            if (hasCurio.has(init.firecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                    //在地狱或岩浆中：+50% 挖掘速度,速度,伤害,护甲,生命
                    if (player.isInLava()){
                        cir.setReturnValue(cir.getReturnValue() * 1.5f);
                    }else if (player.isTouchingWaterOrRain()) {
                        cir.setReturnValue(cir.getReturnValue() * 0.3f);
                    }
                }
            }
            if (hasCurio.has(init.watercottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.watercottoncandy)) {
                    //在陆地减少50%伤害,移速并增加100%受到伤害
                    if (player.isOnGround()){
                        cir.setReturnValue(cir.getReturnValue() * 0.5f);
                    }
                }
            }
            //在阳光下：+35%速度，生命恢复，护甲
            if (hasCurio.has(init.woodcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.woodcottoncandy)) {
                    if (player.getWorld().isSkyVisible(player.getBlockPos())||player.getWorld().isDay()) {
                        cir.setReturnValue(cir.getReturnValue() * 1.35f);
                    }
                }
            }

            if (hasCurio.has(init.goldcottoncandy, player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    if (player.isOnFire()) {
                        cir.setReturnValue(cir.getReturnValue() * 0.5f);
                    }
                }
            }
            //着火时：-50%伤害和速度,以及增加50%的受到伤害

        }

    }
    @Inject(method = "getMovementSpeed", at = @At("RETURN"), cancellable = true)
    private void moon$isOnSoulSpeedBlock(CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (hasCurio.has(init.twistedsoul, player)) {
            cir.setReturnValue(cir.getReturnValue()*1.25f);
        }
    }

    @Inject(method = "isInvulnerableTo", at = @At("RETURN"), cancellable = true)
    private void moon$isInvulnerableTo(DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (hasCurio.has(init.firecottoncandy, player)){
            if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                if (damageSource.isOf(DamageTypes.IN_FIRE)
                        ||damageSource.isOf(DamageTypes.ON_FIRE)
                        ||damageSource.isOf(DamageTypes.LAVA))
                {
                    cir.setReturnValue(true);
                }
            }
        }


        if (hasCurio.has(init.watercottoncandy, player)){
            if (!player.getItemCooldownManager().isCoolingDown(init.watercottoncandy)) {
                if (damageSource.isOf(DamageTypes.MAGIC)
                        ||damageSource.isOf(DamageTypes.IN_FIRE)
                        ||damageSource.isOf(DamageTypes.ON_FIRE)
                        ||damageSource.isOf(DamageTypes.LAVA))
                {
                    cir.setReturnValue(true);
                }
            }
        }
        if (hasCurio.has(init.twistedstone, player)) {
            if (damageSource.isOf(DamageTypes.MAGIC)
                    ||damageSource.isOf(DamageTypes.FALL)
                    ||damageSource.isOf(DamageTypes.IN_FIRE)
                    ||damageSource.isOf(DamageTypes.ON_FIRE)
                    ||damageSource.isOf(DamageTypes.LAVA))
            {
                cir.setReturnValue(true);
            }
        }
    }
    @Inject(method = "attack", at = @At("HEAD"), cancellable = true)
    private void moon$attack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        boolean bl3 = getAttackCooldownProgress(0.5F) > 0.9
                && player.fallDistance > 0.0F
                && !player.isOnGround()
                && !player.isClimbing()
                && !player.isTouchingWater()
                && !player.hasStatusEffect(StatusEffects.BLINDNESS)
                && !player.hasVehicle()
                && target instanceof LivingEntity;

        if (bl3) {
            if (hasCurio.has(init.glodstone, player)){

                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0));
                player.heal(2);
                if (target instanceof LivingEntity livingEntity) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));
                }


            }


                //造成暴击伤害时有20%的概削弱目标
            if (hasCurio.has(init.goldcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    Random random = new Random();
                    int a = random.nextInt(100);
                    if (a < 20) {
                        if (target instanceof LivingEntity livingEntity) {
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));
                        }
                    }
                }
            }
            if (hasCurio.has(init.redstone, player)) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0));
            }
            if (hasCurio.has(init.greenstone, player)) {
               player.heal(1);
            }
            if (hasCurio.has(init.bluestone, player)) {
                if (target instanceof LivingEntity livingEntity) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 0));
                }
            }
        }
    }

    @Inject(method = "disableShield", at = @At("RETURN"), cancellable = true)
    private void moon$disableShield(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (hasCurio.has(init.curseshield, player)){
            player.getEntityWorld().createExplosion(player,player.getX(), player.getY(), player.getZ(), 4.0f, false, World.ExplosionSourceType.NONE);
            Vec3d vec3d = player.getPos();
            int r = 10;
            List<LivingEntity> list = player.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
            for (LivingEntity livingEntity : list) {
                if (livingEntity != player) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 300, 2));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 300, 2));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300, 2));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 2));
                }
            }
        }
    }
}
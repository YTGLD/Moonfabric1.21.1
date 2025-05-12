package com.moonfabric.mixin.TickMixin;

import com.moonfabric.Entity.flysword;
import com.moonfabric.MoonFabricMod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public class TickMixin {

    @Inject(method = "tick", at = @At(value = "RETURN"), cancellable = true)
    private void mf$tick(CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity.hasStatusEffect(MoonFabricMod.blood)){
            if  (livingEntity.age%20==1) {
                livingEntity.timeUntilRegen = 0;
                livingEntity.damage(livingEntity.getDamageSources().generic(), 1);
            }
        }


        Vec3d vec3d = livingEntity.getPos().add(0,0.75,0);
        int r = 16;
        List<flysword> list = livingEntity.getEntityWorld().getEntitiesByClass(flysword.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
        for (flysword item : list) {
            {
                if (item.isAlive()) {
                    Vec3d motion = vec3d.subtract(item.getPos().add(0.0D, (item.getHeight() / 2.0F), 0.0D));
                    if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1.0D)
                        motion = motion.normalize();
                    if (item.age > 15) {
                        item.setVelocity(motion.multiply(0.5f));
                    }
                }
            }
        }
    }
}

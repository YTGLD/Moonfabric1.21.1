package com.moonfabric.item.common.NaNo;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class nanoeye extends doom {


    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        Vec3d vec3d = entity.getPos();
        int r = 10;
        if (this.beamTicks < this.getWarmupTime()) {
            ++this.beamTicks;
        }
        if (!entity.getWorld().isClient) {
            List<LivingEntity> list = entity.getEntityWorld().getEntitiesByClass(LivingEntity.class, new Box(vec3d.x + r, vec3d.y + r, vec3d.z + r, vec3d.x - r, vec3d.y - r, vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
            for (LivingEntity getBeamTarget : list) {


                LivingEntity livingEntity = getBeamTarget;
                if (livingEntity != null) {

                    if (livingEntity .age % 140 == 0) {
                        double d = this.getBeamProgress(0.0F);
                        double e = livingEntity.getX() - entity.getX();
                        double f = livingEntity.getBodyY(0.5) - entity.getEyeY();
                        double g = livingEntity.getZ() - entity.getZ();
                        double h = Math.sqrt(e * e + f * f + g * g);
                        e /= h;
                        f /= h;
                        g /= h;
                        double j = entity.getRandom().nextDouble();

                        while (j < h) {
                            j += 1.8 - d + entity.getRandom().nextDouble() * (1.7 - d);

                            if (livingEntity != entity) {
                                if (entity.getWorld() instanceof ServerWorld serverWorld) {
                                    serverWorld.spawnParticles(MoonFabricMod.FOLLOW, entity.getX() + e * j, entity.getEyeY() + f * j, entity.getZ() + g * j, 3, 0.1, 0.1, 0.1, 0);
                                    livingEntity.getWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.NEUTRAL, 0.1F, 0.1F);


                                    livingEntity.damage(livingEntity.getDamageSources().magic(), 10);
                                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 140, 1));
                                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 140, 1));
                                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 140, 1));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public int getWarmupTime() {
        return 80;
    }
    private int beamTicks;
    public float getBeamProgress(float tickDelta) {
        return ((float)this.beamTicks + tickDelta) / (float)this.getWarmupTime();
    }
  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.nanoeye.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
}

package com.moonfabric.item.common.CurseOrDoom;

import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Optional;

public class twistedcube extends ItemTir {
    public static double anInt =0;

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity living) {
        anInt += 0.75;
        double range = 12;
        Vec3d srcVec = living.getEyePos();
        Vec3d lookVec = living.getRotationVec(1.0F);
        Vec3d destVec = srcVec.add(lookVec.getX() * range, lookVec.getY() * range, lookVec.getZ() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = living.getWorld().getOtherEntities(living, living.getBoundingBox().stretch(lookVec.getX() * range, lookVec.getY() * range, lookVec.getZ() * range).expand(var9, var9, var9));
        for (Entity entity : possibleList) {
            Box collisionBB = entity.getBoundingBox();
            Optional<Vec3d> interceptPos = collisionBB.raycast(srcVec, destVec);
            if (interceptPos.isPresent()) {
                if (entity instanceof LivingEntity liv) {
                    liv.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 50, 0));
                    liv.damage(liv.getDamageSources().magic(), 1.5F);
                    liv.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 2));
                    liv.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10, 0, false,false));
                }
            }
        }
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.cube.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
}

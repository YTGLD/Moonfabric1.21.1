package com.moonfabric.item.common;

import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class meye extends ItemTir {
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        Vec3d vec3d = entity.getPos();
        int r = 10;
        List<LivingEntity> list = entity.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
        for (LivingEntity livingEntity : list){
            if (livingEntity != entity){

                if (livingEntity.getType().isIn(EntityTypeTags.ZOMBIES)){
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20,0));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20,0));
                }

            }

        }

    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.meye").formatted(Formatting.GRAY));

    }
}

package com.moonfabric.item.ectoplasm;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.ectoplasm;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class ectoplasmapple extends ectoplasm {
    public static void hurt(LivingEntity me , DamageSource source){
        if (hasCurio.has(init.ectoplasmapple,me)){
            if (source.getSource() instanceof LivingEntity){
                Vec3d vec3d = me.getPos();
                int r = 10;
                List<LivingEntity> list = me.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
                for (LivingEntity livingEntity : list){
                    if (livingEntity != me){
                        livingEntity.damage(livingEntity.getDamageSources().magic(), livingEntity.getMaxHealth() / 50f);
                    }
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.ectoplasmapple.tool.string").formatted(Formatting.GRAY));
    }

}




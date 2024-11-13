package com.moonfabric.item.Ms.CottonCandy;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class firecottoncandy extends CottonCandy{
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        boolean a = true;
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(init.goldcottoncandy, player)){
                a = false;
            }
            if (HasCurio.has(init.woodcottoncandy, player)){
                a = false;
            }
            if (HasCurio.has(init.watercottoncandy, player)){
                a = false;
            }
            if (HasCurio.has(init.firecottoncandy, player)){
                a = false;
            }if (HasCurio.has(init.stonecottoncandy, player)){
                a = false;
            }

        }
        return a;
    }
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            Vec3d vec3d = player.getPos();
            int r = 8;
            List<LivingEntity> list = player.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
            for (LivingEntity livingEntity : list){
                if (livingEntity != player){
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20, 0,false,false));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 0,false,false));


                }

            }

        }
    }
  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.firecottoncandy.1").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.firecottoncandy.2").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.firecottoncandy.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.firecottoncandy.5").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.firecottoncandy.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));


    }
}

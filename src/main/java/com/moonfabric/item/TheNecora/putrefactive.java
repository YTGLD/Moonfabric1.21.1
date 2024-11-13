package com.moonfabric.item.TheNecora;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;

import java.util.List;

public class putrefactive extends TheNecoraIC {

    public static void eat(ItemStack stack, LivingEntity entity){
        if (HasCurio.has(init.putrefactive,entity)){
            if (stack.getUseAction() == UseAction.EAT){
                entity.heal(entity.getMaxHealth()/10);
                entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,200,0));
            }
        }
    }
   @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.putrefactive.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
    }
}



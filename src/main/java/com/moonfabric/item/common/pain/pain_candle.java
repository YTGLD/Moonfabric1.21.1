package com.moonfabric.item.common.pain;

import com.moonfabric.Ievent.IHurtSizeEvent;
import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.extend.ItemTir;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class pain_candle extends ItemTir {
    public static void  pain(){
        IHurtSizeEvent.ON_HURT.register((living, source, size, stack) -> {
            if (stack.isOf(init.pain_candle)&& hasCurio.has(init.pain_candle,living)){
               if (living.getHealth()>=living.getMaxHealth()){
                   return size*0.33f;
               }else {
                   return size*1.1f;
               }
            }
            return size;
        });
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_candle.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_candle.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));


    }
}

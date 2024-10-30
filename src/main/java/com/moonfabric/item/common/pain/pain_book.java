package com.moonfabric.item.common.pain;

import com.moonfabric.item.Ms.extend.ItemTir;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class pain_book extends ItemTir {
    /*
    你可以食用铜块和铜锭

    铜块：力量II，急迫II，生命恢复II（30秒）
    铜锭：速度II，抗性I（10秒）

    +33%吃东西的速度

     越吃越饿
     */

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_book.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_book.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_book.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_book.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_book.5").formatted(Formatting.GRAY));
    }

}

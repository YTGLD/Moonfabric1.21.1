package com.moonfabric.item.common.NaNo;

import com.moonfabric.item.Ms.extend.doom;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class nanocottoncandy extends doom {

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocottoncandy.1").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocottoncandy.2").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocottoncandy.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocottoncandy.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocottoncandy.5").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocottoncandy.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));


    }
}
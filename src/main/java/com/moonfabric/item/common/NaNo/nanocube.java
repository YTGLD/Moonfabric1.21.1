package com.moonfabric.item.common.NaNo;

import com.moonfabric.item.Ms.extend.doom;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class nanocube extends doom {
  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.nanocube.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }

}

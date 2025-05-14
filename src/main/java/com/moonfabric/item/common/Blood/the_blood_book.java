package com.moonfabric.item.common.Blood;

import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class the_blood_book extends TheNecoraIC {
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltipComponents, TooltipType type) {
        super.appendTooltip(stack, context, tooltipComponents, type);
       tooltipComponents.add(Text.translatable("item.the_blood_book.tool.string").formatted(Formatting.RED));
       tooltipComponents.add(Text.translatable("item.the_blood_book.tool.string.1").formatted(Formatting.RED));
       tooltipComponents.add(Text.translatable("item.the_blood_book.tool.string.3").formatted(Formatting.RED));
       tooltipComponents.add(Text.translatable("item.the_blood_book.tool.string.4").formatted(Formatting.RED));
       tooltipComponents.add(Text.literal(""));
       tooltipComponents.add(Text.translatable("item.the_blood_book.tool.string.5").formatted(Formatting.RED));
       tooltipComponents.add(Text.literal(""));
       tooltipComponents.add(Text.translatable("item.the_blood_book.tool.string.6").formatted(Formatting.RED));
       tooltipComponents.add(Text.literal(""));
    }

}

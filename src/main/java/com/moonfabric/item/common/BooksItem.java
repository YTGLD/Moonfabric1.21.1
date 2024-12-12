package com.moonfabric.item.common;

import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class BooksItem extends TheNecoraIC {
    public final String[] text;
    public BooksItem(String... strings){
        text = strings;
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        for (String string : text) {
            tooltip.add(Text.translatable(string).formatted(Formatting.RED));
        }
        tooltip.add(Text.translatable(""));
    }

}

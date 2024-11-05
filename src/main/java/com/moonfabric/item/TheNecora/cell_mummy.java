package com.moonfabric.item.TheNecora;

import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class cell_mummy extends TheNecoraIC {
    public static final String Mummy = " CellMummy";
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.cell_mummy.tool.string").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
}

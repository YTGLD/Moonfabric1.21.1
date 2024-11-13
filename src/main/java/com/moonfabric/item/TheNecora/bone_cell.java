package com.moonfabric.item.TheNecora;

import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class bone_cell extends TheNecoraIC {


   @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.bone_cell.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
    }
}





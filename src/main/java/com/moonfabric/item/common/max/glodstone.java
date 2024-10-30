package com.moonfabric.item.common.max;

import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class glodstone extends ItemTir {
    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.glodstone.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.glodstone.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.glodstone.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.glodstone.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.glodstone.5").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.glodstone.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));

    }
}

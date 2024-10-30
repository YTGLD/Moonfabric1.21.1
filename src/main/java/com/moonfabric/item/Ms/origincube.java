package com.moonfabric.item.Ms;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class origincube extends ItemEPIC {

    public static float anInt = 0;

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        anInt+= 1.55f;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.origincube.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
    }


    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onEquip(stack, slot, entity);
    }
}

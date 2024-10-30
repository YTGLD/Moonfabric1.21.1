package com.moonfabric.item.common.NaNo;

import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class nanofruit extends doom {
  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.nanofruit.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanofruit.2").formatted(Formatting.GRAY));

        tooltip.add(Text.translatable(""));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.hasStatusEffect(StatusEffects.GLOWING)) {
            entity.removeStatusEffect(StatusEffects.GLOWING);
        }
    }
}

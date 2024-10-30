package com.moonfabric.item.Ms.CottonCandy;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class goldcottoncandy extends CottonCandy {
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        boolean a = true;
        if (entity instanceof PlayerEntity player){
            if (hasCurio.has(init.goldcottoncandy, player)){
                a = false;
            }
            if (hasCurio.has(init.woodcottoncandy, player)){
                a = false;
            }
            if (hasCurio.has(init.watercottoncandy, player)){
                a = false;
            }
            if (hasCurio.has(init.firecottoncandy, player)){
                a = false;
            }if (hasCurio.has(init.stonecottoncandy, player)){
                a = false;
            }

        }
        return a;
    }
  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.goldcottoncandy.1").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.goldcottoncandy.2").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.goldcottoncandy.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.goldcottoncandy.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.goldcottoncandy.5").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.goldcottoncandy.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));


    }
}

package com.moonfabric.item.common;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.extend.ItemTir;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class greedcrystal extends ItemTir {
    public static int lvl (LivingEntity living){
        if( HasCurio.has(init.greedcrystal,living)){
            return 2;
        }else return 0;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.greedcrystal").formatted(Formatting.GRAY));

    }
}

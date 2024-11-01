package com.moonfabric.item.common;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.extend.ItemTir;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class mblock extends ItemTir {
    public static int Block(LivingEntity living){
        if( hasCurio.has(init.mblock,living)){
            return 1;
        }else return 0;
    }
    public static int loot(LivingEntity living){
        if( hasCurio.has(init.mblock,living)){
            return 1;
        }else return 0;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.mblock").formatted(Formatting.GRAY));

    }
}


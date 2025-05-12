package com.moonfabric.item.dna.med;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class masticatory extends TheNecoraIC {
    public  static void masticatory_(ItemStack stack,CallbackInfoReturnable<Integer> cir, LivingEntity me) {
        if (me instanceof PlayerEntity player){
            if (HasCurio.has(init.masticatory, player)){
                if (stack.getUseAction() == UseAction.EAT){
                    cir.setReturnValue(cir.getReturnValue() / 2);
                }
            }
        }

    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.masticatory.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.masticatory.tool.string.1").formatted(Formatting.RED));
    }



}


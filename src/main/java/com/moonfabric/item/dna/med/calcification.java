package com.moonfabric.item.dna.med;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class calcification extends TheNecoraIC {
    public static void calcification_(DamageSource source, LivingEntity me, CallbackInfoReturnable<Float> cir) {
        if (me instanceof PlayerEntity player){
            if (HasCurio.has(init.calcification, player)){
                cir.setReturnValue(cir.getReturnValue() * 0.89f);
            }
        }

    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.calcification.tool.string.1").formatted(Formatting.RED));
    }
}



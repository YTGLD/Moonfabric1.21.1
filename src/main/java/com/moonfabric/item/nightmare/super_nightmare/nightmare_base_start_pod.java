package com.moonfabric.item.nightmare.super_nightmare;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class nightmare_base_start_pod extends com.moonfabric.item.Ms.SNightmare{

    public static void damage(DamageSource source, LivingEntity entity, CallbackInfoReturnable<Float> cir) {
        if (entity instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_start_pod, player)) {
                cir.setReturnValue(cir.getReturnValue()*0.8f);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_start_pod.tool.string").formatted(Formatting.DARK_RED));
    }

}




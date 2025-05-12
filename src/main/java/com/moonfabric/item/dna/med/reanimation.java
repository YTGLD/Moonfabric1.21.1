package com.moonfabric.item.dna.med;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class reanimation extends TheNecoraIC {
    public  static void reanimation_(DamageSource source, LivingEntity living, CallbackInfoReturnable<Float> cir) {
        if (living instanceof PlayerEntity player){
            if (HasCurio.has(init.reanimation, player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.reanimation)) {
                    if (cir.getReturnValue() > player.getHealth()) {
                        player.heal(player.getMaxHealth() / 2);
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 600, 4));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600, 1));
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1));
                        player.getItemCooldownManager().set(init.reanimation, 3000);
                    }
                }
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.reanimation.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.reanimation.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.reanimation.tool.string.2").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.reanimation.tool.string.3").formatted(Formatting.RED));
    }
}


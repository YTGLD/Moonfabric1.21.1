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

public class nightmare_base_insight_insane extends com.moonfabric.item.Ms.SNightmare{

    public static void LivingDeathEvents(DamageSource  source, LivingEntity entity){
        if (source.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_insight_insane, player)) {
                player.heal(entity.getMaxHealth()/10);
                player.getItemCooldownManager().set(init.nightmare_base_insight_insane,200);
            }
        }
    }
    public static void damage(DamageSource  source , CallbackInfoReturnable<Float> cir){
        if (source.getAttacker() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_insight_insane, player)) {
                if (player.getItemCooldownManager().isCoolingDown(init.nightmare_base_insight_insane)){
                    cir.setReturnValue(cir.getReturnValue()*2.5f);
                    player.getItemCooldownManager().set(init.nightmare_base_insight_insane,0);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_insight_insane.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_insight_insane.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_insight_insane.tool.string.2").formatted(Formatting.DARK_RED));
    }

}




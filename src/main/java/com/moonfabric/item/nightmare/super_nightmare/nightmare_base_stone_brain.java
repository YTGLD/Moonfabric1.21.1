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

public class nightmare_base_stone_brain  extends com.moonfabric.item.Ms.SNightmare{


    public static void hurts(DamageSource source, LivingEntity entity, CallbackInfoReturnable<Float> cir){
        if (source.getSource() instanceof PlayerEntity player){
            if (HasCurio.has(init.nightmare_base_stone_brain, player)){
                if (player.getHealth()>= player.getMaxHealth()){
                    cir.setReturnValue(0f);
                }else {
                    cir.setReturnValue(cir.getReturnValue()*1.5f);
                }
            }
        }
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(init.nightmare_base_stone_brain, player)){
                if (!(player.getHealth() >= player.getMaxHealth())){
                    cir.setReturnValue(cir.getReturnValue()/2);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_stone_brain.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_stone_brain.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_stone_brain.tool.string.2").formatted(Formatting.DARK_RED));
    }

}


package com.moonfabric.item.nightmare.super_nightmare;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class nightmare_base_stone extends com.moonfabric.item.Ms.SNightmare{


    public static void LivingHurtEvent(DamageSource source, LivingEntity entity, CallbackInfoReturnable<Float> cir){
        if (entity instanceof PlayerEntity player ){
            if (HasCurio.has(init.nightmare_base_stone, player)){
                if (player.getHealth() >= player.getMaxHealth()){
                    cir.setReturnValue(cir.getReturnValue()*6);
                }
            }
        }
    }

    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity  instanceof PlayerEntity player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_stone.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.nightmare_base_stone.tool.string.1").formatted(Formatting.RED));

        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_stone_virus").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_stone_meet").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_stone_brain").formatted(Formatting.DARK_RED));
        tooltip.add(Text.literal(""));

        tooltip.add(Text.translatable("item.nightmareeye.tool.string.2").formatted(Formatting.DARK_RED));
    }

}


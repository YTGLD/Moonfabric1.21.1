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

public class polyphagia extends TheNecoraIC {
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.polyphagia.tool.string.1").formatted(Formatting.RED));
    }
    public static void necora(LivingEntity me , CallbackInfoReturnable<ItemStack> cir,ItemStack stack ) {
        if (me instanceof PlayerEntity player){
            if (HasCurio.has(init.polyphagia, player)){
                if (stack.getUseAction() == UseAction.EAT){
                    player.heal(player.getMaxHealth() / 15);
                }
            }
        }
    }
}


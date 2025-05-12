package com.moonfabric.item.nightmare.super_nightmare;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class nightmare_base_fool_bone extends com.moonfabric.item.Ms.SNightmare{


    public static void attLook(LivingEntity entity, DamageSource source, CallbackInfoReturnable<Float> cir){
        if (source.getAttacker() instanceof PlayerEntity player ){
            if (HasCurio.has(init.nightmare_base_fool_bone, player)) {
                if (entity instanceof MobEntity mob){
                    if (mob.getTarget()!=null &&mob.getTarget()==(player)){
                        if (MathHelper.nextInt(Random.create(),1,100)<=30){
                            mob.timeUntilRegen= 0;
                        }
                        cir.setReturnValue(cir.getReturnValue()*2f);
                    }
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_fool_bone.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_fool_bone.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_fool_bone.tool.string.2").formatted(Formatting.DARK_RED));
    }
}


package com.moonfabric.item.ectoplasm;


import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.ectoplasm;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class ectoplasmshild extends ectoplasm {

    public static final String size = "HurtSize";

    public static void hurt (LivingEntity me, CallbackInfoReturnable<Float> cit){
        if (HasCurio.has(init.ectoplasmshild,me)){
            TrinketsApi.getTrinketComponent(me).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, itemStack) -> {
                    if (itemStack.get(Data.CUSTOM_DATA)!= null){
                        if (itemStack.get(Data.CUSTOM_DATA).getInt(size)<=5) {
                            itemStack.get(Data.CUSTOM_DATA).putInt(size,itemStack.get(Data.CUSTOM_DATA).getInt(size)+1);
                        }else {
                            itemStack.get(Data.CUSTOM_DATA).putInt(size,0);
                            cit.setReturnValue(0f);
                        }
                    }
                });
            });
        }
    }
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 20, 0, false, false));

        if (stack.get(Data.CUSTOM_DATA) == null){
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.ectoplasmshild.tool.string").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.ectoplasmshild.tool.string.1").formatted(Formatting.GRAY));
    }

}

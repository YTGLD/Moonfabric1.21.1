package com.moonfabric.item.common.pain;

import com.google.common.collect.Multimap;
import com.moonfabric.Ievent.IHurtSizeEvent;
import com.moonfabric.hasCurio;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class pain_heart extends ItemTir {
    public static final String hurt="StringHurt";

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getHealth() >= entity.getMaxHealth()){
            if (stack.get(Data.CUSTOM_DATA) != null) {
                stack.get(Data.CUSTOM_DATA).remove(hurt);

            }
        }
    }

    public static void  pain(){
        IHurtSizeEvent.ON_HURT.register((living, source, size,stack) -> {
            if (stack.isOf(init.pain_heart)&& hasCurio.has(init.pain_heart,living)){
                if (stack.get(Data.CUSTOM_DATA) != null) {
                    stack.get(Data.CUSTOM_DATA).putInt(hurt, stack.get(Data.CUSTOM_DATA).getInt(hurt) + 1);
                    float get = (float) stack.get(Data.CUSTOM_DATA).getInt(hurt) / 33;
                   return size *(1 + get);
                }
            }
            return size;
        });
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),10, EntityAttributeModifier.Operation.ADD_VALUE));
        modifiers.put(AttReg.heal,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.33, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifiers;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_heart.1" ).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_heart.2" ).formatted(Formatting.GRAY));
        tooltip.add(Text.literal(""));

    }
}

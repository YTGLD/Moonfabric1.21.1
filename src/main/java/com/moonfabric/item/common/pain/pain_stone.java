package com.moonfabric.item.common.pain;

import com.google.common.collect.Multimap;
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

public class pain_stone  extends ItemTir {
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.GENERIC_JUMP_STRENGTH,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.42, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifiers.put(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifiers;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_stone.1" ).formatted(Formatting.GRAY));
        tooltip.add(Text.literal(""));

    }
}

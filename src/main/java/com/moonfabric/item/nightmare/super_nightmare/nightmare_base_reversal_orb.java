package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class nightmare_base_reversal_orb extends com.moonfabric.item.Ms.SNightmare{

    public static void LivingHealEvent(LivingEntity living, float a){
        if (living instanceof PlayerEntity player){
            if (HasCurio.has(init.nightmare_base_reversal_orb, player)){
                player.damage(player.getDamageSources().dryOut(),a);
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_reversal_orb.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_reversal_orb.tool.string.2").formatted(Formatting.DARK_RED));
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(AttReg.heal,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.42, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifiers;    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity  instanceof PlayerEntity player){
            if (!player.getItemCooldownManager().isCoolingDown(this)){
                player.setHealth(player.getMaxHealth());
                player.getItemCooldownManager().set(this,140);
            }
        }    }



}

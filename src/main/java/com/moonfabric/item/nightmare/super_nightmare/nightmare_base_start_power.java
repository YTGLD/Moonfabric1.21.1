package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class nightmare_base_start_power extends com.moonfabric.item.Ms.SNightmare{


    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().addTemporaryModifiers(gets(entity));
    }


    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().removeModifiers(gets(entity));
    }



    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> gets(LivingEntity slotContext) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext;
        List<Integer> integersHealth = new ArrayList<>();
        for (StatusEffectInstance effect : living.getStatusEffects()) {
            if (effect != null
                    && effect.getEffectType().value().isBeneficial()) {
                integersHealth.add(1);
            }
        }
        float att = 0;
        for (int i : integersHealth){
            att+=2;
        }
        att/=100;

        for (EntityAttribute attribute : Registries.ATTRIBUTE){
            linkedHashMultimap.put(Registries.ATTRIBUTE.getEntry(attribute), new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), att, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }

        return linkedHashMultimap;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.nightmare_base_start_power.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_start_power.tool.string.1").formatted(Formatting.DARK_RED));
    }

}



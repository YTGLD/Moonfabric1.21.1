package com.moonfabric.item.common.Blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.init.AttReg;
import com.moonfabric.item.Ms.TheNecoraIC;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class bloodorb extends TheNecoraIC {

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.4, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),10, EntityAttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(AttReg.heal,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),1.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}

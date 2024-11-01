package com.moonfabric.item.ectoplasm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.ectoplasm;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ectoplasmbattery extends ectoplasm {
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(slotIdentifier, 10, EntityAttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(slotIdentifier, 4, EntityAttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(slotIdentifier, 0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(slotIdentifier, 0.05, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}


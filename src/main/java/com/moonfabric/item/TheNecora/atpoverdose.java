package com.moonfabric.item.TheNecora;

import com.google.common.collect.Multimap;
import com.moonfabric.item.INecora;
import com.moonfabric.item.Ms.TheNecoraIC;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class atpoverdose extends TheNecoraIC implements INecora {
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        SlotAttributes.addSlotModifier(modifiers, "legs/belt", Identifier.of(String.valueOf(this.getOrCreateTranslationKey())), 2, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }

}

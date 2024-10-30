package com.moonfabric.item.common.pain;

import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class pain_box extends ItemTir {
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.GENERIC_MAX_HEALTH,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),-0.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        SlotAttributes.addSlotModifier(modifiers,"chest/back",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),1, EntityAttributeModifier.Operation.ADD_VALUE);
        SlotAttributes.addSlotModifier(modifiers,"legs/belt",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),2, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
    /*
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_box.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_box.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_box.3").formatted(Formatting.GRAY));

        tooltip.add(Text.translatable(""));
    }

     */
}

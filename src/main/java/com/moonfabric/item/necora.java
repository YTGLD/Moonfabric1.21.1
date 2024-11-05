package com.moonfabric.item;

import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.TheNecoraIC;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class necora extends ItemTir {
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.necora").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        SlotAttributes.addSlotModifier(modifiers,"legs/belt",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),2, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }

}

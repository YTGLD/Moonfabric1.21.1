package com.moonfabric.item.nightmare;

import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.nightmare;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class nightmarecharm extends nightmare {


    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        SlotAttributes.addSlotModifier(modifiers,"hand/ring",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),2, EntityAttributeModifier.Operation.ADD_VALUE);

        return modifiers;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmarecharm.tool.string").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.nightmarecharm.tool.string.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmarecharm.tool.string.2").formatted(Formatting.GRAY));

        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.7").formatted(Formatting.GRAY));
    }
}

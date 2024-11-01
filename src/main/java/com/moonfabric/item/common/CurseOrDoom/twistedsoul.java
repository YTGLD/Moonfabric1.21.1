package com.moonfabric.item.common.CurseOrDoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
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

public class twistedsoul extends ItemTir {


    public twistedsoul() {
        TrinketsApi.registerTrinket(this, this);
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),-0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.twistedsoul.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.twistedsoul.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
}
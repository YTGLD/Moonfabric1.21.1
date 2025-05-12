package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.nightmare;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class nightmare_base_insight_drug extends com.moonfabric.item.Ms.SNightmare{

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
        TrinketsApi.getTrinketComponent(living).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {
                if (!stack.isEmpty()&&stack.getItem() instanceof nightmare) {
                    integersHealth.add(1);
                }
            });
        });
        float health = 100;
        for (int ignored : integersHealth) {
            health-=8;
        }
        health/=100;
        linkedHashMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), health, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), health, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), health, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_insight_drug.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_insight_drug.tool.string.1").formatted(Formatting.DARK_RED));
    }

}



package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
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

public class nightmare_base_fool_soul extends com.moonfabric.item.Ms.SNightmare{

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().addTemporaryModifiers(gets(entity));
        entity .getAttributes().addTemporaryModifiers(getsHEAL(entity));      }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().removeModifiers(gets(entity));
        entity .getAttributes().removeModifiers(getsHEAL(entity));
    }


   
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> gets(LivingEntity slotContext) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> linkedHashMultimap = HashMultimap.create();
        List<Integer> integersHealth = new ArrayList<>();


        TrinketsApi.getTrinketComponent(slotContext).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {
                if (stack.isOf(init.nightmare_base_fool_soul)) {
                    if (stack.get(Data.CUSTOM_DATA) != null) {
                        if (!stack.isEmpty()&&stack.getItem() instanceof nightmare) {
                            integersHealth.add(1);
                        }
                    }
                }
            });
        });
        float health = 0;
        for (int ignored : integersHealth) {
            health+=6;
        }
        linkedHashMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), health, EntityAttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getsHEAL(LivingEntity slotContext) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> linkedHashMultimap = HashMultimap.create();
        LivingEntity living = slotContext;
        List<Integer> integersHealth = new ArrayList<>();
        TrinketsApi.getTrinketComponent(living).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {
                if (stack.isOf(init.nightmare_base_fool_soul)) {
                    if (stack.get(Data.CUSTOM_DATA) != null) {
                        if (!stack.isEmpty()&&stack.getItem() instanceof nightmare) {
                            integersHealth.add(1);
                        }
                    }
                }
            });
        });
        float health = 0;
        for (int ignored : integersHealth) {
            health++;
        }
        health /=100;
        health*=3f;
        linkedHashMultimap.put(AttReg.heal, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), health, EntityAttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_fool_soul.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_fool_soul.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_fool_soul.tool.string.2").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_fool_soul.tool.string.3").formatted(Formatting.DARK_RED));
    }


}


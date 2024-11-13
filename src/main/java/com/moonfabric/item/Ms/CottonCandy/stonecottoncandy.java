package com.moonfabric.item.Ms.CottonCandy;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.UUID;

public class stonecottoncandy extends CottonCandy{
    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().addTemporaryModifiers(this.getModifiers(entity));

        if (stack.get(Data.CUSTOM_DATA) == null) {
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().removeModifiers(this.getModifiers(entity));
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        boolean a = true;
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(init.goldcottoncandy, player)){
                a = false;
            }
            if (HasCurio.has(init.woodcottoncandy, player)){
                a = false;
            }
            if (HasCurio.has(init.watercottoncandy, player)){
                a = false;
            }
            if (HasCurio.has(init.firecottoncandy, player)){
                a = false;
            }if (HasCurio.has(init.stonecottoncandy, player)){
                a = false;
            }

        }
        return a;
    }
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(LivingEntity entity) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier>modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("2dc91382-bdf7-3364-b2ee-09532f57b948");
        modifierMultimap.put(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),999, EntityAttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.4, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.4, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_SPEED,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.35, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(AttReg.heal,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),-1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.stonecottoncandy.1").formatted(Formatting.GRAY));//
        tooltip.add(Text.translatable("moonfabric.tooltip.stonecottoncandy.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.stonecottoncandy.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.stonecottoncandy.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.stonecottoncandy.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.stonecottoncandy.5").formatted(Formatting.GRAY));


    }
}

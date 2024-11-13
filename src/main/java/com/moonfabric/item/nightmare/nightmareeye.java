package com.moonfabric.item.nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.nightmare;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.UUID;

public class nightmareeye extends nightmare {
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA)==null){
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
        entity.getAttributes().addTemporaryModifiers(un_un_pla(entity,stack));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().removeModifiers(un_un_pla(entity,stack));
    }


    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        SlotAttributes.addSlotModifier(modifiers,"hand/ring",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),2, EntityAttributeModifier.Operation.ADD_VALUE);

        return modifiers;
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> un_un_pla(LivingEntity player, ItemStack stack) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        UUID uuid = UUID.fromString("83f9fb4e-5c3f-3b02-a19a-70f2fa258dfa");


        float s = 0.25f;
        if (HasCurio.has(init.nightmarecharm, player)){
            s -= 0.15f;
        }

        if (HasCurio.has(init.nightmareanchor, player)){
            s -= 0.05f;
        }
        if (HasCurio.has(init.nightmarerotten, player)){
            float a = Registries.ATTRIBUTE.size();
            a /= 100;
            a *= 1.1F;
            s += a;
        }

        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), -s, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), -s, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), -s, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), -s, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), -s, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), -s, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.nightmareeye.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.nightmareeye.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.nightmareeye.tool.string.2").formatted(Formatting.RED));

    }

}

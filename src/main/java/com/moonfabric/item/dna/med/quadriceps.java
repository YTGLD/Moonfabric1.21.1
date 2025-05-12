package com.moonfabric.item.dna.med;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.item.Ms.TheNecoraIC;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class  quadriceps extends TheNecoraIC {

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (player.isSprinting()) {
                player.getAttributes().addTemporaryModifiers(aaa(player, stack));
            }else {
                player.getAttributes().removeModifiers(aaa(player, stack));
            }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            player.getAttributes().removeModifiers(aaa(player, stack));
        }
    }


    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier>  aaa(PlayerEntity player , ItemStack stack){
       Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()), 0.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, new EntityAttributeModifier(Identifier.of("base_attack_damage"+this.getTranslationKey()),3, EntityAttributeModifier.Operation.ADD_VALUE));
        return linkedHashMultimap;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.quadriceps.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.quadriceps.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.quadriceps.tool.string.2").formatted(Formatting.RED));
    }



}

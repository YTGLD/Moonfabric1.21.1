package com.moonfabric.item.common.Mise;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class whiteorb extends ItemTir {
    public static int anInt = 0 ;
    public whiteorb() {
        TrinketsApi.registerTrinket(this, this);
    }
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (hasCurio.has(this,player)){
                return false;
            }
        }
        return true;
    }


    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        float a = 0.15f;

        if (entity instanceof PlayerEntity p){
            if (hasCurio.has(init.blackorb, p)){
                a *= 2;
            }
        }


        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_SPEED,new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),a, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            anInt++;
        }
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.whiteorb.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));


    }
}


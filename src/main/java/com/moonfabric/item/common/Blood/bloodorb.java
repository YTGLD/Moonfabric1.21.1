package com.moonfabric.item.common.Blood;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.MoonFabricMod;
import com.moonfabric.item.Ms.extend.BloodE;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class bloodorb extends BloodE {

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            player.getHungerManager().setFoodLevel(20);
            Vec3d vec3d = player.getPos();
            int r = 6;
            List<LivingEntity> list = player.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
            for (LivingEntity livingEntity : list){
                livingEntity.addStatusEffect(new StatusEffectInstance(MoonFabricMod.blood, 100, 0,false,false));
                if (livingEntity!= player){
                    livingEntity.damage(livingEntity.getDamageSources().magic(), 0.1f);
                }
            }
        }
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),0.4, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),10, EntityAttributeModifier.Operation.ADD_VALUE));
        return modifierMultimap;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.bloodorb.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.bloodorb.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.bloodorb.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.bloodorb.5").formatted(Formatting.GRAY));

    }
}

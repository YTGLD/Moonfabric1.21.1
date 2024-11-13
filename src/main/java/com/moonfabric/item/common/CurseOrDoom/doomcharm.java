package com.moonfabric.item.common.CurseOrDoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Collection;
import java.util.List;

public class doomcharm extends doom {
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(this,player)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (stack.get(Data.CUSTOM_DATA)!= null) {
                stack.get(Data.CUSTOM_DATA).putFloat(lvl, acc(player));
                if (acc(player) >= 0) {
                    player.getAttributes().addTemporaryModifiers(getMap(stack));
                }
                if (stack.get(Data.CUSTOM_DATA).getFloat(lvl) < 0) {
                    stack.get(Data.CUSTOM_DATA).putFloat(lvl, 0);
                }
            }
        }
    }
    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (stack.get(Data.CUSTOM_DATA)!= null) {
                stack.get(Data.CUSTOM_DATA).putFloat(lvl, 0);
                player.getAttributes().removeModifiers(getMap(stack));
            }
        }
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {

        tooltip.add(Text.translatable("moonfabric.tooltip.doomcharm.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomcharm.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomcharm.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomcharm.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomcharm.5").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomcharm.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
      if (stack.get(Data.CUSTOM_DATA)!= null) {
          tooltip.add(Text.translatable("now: " + stack.get(Data.CUSTOM_DATA).getFloat(lvl)).formatted(Formatting.GRAY));
          tooltip.add(Text.translatable(""));
      }
    }
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getMap(ItemStack stack) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        float a = stack.get(Data.CUSTOM_DATA).getFloat(lvl);
        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),a / 25 * 2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),a / 14 / 2* 2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),a / 20 / 2* 2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),a / 30* 2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
    private final String lvl = "lvl";
    private float acc(PlayerEntity player) {
        float size = 0;
        List<Integer> Int = Lists.newArrayList();
        Collection<StatusEffectInstance> collection = player.getStatusEffects();
        for (StatusEffectInstance mobEffectInstance : collection){
            StatusEffect mobEffect = mobEffectInstance.getEffectType().value();
            if (!mobEffect.isBeneficial()){
                Int.add(mobEffectInstance.getAmplifier());
            }
        }
        for (Integer i : Int){
            size += i;
        }
        return size;
    }
}

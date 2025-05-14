package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
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

import java.util.ArrayList;
import java.util.List;

import static com.moonfabric.item.nightmare.super_nightmare.nightmare_base_black_eye_red.aty;

public class nightmare_base_fool extends com.moonfabric.item.Ms.SNightmare{

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().addTemporaryModifiers(gets(entity));
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        SlotAttributes.addSlotModifier(modifiers,"legs/belt",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),3, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity  instanceof PlayerEntity player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onUnequip(stack, slot,entity);
        entity .getAttributes().removeModifiers(gets(entity));
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> gets(LivingEntity player) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> linkedHashMultimap = HashMultimap.create();
        {
            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, stack) -> {
                    if (stack.isOf(init.nightmare_base_fool)) {
                        if (stack.get(Data.CUSTOM_DATA) != null) {
                            if (stack.get(Data.CUSTOM_DATA).getInt(aty) < 50) {
                                stack.get(Data.CUSTOM_DATA).putInt(aty, stack.get(Data.CUSTOM_DATA).getInt(aty) + 5);
                            }
                        }
                    }
                });
            });
        }
        {
            List<Integer> integersATTACK_DAMAGE = new ArrayList<>();

            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, stack) -> {
                    if (stack.isOf(init.nightmare_base_fool)) {
                        if (stack.get(Data.CUSTOM_DATA) != null) {
                            if (!stack.isEmpty()) {
                                integersATTACK_DAMAGE.add(1);
                            }
                        }
                    }
                });
            });
            {
                float dam = 0;
                for (int ignored : integersATTACK_DAMAGE) {
                    dam++;
                }
                int j = 5;
                if (HasCurio.has(init.nightmare_base_fool_soul, player)) {
                    j += 9;
                }
                dam -= j;
                if (dam < 0) {
                    dam = 0;
                }
                dam /= 100f;
                dam *= 0.5f;


                linkedHashMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), dam, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            }
        }
        {

            List<Integer> integersHealth = new ArrayList<>();
            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, stack) -> {
                    if (stack.isOf(init.nightmare_base_fool)) {
                        if (stack.get(Data.CUSTOM_DATA) != null) {
                            if (!stack.isEmpty()) {
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
            int j = 2;
            if (HasCurio.has(init.nightmare_base_fool_soul, player)){
                j += 7;
            }
            health -= j;
            if (health < 0) {
                health = 0;
            }
            health /= 100f;
            health *= 0.3f;
            linkedHashMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), health, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        }


        return linkedHashMultimap;
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_fool.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_fool.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.nightmare_base_black_eye.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_fool_betray").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_fool_bone").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_fool_soul").formatted(Formatting.DARK_RED));
        tooltip.add(Text.literal(""));

        tooltip.add(Text.translatable("item.nightmareeye.tool.string.2").formatted(Formatting.DARK_RED));
    }
}


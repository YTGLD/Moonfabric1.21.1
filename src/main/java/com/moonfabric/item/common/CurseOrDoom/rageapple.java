package com.moonfabric.item.common.CurseOrDoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.extend.rage;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
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
import java.util.Set;

public class rageapple extends rage {
    public rageapple() {
        TrinketsApi.registerTrinket(this, this);
    }

    public static float aFloat = 0;
    public static String lvl = "lvl";
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
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            player.getAttributes().removeModifiers(get(stack, player));
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onEquip(stack, slot, entity);
        if (entity instanceof PlayerEntity player) {
            player.getAttributes().addTemporaryModifiers(get(stack, player));
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        if (entity instanceof PlayerEntity player){
            if (stack.get(Data.CUSTOM_DATA)!= null){
                aFloat = acc(player);
                stack.get(Data.CUSTOM_DATA).putFloat(lvl, acc(player));
            }
        }
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.rageapple.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.rageapple.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.rageapple.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""+aFloat).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));

    }

    private float acc(PlayerEntity player) {
        float size = 0;
        List<ItemStack> slot_stack = Lists.newArrayList();
        Iterable<ItemStack> S  = player.getArmorItems();
        for (ItemStack sck : S){
            slot_stack.add(sck);
        }

        for (ItemStack stack : slot_stack){
            if (!stack.isEmpty()){
                Set<RegistryEntry<Enchantment>> map =  EnchantmentHelper.getEnchantments(stack).getEnchantments();
                for (RegistryEntry<Enchantment> enchantment : map){
                    int lvl = EnchantmentHelper.getLevel(enchantment, stack);
                    if  (size < 75){
                        size += lvl;
                    }else {
                        size = 75;
                    }

                }

            }
        }

        return size;
    }
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> get(ItemStack stack, PlayerEntity entity) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();


        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),stack.get(Data.CUSTOM_DATA).getFloat(lvl) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),stack.get(Data.CUSTOM_DATA).getFloat(lvl) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),stack.get(Data.CUSTOM_DATA).getFloat(lvl) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),stack.get(Data.CUSTOM_DATA).getFloat(lvl) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),stack.get(Data.CUSTOM_DATA).getFloat(lvl) / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return modifierMultimap;
    }
}





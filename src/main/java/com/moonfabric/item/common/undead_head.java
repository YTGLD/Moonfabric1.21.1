package com.moonfabric.item.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.TheNecoraIC;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class undead_head extends TheNecoraIC {
    public static final String  uDead = "undead";
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){

            float lv = player.getHealth() / player.getMaxHealth();

            lv *= 100;
            int now = (int) (100 -(lv));
            if (stack.get(Data.CUSTOM_DATA)==null){
                stack.set(Data.CUSTOM_DATA,new NbtCompound());
            }
            if (stack.get(Data.CUSTOM_DATA)!=null){
                stack.get(Data.CUSTOM_DATA).putInt(uDead,now);
            }

            player.getAttributes().addTemporaryModifiers(ad(stack));
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            player.getAttributes().removeModifiers(ad(stack));
        }

    }
    /*
    +1.25%治疗
	+0.8%速度
	+0.75%伤害
	+0.5%攻速
	+0.35%护甲

     */

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> ad(ItemStack stack) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifiers = HashMultimap.create();

        if (stack.get(Data.CUSTOM_DATA)!=null) {
            int lvl = stack.get(Data.CUSTOM_DATA).getInt(uDead);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;



            modifiers.put(AttReg.heal,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),
                    heal, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),
                    speed, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),
                    damage, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),
                    attSpeed, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
            modifiers.put(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),
                    armor, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));



        }
        return modifiers;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.undead_head.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.undead_head.tool.string.2").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.undead_head.tool.string.3").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.undead_head.tool.string.4").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.undead_head.tool.string.5").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.undead_head.tool.string.6").formatted(Formatting.RED));
        tooltip.add(Text.literal(""));
        if (stack.get(Data.CUSTOM_DATA)!=null) {
            int lvl = stack.get(Data.CUSTOM_DATA).getInt(uDead);
            float heal = 1.25f / 100f * lvl;
            float speed = 0.8f / 100f * lvl;
            float damage = 0.75f / 100f * lvl;
            float attSpeed = 0.5f / 100f * lvl;
            float armor = 0.35f / 100f * lvl;
            tooltip.add(Text.translatable("item.undead_head.tool.string.7").formatted(Formatting.DARK_RED));
            tooltip.add(Text.translatable("item.undead_head.tool.string.8").append(heal * 100 +"%").formatted(Formatting.DARK_RED));
            tooltip.add(Text.translatable("item.undead_head.tool.string.9").append(speed * 100 +"%").formatted(Formatting.DARK_RED));
            tooltip.add(Text.translatable("item.undead_head.tool.string.10").append(damage * 100 +"%").formatted(Formatting.DARK_RED));
            tooltip.add(Text.translatable("item.undead_head.tool.string.11").append(attSpeed * 100 +"%").formatted(Formatting.DARK_RED));
            tooltip.add(Text.translatable("item.undead_head.tool.string.12").append(armor * 100 +"%").formatted(Formatting.DARK_RED));
        }

        tooltip.add(Text.literal(""));


    }
}


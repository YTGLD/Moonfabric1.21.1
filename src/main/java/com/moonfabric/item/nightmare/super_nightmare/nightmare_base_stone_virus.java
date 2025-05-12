package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.HasCurio;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class nightmare_base_stone_virus extends com.moonfabric.item.Ms.SNightmare{



    public static void h(DamageSource source, LivingEntity entity, CallbackInfoReturnable<Float> cir){
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(init.nightmare_base_stone_virus, player)){
                player.setHealth(player.getHealth() - player.getMaxHealth()/4);
            }
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().addTemporaryModifiers(gets(entity, stack));
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity .getAttributes().addTemporaryModifiers(gets(entity, stack));

    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> gets(LivingEntity slotContext, ItemStack stack) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> linkedHashMultimap = HashMultimap.create();
        linkedHashMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(AttReg.heal, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        linkedHashMultimap.put(AttReg.cit, new EntityAttributeModifier(Identifier.of("base_attack_damage" + this.getTranslationKey()), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return linkedHashMultimap;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_stone_virus.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_stone_virus.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_stone_virus.tool.string.2").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_stone_virus.tool.string.3").formatted(Formatting.DARK_RED));
    }


}

package com.moonfabric.item.common.pain;

import com.google.common.collect.Multimap;
import com.moonfabric.Ievent.old.IeventAttack;
import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class pain_ring extends ItemTir {
    public static void pain(){
        IeventAttack.ON_HURT.register((living,source, size, stack) -> {
            if (stack.isOf(init.pain_ring)&& HasCurio.has(init.pain_ring,living)){
                float as = living.getArmor();
                if (as<0){
                    as=0;
                }
                return size+(as/4);
            }
            return size;
        });
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier(Identifier.of("moonfabric"+this.getOrCreateTranslationKey()),-10, EntityAttributeModifier.Operation.ADD_VALUE));
        return modifiers;
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_ring.1" ).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.pain_ring.2" ).formatted(Formatting.GRAY));
        tooltip.add(Text.literal(""));

    }
}

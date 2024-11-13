package com.moonfabric.item.nightmare;

import com.google.common.collect.Multimap;
import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.nightmare;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class nightmareanchor extends nightmare {

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        SlotAttributes.addSlotModifier(modifiers,"hand/ring",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),1, EntityAttributeModifier.Operation.ADD_VALUE);

        return modifiers;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA)== null) {
            stack.set(Data.CUSTOM_DATA, new NbtCompound());
        }
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.5").formatted(Formatting.RED));

        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.6").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.7").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));


    }

}

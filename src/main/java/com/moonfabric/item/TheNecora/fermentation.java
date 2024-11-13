package com.moonfabric.item.TheNecora;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class fermentation extends TheNecoraIC {
   @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.fermentation.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.fermentation.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.fermentation.tool.string.2").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        if (entity instanceof PlayerEntity player){
            player.getAttributes().addTemporaryModifiers(this.Head(player));
        }

    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            player.getAttributes().removeModifiers(this.Head(player));
        }

    }


    private Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> Head( PlayerEntity player){
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> multimap = HashMultimap.create();

        float s = 0;
        if (player.getItemCooldownManager().isCoolingDown(init.fermentation)){
            s= -0.7f;
        }else {
            s=3.0f;
        }

        multimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                Identifier.of("base_attack_damage"+this.getTranslationKey()),
                s,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));

        return multimap;
    }
}




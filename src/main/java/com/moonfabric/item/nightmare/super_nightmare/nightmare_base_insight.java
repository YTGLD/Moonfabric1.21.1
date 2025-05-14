package com.moonfabric.item.nightmare.super_nightmare;

import com.google.common.collect.Multimap;
import com.moonfabric.Ievent.AdvancementEvt;
import com.moonfabric.Ievent.AllEvent;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class nightmare_base_insight extends com.moonfabric.item.Ms.SNightmare{

    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity  instanceof PlayerEntity player){
            if (player.isCreative()){
                return true;
            }
        }
        return false;    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        SlotAttributes.addSlotModifier(modifiers,"legs/belt",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),3, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        TrinketsApi.getTrinketComponent(entity).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, box) -> {
                if (box.isOf(init.medicinebox)) {
                    NbtCompound tag = box.get(Data.CUSTOM_DATA);
                    if (tag != null) {
                        if (tag.getBoolean(AllEvent.blood_eat) &&
                                tag.getBoolean(AllEvent.blood_hurt) &&
                                tag.getBoolean(AllEvent.blood_jump) &&
                                tag.getBoolean(AllEvent.blood_spawn) &&
                                tag.getBoolean(AllEvent.blood_enchant))
                        {
                            if (stack.get(Data.CUSTOM_DATA)!=null&& !stack.get(Data.CUSTOM_DATA).getBoolean(AdvancementEvt.nightmare_base_insight_drug)){
                                if (entity  instanceof PlayerEntity player){
                                    player.giveItemStack(new ItemStack(init.nightmare_base_insight_drug));
                                    stack.get(Data.CUSTOM_DATA).putBoolean(AdvancementEvt.nightmare_base_insight_drug,true);
                                }
                            }
                        }
                    }
                }
            });
        });
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_insight.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_insight.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_black_eye.tool.string.1").formatted(Formatting.RED));

        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_insight_collapse").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_insight_insane").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.moonfabric.nightmare_base_insight_drug").formatted(Formatting.DARK_RED));
        tooltip.add(Text.literal(""));
        tooltip.add(Text.translatable("item.nightmareeye.tool.string.2").formatted(Formatting.DARK_RED));
    }
}

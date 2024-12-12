package com.moonfabric.item.common.Blood;

import com.google.common.collect.Multimap;
import com.moonfabric.Entity.owner_blood;
import com.moonfabric.init.Data;
import com.moonfabric.init.InItEntity;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

public class blood_candle extends TrinketItem {
    public blood_candle() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).fireproof());
    }
    public static final String bloods= " hasBlood";

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA)!=null){
            stack.get(Data.CUSTOM_DATA).putBoolean(bloods,false);
        }
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        SlotAttributes.addSlotModifier(modifiers,"legs/belt",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),2, EntityAttributeModifier.Operation.ADD_VALUE);

        return modifiers;
    }


    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {

        if (stack.get(Data.CUSTOM_DATA)==null){
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
        if (stack.get(Data.CUSTOM_DATA)!=null){
            if (!stack.get(Data.CUSTOM_DATA).getBoolean(bloods)){
                if (entity instanceof PlayerEntity player) {
                    owner_blood owner_blood = new owner_blood(InItEntity.OWNER_BLOOD_ENTITY_TYPE, entity.getEntityWorld());
                    owner_blood.setOwner(player);
                    owner_blood.setOwnerUuid(player.getUuid());
                    owner_blood.setPos(player.getX(),player.getY(),player.getZ());

                    player.getWorld().spawnEntity(owner_blood);

                    stack.get(Data.CUSTOM_DATA).putBoolean(bloods, true);
                }
            }

        }else stack.set(Data.CUSTOM_DATA,new NbtCompound());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {

        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.blood_candle").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.blood_candle.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.blood_candle.2").formatted(Formatting.GRAY));

        tooltip.add(Text.translatable(""));

    }
}


package com.moonfabric.item.Ms.extend;

import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Rarity;

public class BloodE extends TrinketItem {
    public BloodE() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).fireproof());
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            if (HasCurio.has(this, player)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA) == null) {
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }
}
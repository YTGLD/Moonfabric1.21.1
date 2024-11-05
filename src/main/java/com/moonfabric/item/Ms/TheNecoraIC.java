package com.moonfabric.item.Ms;

import com.moonfabric.hasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Rarity;

public class TheNecoraIC  extends TrinketItem {
    public TheNecoraIC() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).fireproof());
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (hasCurio.has(this,player)){
                return false;
            }
        }
        return true;
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

    @Override
    public boolean canEquipFromUse(ItemStack stack, LivingEntity entity) {
        return false;
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA) == null) {
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }
}



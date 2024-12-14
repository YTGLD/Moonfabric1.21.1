package com.moonfabric.item.Ms;

import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Rarity;

public class ectoplasm extends TrinketItem {
    public ectoplasm() {
        super(new Item.Settings().maxCount(64).rarity(Rarity.RARE).fireproof()
                .food(new FoodComponent.Builder().nutrition(4).alwaysEdible().saturationModifier(0.2f).build()));
    }

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
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA) == null) {
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }
}


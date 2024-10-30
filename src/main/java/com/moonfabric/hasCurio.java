package com.moonfabric;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;

public class hasCurio {
    public static boolean has(Item item , LivingEntity player){
        if (player!= null && !(TrinketsApi.getEntitySlots(player) == null)) {
            if (!TrinketsApi.getEntitySlots(player).isEmpty()) {
                if (TrinketsApi.getTrinketComponent(player).isPresent()) {
                    return !TrinketsApi.getTrinketComponent(player).get().getEquipped(item).isEmpty();
                }
            }
        }
        return false;
    }

}

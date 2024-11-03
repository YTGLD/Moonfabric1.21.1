package com.moonfabric.init;

import com.moonfabric.hasCurio;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.util.math.MathHelper;
import org.joml.Random;

import java.util.List;

public class LootTableEvent {
    public static Item addLoot(LootContext context,
                               List<Item> itemList,
                               int gLvl,
                               Item mustHas){
        if (context.get(LootContextParameters.THIS_ENTITY) != null
                && context.get(LootContextParameters.THIS_ENTITY)  instanceof PlayerEntity player) {
            if (hasCurio.has(mustHas,player)) {
                Random random = new Random();
                int i = random.nextInt(itemList.size());
                if (MathHelper.nextInt(net.minecraft.util.math.random.Random.create(), 1, 100) <= gLvl) {
                    return itemList.get(i);
                }
            }
        }
        return ItemStack.EMPTY.getItem();
    }
    public static ItemStack addLootDNA(LootContext context,
                                       List<Item> itemList,
                                       int gLvl,
                                       Item mustHas,
                                       int size) {
        if (context.get(LootContextParameters.THIS_ENTITY) != null
                && context.get(LootContextParameters.THIS_ENTITY)  instanceof PlayerEntity player) {
            if (hasCurio.has(mustHas,player)) {
                Random random = new Random();
                int i = random.nextInt(itemList.size());
                if (MathHelper.nextInt(net.minecraft.util.math.random.Random.create(), 1, 100) <= gLvl) {
                    return new ItemStack(itemList.get(i),size);
                }
            }
        }
        return ItemStack.EMPTY;
    }
}

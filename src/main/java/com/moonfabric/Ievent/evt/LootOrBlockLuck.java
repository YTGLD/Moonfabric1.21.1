package com.moonfabric.Ievent.evt;

import com.moonfabric.HasCurio;
import com.moonfabric.item.common.greedcrystal;
import com.moonfabric.item.common.mblock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Random;

public class LootOrBlockLuck {
    public static int getBlockLuck(Entity entity){

        if (entity instanceof LivingEntity living) {
            return greedcrystal.lvl(living)
                    + mblock.Block(living);
        }
        return 0;
    }
    public static int getLoot(Entity entity){

        if (entity instanceof LivingEntity living) {
            return mblock.loot(living);
        }
        return 0;
    }

    public static<T extends LivingEntity> void dropLootItem(LivingEntity thiEntity,
                                                            Item item,
                                                            int lv,
                                                            DamageSource playerS,
                                                            EntityType<T> isLiving){
        Random random = new Random();
        ItemStack stack = new ItemStack(item);
        if (random.nextInt(100)<=lv){
            if (playerS.getSource() instanceof PlayerEntity player){
                if (!HasCurio.has(item,player)) {
                    if (thiEntity.getType() == isLiving) {
                        player.getWorld().spawnEntity(
                                new ItemEntity(thiEntity.getWorld(), thiEntity.getX(), thiEntity.getY(), thiEntity.getZ(),
                                        stack));

                    }
                }
            }
        }
    }
}

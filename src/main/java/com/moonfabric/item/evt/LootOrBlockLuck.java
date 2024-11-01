package com.moonfabric.item.evt;

import com.moonfabric.item.common.greedcrystal;
import com.moonfabric.item.common.mblock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

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
}

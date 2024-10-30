package com.moonfabric.Ievent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class IFood {
    public static final Event<IFood.onIFood> Break =
            EventFactory.createArrayBacked(IFood.onIFood.class,
                    callbacks -> (entity,world, user) -> {
                        for (IFood.onIFood callback : callbacks) {
                            callback.Food(entity,world, user);
                        }
                    });
    @FunctionalInterface
    public interface onIFood {
        void Food(ItemStack entity, World world, LivingEntity user);
    }
}

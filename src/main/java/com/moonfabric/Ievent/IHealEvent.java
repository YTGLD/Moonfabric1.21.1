package com.moonfabric.Ievent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class IHealEvent {
    public static final Event<IHealEvent.onHurt> ON_HURT = EventFactory.createArrayBacked(IHealEvent.onHurt.class,
            callbacks -> (living, size,stack) -> {
                for (IHealEvent.onHurt callback : callbacks) {
                    callback.hurt(living, size,stack);
                }
            });
    @FunctionalInterface
    public interface onHurt {
        void hurt(LivingEntity living, float size, ItemStack stack);
    }
}

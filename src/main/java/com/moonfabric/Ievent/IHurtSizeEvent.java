package com.moonfabric.Ievent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

public class IHurtSizeEvent {
    public static final Event<onHurt> ON_HURT = EventFactory.createArrayBacked(onHurt.class,
            callbacks -> (living, source, size,stack) -> {
                float modifiedSize = size;
                for (onHurt callback : callbacks) {
                    modifiedSize = callback.hurt(living, source, modifiedSize,stack);
                }
                return modifiedSize;
            });

    @FunctionalInterface
    public interface onHurt {
        float hurt(LivingEntity living, DamageSource source, float size, ItemStack stack);
    }
}


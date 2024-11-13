package com.moonfabric.Ievent.old;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

public class IeventAttack {
    public static final Event<IeventAttack.onHurt> ON_HURT = EventFactory.createArrayBacked(IeventAttack.onHurt.class,
            callbacks -> (living, source, size,stack) -> {
                float modifiedSize = size;
                for (IeventAttack.onHurt callback : callbacks) {
                    modifiedSize = callback.hurt(living, source, modifiedSize,stack);
                }
                return modifiedSize;
            });

    @FunctionalInterface
    public interface onHurt {
        float hurt(LivingEntity living, DamageSource source, float size, ItemStack stack);
    }
}
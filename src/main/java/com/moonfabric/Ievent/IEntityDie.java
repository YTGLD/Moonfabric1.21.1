package com.moonfabric.Ievent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class IEntityDie {
    public static final Event<IEntityDie.onDie> Break =
            EventFactory.createArrayBacked(IEntityDie.onDie.class,
                    callbacks -> (living, source) -> {
                        for (IEntityDie.onDie callback : callbacks) {
                            callback.die(living, source);
                        }
                    });
    @FunctionalInterface
    public interface onDie {
        void die(LivingEntity living, DamageSource source);
    }
}

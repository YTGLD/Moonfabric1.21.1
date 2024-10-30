package com.moonfabric.Ievent;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class IEventHurt {
    public static final Event<IEventHurt.AllowDamage> ALLOW_DAMAGE =
            EventFactory.createArrayBacked(IEventHurt.AllowDamage.class,
            callbacks -> (entity, source, amount) -> {
                for (IEventHurt.AllowDamage callback : callbacks) {
                    if (!callback.allowDamage(entity, source, amount)) {
                        return false;
                    }
                }
                return true;
            });
    @FunctionalInterface
    public interface AllowDamage {
        boolean allowDamage(LivingEntity entity, DamageSource source, float amount);
    }
}

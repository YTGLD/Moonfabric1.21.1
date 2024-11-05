package com.moonfabric.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.World;

public abstract class TameableZombie extends TameableEntity {
    protected TameableZombie(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }
}

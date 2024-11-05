package com.moonfabric.EntiyMl;

import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.passive.TameableEntity;

public class ZombieEntityModel <T extends TameableEntity> extends AbstractZombieModel<T> {
    public ZombieEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public boolean isAttacking(T zombieEntity) {
        return zombieEntity.isAttacking();
    }
}


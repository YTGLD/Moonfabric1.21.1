package com.moonfabric.EntiyMl;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.CrossbowPosing;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.TameableEntity;

public abstract class AbstractZombieModel <T extends TameableEntity> extends BipedEntityModel<T> {
    protected AbstractZombieModel(ModelPart modelPart) {
        super(modelPart);
    }

    public void setAngles(T hostileEntity, float f, float g, float h, float i, float j) {
        super.setAngles(hostileEntity, f, g, h, i, j);
        CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, this.isAttacking(hostileEntity), this.handSwingProgress, h);
    }

    public abstract boolean isAttacking(T entity);
}


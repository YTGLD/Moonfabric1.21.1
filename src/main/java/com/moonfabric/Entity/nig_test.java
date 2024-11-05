package com.moonfabric.Entity;

import com.moonfabric.init.InItEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class nig_test extends TameableEntity {

    public nig_test(EntityType<? extends nig_test> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        nig_test line = InItEntity.nig_test.create(world);
        if (line != null) {
            UUID uuid = this.getOwnerUuid();
            if (uuid != null) {
                line.setOwnerUuid(uuid);
                line.setTamed(true, true);
            }
        }
        return line;
    }
    @Override
    protected void initGoals() {

    }
}

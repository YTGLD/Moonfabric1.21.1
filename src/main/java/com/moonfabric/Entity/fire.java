package com.moonfabric.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class fire  extends ThrownItemEntity {
    private static final java.util.Random RANDOM = new java.util.Random();
    public fire(EntityType<? extends fire> entityType, World world){
        super(entityType,world);
        this.setNoGravity(true);
        this.setInvisible(true);
    }
    @Override
    public void tick() {
        super.tick();
        double randomX = (RANDOM.nextDouble() * 2 - 1) * 0.003;
        double randomY = (RANDOM.nextDouble() * 2 - 1) * 0.003;
        double randomZ = (RANDOM.nextDouble() * 2 - 1) * 0.003;
        this.addVelocity(randomX, randomY+0.005f, randomZ);
        if (this.age > 100) {
            this.discard();
        }
    }



    @Override
    protected Item getDefaultItem() {
        return Items.GLOBE_BANNER_PATTERN.asItem();
    }
}

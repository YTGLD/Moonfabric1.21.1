package com.moonfabric.Entity;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class flysword extends ThrownItemEntity {
    public int age = 0;
    public flysword(EntityType<? extends flysword> entityType, World world) {
        super(entityType, world);
    }
    @Override
    protected Item getDefaultItem() {
        return init.abook.asItem();
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof LivingEntity livingEntity){
            if (hasCurio.has(init.sevensword, livingEntity)){
                this.discard();

            }else {
                entity.timeUntilRegen = 0;
                entity.damage(this.getDamageSources().generic(),5);
                this.discard();
            }
        }
        this.discard();
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        age++;
        if (age > 200){
            this.discard();
        }
        if (age>15) {
            this.getWorld().addParticle(MoonFabricMod.S, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        }
    }
}

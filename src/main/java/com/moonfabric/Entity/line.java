package com.moonfabric.Entity;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.init.InItEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class line extends TameableEntity {

    private LivingEntity target;
    private List<Vec3d> trailPositions = new ArrayList<>();
    public line(EntityType<? extends line> p_27412_, World p_27413_) {
        super(p_27412_, p_27413_);
    }

    private int cloudTime = 0;
    private int time  = 0;

    public void tick() {
        super.tick();
        time++;
        if (this.time>600){
            this.discard();
        }
        if (cloudTime > 0){
            cloudTime--;
        }
        trailPositions.add(new Vec3d(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 20) {
            trailPositions.removeFirst();
        }

        Vec3d playerPos = this.getPos().add(0, 0.75, 0);
        int range = 1;
        List<LivingEntity> entities = this.getWorld().getEntitiesByClass(LivingEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range), EntityPredicates.EXCEPT_SPECTATOR);

        if (this.target!=null){
            Identifier entitys = Registries.ENTITY_TYPE.getId(target.getType());
            if (entitys.getNamespace().equals(MoonFabricMod.MODID)){
                this.target=null;
            }
        }

        if (this.getOwner()!=null
                && this.getOwner() instanceof PlayerEntity player) {
            for (LivingEntity living : entities) {
                if (this.target != null
                        && living ==(this.target)
                        && player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)!=null)
                {
                    cloudTime = 20;
                    float dam = (float) player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue();
                    dam/=20;
                    if (this.target.age % 5 == 0) {
                        this.target.timeUntilRegen = 0;
                        this.target.damage(living.getDamageSources().mobAttack(this), dam);
                    }
                }
            }
        }





        if (target == null || !target.isAlive()) {
            findNewTarget();
        }

        if (target != null&&this.cloudTime<=0) {
            Vec3d targetPos = target.getPos().add(0, 0.5, 0);
            Vec3d currentPos = this.getPos();
            Vec3d direction = targetPos.subtract(currentPos).normalize();
            this.setVelocity(direction.x *0.5f, direction.y *0.5f, direction.z *0.5f);
        }

        trailPositions.add(new Vec3d(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 15) {
            trailPositions.removeFirst();
        }

        this.setNoGravity(true);

        this.headYaw = 0;
        this.bodyYaw = 0;
    }


    public List<Vec3d> getTrailPositions() {
        return trailPositions;
    }
    private void findNewTarget() {
        Box searchBox = this.getBoundingBox().expand(16);
        List<LivingEntity> entities = this.getWorld().getEntitiesByClass(LivingEntity.class, searchBox,EntityPredicates.EXCEPT_SPECTATOR);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                if (entity!=this && !(entity ==(this.getOwner()))) {
                    double distance = this.squaredDistanceTo(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        this.target = closestEntity;
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        line line = InItEntity.Line.create(world);
        if (line != null) {
            UUID uuid = this.getOwnerUuid();
            if (uuid != null) {
                line.setOwnerUuid(uuid);
                line.setTamed(true, true);
            }
        }
        return line;
    }
}





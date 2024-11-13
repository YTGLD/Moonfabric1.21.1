package com.moonfabric.Entity;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.init.InItEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.jar.Attributes;

public class head extends TameableEntity {


    private final List<Vec3d> trailPositions = new ArrayList<>();

    public head(EntityType<? extends head> entityType, World level) {
        super(entityType, level);
        this.setNoGravity(true);

    }
    public List<Vec3d> getTrailPositions() {
        return trailPositions;
    }


    @Override
    public boolean isCollidable() {
        return false;
    }

    public int onAttack;
    @Override
    public void tick() {
        super.tick();
        onAttack--;
        if (this.age > 20) {
            Vec3d playerPos = this.getPos().add(0, 0.75, 0);
            int range = 1;
            List<MobEntity> entities = this.getEntityWorld().getEntitiesByClass(MobEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range), EntityPredicates.EXCEPT_SPECTATOR);
            for (MobEntity entity : entities) {
                if (this.getOwner() != null) {
                    if (!(entity == (this.getOwner())) && this.getOwner() instanceof PlayerEntity player) {
                        Identifier entitys = Registries.ENTITY_TYPE.getId(entity.getType());
                        if (!entitys.getNamespace().equals(MoonFabricMod.MODID)) {
                           if (onAttack <= 0) {
                               if (player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)!=null) {
                                   entity.timeUntilRegen = 0;
                                   entity.damage(this.getOwner().getDamageSources().dryOut(), (float) (2 + (player.getMaxHealth() / 10)+(player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).getValue() / 10f)));
                                   player.heal(5 + player.getMaxHealth() / 10);
                                   onAttack = 20;
                               }
                           }
                        }
                    }
                }
            }
            if (this.getTarget() != null) {
                Identifier entitys = Registries.ENTITY_TYPE.getId(getTarget().getType());
                if (entitys.getNamespace().equals(MoonFabricMod.MODID)) {
                    this.setTarget(null);
                }
            }
            if (this.age > 240) {
                this.kill();
            }
            if (this.getTarget() != null){
                if (!this.getTarget().isAlive()){
                    this.setTarget(null);
                }
            }
            if (getTarget() == null) {
                findNewTarget();
            }
            float s = 0.74f;
            if (getTarget() != null) {
                Vec3d targetPos = getTarget().getPos().add(0, 0.5, 0);
                Vec3d currentPos = this.getPos();
                Vec3d direction = targetPos.subtract(currentPos).normalize();

                // 获取当前运动方向
                Vec3d currentDirection = this.getVelocity().normalize();

                // 计算目标方向与当前方向之间的夹角
                double angle = Math.acos(currentDirection.dotProduct(direction)) * (180.0 / Math.PI);

                // 如果夹角超过10度，则限制方向
                if (angle > 10) {
                    // 计算旋转后的新方向
                    double angleLimit = Math.toRadians(10); // 将10度转为弧度

                    // 根据正弦法则计算限制后的方向
                    Vec3d limitedDirection = currentDirection.multiply(Math.cos(angleLimit)) // 计算缩放因子
                            .add(direction.normalize().multiply(Math.sin(angleLimit))); // 根据目标方向进行调整

                    this.setVelocity(limitedDirection.x * (0.025f + s), limitedDirection.y * (0.025f + s), limitedDirection.z * (0.025f + s));
                } else {
                    this.setVelocity(direction.x * (0.025f + s), direction.y * (0.025f + s), direction.z * (0.025f + s));
                }
            }
        }else {
            this.setPos(getX(),getY()+0.1,getZ());
        }

        trailPositions.add(new Vec3d((float)this.getX(), (float)this.getY(),(float) this.getZ()));

        if (trailPositions.size() > 7) {
            trailPositions.removeFirst();
        }
        this.setNoGravity(true);
        this.headYaw = 0;
        this.bodyYaw = 0;
    }


    private void findNewTarget() {
        Box searchBox = this.getBoundingBox().expand(16);
        List<LivingEntity> entities = this.getWorld().getEntitiesByClass(LivingEntity.class, searchBox, EntityPredicates.EXCEPT_SPECTATOR);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                Identifier entitys = Registries.ENTITY_TYPE.getId(entity.getType());
                if (entity!=this && !(entity ==(this.getOwner()))&&!entitys.getNamespace().equals(MoonFabricMod.MODID)) {
                    double distance = this.squaredDistanceTo(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        this.setTarget(closestEntity);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }
    @Override
    public void onDeath(DamageSource damageSource) {
        this.getEntityWorld().createExplosion(null, this.getX(), this.getY(), this.getZ(), 1, false, World.ExplosionSourceType.NONE);
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    protected void playBlockFallSound() {

    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null ;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        head line = InItEntity.head.create(world);
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



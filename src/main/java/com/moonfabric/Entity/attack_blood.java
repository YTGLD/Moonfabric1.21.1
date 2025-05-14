package com.moonfabric.Entity;


import com.moonfabric.MoonFabricMod;
import com.moonfabric.init.InItEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

public class attack_blood extends ThrownItemEntity {

    private final List<Vec3d> trailPositions = new ArrayList<>();
    public float damages = 4;
    public boolean follow = true;
    public boolean slime = false;
    public boolean boom = false;
    public boolean effect = false;
    public float speeds = 0.125f;
    public float maxTime = 200;
    public boolean isPlayer = false;
    public LivingEntity target;
    public attack_blood(EntityType<? extends attack_blood> entityType, World level) {
        super(entityType, level);
        this.setNoGravity(true);

    }


    public List<Vec3d> getTrailPositions() {
        return trailPositions;
    }

    @Override
    protected Item getDefaultItem() {
        return Items.GLOBE_BANNER_PATTERN;
    }

    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);
        Vec3d playerPos = this.getPos().add(0, 0.75, 0);
        int range = 1;
        List<MobEntity> entities = this.getEntityWorld().getEntitiesByClass(MobEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range),EntityPredicates.EXCEPT_SPECTATOR);
        for (MobEntity entity : entities){
            if (this.getOwner()!=null) {
                if (!(entity ==(this.getOwner())) &&this.getOwner() instanceof PlayerEntity player){
                    Identifier entitys = Registries.ENTITY_TYPE.getId(entity.getType());
                    if (!entitys.getNamespace().equals(MoonFabricMod.MODID)) {
                        entity.timeUntilRegen = 0;
                        if (boom) {
                            this.getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 3, false, World.ExplosionSourceType.NONE);
                        }
                        if (effect) {
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1));
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1));
                            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1));
                        }
                        if (slime){
                            player.heal(damages);
                        }
                        if (isPlayer){
                            entity.damage(this.getOwner().getDamageSources().playerAttack(player), damages + player.getMaxHealth() / 10);

                        }else {
                            entity.damage(this.getOwner().getDamageSources().dryOut(), damages + player.getMaxHealth() / 10);
                        }

                        this.discard();
                    }
                }
            }
        }
        if (this.getTarget()!=null){
            Identifier entitys = Registries.ENTITY_TYPE.getId(getTarget().getType());
            if (entitys.getNamespace().equals(MoonFabricMod.MODID)){
                this.setTarget(null);;
            }
        }
        if (this.age > maxTime) {
            this.discard();
        }
        if (getTarget() != null) {
            if (!getTarget().isAlive()) {
                findNewTarget();
            }
        }
        float s = 0.075f;
        if (getTarget() != null) {
            if (follow) {
                Vec3d targetPos = getTarget().getPos().add(0, 0.5, 0);
                Vec3d currentPos = this.getPos();
                Vec3d direction = targetPos.subtract(currentPos).normalize();

                // 获取当前运动方向
                Vec3d currentDirection = this.getVelocity().normalize();

                // 计算目标方向与当前方向之间的夹角
                double angle = Math.acos(currentDirection.dotProduct(direction)) * (180.0 / Math.PI);

                // 如果夹角超过10度，则限制方向
                if (angle > 23) {
                    // 计算旋转后的新方向
                    double angleLimit = Math.toRadians(45); // 将10度转为弧度

                    // 根据正弦法则计算限制后的方向
                    Vec3d limitedDirection = currentDirection.multiply(Math.cos(angleLimit)) // 计算缩放因子
                            .add(direction.normalize().multiply(Math.sin(angleLimit))); // 根据目标方向进行调整

                    this.setVelocity(limitedDirection.x * (speeds + s), limitedDirection.y * (speeds + s), limitedDirection.z * (speeds + s));
                } else {
                    this.setVelocity(direction.x * (speeds + s), direction.y * (speeds + s), direction.z * (speeds + s));
                }
            }else {
                if (this.age < 5) {
                    Vec3d targetPos = getTarget().getPos().add(0, 0.5, 0);
                    Vec3d currentPos = this.getPos();
                    Vec3d direction = targetPos.subtract(currentPos).normalize();
                    this.setVelocity(direction.x * (speeds + s), direction.y * (speeds + s), direction.z * (speeds + s));
                }
            }
        }

        trailPositions.add(new Vec3d(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 20) {
            trailPositions.removeFirst();
        }

        this.setNoGravity(true);
    }

    private LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    private void findNewTarget() {
        Box searchBox = this.getBoundingBox().expand(16);
        List<LivingEntity> entities = this.getWorld().getEntitiesByClass(LivingEntity.class, searchBox, EntityPredicates.EXCEPT_SPECTATOR);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                Identifier entitys = Registries.ENTITY_TYPE.getId(entity.getType());
                if (!(entity ==(this.getOwner()))&&!entitys.getNamespace().equals(MoonFabricMod.MODID)) {
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



    public void setBoom(boolean boom) {
        this.boom = boom;
    }

    public void setDamage(float damage) {
        damages = damage;
    }

    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    public void setSpeed(float speed) {
        speeds = speed;
    }

    public float getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public float getSpeeds() {
        return speeds;
    }

    public float getDamages() {
        return damages;
    }

    public void setHeal(boolean slime) {
        this.slime = slime;
    }

    public void setCannotFollow(boolean t) {
        follow = t;
    }
}


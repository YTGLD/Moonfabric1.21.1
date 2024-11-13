package com.moonfabric.Entity;

import com.moonfabric.init.InItEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class penalty  extends TameableEntity {


    private final List<Vec3d> trailPositions = new ArrayList<>();

    public penalty(EntityType<? extends penalty> entityType, World level) {
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

    @Override
    public void tick() {
        super.tick();
        if (this.age > 20) {
            if (this.getOwner() != null) {
                Vec3d playerPos = this.getPos().add(0, 0.75, 0);
                int range = 1;
                List<PlayerEntity> entities = this.getEntityWorld().getEntitiesByClass(PlayerEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range), EntityPredicates.EXCEPT_SPECTATOR);
                for (PlayerEntity player : entities) {

                    if (this.getOwner() == player) {
                        player.heal(player.getMaxHealth() / 5);
                        if (player.timeUntilRegen <120) {
                            player.timeUntilRegen += 40;
                        }
                        this.getEntityWorld().playSound(this, new BlockPos((int) player.getX(), (int) (player.getY() + 1), (int) player.getZ()), SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.AMBIENT, 1, 1);
                        this.discard();

                    }
                }
            }
            if (getTarget() == null) {
                findNewTarget();
            }
            if (this.age > 300){
                this.discard();
            }
            float s = this.age / 200f;
            if (s>0.5f){
                s=0.5f;
            }
            if (getTarget() != null) {
                Vec3d targetPos = getTarget().getPos().add(0, 0.5, 0);
                Vec3d currentPos = this.getPos();
                Vec3d direction = targetPos.subtract(currentPos).normalize();

                // 获取当前运动方向
                Vec3d currentDirection = this.getVelocity().normalize();

                // 计算目标方向与当前方向之间的夹角
                double angle = Math.acos(currentDirection.dotProduct(direction)) * (180.0 / Math.PI);

                // 如果夹角超过10度，则限制方向
                if (angle > 7) {
                    // 计算旋转后的新方向
                    double angleLimit = Math.toRadians(7); // 将10度转为弧度

                    // 根据正弦法则计算限制后的方向
                    Vec3d limitedDirection = currentDirection.multiply(Math.cos(angleLimit)) // 计算缩放因子
                            .add(direction.normalize().multiply(Math.sin(angleLimit))); // 根据目标方向进行调整

                    this.setVelocity(limitedDirection.x * (0.025f + s), limitedDirection.y * (0.025f + s), limitedDirection.z * (0.025f + s));
                } else {
                    this.setVelocity(direction.x * (0.025f + s), direction.y * (0.025f + s), direction.z * (0.025f + s));
                }
            }
        }

        trailPositions.add(new Vec3d((float)this.getX(), (float)this.getY(),(float) this.getZ()));

        if (trailPositions.size() > 15) {
            trailPositions.removeFirst();
        }
        this.setNoGravity(true);
        this.headYaw = 0;
        this.bodyYaw = 0;
    }


    private void findNewTarget() {
        if (this.getOwner() != null) {
            this.setTarget(this.getOwner());
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
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




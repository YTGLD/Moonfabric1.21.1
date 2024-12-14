package com.moonfabric.Entity;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.HasCurio;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;


public class owner_blood extends TameableEntity {
    public owner_blood(EntityType<? extends owner_blood> p_21803_, World p_21804_) {
        super(p_21803_, p_21804_);
        this.setNoGravity(true);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource damageSource) {
        return true;
    }

    private final List<Vec3d> trailPositions = new ArrayList<>();
    public List<Vec3d> getTrailPositions() {
        return trailPositions;
    }
    @Override
    public void tick() {
        super.tick();
        this.setNoGravity(true);

        this.timeUntilRegen += 100;

        if (getOwner()==null){
            this.discard();
        }


        LivingEntity owner = getOwner(); // 获取主人
        LivingEntity target = getTarget(); // 获取目标
        Vec3d currentPos = this.getPos();

        if ( target != null) {
            Vec3d targetPos = target.getPos().add(0, 0.5, 0);
            Vec3d direction = targetPos.subtract(currentPos).normalize();
            this.setVelocity(direction.x * (0.075f + 0.5), direction.y * (0.075f + 0.5), direction.z * (0.075f + 0.5));
        }
        if (owner != null){
            double desiredDistance = 2; // 设置想要保持的距离
            Vec3d targetPos = owner.getPos().add(0, 3, 0); // 获取玩家位置并抬高

            Vec3d forward = owner.getRotationVector(); // 获取玩家的朝向向量
            Vec3d direction = forward.multiply(-1).normalize(); // 计算背后的方向（逆向）

            Vec3d newTargetPos = targetPos.add(direction.multiply(desiredDistance)); // 计算新的目标位置

            this.setVelocity(newTargetPos.subtract(currentPos).normalize().multiply(0.15f)); // 设置对象的运动速度
        }
        if (target == null) {
            findNewTarget();
        }

        if (this.getOwner() != null) {
            if (this.getOwner() instanceof PlayerEntity player){
                if (!HasCurio.has(init.blood_candle, player)){
                    this.discard();
                }
            }
        }
        trailPositions.add(new Vec3d(this.getX(), this.getY(), this.getZ()));

        if (trailPositions.size() > 66) {
            trailPositions.removeFirst();
        }

        if (this.getTarget() != null) {
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
            }
        }
        if (this.getOwner()!= null) {
            if (this.getOwner().getLastAttacker()!= null) {
                if (!(this.getOwner().getLastAttacker() == (this))) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }
            }
            if (this.getOwner().getAttacking()!= null) {
                if (!(this.getOwner().getAttacking() == (this))) {
                    this.setTarget(this.getOwner().getAttacking());
                }

            }
            if (this.getOwner().getAttacker()!= null) {
                if (!(this.getOwner().getAttacker() == (this))) {
                    this.setTarget(this.getOwner().getAttacker());
                }
            }
        }
        if (this.getTarget()!=null){
            Identifier entitys = Registries.ENTITY_TYPE.getId(getTarget().getType());
            if (entitys.getNamespace().equals(MoonFabricMod.MODID)){
                this.setTarget(null);;
            }
        }
        float s = 20;
        if (this.getOwner()!= null &&this.getOwner() instanceof PlayerEntity player){
            if (HasCurio.has(init.owner_blood_eye, player)){
                s*=0.8f;
            }
            if (HasCurio.has(init.owner_blood_attack_eye, player)){
                s*=1.1f;
            }
            if (HasCurio.has(init.owner_blood_speed_eye, player)){
                s*=0.5f;
            }
            if (HasCurio.has(init.owner_blood_boom_eye, player)){
                s*= 3;
            }
            if (HasCurio.has(init.owner_blood_earth, player)){
                {
                    Vec3d position = this.getPos();
                    int is = 12;
                    List<Entity> ess = this.getEntityWorld().getEntitiesByClass(Entity.class, new Box(position.x - is, position.y - is, position.z - is, position.x + is, position.y + is, position.z + is), EntityPredicates.EXCEPT_SPECTATOR);
                    for (Entity es : ess) {
                        Identifier entitys = Registries.ENTITY_TYPE.getId(es.getType());
                        if (!entitys.getNamespace().equals(MoonFabricMod.MODID) && es != this.getOwner() && !(es ==this)) {
                            if (es instanceof ItemEntity item) {
                                Vec3d motion = position.subtract(item.getPos().add(0, item.getHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setVelocity(motion.multiply(1));
                            }
                            if (es instanceof LivingEntity item) {
                                Vec3d motion = position.subtract(item.getPos().add(0, item.getHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setVelocity(motion.multiply(0.25));
                            }
                            if (es instanceof ProjectileEntity item) {
                                Vec3d motion = position.subtract(item.getPos().add(0, item.getHeight() / 2, 0));
                                if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
                                    motion = motion.normalize();
                                }
                                item.setVelocity(motion.multiply(3));
                            }
                        }
                    }
                }
                {
                    Vec3d added = this.getPos().add(0, 0.75, 0);
                    int r = 1;
                    List<ProjectileEntity> ss = this.getEntityWorld().getEntitiesByClass(ProjectileEntity.class, new Box(added.x - r, added.y - r, added.z - r, added.x + r, added.y + r, added.z + r), EntityPredicates.EXCEPT_SPECTATOR);
                    for (ProjectileEntity entity : ss){
                        entity.discard();
                        player.heal(4);
                    }
                }
            }
        }
        if (this.getTarget()!=null){
            if (this.age % s == 0&&this.getOwner() instanceof PlayerEntity player) {
                if (!HasCurio.has(init.owner_blood_earth, player)) {
                    attack_blood attackBlood = new attack_blood(InItEntity.ATTACK_BLOOD_ENTITY_TYPEttack_blood, this.getEntityWorld());
                    if (HasCurio.has(init.owner_blood_speed_eye, player)) {
                        attackBlood.setCannotFollow(false);
                        attackBlood.setSpeed(attackBlood.getSpeeds() * 4);
                    }
                    if (HasCurio.has(init.owner_blood_attack_eye, player)) {
                        attackBlood.setDamage(attackBlood.getDamages() * 1.2f);
                    }
                    if (HasCurio.has(init.owner_blood_effect_eye, player)) {
                        attackBlood.setEffect(true);
                    }
                    if (HasCurio.has(init.owner_blood_vex, player)) {
                        attackBlood.setHeal(true);
                        attackBlood.setDamage(attackBlood.getDamages() - 3);
                    }
                    if (HasCurio.has(init.owner_blood_boom_eye, player)) {
                        attackBlood.setSpeed(attackBlood.getSpeeds() * 0.65f);
                        attackBlood.setBoom(true);
                    }


                    attackBlood.setNoGravity(true);
                    attackBlood.setTarget(this.getTarget());
                    attackBlood.setPos(this.getX(), this.getY(), this.getZ());
                    attackBlood.setOwner(player);
                    this.getEntityWorld().spawnEntity(attackBlood);

                    playRemoveOneSound(this);
                }
            }
        }
    }

    private void playRemoveOneSound(Entity p_186343_) {
        p_186343_.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_DEPLETE.value(), 0.8F, 0.8F + p_186343_.getEntityWorld().getRandom().nextFloat() * 0.4F);
    }
    public boolean isPushable() {
        return false;
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }
    private void findNewTarget() {
        Box searchBox = this.getBoundingBox().expand(16);
        List<LivingEntity> entities = this.getWorld().getEntitiesByClass(LivingEntity.class, searchBox, EntityPredicates.EXCEPT_SPECTATOR);
        double closestDistance = Double.MAX_VALUE;
        LivingEntity closestEntity = null;


        for (LivingEntity entity : entities) {
            if (this.getOwner() != null) {
                Identifier entitys = Registries.ENTITY_TYPE.getId(entity.getType());
                if (entity!=this && !(entity ==(this.getOwner()))
                        && !entitys.getNamespace().equals(MoonFabricMod.MODID)) {
                    double distance = this.squaredDistanceTo(entity);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestEntity = entity;
                    }
                }
            }
        }

        this.setTarget(closestEntity);;
    }
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        owner_blood line = InItEntity.OWNER_BLOOD_ENTITY_TYPE.create(world);
        if (line != null) {
            UUID uuid = this.getOwnerUuid();
            if (uuid != null) {
                line.setOwnerUuid(uuid);
                line.setTamed(true, true);
            }
        }
        return line;
    }
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(7, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));

    }
}

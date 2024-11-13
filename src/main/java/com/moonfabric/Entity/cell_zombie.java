package com.moonfabric.Entity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.Ievent.AllEvent;
import com.moonfabric.HasCurio;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class cell_zombie  extends TameableZombie {
    public cell_zombie(EntityType<? extends cell_zombie> c  , World p_34272_) {
        super(c, p_34272_);
    }

    public int time = 0;
    @Override
    public void tick() {
        super.tick();
        if (this.getTarget() != null) {
            if (this.getTarget()  instanceof TameableZombie) {
                this.setTarget(null);
            }
        }
        if (this.getTarget() != null&&this.getOwner() != null) {
            if (this.getTarget() == this.getOwner()) {
                this.setTarget(null);
            }
        }
        Vec3d playerPos = this.getPos().add(0, 0.75, 0);
        int range = 20;
        List<MobEntity> entities = this.getEntityWorld().getEntitiesByClass(MobEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range), EntityPredicates.EXCEPT_SPECTATOR);
        for (MobEntity mob : entities) {
            if (this.getTarget() == null) {
                if (!(mob instanceof TameableZombie)) {
                    this.setTarget(mob);
                }
            }
        }
        if (this.getTarget() != null) {
            if (!this.getTarget().isAlive()) {
                this.setTarget(null);
            }
        }
        if (this.getOwner()!= null) {
            if (this.getOwner().getLastAttacker()!= null) {
                if (!(this.getOwner().getLastAttacker() ==(this))) {
                    this.setTarget(this.getOwner().getLastAttacker());
                }
            }
            if (this.getOwner().getAttacker()!= null) {
                if (!(this.getOwner().getAttacker() ==(this))) {
                    this.setTarget(this.getOwner().getAttacker());
                }

            }
            if (this.getOwner().getAttacking()!= null) {
                if (!(this.getOwner().getAttacking() ==(this))) {
                    this.setTarget(this.getOwner().getAttacking());
                }

            }
        }
        if (!this.getCommandTags().contains(AllEvent.muMMY)) {
            this.time+=2;
        }else {
            this.time++;
        }
        if (this.time > 1000){
            this.kill();
        }
        if (this.getCommandTags().contains(AllEvent.DamageCell)){
            if (this.getOwner()!= null) {
                this.getAttributes().addTemporaryModifiers(modifierMultimap(this.getOwner()));
            }
        }
        if (this.getCommandTags().contains(AllEvent.calcification)){
            if (this.getOwner()!= null) {
                this.getAttributes().addTemporaryModifiers(calcificationMultimap(this.getOwner()));

                if (this.age < 5){
                    this.heal(100);
                }
            }
        }
    }
    private Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> calcificationMultimap(LivingEntity livingEntity){
       Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        if (HasCurio.has(init.cell, livingEntity) && HasCurio.has(init.cell_calcification, livingEntity)) {
            modifierMultimap.put(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier(Identifier.of("base_attack_damage"+"cell_armor"), livingEntity.getAttributeValue(EntityAttributes.GENERIC_ARMOR) / 2, EntityAttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of("base_attack_damage"+"cell_health"), livingEntity.getAttributeValue(EntityAttributes.GENERIC_MAX_HEALTH) / 2, EntityAttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }
    private Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap(LivingEntity livingEntity){
       Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        if (HasCurio.has(init.cell, livingEntity) && HasCurio.has(init.adrenaline, livingEntity)) {
            modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(Identifier.of("base_attack_damage"+"cell_damage"), livingEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE), EntityAttributeModifier.Operation.ADD_VALUE));
            modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of("base_attack_damage"+"cell_speed"), livingEntity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED), EntityAttributeModifier.Operation.ADD_VALUE));
        }
        return modifierMultimap;
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if (this.getCommandTags().contains(AllEvent.boom)){
            this.getEntityWorld().createExplosion(null, this.getX(), this.getY(), this.getZ(), 5.5f, false, World.ExplosionSourceType.NONE);
        }

    }

    @Override
    public boolean tryAttack(Entity target) {
        if (this.getCommandTags().contains(AllEvent.cb_blood)){
            this.heal(this.getMaxHealth()/10);
            if (this.time>0) {
                this.time -= 100;
            }
        }
        return super.tryAttack(target);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(7, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));
        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, (new RevengeGoal(this, new Class[0])).setGroupRevenge(new Class[0]));

    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        cell_zombie line = InItEntity.cell_zombie.create(world);
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

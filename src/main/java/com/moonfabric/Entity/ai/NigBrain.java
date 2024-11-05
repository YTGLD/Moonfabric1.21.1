package com.moonfabric.Entity.ai;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import com.moonfabric.Entity.nightmare_giant;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.*;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.WardenBrain;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class NigBrain {
    private static final float STROLL_SPEED = 0.5F;
    private static final float CELEBRATE_TIME = 0.7F;
    private static final float RANGED_APPROACH_SPEED = 1.2F;
    private static final int MELEE_ATTACK_INTERVAL = 18;
    private static final int DIG_DURATION = MathHelper.ceil(100.0F);
    public static final int EMERGE_DURATION = MathHelper.ceil(133.59999F);
    public static final int ROAR_DURATION = MathHelper.ceil(84.0F);
    private static final int SNIFF_DURATION = MathHelper.ceil(83.2F);
    public static final int DIG_COOLDOWN = 1200;
    private static final int field_38181 = 100;
    private static final List<SensorType<? extends Sensor<? super nightmare_giant>>> SENSORS;
    private static final List<MemoryModuleType<?>> MEMORY_MODULES;
    private static final Task<nightmare_giant> RESET_DIG_COOLDOWN_TASK;

    public NigBrain() {
    }

    public static void updateActivities(nightmare_giant warden) {
        warden.getBrain().resetPossibleActivities(ImmutableList.of(Activity.EMERGE, Activity.DIG, Activity.ROAR, Activity.FIGHT, Activity.INVESTIGATE, Activity.SNIFF, Activity.IDLE));
    }

    public static Brain<?> create(nightmare_giant warden, Dynamic<?> dynamic) {
        Brain.Profile<nightmare_giant> profile = Brain.createProfile(MEMORY_MODULES, SENSORS);
        Brain<nightmare_giant> brain = profile.deserialize(dynamic);
        addCoreActivities(brain);
        addDigActivities(brain);
        addFightActivities(warden, brain);
        brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
        brain.setDefaultActivity(Activity.IDLE);
        brain.resetPossibleActivities();
        return brain;
    }

    private static void addCoreActivities(Brain<nightmare_giant> brain) {
        brain.setTaskList(Activity.CORE, 0, ImmutableList.of(new StayAboveWaterTask(0.8F), LookAtDisturbanceTask.create(), new LookAroundTask(45, 90), new MoveToTargetTask()));
    }



    private static void addDigActivities(Brain<nightmare_giant> brain) {
        brain.setTaskList(Activity.DIG, ImmutableList.of(Pair.of(0, new DismountVehicleTask()), Pair.of(1, new DigTask(DIG_DURATION))), ImmutableSet.of(Pair.of(MemoryModuleType.ROAR_TARGET, MemoryModuleState.VALUE_ABSENT), Pair.of(MemoryModuleType.DIG_COOLDOWN, MemoryModuleState.VALUE_ABSENT)));
    }


    private static void addFightActivities(nightmare_giant warden, Brain<nightmare_giant> brain) {
        brain.setTaskList(Activity.FIGHT,10,  ImmutableList.of(new SonicBoomTask()));
    }


    static {
        SENSORS = List.of(SensorType.NEAREST_PLAYERS);
        MEMORY_MODULES = List.of(MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_NEMESIS, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_ATTACKABLE, MemoryModuleType.ROAR_TARGET, MemoryModuleType.DISTURBANCE_LOCATION, MemoryModuleType.RECENT_PROJECTILE, MemoryModuleType.IS_SNIFFING, MemoryModuleType.IS_EMERGING, MemoryModuleType.ROAR_SOUND_DELAY, MemoryModuleType.DIG_COOLDOWN, MemoryModuleType.ROAR_SOUND_COOLDOWN, MemoryModuleType.SNIFF_COOLDOWN, MemoryModuleType.TOUCH_COOLDOWN, MemoryModuleType.VIBRATION_COOLDOWN, MemoryModuleType.SONIC_BOOM_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, MemoryModuleType.SONIC_BOOM_SOUND_DELAY);
        RESET_DIG_COOLDOWN_TASK = TaskTriggerer.task((context) -> {
            return context.group(context.queryMemoryOptional(MemoryModuleType.DIG_COOLDOWN)).apply(context, (digCooldown) -> {
                return (world, entity, time) -> {
                    if (context.getOptionalValue(digCooldown).isPresent()) {
                        digCooldown.remember(Unit.INSTANCE, 1200L);
                    }

                    return true;
                };
            });
        });
    }
}

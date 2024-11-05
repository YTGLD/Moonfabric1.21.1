package com.moonfabric.Entity.ai;

import com.google.common.collect.ImmutableMap;
import com.moonfabric.Entity.nig_test;
import com.moonfabric.Entity.nightmare_giant;
import com.moonfabric.init.InItEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAttachmentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;
import java.util.Optional;

public class SonicBoomTask extends MultiTickTask<nightmare_giant> {
    private static final int HORIZONTAL_RANGE = 15;
    private static final int VERTICAL_RANGE = 20;
    private static final double field_38852 = 0.5;
    private static final double field_38853 = 2.5;
    public static final int COOLDOWN = 40;
    private static final int SOUND_DELAY = MathHelper.ceil(34.0);
    private static final int RUN_TIME = MathHelper.ceil(60.0F);

    public SonicBoomTask() {
        super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryModuleState.VALUE_PRESENT, MemoryModuleType.SONIC_BOOM_COOLDOWN, MemoryModuleState.VALUE_ABSENT, MemoryModuleType.SONIC_BOOM_SOUND_COOLDOWN, MemoryModuleState.REGISTERED, MemoryModuleType.SONIC_BOOM_SOUND_DELAY, MemoryModuleState.REGISTERED), RUN_TIME);
    }

    protected boolean shouldRun(ServerWorld serverWorld, nightmare_giant wardenEntity) {
        return wardenEntity.isInRange((Entity)wardenEntity.getBrain().getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET).get(), 15.0, 20.0);
    }

    protected boolean shouldKeepRunning(ServerWorld serverWorld, nightmare_giant wardenEntity, long l) {
        return true;
    }

    protected void run(ServerWorld serverWorld, nightmare_giant wardenEntity, long l) {
        wardenEntity.getBrain().remember(MemoryModuleType.ATTACK_COOLING_DOWN, true, (long)RUN_TIME);
        wardenEntity.getBrain().remember(MemoryModuleType.SONIC_BOOM_SOUND_DELAY, Unit.INSTANCE, (long)SOUND_DELAY);
        serverWorld.sendEntityStatus(wardenEntity, (byte)62);
        wardenEntity.playSound(SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, 3.0F, 1.0F);
    }

    protected void keepRunning(ServerWorld serverWorld, nightmare_giant wardenEntity, long l) {


    }
    protected void finishRunning(ServerWorld serverWorld, nightmare_giant wardenEntity, long l) {
        cooldown(wardenEntity, 40);
    }

    public static void cooldown(LivingEntity warden, int cooldown) {
        warden.getBrain().remember(MemoryModuleType.SONIC_BOOM_COOLDOWN, Unit.INSTANCE, (long)cooldown);
    }
}


package com.moonfabric.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ytgld extends nightmare_giant{
    public ytgld(EntityType<? extends ytgld> entityType, World world) {
        super(entityType, world);
    }
    private final List<Vec3d> trailPositions = new ArrayList<>();
    public List<Vec3d> getTrailPositions() {
        return trailPositions;
    }

    @Override
    public void tick() {
        super.tick();
        time=0;
        trailPositions.add(new Vec3d(this.getX(), this.getY(), this.getZ()));
        if (trailPositions.size() > 10) {
            trailPositions.removeFirst();
        }
    }
}

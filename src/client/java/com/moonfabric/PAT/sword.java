package com.moonfabric.PAT;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

public class sword extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    sword(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.velocityX = 0;
        this.velocityY = 0;
        this.velocityZ = 0;

        this.scale =  0.5f;
        this.maxAge = 150;
        this.setSpriteForAge(spriteProvider);
    }
    @Override
    protected int getBrightness(float tint) {
        return 240;
    }



    @Override
    public ParticleTextureSheet getType() {
        return  ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
    @Override
    public void tick() {
        this.angle += 0.50f+ MathHelper.nextFloat(Random.create(), 0.3f, 1.5f);
        this.scale *= 0.93f;
        super.tick();

        if (age>60){
            this.alpha -= 0.05f;
        }

        this.setSpriteForAge(this.spriteProvider);
    }


    @Environment(EnvType.CLIENT)
    public static class CloudFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public CloudFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new sword(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}





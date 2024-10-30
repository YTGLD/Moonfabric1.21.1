package com.moonfabric.PAT;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

public class Blood extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    Blood(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.gravityStrength = -0.1F;
        this.velocityMultiplier = 0.9F;
        this.spriteProvider = spriteProvider;
        this.velocityX = velocityX + (Math.random() * 2.0 - 1.0) * 0.1000000074505806;
        this.velocityY = velocityY + (Math.random() * 2.0 - 1.0) * 0.1000000074505806;
        this.velocityZ = velocityZ + (Math.random() * 2.0 - 1.0) * 0.1000000074505806;
        this.scale = 0.055F * (this.random.nextFloat() * this.random.nextFloat() * 6.0F + 1.0F);
        this.maxAge = 200;
        this.setSpriteForAge(spriteProvider);
    }


    @Override
    protected int getBrightness(float tint) {
        return 240;
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        this.angle += 0.5f;
        this.alpha -= 0.01f;
        this.scale *= 0.97f;

        super.tick();

        this.setSpriteForAge(this.spriteProvider);
    }

    @Environment(EnvType.CLIENT)
    public static class CloudFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public CloudFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new Blood(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}


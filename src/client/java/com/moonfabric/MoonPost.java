package com.moonfabric;

import com.google.gson.JsonSyntaxException;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MoonPost {


    private static final List<Identifier> registry = new ArrayList<>();

    public static List<Identifier> getRegistry() {
        return registry;
    }

    private static final Map<Identifier, MoonPost.PostEffect> postEffects = new HashMap<>();

    public static void clear() {
        for(MoonPost.PostEffect postEffect : postEffects.values()){
            postEffect.close();
        }
        postEffects.clear();
    }

    public static void onInitializeOutline(MinecraftClient minecraft) {
        registry.add(MoonFabricModClient.POST);
        clear();
        for (Identifier resourceLocation : registry) {
            PostEffectProcessor postChain;
            Framebuffer renderTarget;
            try {
                postChain = new PostEffectProcessor(minecraft.getTextureManager(), minecraft.getResourceManager(), minecraft.getFramebuffer(), resourceLocation);
                postChain.setupDimensions(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight());
                renderTarget = postChain.getSecondaryTarget("final");
            } catch (IOException | JsonSyntaxException ioexception) {
                MoonFabricMod.LOGGER.error(String.valueOf(ioexception));
                postChain = null;
                renderTarget = null;
            }
            postEffects.put(resourceLocation, new MoonPost.PostEffect(postChain, renderTarget, false));
        }
    }


    public static void resize(int x, int y) {
        for (MoonPost.PostEffect postEffect : postEffects.values()) {
            postEffect.setupDimensions(x, y);
        }
    }

    public static Framebuffer getFramebufferFor(Identifier resourceLocation) {
        MoonPost.PostEffect effect = postEffects.get(resourceLocation);
        return effect == null ? null : effect.getFramebuffer();
    }

    public static void renderEffectForNextTick(Identifier resourceLocation) {
        MoonPost.PostEffect effect = postEffects.get(resourceLocation);
        if (effect != null) {
            effect.setEnabled(true);
        }
    }

    public static void blitEffects(MinecraftClient minecraft) {
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        for (MoonPost.PostEffect postEffect : postEffects.values()) {
            if (postEffect.postChain != null && postEffect.isEnabled()) {
                postEffect.getFramebuffer().draw(minecraft.getWindow().getWidth(), minecraft.getWindow().getHeight(), false);
                postEffect.getFramebuffer().clear(MinecraftClient.IS_SYSTEM_MAC);
                minecraft.getFramebuffer().beginWrite(false);
                postEffect.setEnabled(false);
            }
        }
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    }

    public static void clearAndBindWrite(Framebuffer mainTarget) {
        for (MoonPost.PostEffect postEffect : postEffects.values()) {
            if (postEffect.isEnabled() && postEffect.postChain != null) {
                postEffect.getFramebuffer().clear(MinecraftClient.IS_SYSTEM_MAC);
                mainTarget.beginWrite(false);
            }
        }
    }

    public static void processEffects(Framebuffer mainTarget) {
        for (MoonPost.PostEffect postEffect : postEffects.values()) {
            if (postEffect.isEnabled() && postEffect.postChain != null) {
                postEffect.postChain.render(MinecraftClient.getInstance().getRenderTime());
                mainTarget.beginWrite(false);
            }
        }
    }

    private static class PostEffect {
        private final PostEffectProcessor postChain;
        private final Framebuffer renderTarget;
        private boolean enabled;

        public PostEffect(PostEffectProcessor postChain, Framebuffer renderTarget, boolean enabled) {
            this.postChain = postChain;
            this.renderTarget = renderTarget;
            this.enabled = enabled;
        }

        public Framebuffer getFramebuffer() {
            return renderTarget;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public void close() {
            if (postChain != null) {
                postChain.close();
            }
        }

        public void setupDimensions(int x, int y) {
            if (postChain != null) {
                postChain.setupDimensions(x, y);
            }
        }
    }
}

package com.moonfabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class MRender extends RenderLayer {
    public MRender(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }
    public static final ShaderProgram BLOOD_PROGRAM = new ShaderProgram(MRender::getRenderTypeEndPortalProgram);
    @Nullable
    public static net.minecraft.client.gl.ShaderProgram getRenderTypeEndPortalProgram() {
        return renderTypeEndPortalProgram;
    }
    private static net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram;
    public static final RenderLayer LIGHTNING = RenderLayer.of("lightning", VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS, 1536, false, true, MultiPhaseParameters.builder().program(LIGHTNING_PROGRAM).cull(DISABLE_CULLING).writeMaskState(ALL_MASK).transparency(LIGHTNING_TRANSPARENCY).target(WEATHER_TARGET).build(false));

    private static final RenderLayer BLOOD =
            of("blood",
                    VertexFormats.POSITION,
                    VertexFormat.DrawMode.QUADS,
                    1536,
                    false,
                    false,
                    RenderLayer.MultiPhaseParameters.builder().program(BLOOD_PROGRAM)
                            .texture(Textures.create().add(Identifier.of(MoonFabricMod.MODID,"textures/gui/blood.png"),
                                    false, false).add(Identifier.of(MoonFabricMod.MODID,"textures/gui/blood.png"),
                                    false, false).build()).build(false));


    public static RenderLayer getBlood() {
        return BLOOD;
    }
    public static void setRenderTypeEndPortalProgram(net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram) {
        MRender.renderTypeEndPortalProgram = renderTypeEndPortalProgram;
    }


    protected static final RenderPhase.Target setOutputState = new RenderPhase.Target("set", () -> {
        Framebuffer target = MoonPost.getFramebufferFor(MoonFabricModClient.POST);
        if (target != null) {
            target.copyDepthFrom(MinecraftClient.getInstance().getFramebuffer());
            target.beginWrite(false);
        }
    }, () -> {
        MinecraftClient.getInstance().getFramebuffer().beginWrite(false);
    });




    public static final ShaderProgram BLOOD_PROGRAMOutLine = new ShaderProgram(MRender::getRenderTypeEndPortalProgramOutLine);
    @Nullable
    public static net.minecraft.client.gl.ShaderProgram getRenderTypeEndPortalProgramOutLine() {
        return renderTypeEndPortalProgramOutLine;
    }
    private static net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgramOutLine;

    private static final RenderLayer BLOODOutLine =of(
            "p_blood",
            VertexFormats.POSITION,
            VertexFormat.DrawMode.QUADS,
            256,
            false,
            true,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(BLOOD_PROGRAMOutLine)
                    .writeMaskState(RenderPhase.COLOR_MASK)
                    .transparency(LIGHTNING_TRANSPARENCY)
                    .target(setOutputState)
                    .texture(Textures.create().add(Identifier.of(MoonFabricMod.MODID,"textures/gui/blood2.png"),
                            false, false).add(Identifier.of(MoonFabricMod.MODID,"textures/gui/blood2.png"),
                            false, false).build()).build(false));



    public static RenderLayer getBloodOutLine() {
        return BLOODOutLine;
    }
    public static void setRenderTypeEndPortalProgramOutLine(net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram) {
        MRender.renderTypeEndPortalProgramOutLine = renderTypeEndPortalProgram;
    }
    ///=-
    ///=-
    ///=-
    ///=-
    ///=-
    ///=-

    public static final ShaderProgram BLOOD_PROGRAMNIG = new ShaderProgram(MRender::getRenderTypeEndPortalProgramNIG);
    @Nullable
    public static net.minecraft.client.gl.ShaderProgram getRenderTypeEndPortalProgramNIG() {
        return renderTypeEndPortalProgramNIG;
    }
    private static net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgramNIG;

    private static final RenderLayer BLOODNIG =of(
            "nig",
            VertexFormats.POSITION,
            VertexFormat.DrawMode.QUADS,
            256,
            false,
            true,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(BLOOD_PROGRAMNIG)
                    .writeMaskState(RenderPhase.COLOR_MASK)
                    .transparency(LIGHTNING_TRANSPARENCY)
                    .target(setOutputState)
                    .texture(Textures.create().add(Identifier.of(MoonFabricMod.MODID,"textures/gui/nig.png"),
                            false, false).add(Identifier.of(MoonFabricMod.MODID,"textures/gui/nig.png"),
                            false, false).build()).build(false));



    public static RenderLayer getBloodNIG() {
        return BLOODNIG;
    }
    public static void setRenderTypeEndPortalProgramNIG(net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram) {
        MRender.renderTypeEndPortalProgramNIG = renderTypeEndPortalProgram;
    }
    //--------------------------------------



    public static final ShaderProgram BLOOD_PROGRAM_common = new ShaderProgram(MRender::getRenderTypeEndPortalProgram_common);
    @Nullable
    public static net.minecraft.client.gl.ShaderProgram getRenderTypeEndPortalProgram_common() {
        return renderTypeEndPortalProgram_common;
    }
    private static net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram_common;

    private static final RenderLayer BLOOD_common =
            of("blood",
                    VertexFormats.POSITION,
                    VertexFormat.DrawMode.QUADS,
                    1536,
                    false,
                    false,
                    RenderLayer.MultiPhaseParameters.builder().program(BLOOD_PROGRAM_common)
                            .texture(Textures.create().add(Identifier.of(MoonFabricMod.MODID,"textures/gui/nig.png"),
                                    false, false).add(Identifier.of(MoonFabricMod.MODID,"textures/gui/nig.png"),
                                    false, false).build()).build(false));


    public static RenderLayer getBlood_common() {
        return BLOOD_common;
    }
    public static void setRenderTypeEndPortalProgram_common(net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram) {
        MRender.renderTypeEndPortalProgram_common = renderTypeEndPortalProgram;
    }
    public static final RenderLayer colorLight =
            of("color_light",VertexFormats.POSITION_COLOR,
                    VertexFormat.DrawMode.QUADS, 1536,
                    false, true,
                    RenderLayer.MultiPhaseParameters.builder()
                            .program(LIGHTNING_PROGRAM)
                            .writeMaskState(ALL_MASK)
                            .transparency(LIGHTNING_TRANSPARENCY)
                            .target(WEATHER_TARGET)
                            .cull(RenderPhase.DISABLE_CULLING)
                            .build(false));
    public static final RenderLayer colorLightOutLine =
            of("color_light",VertexFormats.POSITION_COLOR,
                    VertexFormat.DrawMode.QUADS, 1536,
                    false, true,
                    RenderLayer.MultiPhaseParameters.builder()
                            .program(LIGHTNING_PROGRAM)
                            .writeMaskState(ALL_MASK)
                            .transparency(LIGHTNING_TRANSPARENCY)
                            .target(setOutputState)
                            .cull(RenderPhase.DISABLE_CULLING)
                            .build(false));

}

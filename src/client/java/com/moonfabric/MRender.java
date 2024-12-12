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
import org.jetbrains.annotations.Nullable;
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
            false,
            RenderLayer.MultiPhaseParameters.builder()
                    .program(BLOOD_PROGRAMOutLine)
                    .writeMaskState(RenderPhase.COLOR_MASK)
                    .transparency(LIGHTNING_TRANSPARENCY)
                    .target(setOutputState)
                    .texture(Textures.create().add(Identifier.of(MoonFabricMod.MODID,"textures/gui/blood.png"),
                            false, false).add(Identifier.of(MoonFabricMod.MODID,"textures/gui/blood.png"),
                            false, false).build()).build(false));



    public static RenderLayer getBloodOutLine() {
        return BLOODOutLine;
    }
    public static void setRenderTypeEndPortalProgramOutLine(net.minecraft.client.gl.ShaderProgram renderTypeEndPortalProgram) {
        MRender.renderTypeEndPortalProgramOutLine = renderTypeEndPortalProgram;
    }
}

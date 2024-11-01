package com.moonfabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
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
    public static final ShaderProgram
            BLOOD_PROGRAM =
            new ShaderProgram(MRender::getRenderTypeEndPortalProgram);
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
}

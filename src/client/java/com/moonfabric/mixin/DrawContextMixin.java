package com.moonfabric.mixin;
//
//import com.moonfabric.MRender;
//import com.moonfabric.item.Ms.nightmare;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.font.TextRenderer;
//import net.minecraft.client.gui.DrawContext;
//import net.minecraft.client.gui.tooltip.TooltipComponent;
//import net.minecraft.client.gui.tooltip.TooltipPositioner;
//import net.minecraft.client.render.RenderLayer;
//import net.minecraft.client.render.VertexConsumer;
//import net.minecraft.client.render.VertexConsumerProvider;
//import net.minecraft.client.util.math.MatrixStack;
//import org.joml.Matrix4f;
//import org.joml.Vector2ic;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.List;
//
//@Mixin(DrawContext.class)
//public abstract class DrawContextMixin {
//    @Shadow @Final private MinecraftClient client;
//
//    @Shadow @Deprecated public abstract void draw(Runnable drawCallback);
//
//    @Shadow public abstract int getScaledWindowWidth();
//
//    @Shadow public abstract int getScaledWindowHeight();
//
//    @Shadow @Final private MatrixStack matrices;
//
//    @Shadow @Final private VertexConsumerProvider.Immediate vertexConsumers;
//
//    @Shadow @Deprecated protected abstract void tryDraw();
//
//    @Inject(method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V", at = @At(value = "HEAD"))
//    private void drawItemTooltips1(TextRenderer textRenderer, List<TooltipComponent> list, int x, int y, TooltipPositioner tooltipPositioner, CallbackInfo ci){
//
//        draw(()->{
//            int i = 0;
//            int j = list.size() == 1 ? -2 : 0;
//            for(TooltipComponent clienttooltipcomponent : list) {
//                int k = clienttooltipcomponent.getWidth(textRenderer);
//                if (k > i) {
//                    i = k;
//                }
//
//                j += clienttooltipcomponent.getHeight();
//            }
//            Vector2ic vector2ic = tooltipPositioner.getPosition(this.getScaledWindowWidth(), this.getScaledWindowHeight(), x, y, i, j);
//            int n = vector2ic.x();
//            int o = vector2ic.y();
//
//            if (this.stack!= null &&stack.getItem() instanceof nightmare) {
//               this.matrices.push();
//               render(MRender.getBlood(), (DrawContext) (Object) this, n, o, i, j, 400);
//               this.matrices.pop();
//            }
//        });
//    }
//
//    @Unique
//    private  void render(RenderLayer layer,DrawContext context, int x, int y, int width, int height, int z) {
//        int i = x - 3;
//        int j = y - 3;
//        int k = width + 3 + 3;
//        int l = height + 3 + 3;
//        renderHorizontalLine(layer,context, i, j - 1, k, z, -267386864);
//        renderHorizontalLine(layer,context, i, j + l, k, z, -267386864);
//        renderRectangle(layer,context, i, j, k, l, z, -267386864);
//        renderVerticalLine(layer,context, i - 1, j, l, z, -267386864);
//        renderVerticalLine(layer,context, i + k, j, l, z, -267386864);
//        renderBorder(layer,context, i, j + 1, k, l, z, 1347420415, 1344798847);
//    }
//
//    @Unique
//    private  void renderBorder(RenderLayer layer,DrawContext context, int x, int y, int width, int height, int z, int startColor, int endColor) {
//        renderVerticalLine(layer,context, x, y, height - 2, z, startColor, endColor);
//        renderVerticalLine(layer,context, x + width - 1, y, height - 2, z, startColor, endColor);
//        renderHorizontalLine(layer,context, x, y - 1, width, z, startColor);
//        renderHorizontalLine(layer,context, x, y - 1 + height - 1, width, z, endColor);
//    }
//
//    @Unique
//    private  void renderVerticalLine(RenderLayer layer,DrawContext context, int x, int y, int height, int z, int color) {
//        fill(layer,x, y, x + 1, y + height, z, color);
//    }
//
//    @Unique
//    private  void renderVerticalLine(RenderLayer layer,DrawContext context, int x, int y, int height, int z, int startColor, int endColor) {
//        fillGradient(layer,x, y, x + 1, y + height, z, startColor, endColor);
//    }
//
//    @Unique
//    private  void renderHorizontalLine(RenderLayer layer,DrawContext context, int x, int y, int width, int z, int color) {
//        fill(layer,x, y, x + width, y + 1, z, color);
//    }
//
//    @Unique
//    private  void renderRectangle(RenderLayer layer,DrawContext context, int x, int y, int width, int height, int z, int color) {
//       fill(layer,x, y, x + width, y + height, z, color);
//    }
//
//    @Unique
//    public void fill(RenderLayer layer, int x1, int y1, int x2, int y2, int z, int color) {
//        Matrix4f matrix4f = this.matrices.peek().getPositionMatrix();
//        int i;
//        if (x1 < x2) {
//            i = x1;
//            x1 = x2;
//            x2 = i;
//        }
//
//        if (y1 < y2) {
//            i = y1;
//            y1 = y2;
//            y2 = i;
//        }
//
//        VertexConsumer vertexConsumer = this.vertexConsumers.getBuffer(layer);
//        vertexConsumer.vertex(matrix4f, (float)x1, (float)y1, (float)z).color(color);
//        vertexConsumer.vertex(matrix4f, (float)x1, (float)y2, (float)z).color(color);
//        vertexConsumer.vertex(matrix4f, (float)x2, (float)y2, (float)z).color(color);
//        vertexConsumer.vertex(matrix4f, (float)x2, (float)y1, (float)z).color(color);
//        this.tryDraw();
//    }
//
//    @Unique
//    public void fillGradient(RenderLayer layer, int startX, int startY, int endX, int endY, int colorStart, int colorEnd, int z) {
//        VertexConsumer vertexConsumer = this.vertexConsumers.getBuffer(layer);
//        this.fillGradient(vertexConsumer, startX, startY, endX, endY, z, colorStart, colorEnd);
//        this.tryDraw();
//    }
//
//    @Unique
//    private void fillGradient(VertexConsumer vertexConsumer, int startX, int startY, int endX, int endY, int z, int colorStart, int colorEnd) {
//        Matrix4f matrix4f = this.matrices.peek().getPositionMatrix();
//        vertexConsumer.vertex(matrix4f, (float)startX, (float)startY, (float)z).color(colorStart);
//        vertexConsumer.vertex(matrix4f, (float)startX, (float)endY, (float)z).color(colorEnd);
//        vertexConsumer.vertex(matrix4f, (float)endX, (float)endY, (float)z).color(colorEnd);
//        vertexConsumer.vertex(matrix4f, (float)endX, (float)startY, (float)z).color(colorStart);
//    }
//
//}

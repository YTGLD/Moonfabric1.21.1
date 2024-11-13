package com.moonfabric.mixin;

import com.moonfabric.MRender;
import com.moonfabric.item.Ms.TheNecoraIC;
import com.moonfabric.item.Ms.nightmare;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.gui.tooltip.HoveredTooltipPositioner;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector2ic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin <T extends ScreenHandler> extends Screen implements ScreenHandlerProvider<T> {

    @Shadow
    @Final
    protected T handler;

    @Shadow
    @Nullable
    protected Slot focusedSlot;

    protected HandledScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "drawMouseoverTooltip")
    public void drawMouseoverTooltip(DrawContext context, int x, int y, CallbackInfo ci) {
        if (context!=null && this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            ItemStack itemStack = this.focusedSlot.getStack();
            if (this.client != null) {
                List<Text> text = Screen.getTooltipFromItem(this.client, itemStack);
                List<TooltipComponent> components = text.stream().map(Text::asOrderedText).map(TooltipComponent::of).collect(Util.toArrayList());
                TooltipPositioner positioner = HoveredTooltipPositioner.INSTANCE;
                if (!components.isEmpty()) {
                    int i = 0;
                    int j = components.size() == 1 ? -2 : 0;
                    TooltipComponent tooltipComponent;
                    for (Iterator<TooltipComponent> var8 = components.iterator(); var8.hasNext(); j += tooltipComponent.getHeight()) {
                        tooltipComponent = var8.next();
                        int k = tooltipComponent.getWidth(textRenderer);
                        if (k > i) {
                            i = k;
                        }
                    }
                    Vector2ic vector2ic = positioner.getPosition(context.getScaledWindowWidth(), context.getScaledWindowHeight(), x, y, i, j);
                    int n = vector2ic.x();
                    int o = vector2ic.y();
                    if (context.getMatrices()!=null) {
                        if (itemStack.getItem() instanceof nightmare ||
                                itemStack.getItem() instanceof TheNecoraIC) {
                            context.getMatrices().push();

                            render(MRender.getBlood(), context, n, o, i, j, 400);

                            context.getMatrices().pop();
                        }
                    }
                }
            }
        }
    }

    @Unique
    private void render(RenderLayer layer, DrawContext context, int x, int y, int width, int height, int z) {
        int i = x - 3;
        int j = y - 3;
        int k = width + 3 + 3;
        int l = height + 3 + 3;
        renderHorizontalLine(RenderLayer.getGui(), context, i, j - 1, k, z, 0xffff4040);
        renderHorizontalLine(RenderLayer.getGui(), context, i, j + l, k, z, 0xffff4040);
        renderRectangle(layer, context, i, j, k, l, z, -267386864);
        renderVerticalLine(RenderLayer.getGui(), context, i - 1, j, l, z, 0xffff0000);
        renderVerticalLine(RenderLayer.getGui(), context, i + k, j, l, z, 0xffff0000);
        renderBorder(context, i, j + 1, k, l, z, 1347420415, 1344798847);
    }

    @Unique
    private void renderBorder(DrawContext context, int x, int y, int width, int height, int z, int startColor, int endColor) {
        renderVerticalLine(RenderLayer.getGui(), context, x, y, height - 2, z, 0xffff4040, 0xffff0000);
        renderVerticalLine(RenderLayer.getGui(), context, x + width - 1, y, height - 2, z, 0xffff4040, 0xffff0000);
        renderHorizontalLine(RenderLayer.getGui(), context, x, y - 1, width, z, 0xffff4040);
        renderHorizontalLine(RenderLayer.getGui(), context, x, y - 1 + height - 1, width, z, 0xffff4040);
    }

    @Unique
    private void renderVerticalLine(RenderLayer layer, DrawContext context, int x, int y, int height, int z, int color) {
        fill(context, layer, x, y, x + 1, y + height, z, color);
    }

    @Unique
    private void renderVerticalLine(RenderLayer layer, DrawContext context, int x, int y, int height, int z, int startColor, int endColor) {
        fillGradient(context, layer, x, y, x + 1, y + height, z, startColor, endColor);
    }

    @Unique
    private void renderHorizontalLine(RenderLayer layer, DrawContext context, int x, int y, int width, int z, int color) {
        fill(context, layer, x, y, x + width, y + 1, z, color);
    }

    @Unique
    private void renderRectangle(RenderLayer layer, DrawContext context, int x, int y, int width, int height, int z, int color) {
        fill(context, layer, x, y, x + width, y + height, z, color);
    }

    @Unique
    public void fill(DrawContext context, RenderLayer layer, int x1, int y1, int x2, int y2, int z, int color) {
        Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
        int i;
        if (x1 < x2) {
            i = x1;
            x1 = x2;
            x2 = i;
        }
        if (y1 < y2) {
            i = y1;
            y1 = y2;
            y2 = i;
        }
        VertexConsumer vertexConsumer = context.getVertexConsumers().getBuffer(layer);
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y1, (float) z).color(color);
        vertexConsumer.vertex(matrix4f, (float) x1, (float) y2, (float) z).color(color);
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y2, (float) z).color(color);
        vertexConsumer.vertex(matrix4f, (float) x2, (float) y1, (float) z).color(color);
    }

    @Unique
    public void fillGradient(DrawContext context, RenderLayer layer, int startX, int startY, int endX, int endY, int colorStart, int colorEnd, int z) {
        VertexConsumer vertexConsumer = context.getVertexConsumers().getBuffer(layer);
        this.fillGradient(context, vertexConsumer, startX, startY, endX, endY, z, colorStart, colorEnd);
    }

    @Unique
    private void fillGradient(DrawContext context, VertexConsumer vertexConsumer, int startX, int startY, int endX, int endY, int z, int colorStart, int colorEnd) {
        Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
        vertexConsumer.vertex(matrix4f, (float) startX, (float) startY, (float) z).color(colorStart);
        vertexConsumer.vertex(matrix4f, (float) startX, (float) endY, (float) z).color(colorEnd);
        vertexConsumer.vertex(matrix4f, (float) endX, (float) endY, (float) z).color(colorEnd);
        vertexConsumer.vertex(matrix4f, (float) endX, (float) startY, (float) z).color(colorStart);
    }
}

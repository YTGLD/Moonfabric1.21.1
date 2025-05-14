package com.moonfabric.mixin;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.advancement.IAdvancementWidget;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.PlacedAdvancement;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.advancement.AdvancementTab;
import net.minecraft.client.gui.screen.advancement.AdvancementWidget;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(AdvancementTab.class)
public abstract class ATabMixin {

    @Shadow private double originX;

    @Shadow private double originY;

    @Shadow @Final private AdvancementDisplay display;

    @Shadow @Final private AdvancementWidget rootWidget;

    @Shadow @Final private Map<AdvancementEntry, AdvancementWidget> widgets;

    @Shadow private float alpha;

    @Shadow @Final private int index;

    @Shadow private boolean initialized;

    @Shadow @Final private PlacedAdvancement root;

    @Inject(at = @At("RETURN"), method = "render")
    public void moonstone1rawContents(DrawContext context, int x, int y, CallbackInfo ci){
        if (root.getAdvancementEntry().id().equals(Identifier.of(MoonFabricMod.MODID,"moonfabric/root"))) {
            context.enableScissor(x, y, x + 234, y + 113);
            context.getMatrices().push();
            context.getMatrices().translate(x, y, 0.0f);
            Identifier identifier = this.display.getBackground().orElse(TextureManager.MISSING_IDENTIFIER);
            int i = MathHelper.floor(this.originX);
            int j = MathHelper.floor(this.originY);
            int k = i % 16;
            int l = j % 16;
            for (int m = -1; m <= 15; ++m) {
                for (int n = -1; n <= 8; ++n) {
                    context.drawTexture(identifier, k + 16 * m, l + 16 * n, 0.0f, 0.0f, 16, 16, 16, 16);
                }
            }
            this.rootWidget.renderLines(context, i, j, true);
            this.rootWidget.renderLines(context, i, j, false);
            if (rootWidget instanceof IAdvancementWidget advancementWidget) {
                advancementWidget.moonfabric121moonstone1$draw(context, i, j);
            }
            context.getMatrices().pop();
            context.disableScissor();
        }
    }

    @Inject(at = @At("RETURN"), method = "drawWidgetTooltip")
    public void  moonstone1drawTooltips(DrawContext context, int mouseX, int mouseY, int x, int y, CallbackInfo ci) {
        if (root.getAdvancementEntry().id().equals(Identifier.of(MoonFabricMod.MODID,"moonfabric/root"))) {

        context.getMatrices().push();
        context.getMatrices().translate(0.0f, 0.0f, -200.0f);
        context.fill(0, 0, 234, 113, MathHelper.floor((float)(this.alpha * 255.0f)) << 24);
        boolean bl = false;
        int i = MathHelper.floor(this.originX);
        int j = MathHelper.floor(this.originY);
        if (mouseX > 0 && mouseX < 234 && mouseY > 0 && mouseY < 113) {
            for (AdvancementWidget advancementWidget : this.widgets.values()) {
                if (advancementWidget  instanceof IAdvancementWidget iAdvancementWidget) {
                    if (!advancementWidget.shouldRender(i, j, mouseX, mouseY)) {
                        continue;
                    }
                    iAdvancementWidget.moonfabric121moonstone1$drawHover(context, i, j, this.alpha, x, y);
                    break;
                }
            }
        }
        context.getMatrices().pop();
    }
}}

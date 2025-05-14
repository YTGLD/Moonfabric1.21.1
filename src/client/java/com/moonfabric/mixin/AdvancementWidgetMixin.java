package com.moonfabric.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.moonfabric.advancement.IAdvancementWidget;
import com.moonfabric.MoonFabricMod;
import com.moonfabric.advancement.WidgetTypes;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.advancement.AdvancementTab;
import net.minecraft.client.gui.screen.advancement.AdvancementWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(AdvancementWidget.class)
public class AdvancementWidgetMixin implements IAdvancementWidget {
    @Shadow @Final private int x;

    @Shadow @Final private int width;


    @Shadow private @Nullable AdvancementProgress progress;

    @Shadow @Final private AdvancementDisplay display;

    @Shadow @Final private List<AdvancementWidget> children;

    @Shadow @Final private List<OrderedText> description;

    @Shadow @Final private MinecraftClient client;

    @Shadow @Final private AdvancementTab tab;

    @Shadow @Final private int y;

    @Shadow @Final private OrderedText title;

    @Override
    public void moonfabric121moonstone1$draw(DrawContext context, int x, int y) {
        if (!this.display.isHidden() || this.progress != null && this.progress.isDone()) {
            float f = this.progress == null ? 0.0f : this.progress.getProgressBarPercentage();
            WidgetTypes advancementObtainedStatus = f >= 1.0f ? WidgetTypes.OBTAINED : WidgetTypes.UNOBTAINED;
            context.drawGuiTexture(advancementObtainedStatus.getFrameTexture(this.display.getFrame()), x + this.x + 3, y + this.y, 26, 26);
            context.drawItemWithoutEntity(this.display.getIcon(), x + this.x + 8, y + this.y + 5);
        }
        for (AdvancementWidget advancementWidget : this.children) {
            if (advancementWidget instanceof IAdvancementWidget advancementWidget1) {
                advancementWidget1.moonfabric121moonstone1$draw(context, x, y);
            }
        }

    }



    @Override
    public void moonfabric121moonstone1$drawHover(DrawContext context, int originX, int originY, float alpha, int x, int y ){
        WidgetTypes advancementObtainedStatus3;
        WidgetTypes advancementObtainedStatus2;
        WidgetTypes advancementObtainedStatus;
        boolean bl = x + originX + this.x + this.width + 26 >= this.tab.getScreen().width;
        Text text = this.progress == null ? null : this.progress.getProgressBarFraction();
        int i = text == null ? 0 : this.client.textRenderer.getWidth((StringVisitable)text);
        boolean bl2 = 113 - originY - this.y - 26 <= 6 + this.description.size() * this.client.textRenderer.fontHeight;
        float f = this.progress == null ? 0.0f : this.progress.getProgressBarPercentage();
        int j = MathHelper.floor((float)(f * (float)this.width));
        if (f >= 1.0f) {
            j = this.width / 2;
            advancementObtainedStatus = WidgetTypes.OBTAINED;
            advancementObtainedStatus2 = WidgetTypes.OBTAINED;
            advancementObtainedStatus3 = WidgetTypes.OBTAINED;
        } else if (j < 2) {
            j = this.width / 2;
            advancementObtainedStatus = WidgetTypes.UNOBTAINED;
            advancementObtainedStatus2 = WidgetTypes.UNOBTAINED;
            advancementObtainedStatus3 = WidgetTypes.UNOBTAINED;
        } else if (j > this.width - 2) {
            j = this.width / 2;
            advancementObtainedStatus = WidgetTypes.OBTAINED;
            advancementObtainedStatus2 = WidgetTypes.OBTAINED;
            advancementObtainedStatus3 = WidgetTypes.UNOBTAINED;
        } else {
            advancementObtainedStatus = WidgetTypes.OBTAINED;
            advancementObtainedStatus2 = WidgetTypes.UNOBTAINED;
            advancementObtainedStatus3 = WidgetTypes.UNOBTAINED;
        }
        int k = this.width - j;
        RenderSystem.enableBlend();
        int l = originY + this.y;
        int m = bl ? originX + this.x - this.width + 26 + 6 : originX + this.x;
        int n = 32 + this.description.size() * this.client.textRenderer.fontHeight;
        if (!this.description.isEmpty()) {
            if (bl2) {
                context.drawGuiTexture(Identifier.of(MoonFabricMod.MODID,"advancements/box"), m, l + 26 - n, this.width, n);
            } else {
                context.drawGuiTexture(Identifier.of(MoonFabricMod.MODID,"advancements/box"), m, l, this.width, n);
            }
        }
        context.drawGuiTexture(advancementObtainedStatus.getBoxTexture(), 200, 26, 0, 0, m, l, j, 26);
        context.drawGuiTexture(advancementObtainedStatus2.getBoxTexture(), 200, 26, 200 - k, 0, m + j, l, k, 26);
        context.drawGuiTexture(advancementObtainedStatus3.getFrameTexture(this.display.getFrame()), originX + this.x + 3, originY + this.y, 26, 26);
        if (bl) {
            context.drawTextWithShadow(this.client.textRenderer, this.title, m + 5, originY + this.y + 9, -1);
            if (text != null) {
                context.drawTextWithShadow(this.client.textRenderer, text, originX + this.x - i, originY + this.y + 9, Colors.WHITE);
            }
        } else {
            context.drawTextWithShadow(this.client.textRenderer, this.title, originX + this.x + 32, originY + this.y + 9, -1);
            if (text != null) {
                context.drawTextWithShadow(this.client.textRenderer, text, originX + this.x + this.width - i - 5, originY + this.y + 9, Colors.WHITE);
            }
        }
        if (bl2) {
            for (int o = 0; o < this.description.size(); ++o) {
                context.drawText(this.client.textRenderer, this.description.get(o), m + 5, l + 26 - n + 7 + o * this.client.textRenderer.fontHeight, -5592406, false);
            }
        } else {
            for (int o = 0; o < this.description.size(); ++o) {
                context.drawText(this.client.textRenderer, this.description.get(o), m + 5, originY + this.y + 9 + 17 + o * this.client.textRenderer.fontHeight, -5592406, false);
            }
        }
        context.drawItemWithoutEntity(this.display.getIcon(), originX + this.x + 8, originY + this.y + 5);
    }

}

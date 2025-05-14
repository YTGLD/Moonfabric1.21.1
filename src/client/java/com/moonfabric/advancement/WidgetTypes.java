package com.moonfabric.advancement;

import com.moonfabric.MoonFabricMod;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.util.Identifier;

public enum WidgetTypes {
    OBTAINED(Identifier.of(MoonFabricMod.MODID,"advancements/box_obtained"),
            Identifier.of(MoonFabricMod.MODID,"advancements/task_frame_obtained"),
            Identifier.of(MoonFabricMod.MODID,"advancements/challenge_frame_obtained"),
            Identifier.of(MoonFabricMod.MODID,"advancements/goal_frame_obtained")),
    UNOBTAINED(Identifier.of(MoonFabricMod.MODID,"advancements/box_unobtained"),
            Identifier.of(MoonFabricMod.MODID,"advancements/task_frame_unobtained"),
            Identifier.of(MoonFabricMod.MODID,"advancements/challenge_frame_unobtained"),
            Identifier.of(MoonFabricMod.MODID,"advancements/goal_frame_unobtained"));

    private final Identifier boxTexture;
    private final Identifier taskFrameTexture;
    private final Identifier challengeFrameTexture;
    private final Identifier goalFrameTexture;

    private WidgetTypes(Identifier boxTexture, Identifier taskFrameTexture, Identifier challengeFrameTexture, Identifier goalFrameTexture) {
        this.boxTexture = boxTexture;
        this.taskFrameTexture = taskFrameTexture;
        this.challengeFrameTexture = challengeFrameTexture;
        this.goalFrameTexture = goalFrameTexture;
    }

    public Identifier getBoxTexture() {
        return this.boxTexture;
    }

    public Identifier getFrameTexture(AdvancementFrame frame) {
        return switch (frame) {
            default -> throw new MatchException(null, null);
            case AdvancementFrame.TASK -> this.taskFrameTexture;
            case AdvancementFrame.CHALLENGE -> this.challengeFrameTexture;
            case AdvancementFrame.GOAL -> this.goalFrameTexture;
        };
    }
}

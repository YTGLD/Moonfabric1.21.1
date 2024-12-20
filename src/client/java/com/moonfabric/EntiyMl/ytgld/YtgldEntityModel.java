package com.moonfabric.EntiyMl.ytgld;

import com.google.common.collect.ImmutableList;
import com.moonfabric.Entity.nightmare_giant;
import com.moonfabric.Entity.ytgld;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.animation.WardenAnimations;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class YtgldEntityModel<T extends ytgld> extends SinglePartEntityModel<T> {
    private final ModelPart root;
    public final ModelPart bone;
    public final ModelPart body;
    public final ModelPart head;
    public final ModelPart rightTendril;
    public final ModelPart leftTendril;
    public final ModelPart leftLeg;
    public final ModelPart leftArm;
    public final ModelPart leftRibcage;
    public final ModelPart rightArm;
    public final ModelPart rightLeg;
    public final ModelPart rightRibcage;
    private final List<ModelPart> tendrils;
    private final List<ModelPart> justBody;
    private final List<ModelPart> headAndLimbs;
    private final List<ModelPart> bodyHeadAndLimbs;

    public YtgldEntityModel(ModelPart root) {
        super(RenderLayer::getEntityCutoutNoCull);
        this.root = root;
        this.bone = root.getChild("bone");
        this.body = this.bone.getChild("body");
        this.head = this.body.getChild("head");
        this.rightLeg = this.bone.getChild("right_leg");
        this.leftLeg = this.bone.getChild("left_leg");
        this.rightArm = this.body.getChild("right_arm");
        this.leftArm = this.body.getChild("left_arm");
        this.rightTendril = this.head.getChild("right_tendril");
        this.leftTendril = this.head.getChild("left_tendril");
        this.rightRibcage = this.body.getChild("right_ribcage");
        this.leftRibcage = this.body.getChild("left_ribcage");
        this.tendrils = ImmutableList.of(this.leftTendril, this.rightTendril);
        this.justBody = ImmutableList.of(this.body);
        this.headAndLimbs = ImmutableList.of(this.head, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
        this.bodyHeadAndLimbs = ImmutableList.of(this.body, this.head, this.leftArm, this.rightArm, this.leftLeg, this.rightLeg);
    }

    public void setAngles(T wardenEntity, float f, float g, float h, float i, float j) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        float k = h - (float)wardenEntity.age;
        this.setHeadAngle(i, j);
        this.setLimbAngles(f, g);
        this.setHeadAndBodyAngles(h);
        this.setTendrilPitches(wardenEntity, h, k);
        this.updateAnimation(wardenEntity.attackingAnimationState, WardenAnimations.ATTACKING, h);
        this.updateAnimation(wardenEntity.chargingSonicBoomAnimationState, WardenAnimations.CHARGING_SONIC_BOOM, h);
        this.updateAnimation(wardenEntity.diggingAnimationState, WardenAnimations.DIGGING, h);
        this.updateAnimation(wardenEntity.emergingAnimationState, WardenAnimations.EMERGING, h);
        this.updateAnimation(wardenEntity.roaringAnimationState, WardenAnimations.ROARING, h);
        this.updateAnimation(wardenEntity.sniffingAnimationState, WardenAnimations.SNIFFING, h);
    }

    private void setHeadAngle(float yaw, float pitch) {
        this.head.pitch = pitch * 0.017453292F;
        this.head.yaw = yaw * 0.017453292F;
        this.head.pitch -= 35 * 0.017453292F;
    }

    private void setHeadAndBodyAngles(float animationProgress) {
        float f = animationProgress * 0.1F;
        float g = MathHelper.cos(f);
        float h = MathHelper.sin(f);
        ModelPart var10000 = this.head;
        var10000.roll += 0.06F * g;
        var10000 = this.head;
        var10000.pitch += 0.06F * h;
        var10000 = this.body;
        var10000.roll += 0.025F * h;
        var10000 = this.body;
        var10000.pitch += 0.025F * g;
    }

    private void setLimbAngles(float angle, float distance) {
        float f = Math.min(0.5F, 3.0F * distance);
        float g = angle * 0.8662F;
        float h = MathHelper.cos(g);
        float i = MathHelper.sin(g);
        float j = Math.min(0.35F, f);
        ModelPart var10000 = this.head;
        var10000.roll += 0.3F * i * f;
        var10000 = this.head;
        var10000.pitch += 1.2F * MathHelper.cos(g + 1.5707964F) * j;
        this.body.roll = 0.1F * i * f;
        this.body.pitch = 1.0F * h * j;
        this.leftLeg.pitch = 1.0F * h * f;
        this.rightLeg.pitch = 1.0F * MathHelper.cos(g + 3.1415927F) * f;
        this.leftArm.pitch = -(0.8F * h * f);
        this.leftArm.roll = 0.0F;
        this.rightArm.pitch = -(0.8F * i * f);
        this.rightArm.roll = 0.0F;

        this.body.pitch += 35 * 0.017453292F;
        this.setArmPivots();
    }

    private void setArmPivots() {
        this.leftArm.yaw = 0.0F;
        this.leftArm.pivotZ = 1.0F;
        this.leftArm.pivotX = 13.0F;
        this.leftArm.pivotY = -13.0F;
        this.rightArm.yaw = 0.0F;
        this.rightArm.pivotZ = 1.0F;
        this.rightArm.pivotX = -13.0F;
        this.rightArm.pivotY = -13.0F;


        this.rightArm.pitch -= 35 * 0.017453292F;
        this.leftArm.pitch -= 35 * 0.017453292F;

    }

    private void setTendrilPitches(T warden, float animationProgress, float tickDelta) {
        float f = warden.getTendrilPitch(tickDelta) * (float)(Math.cos((double)animationProgress * 2.25) * Math.PI * 0.10000000149011612);
        this.leftTendril.pitch = f;
        this.rightTendril.pitch = -f;
    }

    public ModelPart getPart() {
        return this.root;
    }

    public List<ModelPart> getTendrils() {
        return this.tendrils;
    }

    public List<ModelPart> getBody() {
        return this.justBody;
    }

    public List<ModelPart> getHeadAndLimbs() {
        return this.headAndLimbs;
    }

    public List<ModelPart> getBodyHeadAndLimbs() {
        return this.bodyHeadAndLimbs;
    }
}


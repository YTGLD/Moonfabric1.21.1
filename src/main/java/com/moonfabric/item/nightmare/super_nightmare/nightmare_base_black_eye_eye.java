package com.moonfabric.item.nightmare.super_nightmare;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

public class nightmare_base_black_eye_eye  extends com.moonfabric.item.Ms.SNightmare{


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_black_eye_eye.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_black_eye_eye.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_black_eye_eye.tool.string.2").formatted(Formatting.DARK_RED));
    }


    public static void attLook(DamageSource source , LivingEntity living, CallbackInfoReturnable<Float> cir){
        if (source.getAttacker() instanceof PlayerEntity player ){
            if (HasCurio.has(init.nightmare_base_black_eye_eye, player)) {
                Entity entity = getPlayerLookTarget(player.getEntityWorld(), player);
                if (entity instanceof LivingEntity living0) {
                    if (living0 == living) {
                        cir.setReturnValue(cir.getReturnValue()*2);
                    }
                }
            }
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        Entity entitys = getPlayerLookTarget(entity .getWorld(), entity);
        if (entitys instanceof LivingEntity living0){
            living0.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 30, 3));
            living0.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 30, 2));
            living0.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30, 5));
            living0.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 30, 3));
        }
    }


    public static Entity getPlayerLookTarget(World world, LivingEntity living) {
        Entity pointedEntity = null;
        double range = 20.0D;
        Vec3d srcVec = living.getCameraPosVec(1.0F);
        Vec3d lookVec = living.getRotationVec(1.0F);
        Vec3d destVec = srcVec.add(lookVec.x * range, lookVec.y * range, lookVec.z * range);
        float var9 = 1.0F;
        List<Entity> possibleList = world.getEntitiesByClass(Entity.class, living.getBoundingBox().stretch(lookVec.x * range, lookVec.y * range, lookVec.z * range).expand(var9, var9, var9), entity -> entity != living);
        double hitDist = 0;

        for (Entity possibleEntity : possibleList) {
            if (possibleEntity.isCollidable()) {
                float borderSize = possibleEntity.getTargetingMargin();
                Box collisionBB = possibleEntity.getBoundingBox().expand(borderSize, borderSize, borderSize);
                Optional<Vec3d> interceptPos = collisionBB.raycast(srcVec, destVec);

                if (collisionBB.contains(srcVec)) {
                    if (0.0D < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = 0.0D;
                    }
                } else if (interceptPos.isPresent()) {
                    double possibleDist = srcVec.distanceTo(interceptPos.get());

                    if (possibleDist < hitDist || hitDist == 0.0D) {
                        pointedEntity = possibleEntity;
                        hitDist = possibleDist;
                    }
                }
            }
        }
        return pointedEntity;
    }
}

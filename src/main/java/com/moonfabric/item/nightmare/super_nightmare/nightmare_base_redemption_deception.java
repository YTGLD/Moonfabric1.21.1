package com.moonfabric.item.nightmare.super_nightmare;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class nightmare_base_redemption_deception extends com.moonfabric.item.Ms.SNightmare{



    public static void LivingIncomingDamageEvent(DamageSource source, LivingEntity entity, CallbackInfoReturnable<Float> cir){
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(init.nightmare_base_redemption_deception, player)){

                if (!player.getItemCooldownManager().isCoolingDown(init.nightmare_base_redemption_deception)) {
                    if (cir.getReturnValue() > player.getHealth()) {

                        player.heal(player.getMaxHealth());
                        player.timeUntilRegen += 140;
                        Vec3d playerPos = player.getPos().add(0, 0.75, 0);
                        float range =10;
                        List<LivingEntity> entities =
                                player.getWorld().getEntitiesByClass(LivingEntity.class,
                                        new Box(playerPos.x - range,
                                                playerPos.y - range,
                                                playerPos.z - range,
                                                playerPos.x + range,
                                                playerPos.y + range,
                                                playerPos.z + range)
                                , EntityPredicates.EXCEPT_SPECTATOR);

                        for (LivingEntity living : entities){
                            if (living instanceof MobEntity targeting){
                                if (targeting.getTarget()!=null && targeting.getTarget()==(player)){
                                    targeting.setTarget(null);
                                }
                            }
                        }
                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 1, 1);
                        cir.setReturnValue(0f);

                        player.getItemCooldownManager().set(init.nightmare_base_redemption_deception, 1200);
                    }
                }
            }
        }
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_redemption_deception.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_redemption_deception.tool.string.1").formatted(Formatting.DARK_RED));
    }

}

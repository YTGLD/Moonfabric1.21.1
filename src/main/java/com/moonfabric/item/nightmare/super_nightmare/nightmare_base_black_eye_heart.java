package com.moonfabric.item.nightmare.super_nightmare;

import com.moonfabric.HasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class nightmare_base_black_eye_heart  extends com.moonfabric.item.Ms.SNightmare{

    public static void heal(LivingEntity living,float a){
        Vec3d playerPos = living.getPos().add(0, 0.75, 0);
        float range = 8;
        List<PlayerEntity> entities =
                living.getEntityWorld().getEntitiesByClass(PlayerEntity.class,
                        new Box(playerPos.x - range,
                                playerPos.y - range,
                                playerPos.z - range,
                                playerPos.x + range,
                                playerPos.y + range,
                                playerPos.z + range)
                        , EntityPredicates.EXCEPT_SPECTATOR);
        for (PlayerEntity player : entities){
            if (!(living ==(player))) {
                if (HasCurio.has(init.nightmare_base_black_eye_heart, player)) {
                    player.heal(a);
                }
            }
        }
    }
    public static void hurt(DamageSource source, CallbackInfoReturnable<Float> cir){
        if (source.getSource() instanceof LivingEntity living) {
            Vec3d playerPos = living.getPos().add(0, 0.75, 0);
            float range = 8;
            List<PlayerEntity> entities =
                    living.getEntityWorld().getEntitiesByClass(PlayerEntity.class,
                            new Box(playerPos.x - range,
                                    playerPos.y - range,
                                    playerPos.z - range,
                                    playerPos.x + range,
                                    playerPos.y + range,
                                    playerPos.z + range)
                            , EntityPredicates.EXCEPT_SPECTATOR);
            for (PlayerEntity player : entities){
                if (!(living ==(player))) {
                    if (HasCurio.has(init.nightmare_base_black_eye_heart, player)) {
                        cir.setReturnValue(cir.getReturnValue()*1.25f);
                    }
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("item.nightmare_base_black_eye_heart.tool.string").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_black_eye_heart.tool.string.1").formatted(Formatting.DARK_RED));
        tooltip.add(Text.translatable("item.nightmare_base_black_eye_heart.tool.string.2").formatted(Formatting.DARK_RED));
    }


}

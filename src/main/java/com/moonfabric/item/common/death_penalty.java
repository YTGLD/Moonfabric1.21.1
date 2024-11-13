package com.moonfabric.item.common;

import com.moonfabric.Entity.penalty;
import com.moonfabric.HasCurio;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Random;

public class death_penalty extends TheNecoraIC {

    public static void hurts(LivingEntity living , DamageSource source){
        if (source.getSource() instanceof PlayerEntity player){
            if (HasCurio.has(init.death_penalty,player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.death_penalty)) {

                    penalty head = new penalty(InItEntity.penalty, player.getEntityWorld());
                    head.setPos(living.getX(), living.getY() + 1, living.getZ());
                    head.setVelocity(new Random().nextDouble(0.3), new Random().nextDouble(0.2) + 0.1f, new Random().nextDouble(0.32));
                    head.setOwner(player);
                    head.setOwnerUuid(player.getUuid());

                    player.getWorld().spawnEntity(head);

                    player.getItemCooldownManager().set(init.death_penalty, 20);

                }
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("moonfabric.tooltip.death_penalty").formatted(Formatting.RED));
        tooltip.add(Text.translatable("moonfabric.tooltip.death_penalty.1").formatted(Formatting.RED));
    }
}


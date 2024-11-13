package com.moonfabric.item.common;


import com.moonfabric.Entity.head;
import com.moonfabric.HasCurio;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;
import java.util.Random;

public class double_head extends TheNecoraIC {

    public static void hurts(LivingEntity living , DamageSource source){
        Random random = new Random();
        if (source.getSource() instanceof PlayerEntity player){
            if (HasCurio.has(init.double_head,player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.double_head)) {
                    if (random.nextInt(100) <= 25) {
                        {
                            head head = new head(InItEntity.head, player.getEntityWorld());
                            head.setPos(living.getX(), living.getY() + 1, living.getZ());
                            head.setVelocity(new Random().nextDouble(0.1), new Random().nextDouble(0.11) + 0.1f, new Random().nextDouble(0.12));
                            head.setOwner(player);
                            head.setOwnerUuid(player.getUuid());
                            player.playSound(SoundEvents.ENTITY_WITHER_HURT, 1, 1);

                            player.getWorld().spawnEntity(head);

                            player.getItemCooldownManager().set(init.double_head, 20);
                        }
                        {
                            head head = new head(InItEntity.head, player.getEntityWorld());
                            head.setPos(living.getX(), living.getY() + 1, living.getZ());
                            head.setVelocity(-new Random().nextDouble(0.1), -new Random().nextDouble(0.11) + 0.1f, -new Random().nextDouble(0.12));
                            head.setOwner(player);
                            head.setOwnerUuid(player.getUuid());
                            player.playSound(SoundEvents.ENTITY_WITHER_HURT, 1, 1);

                            player.getWorld().spawnEntity(head);

                            player.getItemCooldownManager().set(init.double_head,20);
                        }
                    }
                }
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.translatable("moonfabric.tooltip.double_head").formatted(Formatting.RED));
    }
}

package com.moonfabric.item.nightmare.super_nightmare;

import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
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

import java.util.List;

public class nightmare_base_fool_betray extends com.moonfabric.item.Ms.SNightmare{


    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity  instanceof PlayerEntity player){
            if (!player.getItemCooldownManager().isCoolingDown(this)) {
                Vec3d playerPos = player.getPos().add(0, 0.75, 0);
                float range = 10;
                List<MobEntity> entities =
                        player.getWorld().getEntitiesByClass(MobEntity.class,
                                new Box(playerPos.x - range,
                                        playerPos.y - range,
                                        playerPos.z - range,
                                        playerPos.x + range,
                                        playerPos.y + range,
                                        playerPos.z + range),
                                EntityPredicates.EXCEPT_SPECTATOR);
                for (MobEntity mob : entities) {
                    if (mob instanceof Ownable ownableEntity) {
                        if (ownableEntity.getOwner()!=null&& !(ownableEntity.getOwner() ==(player))) {
                            ownableEntity.getOwner().damage(ownableEntity.getOwner().getDamageSources().dryOut(),
                                    player.getMaxHealth() / 10);
                        }else {
                            if (mob instanceof TameableEntity animal) {
                                for (StatusEffectInstance effect : player.getStatusEffects()) {
                                    if (effect != null
                                            && effect.getEffectType().value().isBeneficial()) {
                                        animal.addStatusEffect(effect);
                                    }
                                }
                                animal.setOwner(player);
                                animal.setOwnerUuid(player.getUuid());
                            }
                        }
                    }


                    if (player.getLastAttacker() != null) {
                        mob.setTarget(player.getLastAttacker());
                    }
                    entity .getWorld().playSound(null, entity .getX(), entity .getY(), entity .getZ(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.AMBIENT, 1, 1);
                    player.getItemCooldownManager().set(this, 100 + entities.size() * 100);
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> pTooltipComponents, TooltipType type) {
        super.appendTooltip(stack, context, pTooltipComponents, type);
        pTooltipComponents.add(Text.translatable("item.nightmare_base_fool_betray.tool.string").formatted(Formatting.DARK_RED));
        pTooltipComponents.add(Text.translatable("item.nightmare_base_fool_betray.tool.string.1").formatted(Formatting.DARK_RED));
        pTooltipComponents.add(Text.translatable("item.nightmare_base_fool_betray.tool.string.2").formatted(Formatting.DARK_RED));
        pTooltipComponents.add(Text.translatable("item.nightmare_base_fool_betray.tool.string.3").formatted(Formatting.DARK_RED));
    }


}



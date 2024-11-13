package com.moonfabric.item.common.CurseOrDoom;

import com.moonfabric.HasCurio;
import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class doomsoul extends doom {
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(this,player)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (player.isDead()){
                player.getEntityWorld().createExplosion(null,player.getX(), player.getY(), player.getZ(), 5.0f, true, World.ExplosionSourceType.MOB);
            }
        }
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.doomsoul.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomsoul.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomsoul.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomsoul.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomsoul.5").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
}

package com.moonfabric.item.common.Mise;

import com.moonfabric.Ievent.old.IEventHurt;
import com.moonfabric.HasCurio;
import com.moonfabric.item.Ms.extend.ItemTir;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class blackhead extends ItemTir {
    public blackhead(){
        IEventHurt.ALLOW_DAMAGE.register((livingEntity, source, amt)->{
            if (livingEntity instanceof PlayerEntity player) {
                if (HasCurio.has(this, player)) {
                    if (!player.getItemCooldownManager().isCoolingDown(this)){
                        player.getItemCooldownManager().set(this, 140);
                        return false;
                    }
                }
            }
            return true;
        });
    }
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(this,player)){
                return false;
            }
        }
        return true;
    }

    public static float anInt = 0;
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            anInt+= 0.75f;
        }
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.blackhead.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.blackhead.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));


    }
}




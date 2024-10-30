package com.moonfabric.item.common.Blood;

import com.moonfabric.Entity.line;
import com.moonfabric.hasCurio;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.List;

public class blood_amout extends TrinketItem {
    public blood_amout() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).fireproof());
    }

    public static void Hurt(LivingEntity me, DamageSource source) {

        if (me instanceof PlayerEntity player) {

            if (source.getSource() != null && hasCurio.has(init.blood_amout, player)) {

                if (!player.getItemCooldownManager().isCoolingDown(init.blood_amout)) {
                    line line = new line(InItEntity.Line, player.getEntityWorld());
                    line.setPos(player.getX(),player.getY(),player.getZ());
                    line.setOwnerUuid(player.getUuid());
                    player.getWorld().spawnEntity(line);

                    player.getItemCooldownManager().set(init.blood_amout, 10);
                }
            }
        }
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("moonfabric.tooltip.blood_amout").formatted(Formatting.GRAY));

    }
}

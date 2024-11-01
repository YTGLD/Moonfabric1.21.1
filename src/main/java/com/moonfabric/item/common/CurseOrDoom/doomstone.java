package com.moonfabric.item.common.CurseOrDoom;

import com.google.common.collect.Multimap;
import com.moonfabric.Ievent.IEventHurt;
import com.moonfabric.hasCurio;
import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class doomstone extends doom {
    public doomstone(){
        IEventHurt.ALLOW_DAMAGE.register((livingEntity, source, amt)->{
            if (livingEntity instanceof PlayerEntity player) {
                if (hasCurio.has(this, player)) {
                    if (amt <= 1){
                        return false;
                    }
                    if (!player.getItemCooldownManager().isCoolingDown(this)) {
                        player.getItemCooldownManager().set(this, 24);
                        return false;
                    }else {
                        amt *= 4;
                        player.setHealth(player.getHealth() - amt);
                    }
                }
            }
            return true;
        });
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (hasCurio.has(this,player)){
                return false;
            }
        }

        return true;
    }



    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

       SlotAttributes.addSlotModifier(modifiers,"hand/ring",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),2, EntityAttributeModifier.Operation.ADD_VALUE);

        return modifiers;
    }
    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.doomstone.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomstone.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomstone.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));

    }
}

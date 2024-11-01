package com.moonfabric.item.nightmare;

import com.google.common.collect.Multimap;
import com.moonfabric.hasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.nightmare;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class nightmareanchor extends nightmare {

    public static void die( LivingEntity entity){
        if (hasCurio.has(init.nightmareanchor,entity)){
            TrinketsApi.getTrinketComponent(entity).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, stack) -> {
                    if (stack.get(Data.CUSTOM_DATA)!= null) {
                        NbtCompound tag = stack.get(Data.CUSTOM_DATA);
                        tag.putDouble("x",entity.getX());
                        tag.putDouble("y",entity.getY());
                        tag.putDouble("z",entity.getZ());
                    }
                });
            });
        }
    }
    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        SlotAttributes.addSlotModifier(modifiers,"hand/ring",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),1, EntityAttributeModifier.Operation.ADD_VALUE);

        return modifiers;
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA)== null) {
            stack.set(Data.CUSTOM_DATA, new NbtCompound());
        }
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int i = this.getMaxUseTime(stack,user) - remainingUseTicks;

        if (user instanceof PlayerEntity player) {
            float f = BowItem.getPullProgress(i);
            if (f == 1.0f) {
                NbtCompound tag = stack.get(Data.CUSTOM_DATA);
                if (tag != null){
                    if (player.getWorld().getDimension().toString().contains(tag.getString("level"))) {
                        if (tag.getDouble("x") != 0
                                && tag.getDouble("y") != 0
                                && tag.getDouble("z") != 0) {

                            player.setPos(tag.getDouble("x"),
                                    tag.getDouble("y"),
                                    tag.getDouble("z"));

                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 200, 2));
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 2));
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 2));


                        }
                    }
                }
            }
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.5").formatted(Formatting.GRAY));

        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.6").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.nightmareanchor.tool.string.7").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }

}

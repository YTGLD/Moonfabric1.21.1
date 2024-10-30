package com.moonfabric.item.common.CurseOrDoom;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

public class doomfruit extends Item {
    public doomfruit() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.RARE).food(new FoodComponent.Builder().nutrition(10).alwaysEdible().saturationModifier(10.0f).build()).fireproof());
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 32;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack stack1 = super.finishUsing(stack, world, user);
        TrinketsApi.getTrinketComponent(user).ifPresent((trinketComponent)->{
            Multimap<String, EntityAttributeModifier> modifierMultimap = trinketComponent.getModifiers();
            modifierMultimap.put("hand/ring",new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),1.0, EntityAttributeModifier.Operation.ADD_VALUE));

            user.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 2000, 2));
            user.damage(user.getDamageSources().magic(), 10);
            trinketComponent.addPersistentModifiers(modifierMultimap);
        });
        return stack1;
    }

  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.doomfruit.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.doomfruit.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));

    }
}

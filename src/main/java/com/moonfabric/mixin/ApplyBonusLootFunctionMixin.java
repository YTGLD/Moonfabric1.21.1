package com.moonfabric.mixin;

import com.moonfabric.Ievent.evt.LootOrBlockLuck;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ApplyBonusLootFunction.class)
public class ApplyBonusLootFunctionMixin {

    @Shadow @Final private RegistryEntry<Enchantment> enchantment;

    @ModifyVariable(method = "process", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getLevel(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/item/ItemStack;)I", ordinal = 0), index = 4)
    public int curios$applyFont(int original, ItemStack stack, LootContext lootContext) {
        Entity entity = lootContext.get(LootContextParameters.THIS_ENTITY);
        return enchantment == lootContext.getWorld().createCommandRegistryWrapper(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE)
                ? original

                + LootOrBlockLuck.getBlockLuck(entity)

                : original;
    }
}

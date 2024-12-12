package com.moonfabric.mixin;

import com.moonfabric.Ievent.evt.LootOrBlockLuck;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.EnchantedCountIncreaseLootFunction;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(EnchantedCountIncreaseLootFunction.class)
public class EnchantedCountIncreaseLootFunctionMixin {

    @Shadow @Final private RegistryEntry<Enchantment> enchantment;

    @ModifyVariable(method = "process", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getEquipmentLevel(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/entity/LivingEntity;)I", ordinal = 0), index = 5)
    public int curios$applyLooting(int original, ItemStack stack, LootContext lootContext) {
        Entity entity = lootContext.get(LootContextParameters.THIS_ENTITY);
        return enchantment == lootContext.getWorld().createCommandRegistryWrapper(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.LOOTING)
                ? original

                + LootOrBlockLuck.getLoot(entity)

                : original;
    }
}

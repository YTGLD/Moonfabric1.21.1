package com.moonfabric.mixin.common;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentHelperMixin {
    @Shadow @Final private Inventory inventory;

    @Shadow @Final private ScreenHandlerContext context;


    @Shadow @Final public int[] enchantmentPower;

    @Shadow @Final private Property seed;

    @Shadow public abstract void onContentChanged(Inventory inventory);

    @Shadow protected abstract List<EnchantmentLevelEntry> generateEnchantments(DynamicRegistryManager registryManager, ItemStack stack, int slot, int level);

    @Inject(method = "onButtonClick", at = @At("HEAD"), cancellable = true)
        private void moon$onButtonClick(PlayerEntity player, int id, CallbackInfoReturnable<Boolean> cir) {
        ItemStack itemStack = inventory.getStack(0);
        if (hasCurio.has(init.rageorb, player)){
            if (!itemStack.isOf(Items.BOOK)) {
                EnchantmentScreenHandler handler = (EnchantmentScreenHandler) (Object) this;
                context.run((world, pos) -> {
                    List<EnchantmentLevelEntry> list = generateEnchantments(world.getRegistryManager(),itemStack, id, handler.enchantmentPower[id]);
                    ItemStack itemStack3 = itemStack;

                    if (!list.isEmpty()) {
                        player.applyEnchantmentCosts(itemStack, id + 1);

                        for (EnchantmentLevelEntry levelEntry : list) {
                            itemStack3.addEnchantment(levelEntry.enchantment, levelEntry.level + 2);
                        }
                        this.inventory.markDirty();
                        this.seed.set(player.getEnchantmentTableSeed());
                        this.onContentChanged(this.inventory);

                    }
                });
                cir.setReturnValue(true);

            }
        }
    }
}

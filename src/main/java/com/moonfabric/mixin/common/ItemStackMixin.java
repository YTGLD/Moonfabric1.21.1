package com.moonfabric.mixin.common;

import com.moonfabric.Ievent.IFood;
import com.moonfabric.hasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.common.pain.pain_carrot;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract UseAction getUseAction();

    @Shadow @Nullable private Entity holder;

    @Shadow public abstract Item getItem();

    @Shadow public abstract void setHolder(@Nullable Entity holder);

    @Shadow public abstract ItemStack finishUsing(World world, LivingEntity user);

    @Shadow public abstract ItemStack withItem(ItemConvertible item);

    @Inject(method = "usageTick", at = @At(value = "RETURN"))
    private void moon$finishUsing(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        if (hasCurio.has(init.pain_book, user)){
            ItemStack stack =(ItemStack) (Object) this;
            if (stack.isOf(Items.COPPER_BLOCK) || stack.isOf(Items.COPPER_INGOT)){
                this.setHolder(user);
            }
        }
    }
    @Inject(method = "finishUsing", at = @At(value = "RETURN"))
    private void moon$finishUsing(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        IFood.Break.invoker().Food((ItemStack)(Object)this,world,user);
        if (user instanceof PlayerEntity player){
            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, itemStack) -> {
                    if (hasCurio.has(init.pain_carrot, player)){
                        if (itemStack.get(Data.CUSTOM_DATA)!=null) {
                            itemStack.get(Data.CUSTOM_DATA).putFloat(pain_carrot.eat, itemStack.get(Data.CUSTOM_DATA).getFloat(pain_carrot.eat) + 0.1f);
                        }
                    }
                });
            });
            if (hasCurio.has(init.pain_book, player)){
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 2));
                ItemStack stack =(ItemStack) (Object) this;
                if (stack.isOf(Items.COPPER_BLOCK)){
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 1));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600, 1));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 1));
                    this.getItem().finishUsing((ItemStack) (Object) this, world, user);
                    stack.decrementUnlessCreative(1,user);

                } else if (stack.isOf(Items.COPPER_INGOT)) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 1));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0));
                    this.getItem().finishUsing((ItemStack) (Object) this, world, user);
                    stack.decrementUnlessCreative(1,user);
                }
            }


            if (hasCurio.has(init.grail, player)){
                if (getUseAction() == UseAction.EAT)
                    IFood.Break.invoker().Food((ItemStack)(Object)this,world,user);{
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0));
                }
            }
        }
    }

}

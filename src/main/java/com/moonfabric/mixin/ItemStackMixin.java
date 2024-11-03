package com.moonfabric.mixin;

import com.moonfabric.item.dna.dna;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN"), cancellable = true)
    private void moon$finishUsing(LivingEntity user, CallbackInfoReturnable<Integer> cir){
        ItemStack stack =(ItemStack) (Object) this;
        dna.Stater(user,stack,cir);

    }
    @Inject(method = "finishUsing", at = @At(value = "RETURN"))
    private void moon$finishUsing(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
        ItemStack stack =(ItemStack) (Object) this;
        dna.Finish(user,stack);

    }
}

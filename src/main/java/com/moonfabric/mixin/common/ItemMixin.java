package com.moonfabric.mixin.common;

import com.moonfabric.hasCurio;
import com.moonfabric.init.init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemMixin {


    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN"), cancellable = true)
    private void getMaxUseTime(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir){
        if (hasCurio.has(init.pain_book,user)){
            if (stack.isOf(Items.COPPER_BLOCK) || stack.isOf(Items.COPPER_INGOT)) {
                cir.setReturnValue(32);
            }
        }
    }
    @Inject(method = "getMaxUseTime", at = @At(value = "RETURN"), cancellable = true)
    private void eat(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir){
        if (hasCurio.has(init.pain_book,user)){

            if (stack.getUseAction() == UseAction.EAT){
                cir.setReturnValue((int) (cir.getReturnValue() * 0.66));

            }
        }
    }
    @Inject(method = "use", at = @At(value = "RETURN"), cancellable = true)
    private void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir){
        if (hasCurio.has(init.pain_book,user)){
            Item stack =(Item) (Object) this;
            if (stack.getDefaultStack().isOf(Items.COPPER_BLOCK)||stack.getDefaultStack().isOf(Items.COPPER_INGOT)){
                user.setCurrentHand(hand);
                cir.setReturnValue(TypedActionResult.consume(user.getStackInHand(hand)));
            }
        }
    }
    @Inject(method = "getUseAction", at = @At(value = "RETURN"), cancellable = true)
    private void use(ItemStack stack, CallbackInfoReturnable<UseAction> cir){
       Entity living = stack.getHolder();
       if (living!=null &&living instanceof PlayerEntity user) {
           if (hasCurio.has(init.pain_book, user)) {
               if (stack.isOf(Items.COPPER_BLOCK) || stack.isOf(Items.COPPER_INGOT)) {
                   cir.setReturnValue(UseAction.EAT);

               }
           }
       }
    }
}

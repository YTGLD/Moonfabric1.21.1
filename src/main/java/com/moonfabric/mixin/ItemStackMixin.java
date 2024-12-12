package com.moonfabric.mixin;

import com.moonfabric.ABook;
import com.moonfabric.item.TheNecora.putrefactive;
import com.moonfabric.item.dna.dna;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

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
        putrefactive.eat(stack,user);
    }
    @Inject(method = "getTooltip", at = @At(value = "RETURN"), cancellable = true)
    private void getTooltip(Item.TooltipContext context, PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir){
        ItemStack stack =(ItemStack) (Object) this;

        ABook.getTooltip(stack,context,player,type,cir);
    }
}

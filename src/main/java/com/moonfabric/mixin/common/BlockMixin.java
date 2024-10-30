package com.moonfabric.mixin.common;

import com.moonfabric.Ievent.BlockBreakEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {



    @Inject(method = "onPlaced", at = @At(value = "RETURN"), cancellable = true)
    private void mf$modifyAppliedDamage(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        Block block = (Block) (Object) this;

        BlockBreakEvent.Break.invoker().b_re_ak(block, world, pos, state,placer, itemStack);

    }

}

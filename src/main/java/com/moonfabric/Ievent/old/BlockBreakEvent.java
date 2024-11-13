package com.moonfabric.Ievent.old;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBreakEvent {
    public static final Event<BlockBreakEvent.onBreak> Break =
            EventFactory.createArrayBacked(BlockBreakEvent.onBreak.class,
                    callbacks -> (block, world, pos, state,placer, itemStack) -> {
                        for (BlockBreakEvent.onBreak callback : callbacks) {
                            callback.b_re_ak(block, world, pos, state,placer, itemStack);
                        }
                    });
    @FunctionalInterface
    public interface onBreak {
        void b_re_ak(Block block,World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack);
    }
}

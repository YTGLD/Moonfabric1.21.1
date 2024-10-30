package com.moonfabric.item.common.Mise;

import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.extend.ItemTir;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class snail extends ItemTir {

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);

        ItemStack s  = user.getStackInHand(hand);
        s.set(Data.CUSTOM_DATA,new NbtCompound());
        return TypedActionResult.consume(s);
    }
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks){
        if (stack.get(Data.CUSTOM_DATA)!=null) {
            int i = this.getMaxUseTime(stack) - remainingUseTicks;
            float f = getPullProgress(i);
            if (f >= 1) {

                BlockPos pos = new BlockPos(stack.get(Data.CUSTOM_DATA).getInt("x"),
                        stack.get(Data.CUSTOM_DATA).getInt("y"),
                        stack.get(Data.CUSTOM_DATA).getInt("z"));

                if (pos.getX() != 0 && pos.getY() != 0 && pos.getZ() != 0) {

                    user.teleport(pos.getX(), pos.getY(), pos.getZ(),false);
                }
            }
        }

    }
    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();
        BlockState blockState = context.getWorld().getBlockState(pos);
        if (blockState.isOf(Blocks.BEACON)) {
            if ( stack.get(Data.CUSTOM_DATA)!=null) {
                stack.get(Data.CUSTOM_DATA).putInt("x", pos.getX());
                stack.get(Data.CUSTOM_DATA).putInt("y", pos.getY());
                stack.get(Data.CUSTOM_DATA).putInt("z", pos.getZ());
                return super.useOnBlock(context);
            }
        }
        return ActionResult.CONSUME;
    }


  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.snail.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.snail.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }

}

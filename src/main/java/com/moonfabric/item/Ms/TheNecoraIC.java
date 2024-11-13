package com.moonfabric.item.Ms;

import com.moonfabric.Ievent.AllEvent;
import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Rarity;
import net.minecraft.world.Difficulty;

import java.util.List;

public class TheNecoraIC  extends TrinketItem {
    public TheNecoraIC() {
        super(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).fireproof());
    }
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(Data.CUSTOM_DATA)!= null) {
            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.PEACEFUL.getName())) {

                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.peaceful").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.EASY.getName())) {

                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.easy").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.NORMAL.getName())) {
                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.normal").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));

            }
            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.HARD.getName())) {
                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.hard").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }
            if (stack.get(Data.CUSTOM_DATA).getBoolean(AllEvent.lootTable)) {
                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.god").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
            }
        }
    }
    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (HasCurio.has(this,player)){
                return false;
            }
        }
        return true;
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

    @Override
    public boolean canEquipFromUse(ItemStack stack, LivingEntity entity) {
        return false;
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (stack.get(Data.CUSTOM_DATA) == null) {
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }
}



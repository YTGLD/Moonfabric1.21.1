package com.moonfabric.Ievent;

import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.TheNecoraIC;
import com.moonfabric.item.TheNecora.cell_blood;
import com.moonfabric.item.TheNecora.cell_boom;
import com.moonfabric.item.TheNecora.cell_calcification;
import com.moonfabric.item.TheNecora.cell_mummy;
import dev.emi.trinkets.api.TrinketsApi;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class AllEvent {
    public static String blood = "bloodgene";
    public static String rage = "ragegene";
    public static final String DamageCell = "DamageCell";
    public static final  String muMMY = cell_mummy.Mummy;
    public static final  String boom = cell_boom.cb;
    public static final  String calcification = cell_calcification.cc;
    public static final  String cb_blood = cell_blood.c_blood;
    public static final String lootTable = "god_loot";
    public static void doDifLootDamage(LivingEntity player, CallbackInfoReturnable<Float> cir){
        TrinketsApi.getTrinketComponent(player).ifPresent((trinketText) -> {
            trinketText.forEach((slotReference, stack) -> {
                if (stack.get(Data.CUSTOM_DATA)!=null){
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.EASY.getName())){
                    }
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.NORMAL.getName())){
                        cir.setReturnValue(cir.getReturnValue()+0.15f);
                    }
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.HARD.getName())){
                        cir.setReturnValue(cir.getReturnValue()+0.25f);
                    }
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(lootTable)){
                        cir.setReturnValue(cir.getReturnValue()+0.35f);
                    }

                }
            });
        });
    }
    public static void doDifLootHealth(LivingEntity player, CallbackInfoReturnable<Float> cir){
        TrinketsApi.getTrinketComponent(player).ifPresent((trinketText) -> {
            trinketText.forEach((slotReference, stack) -> {
                if (stack.get(Data.CUSTOM_DATA)!=null){
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.EASY.getName())){
                        cir.setReturnValue(cir.getReturnValue()+0.25f);
                    }
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.NORMAL.getName())){
                        cir.setReturnValue(cir.getReturnValue()+0.5f);
                    }
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.HARD.getName())){
                        cir.setReturnValue(cir.getReturnValue()+0.75f);
                    }
                    if (stack.get(Data.CUSTOM_DATA).getBoolean(lootTable)){
                        cir.setReturnValue(cir.getReturnValue()+1f);
                    }

                }
            });
        });
    }
//    public static void doDifLootToolName(ItemStack stack, List<Text> tooltip){
//        if (stack.get(Data.CUSTOM_DATA)!= null) {
//            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.PEACEFUL.getName())) {
//
//                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.peaceful").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
//                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
//
//            }
//            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.EASY.getName())) {
//
//                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.easy").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
//                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
//
//            }
//            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.NORMAL.getName())) {
//                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.normal").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
//                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
//
//            }
//            if (stack.get(Data.CUSTOM_DATA).getBoolean(Difficulty.HARD.getName())) {
//                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.hard").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
//                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
//            }
//            if (stack.get(Data.CUSTOM_DATA).getBoolean(lootTable)) {
//                tooltip.add(1, Text.translatable("moonfabric.difficulty.name.god").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFCD853F)))
//                        .append(Text.translatable("moonfabric.difficulty.name.all").fillStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDEB887)))));
//            }
//        }
//    }

    public static void doDifLoot(LootContext context, ObjectArrayList<ItemStack> objectArrayList){
        Entity entity = context.get(LootContextParameters.THIS_ENTITY);
        if (entity instanceof PlayerEntity player){
            if (player.getWorld() instanceof ServerWorld serverLevel) {

                for (ItemStack itemStack : objectArrayList) {
                    if (itemStack.getItem() instanceof TheNecoraIC) {
                        if (itemStack.get(Data.CUSTOM_DATA) == null) {
                            NbtCompound compound = new NbtCompound();
                            itemStack.set(Data.CUSTOM_DATA, compound);
                        }

                        if (serverLevel.getDifficulty() == (Difficulty.PEACEFUL)) {
                            itemStack.get(Data.CUSTOM_DATA).putBoolean(Difficulty.PEACEFUL.getName(), true);

                        }
                        if (serverLevel.getDifficulty() == (Difficulty.EASY)) {
                            itemStack.get(Data.CUSTOM_DATA).putBoolean(Difficulty.EASY.getName(), true);
                        }
                        if (serverLevel.getDifficulty() == (Difficulty.NORMAL)) {
                            itemStack.get(Data.CUSTOM_DATA).putBoolean(Difficulty.NORMAL.getName(), true);
                        }
                        if (serverLevel.getDifficulty() == (Difficulty.HARD)) {
                            int lv = MathHelper.nextInt(Random.create(), 1, 2);
                            if (lv == 1) {
                                itemStack.get(Data.CUSTOM_DATA).putBoolean(Difficulty.HARD.getName(), true);
                            } else if (lv == 2) {
                                itemStack.get(Data.CUSTOM_DATA).putBoolean(lootTable, true);
                            }
                        }
                    }
                }
            }
        }
    }


}

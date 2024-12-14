package com.moonfabric.item.common.Blood;

import com.moonfabric.Effects.initEffect;
import com.moonfabric.HasCurio;
import com.moonfabric.MoonFabricMod;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.TheNecoraIC;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class blood_stones extends TheNecoraIC {
    public static final String MaxSword = "MaxSword";

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onEquip(stack, slot, entity);
    }


    public static void hurt(LivingEntity living, DamageSource source, CallbackInfoReturnable<Float> event){
        if (source.getAttacker() instanceof PlayerEntity player){
            if (HasCurio.has(init.blood_stones, player)){

                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (HasCurio.has(init.blood_stones, player)){

                            if (stack.get(Data.CUSTOM_DATA)!=null){
                                float dam = (float) stack.get(Data.CUSTOM_DATA).getInt(MaxSword) /20;
                                event.setReturnValue(event.getReturnValue()*(1+dam));
                                if (MathHelper.nextInt(Random.create(),1,2)==1) {
                                    if (stack.get(Data.CUSTOM_DATA).getInt(MaxSword)<9) {
                                        stack.get(Data.CUSTOM_DATA).putInt(MaxSword, stack.get(Data.CUSTOM_DATA).getInt(MaxSword) + 1);
                                        player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.NEUTRAL, 0.75F, 0.75F);
                                    }
                                }
                                if (stack.get(Data.CUSTOM_DATA).getInt(MaxSword)>=8){
                                    event.setReturnValue(event.getReturnValue()*4);
                                    stack.get(Data.CUSTOM_DATA).remove(MaxSword);
                                    player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WARDEN_ATTACK_IMPACT, SoundCategory.NEUTRAL, 2, 2);
                                    living.getWorld().syncWorldEvent(2001, new BlockPos((int) living.getX(), (int) (living.getY() + 1), (int) living.getZ()), Block.getRawIdFromState(Blocks.RED_WOOL.getDefaultState()));
                                    living.getWorld().syncWorldEvent(2001, new BlockPos((int) living.getX(), (int) (living.getY() + 0), (int) living.getZ()), Block.getRawIdFromState(Blocks.RED_WOOL.getDefaultState()));
                                    player.getWorld().syncWorldEvent(2001, new BlockPos((int)player.getX(), (int) (player.getY() + 1), (int) player.getZ()), Block.getRawIdFromState(Blocks.RED_WOOL.getDefaultState()));

                                }
                            }
                        }
                    });
                });
            }
        }
    }
    public static void die(DamageSource damageSource){
        if (damageSource.getAttacker() instanceof PlayerEntity player){
            if (HasCurio.has(init.blood_stones, player)){
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (HasCurio.has(init.blood_stones, player)){
                            if (stack.get(Data.CUSTOM_DATA)!=null){
                                if (stack.get(Data.CUSTOM_DATA).getInt(MaxSword)<9) {
                                    stack.get(Data.CUSTOM_DATA).putInt(MaxSword, stack.get(Data.CUSTOM_DATA).getInt(MaxSword) + MathHelper.nextInt(Random.create(),1,3));
                                    player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_MACE_SMASH_GROUND_HEAVY, SoundCategory.NEUTRAL, 0.75F, 0.75F);
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        stack.setDamage(stack.getDamage()+1);
        if (stack.get(Data.CUSTOM_DATA)!=null) {
            if (!entity.getWorld().isClient){
                if (entity.age%120==0){
                    if (stack.get(Data.CUSTOM_DATA).getInt(MaxSword) > 0) {
                        stack.get(Data.CUSTOM_DATA).putInt(MaxSword, stack.get(Data.CUSTOM_DATA).getInt(MaxSword) - 1);
                        entity.heal(4+entity.getMaxHealth()/50);
                        entity.getWorld().syncWorldEvent(2001, new BlockPos((int) entity.getX(), (int) (entity.getY() + 1), (int) entity.getZ()), Block.getRawIdFromState(Blocks.RED_WOOL.getDefaultState()));

                        Vec3d playerPos = entity.getPos().add(0, 0.75, 0);
                        int range = 3;
                        List<LivingEntity> entities = entity.getWorld().getEntitiesByClass(LivingEntity.class, new Box(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range), EntityPredicates.EXCEPT_SPECTATOR);

                        for (LivingEntity living : entities){
                            if (!(living ==(entity))){
                                living.addStatusEffect(new StatusEffectInstance(MoonFabricMod.blood,200,2));
                            }
                        }
                    }
                }
            }

        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.max_sword.tool.string").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.max_sword.tool.string.1").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.max_sword.tool.string.2").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.max_sword.tool.string.3").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.max_sword.tool.string.4").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.max_sword.tool.string.5").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));
        tooltip.add(Text.translatable("item.max_sword.tool.string.6").formatted(Formatting.RED));
        tooltip.add(Text.translatable("item.max_sword.tool.string.7").formatted(Formatting.RED));
        tooltip.add(Text.translatable(""));


    }
}

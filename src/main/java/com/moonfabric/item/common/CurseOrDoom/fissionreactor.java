package com.moonfabric.item.common.CurseOrDoom;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.MoonFabricMod;
import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class fissionreactor extends doom {
    public static String fission = "energy";//能量
    public static String reactor = "radiation";//辐射//max= 1000;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        user.setCurrentHand(hand);

        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if ( stack.get(Data.CUSTOM_DATA)!=null) {
            int size = stack.get(Data.CUSTOM_DATA).getInt(reactor);
            if (size > 0) {
                stack.get(Data.CUSTOM_DATA).putInt(reactor, size - 1);
                if (user.getWorld() instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(MoonFabricMod.FOLLOW, user.getX(), user.getY(), user.getZ(), 3, 2, 2, 2, 0.1);
                }
            }
        }
    }
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player ) {
            if ( stack.get(Data.CUSTOM_DATA)!=null) {
                player.getAttributes().addTemporaryModifiers(getMap(stack));
                int size = stack.get(Data.CUSTOM_DATA).getInt(fission);
                Iterable<ItemStack> hands = player.getHandItems();
                if (size > 0) {
                    for (ItemStack hand : hands) {
                        if (hand.getMaxDamage() != 0) {
                            if (hand.getDamage() != 0) {
                                hand.setDamage(hand.getDamage() - 5);
                                if (stack.get(Data.CUSTOM_DATA).getInt(reactor) < 1000) {
                                    stack.get(Data.CUSTOM_DATA).putInt(reactor, stack.get(Data.CUSTOM_DATA).getInt(reactor) + 1);
                                }
                                if (stack.get(Data.CUSTOM_DATA).getInt(fission) > 0) {
                                    stack.get(Data.CUSTOM_DATA).putInt(fission, size - 1);
                                }
                            }
                        }
                    }
                    for (ItemStack armor : player.getInventory().armor) {
                        if (armor.getMaxDamage() != 0) {
                            if (armor.getDamage() != 0) {
                                armor.setDamage(armor.getDamage() - 5);
                                if (stack.get(Data.CUSTOM_DATA).getInt(reactor) < 1000) {
                                    stack.get(Data.CUSTOM_DATA).putInt(reactor, stack.get(Data.CUSTOM_DATA).getInt(reactor) + 1);
                                }
                                if (stack.get(Data.CUSTOM_DATA).getInt(fission) > 0) {
                                    stack.get(Data.CUSTOM_DATA).putInt(fission, size - 1);
                                }
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
        tooltip.add(Text.translatable("moonfabric.tooltip.fissionreactor.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.fissionreactor.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.fissionreactor.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.fissionreactor.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
      if ( stack.get(Data.CUSTOM_DATA)!=null) {
          tooltip.add(Text.translatable(fission + ": " + stack.get(Data.CUSTOM_DATA).getInt(fission)).formatted(Formatting.DARK_BLUE));
          tooltip.add(Text.translatable(reactor + ": " + stack.get(Data.CUSTOM_DATA).getInt(reactor)).formatted(Formatting.RED));
          tooltip.add(Text.translatable(""));
      }


    }
    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().removeModifiers(getMap(stack));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 32;
    }
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getMap(ItemStack stack) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        float a = (float) stack.get(Data.CUSTOM_DATA).getInt(reactor);//1000
        a /= 10;//100
        a /=2;//50
        a /= 100;//0.5
        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),-a/2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),-a, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
}

package com.moonfabric.Ievent;

import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.TrinketsApi;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;

import java.util.Map;

public class AdvancementEvt {
    public static final String nightmare_base_black_eye_heart = "nightmare_base_black_eye_heart";
    //4惶恐肉瘤
    public static final String nightmare_base_black_eye_eye = "nightmare_base_black_eye_eye";
    //口红
    public static final String nightmare_base_black_eye_red = "nightmare_base_black_eye_red";


    public static final String nightmare_base_stone_brain = "nightmare_base_stone_brain";
    public static final String nightmare_base_stone_meet = "nightmare_base_stone_meet";
    public static final String nightmare_base_stone_virus = "nightmare_base_stone_virus";


    public static final String nightmare_base_reversal_card = "nightmare_base_reversal_card";
    public static final String nightmare_base_reversal_mysterious = "nightmare_base_reversal_mysterious";
    public static final String nightmare_base_reversal_orb = "nightmare_base_reversal_orb";


    public static final String nightmare_base_redemption_deception = "nightmare_base_redemption_deception";
    public static final String nightmare_base_redemption_degenerate = "nightmare_base_redemption_degenerate";
    public static final String nightmare_base_redemption_down_and_out = "nightmare_base_redemption_down_and_out";


    public static final String nightmare_base_fool_betray = "nightmare_base_fool_betray";
    public static final String nightmare_base_fool_bone = "nightmare_base_fool_bone";
    public static final String nightmare_base_fool_soul = "nightmare_base_fool_soul";


    public static final String nightmare_base_insight_drug = "nightmare_base_insight_drug";
    public static final String nightmare_base_insight_insane = "nightmare_base_insight_insane";
    public static final String nightmare_base_insight_collapse = "nightmare_base_insight_collapse";


    public static final String nightmare_base_start_power = "nightmare_base_start_power";
    public static final String nightmare_base_start_egg = "nightmare_base_start_egg";
    public static final String nightmare_base_start_pod = "nightmare_base_start_pod";


    public static void nightmare_base_start_egg(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_start, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.get(Data.CUSTOM_DATA) != null) {
                                if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_start_egg)) {
                                    if (livingEntity instanceof SnifferEntity warden) {
                                        livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                new ItemStack(init.nightmare_base_start_egg)));

                                        stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_start_egg, true);
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    public static void nightmare_base_insight_insane(DamageSource source, LivingEntity livingEntity) {
        if (source.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_insight, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (stack.get(Data.CUSTOM_DATA) != null) {
                            if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_insight_insane)) {
                                player.getWorld().spawnEntity(new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(),
                                        new ItemStack(init.nightmare_base_insight_insane)));
                                stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_insight_insane, true);
                            }
                        }
                    });
                });
            }
        }
    }

    public static void nightmare_base_fool(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_fool, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_fool)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof EnderDragonEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_fool_betray)) {

                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_base_fool_betray)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_fool_betray, true);
                                        }
                                    }
                                }
                            }
                            if (stack.isOf(init.nightmare_base_fool)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof WardenEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_fool_bone)) {
                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_base_fool_bone)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_fool_bone, true);
                                        }
                                    }
                                }
                            }
                            if (stack.isOf(init.nightmare_base_fool)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof WitherEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_fool_soul)) {

                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_base_fool_soul)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_fool_soul, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }


    public static void nightmare_base_redemption_degenerate(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_redemption, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_redemption)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof VillagerEntity raider) {
                                        if (stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_degenerate) < 100) {
                                            stack.get(Data.CUSTOM_DATA).putInt(nightmare_base_redemption_degenerate, stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_degenerate) + 1);
                                        } else if (stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_degenerate) == 100) {
                                            player.giveItemStack(new ItemStack(init.nightmare_base_redemption_degenerate));
                                            stack.get(Data.CUSTOM_DATA).putInt(nightmare_base_redemption_degenerate, stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_degenerate) + 1);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    public static void nightmare_base_redemption_deception(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_redemption, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_redemption)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof RaiderEntity raider) {
                                        if (stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_deception) < 100) {
                                            stack.get(Data.CUSTOM_DATA).putInt(nightmare_base_redemption_deception, stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_deception) + 1);
                                        } else if (stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_deception) == 100) {
                                            player.giveItemStack(new ItemStack(init.nightmare_base_redemption_deception));
                                            stack.get(Data.CUSTOM_DATA).putInt(nightmare_base_redemption_deception, stack.get(Data.CUSTOM_DATA).getInt(nightmare_base_redemption_deception) + 1);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    public static void nightmare_base_reversal_card(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_reversal, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_reversal)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof EnderDragonEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_reversal_card)) {

                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_base_reversal_card)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_reversal_card, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    public static void nightmare_base_stone_meet(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_stone, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_stone)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof EnderDragonEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_stone_meet)) {

                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_base_stone_meet)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_stone_meet, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    public static void nightmare_base_stone_virus(DamageSource damageSource, LivingEntity livingEntity) {
        if (livingEntity instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_stone, player)) {
                if (damageSource.getAttacker() instanceof WitherEntity) {
                    TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                        trinketComponent.forEach((slotReference, stack) -> {
                            if (!stack.isEmpty()) {
                                if (stack.isOf(init.nightmare_base_stone)) {
                                    if (stack.get(Data.CUSTOM_DATA) != null) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_stone_virus)) {
                                            player.giveItemStack(new ItemStack(init.nightmare_base_stone_virus));
                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_stone_virus, true);
                                        }
                                    }
                                }
                            }
                        });
                    });
                }
            }
        }
    }


    public static void nightmare_base_stone_brain(DamageSource damageSource, LivingEntity livingEntity) {
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_stone, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_stone)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof ZombieEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_stone_brain)) {

                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_virus)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_stone_brain, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });

            }
        }
    }







    public static void LivingUseTotemEvent(DamageSource damageSource,LivingEntity livingEntity){
            if (livingEntity instanceof PlayerEntity player) {
                if (HasCurio.has(init.nightmare_base_black_eye, player)) {
                    if (player.hasStatusEffect(StatusEffects.POISON)
                            && player.hasStatusEffect(StatusEffects.WITHER)
                            && player.hasStatusEffect(StatusEffects.SLOWNESS)
                            && player.hasStatusEffect(StatusEffects.BLINDNESS)
                            && player.hasStatusEffect(StatusEffects.DARKNESS)) {
                        if (player.getFireTicks() > 0) {
                            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                                trinketComponent.forEach((slotReference, stack) -> {
                                    if (!stack.isEmpty()) {
                                        if (stack.isOf(init.nightmare_base_black_eye)) {
                                            if (stack.get(Data.CUSTOM_DATA) != null) {
                                                if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_black_eye_heart)) {

                                                    player.giveItemStack(new ItemStack(init.nightmare_base_black_eye_heart));

                                                    stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_black_eye_heart, true);
                                                }
                                            }
                                        }
                                    }
                                });
                            });
                        }
                    }
                }
            }
        }





    public static void drop(DamageSource damageSource,LivingEntity livingEntity){
        if (damageSource.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.nightmare_base_black_eye, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (!stack.isEmpty()) {
                            if (stack.isOf(init.nightmare_base_black_eye)) {
                                if (stack.get(Data.CUSTOM_DATA) != null) {
                                    if (livingEntity instanceof WardenEntity warden) {
                                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_black_eye_eye)) {

                                            livingEntity.getWorld().spawnEntity(new ItemEntity(warden.getWorld(), warden.getX(), warden.getY(), warden.getZ(),
                                                    new ItemStack(init.nightmare_base_black_eye_eye)));

                                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_black_eye_eye, true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }
    public static void addLoot(ObjectArrayList<ItemStack> generatedLoot,
                               Entity entity,
                               int lv){
        if (entity instanceof PlayerEntity player ){
            if (HasCurio.has(init.nightmare_base_black_eye, player)) {
                 TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {
                if (!stack.isEmpty()) {
                    if (stack.isOf(init.nightmare_base_black_eye)) {
                        if (stack.get(Data.CUSTOM_DATA) != null) {
                            if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_black_eye_red)) {
                                if (MathHelper.nextInt(Random.create(), 0, 100) <= lv) {
                                    generatedLoot.add(new ItemStack(init.nightmare_base_black_eye_red));

                                    stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_black_eye_red, true);
                                }
                            }
                        }
                    }
                }
            });
        });

            }
        }
    }



    public static void nightmare_base_reversal_mysteriousLOOT(ObjectArrayList<ItemStack> generatedLoot,
                                                              Entity entity){
        if (entity instanceof PlayerEntity player ){
            if (HasCurio.has(init.nightmare_base_reversal_orb, player)) {
                 TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {
                if (stack.isOf(init.nightmare_base_reversal_orb)) {
                    if (stack.get(Data.CUSTOM_DATA) != null) {
                        if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_reversal_mysterious)) {
                            generatedLoot.add(new ItemStack(init.nightmare_base_reversal_mysterious));
                            stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_reversal_mysterious, true);
                        }
                    }
                }
            });
        });

            }
        }
    }
    public static void nightmare_base_start_pod(ObjectArrayList<ItemStack> generatedLoot,
                                                Entity entity){
        if (entity instanceof PlayerEntity player ){
            if (HasCurio.has(init.nightmare_base_start, player)) {
                 TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {
                if (!stack.isEmpty()) {
                    if (stack.isOf(init.nightmare_base_start)) {
                        if (MathHelper.nextInt(Random.create(),1,100)<=25) {
                            if (stack.get(Data.CUSTOM_DATA) != null) {
                                if (!stack.get(Data.CUSTOM_DATA).getBoolean(nightmare_base_start_pod)) {
                                    generatedLoot.add(new ItemStack(init.nightmare_base_start_pod));
                                    stack.get(Data.CUSTOM_DATA).putBoolean(nightmare_base_start_pod, true);
                                }
                            }
                        }
                    }
                }
            });
        });
            }
        }
    }
}

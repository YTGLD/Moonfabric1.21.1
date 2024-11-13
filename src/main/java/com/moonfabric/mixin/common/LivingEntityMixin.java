package com.moonfabric.mixin.common;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.Entity.flysword;
import com.moonfabric.Ievent.old.IEntityDie;
import com.moonfabric.Ievent.old.IEventHurt;
import com.moonfabric.MoonFabricMod;
import com.moonfabric.HasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.init.InItEntity;
import com.moonfabric.init.init;
import com.moonfabric.item.common.CurseOrDoom.fissionreactor;
import com.moonfabric.item.common.Mise.goldbox;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract void heal(float amount);

    @Shadow protected abstract void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition);

    @Shadow public abstract Vec3d applyMovementInput(Vec3d movementInput, float slipperiness);

    @Shadow public abstract boolean hasNoDrag();

    @Shadow public abstract void setHealth(float health);

    @Shadow public abstract boolean canBreatheInWater();

    @Inject(method = "getArmor", at = @At(value = "RETURN"), cancellable = true)
    private void mf$getArmor(CallbackInfoReturnable<Integer> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (livingEntity instanceof PlayerEntity player) {
            //在地狱或岩浆中：+50% 挖掘速度,速度,伤害,护甲,生命
            if (HasCurio.has(init.firecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                   if (player.isInLava()){
                       cir.setReturnValue((int) (cir.getReturnValue() * 1.5));
                   }else if (player.isTouchingWaterOrRain()) {
                       cir.setReturnValue((int) (cir.getReturnValue() * 0.3f));
                   }
                }
            }


            //增加35%在水中或雨中的伤害和护甲并获得增益BUFF
            if (HasCurio.has(init.watercottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.watercottoncandy)) {
                    if (player.isTouchingWaterOrRain()) {
                        cir.setReturnValue((int) (cir.getReturnValue() * 1.35));
                    }
                }
            }

            //在阳光下：+35%速度，生命恢复，护甲
            if (HasCurio.has(init.woodcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.woodcottoncandy)) {
                    if (player.getWorld().isSkyVisible(player.getBlockPos()) || player.getWorld().isDay()) {
                        cir.setReturnValue((int) (cir.getReturnValue() * 1.35));
                    }
                }
            }
        }
    }


    @Inject(method = "damage", at = @At(value = "INVOKE", target = "net/minecraft/entity/LivingEntity.isSleeping()Z"), cancellable = true)
    private void mf$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!IEventHurt.ALLOW_DAMAGE.invoker().allowDamage((LivingEntity) (Object) this, source, amount)) {
            cir.setReturnValue(false);
        }
    }

    @Unique
    private int beamTicks;
    @Unique
    public int getWarmupTime() {
        return 80;
    }

    @Unique
    public float getBeamProgress(float tickDelta) {
        return ((float)this.beamTicks + tickDelta) / (float)this.getWarmupTime();
    }
    @Unique
    private int cotton_candy = 100;
    @Inject(method = "modifyAppliedDamage", at = @At(value = "RETURN"), cancellable = true)
    private void mf$modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity  instanceof PlayerEntity player){
            if (HasCurio.has(init.sevensword,player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.sevensword)){
                    for (int i = 0 ;i<7 ;i++){
                        float s  = (float) Math.sin(i);
                        if (s <= 0){
                            s = 0.12f;
                        }

                        flysword flysword = new flysword(InItEntity.Fly, player.getWorld());
                        flysword.setPos(player.getX()+MathHelper.nextFloat(Random.create(), -s,s),player.getY()+2+s,player.getZ()+MathHelper.nextFloat(Random.create(), -s,s));
                        flysword.setVelocity(MathHelper.nextFloat(Random.create(), -s/1.5f,s/1.5f),s/1.5f,MathHelper.nextFloat(Random.create(), -s/1.5f,s/1.5f));
                        player.getWorld().spawnEntity(flysword);
                        player.getItemCooldownManager().set(init.sevensword,35);
                    }
                }
            }


            if (HasCurio.has(init.bloodtime, player)){
                player.timeUntilRegen = (int) (player.timeUntilRegen * 1.3);
            }


            if (HasCurio.has(init.nanocottoncandy, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, itemStack) -> {

                        if (itemStack.isOf(init.nanocottoncandy)) {
                            if (!player.getItemCooldownManager().isCoolingDown(itemStack.getItem())) {
                                if (MathHelper.nextInt(Random.create(), 0, 100) <= cotton_candy) {
                                    cotton_candy /= 2;
                                    cir.setReturnValue((float) 0);
                                    player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.NEUTRAL, 0.5f, 0.5f);
                                } else {
                                    cotton_candy = 100;
                                    if (player.getWorld() instanceof ServerWorld world) {
                                        world.spawnParticles(MoonFabricMod.Origin, player.getX(), player.getY(), player.getZ(), 30, 2, 2, 2, 0.1);
                                    }
                                    player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WARDEN_DEATH, SoundCategory.NEUTRAL, 0.15f, 0.15f);
                                    cir.setReturnValue(amount * 1.5F);
                                    player.getItemCooldownManager().set(init.nanocottoncandy, 200);

                                }
                            }
                        }
                    });
                });

            }
            if (HasCurio.has(init.nanocube, player)) {
                if (this.beamTicks < this.getWarmupTime()) {
                    ++this.beamTicks;
                }
                if (source != null && source.getSource() instanceof LivingEntity living) {
                    double d = this.getBeamProgress(0.0F);
                    double e = living.getX() - player.getX();
                    double f = living.getBodyY(0.5) - player.getEyeY();
                    double g = living.getZ() - player.getZ();
                    double h = Math.sqrt(e * e + f * f + g * g);
                    e /= h;
                    f /= h;
                    g /= h;
                    double j = player.getRandom().nextDouble();
                    while (j < h) {
                        j += 1.8 - d + player.getRandom().nextDouble() * (1.7 - d);

                        if (player.getWorld() instanceof ServerWorld serverWorld) {
                            serverWorld.spawnParticles(MoonFabricMod.FOLLOW, player.getX() + e * j, player.getEyeY() + f * j, player.getZ() + g * j, 4, 0, 0, 0, 0);
                            living.getWorld().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.NEUTRAL, 0.15f, 0.15f);

                            living.damage(living.getDamageSources().magic(), 8);
                            living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 50, 1));
                            living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 1));
                            living.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 50, 1));
                            living.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 150, 1));

                        }
                    }
                }
            }


            if (HasCurio.has(init.glodstone,player)){
                if (amount > (player.getMaxHealth() / 2)){
                    cir.setReturnValue(amount * 0.2f);
                    player.heal(4);
                }

                if (MathHelper.nextInt(Random.create(), 1, 5) == 1) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 1));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1));

                    if (source != null && source.getSource() != null && source.getSource() instanceof LivingEntity living) {
                        living.damage(living.getDamageSources().magic(), 8);
                    }
                }
            }
            if (HasCurio.has(init.gazer,player)){
                if (amount > (player.getMaxHealth() / 3)){
                    cir.setReturnValue(amount * 0.3f);
                }
            }

            if (HasCurio.has(init.furybloodpearl,player)){
                cir.setReturnValue((amount * 1.6f) + 1);
                if (MathHelper.nextInt(Random.create(), 1 ,10) == 1){
                    player.getWorld().playSound(null,player.getX(), player.getY(),player.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.NEUTRAL,1.5F,1.5F);
                    player.getWorld().playSound(null,player.getX(), player.getY(),player.getZ(), SoundEvents.BLOCK_BEACON_AMBIENT, SoundCategory.NEUTRAL,1.5F,1.5F);
                    player.getWorld().playSound(null,player.getX(), player.getY(),player.getZ(), SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.NEUTRAL,1.5F,1.5F);
                    if (player.getWorld() instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(MoonFabricMod.Origin, player.getX(), player.getY(), player.getZ(), 33, 1, 1, 1, 0.1);
                    }
                    Vec3d vec3d = player.getPos();
                    int r = 12;
                    List<LivingEntity> list = player.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
                    for (LivingEntity living : list) {
                        if (living != player) {

                            living.damage(living.getDamageSources().magic(), living.getMaxHealth() / 5);
                            living.addStatusEffect(new StatusEffectInstance(MoonFabricMod.blood, 400, 0));

                        }
                    }
                }
            }
            if (HasCurio.has(init.bloodcharm,player)){

                if (amount > (player.getMaxHealth() / 2)){
                    player.getItemCooldownManager().set(init.bloodcharm, 200);
                    player.getWorld().playSound(null,player.getX(), player.getY(),player.getZ(), SoundEvents.ENTITY_WARDEN_DEATH, SoundCategory.NEUTRAL,1.5F,1.5F);
                    if (player.getWorld() instanceof ServerWorld world){
                        world.spawnParticles(MoonFabricMod.t,player.getX(), player.getY(),player.getZ(),20,2,2,2,0.1);
                        world.spawnParticles(MoonFabricMod.FOLLOW,player.getX(), player.getY(),player.getZ(),20,2,2,2,0.1);
                    }

                }

                if (player.getItemCooldownManager().isCoolingDown(init.bloodcharm)){
                    cir.setReturnValue(amount * 0.7f);
                }else {
                    cir.setReturnValue(amount * 1.4f);
                }

            }
            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent)-> {
            });
        }

//__________________________________________________________________________________________________________________

        if (source != null && source.getAttacker() instanceof PlayerEntity player) {
            if (HasCurio.has(init.bloodcharm, player)) {
                if (player.getItemCooldownManager().isCoolingDown(init.bloodcharm)) {
                    cir.setReturnValue(amount * 1.3f);
                }
            }

        }

        if (livingEntity instanceof PlayerEntity player){
            if (HasCurio.has(init.watercottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.watercottoncandy)) {
                    //在陆地减少50%伤害,移速并增加100%受到伤害
                    if (player.isOnGround()){
                        cir.setReturnValue(cir.getReturnValue() * 2f);
                    }
                }
            }
            if (HasCurio.has(init.stonecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.stonecottoncandy)) {

                    //增加40%伤害，攻速，生命值和30%抗性以及100%的击退抗性
                    //受到木剑或木斧的伤害增加500%的伤害增加500%
                    cir.setReturnValue(cir.getReturnValue() * 0.7f);
                    if (source.getSource() instanceof  LivingEntity living){
                        ItemStack stack = living.getStackInHand(Hand.MAIN_HAND);
                        if (stack.isOf(Items.WOODEN_SWORD)||stack.isOf(Items.WOODEN_AXE)){
                            cir.setReturnValue(cir.getReturnValue() * 6f);
                        }
                    }
                }
            }


            if (HasCurio.has(init.woodcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.woodcottoncandy)) {
                    //受到伤害增加50%
                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                    //受到武器的伤害增加30%
                    if (source.getSource() instanceof  LivingEntity living){
                        if (!living.getStackInHand(Hand.MAIN_HAND).isEmpty()){
                            cir.setReturnValue(cir.getReturnValue() * 1.3f);
                        }
                    }
                }
            }

            if (HasCurio.has(init.goldcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    //着火时：-50%伤害和速度,以及增加50%的受到伤害
                    if (player.isOnFire()) {
                        cir.setReturnValue(cir.getReturnValue() * 1.5f);
                    }
                }
            }
            //受到的火焰伤害增加100%
            if (HasCurio.has(init.goldcottoncandy, player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    if (source.isOf(DamageTypes.IN_FIRE)
                            || source.isOf(DamageTypes.ON_FIRE)
                            || source.isOf(DamageTypes.LAVA)) {
                        cir.setReturnValue(cir.getReturnValue() * 2);
                    }
                }
            }
            if (HasCurio.has(init.twistedstone, player)){
                cir.setReturnValue(cir.getReturnValue() * 2F);
            }

            if (HasCurio.has(init.candle, player)){
                double aaa = (100 - ((player.getHealth() / player.getMaxHealth()) * 100)) / 100;
                double a = aaa / 4;
                cir.setReturnValue((float) (cir.getReturnValue() * (1 - a)));
            }
        }

        if (source.getAttacker() instanceof PlayerEntity player){
            if (HasCurio.has(init.firecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                    if  (player.isInLava()) {
                        cir.setReturnValue(cir.getReturnValue() * 1.5f);
                    } else if (player.isTouchingWaterOrRain()) {
                        cir.setReturnValue(cir.getReturnValue() * 0.3f);
                    }
                }
            }
            if (HasCurio.has(init.firecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                    //目标的护甲越高，你造成的伤害越多
                    int a = livingEntity.getArmor();
                    cir.setReturnValue(cir.getReturnValue() + a);

                }
            }
            if (HasCurio.has(init.watercottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.watercottoncandy)) {
                    //在陆地减少50%伤害,移速并增加100%受到伤害
                    if (player.isOnGround()){
                        cir.setReturnValue(cir.getReturnValue() * 0.5f);
                    }
                }
            }
            if (HasCurio.has(init.goldcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    //着火时：-50%伤害和速度,以及增加50%的受到伤害
                    if (player.isOnFire()) {
                        cir.setReturnValue(cir.getReturnValue() * 0.5f);
                    }
                }
            }
            if (HasCurio.has(init.firecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.firecottoncandy)) {
                    //攻击禁用目标的 海洋棉花糖，并额外造成50%的伤害
                    if (livingEntity instanceof PlayerEntity playerEntity) {
                        TrinketsApi.getTrinketComponent(playerEntity).ifPresent((trinketComponent) -> {
                            trinketComponent.forEach((slotReference, itemStack) -> {
                                if (itemStack.isOf(init.goldcottoncandy)) {
                                    playerEntity.getItemCooldownManager().set(init.goldcottoncandy, 100);
                                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                                }
                            });
                        });
                    }
                }
            }

            if (HasCurio.has(init.stonecottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.stonecottoncandy)) {
                    //攻击禁用目标的 海洋棉花糖，并额外造成50%的伤害
                    if (livingEntity instanceof PlayerEntity playerEntity) {
                        TrinketsApi.getTrinketComponent(playerEntity).ifPresent((trinketComponent) -> {
                            trinketComponent.forEach((slotReference, itemStack) -> {
                                if (itemStack.isOf(init.watercottoncandy)) {
                                    playerEntity.getItemCooldownManager().set(init.watercottoncandy, 100);
                                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                                }
                            });
                        });
                    }
                }
            }
            if (HasCurio.has(init.woodcottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.woodcottoncandy)) {
                    //攻击禁用目标的 石头棉花糖，并额外造成50%的伤害
                    if (livingEntity instanceof PlayerEntity playerEntity) {
                        TrinketsApi.getTrinketComponent(playerEntity).ifPresent((trinketComponent) -> {
                            trinketComponent.forEach((slotReference, itemStack) -> {
                                if (itemStack.isOf(init.stonecottoncandy)) {
                                    playerEntity.getItemCooldownManager().set(init.stonecottoncandy, 100);
                                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                                }
                            });
                        });
                    }
                }
            }
            if (HasCurio.has(init.watercottoncandy, player)) {
                if (!player.getItemCooldownManager().isCoolingDown(init.watercottoncandy)) {
                    //攻击禁用目标的 火焰棉花糖，并额外造成50%的伤害
                    if (livingEntity instanceof PlayerEntity playerEntity) {
                        TrinketsApi.getTrinketComponent(playerEntity).ifPresent((trinketComponent) -> {
                            trinketComponent.forEach((slotReference, itemStack) -> {
                                if (itemStack.isOf(init.firecottoncandy)) {
                                    playerEntity.getItemCooldownManager().set(init.firecottoncandy, 100);
                                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                                }
                            });
                        });
                    }
                }
            }

            if (HasCurio.has(init.goldcottoncandy, player)){
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    //攻击禁用目标的 木头棉花糖，并额外造成50%的伤害
                    if (livingEntity instanceof PlayerEntity playerEntity) {
                        TrinketsApi.getTrinketComponent(playerEntity).ifPresent((trinketComponent) -> {
                            trinketComponent.forEach((slotReference, itemStack) -> {
                                if (itemStack.isOf(init.woodcottoncandy)) {
                                    playerEntity.getItemCooldownManager().set(init.woodcottoncandy, 100);
                                    cir.setReturnValue(cir.getReturnValue() * 1.5f);
                                }
                            });
                        });
                    }
                }

                //金·棉花糖 ：  使用斧头作为武器时：+80%攻击伤害
                if (!player.getItemCooldownManager().isCoolingDown(init.goldcottoncandy)) {
                    if (player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof AxeItem) {
                        cir.setReturnValue(cir.getReturnValue() * 1.8f);
                    }
                }

            }


            float aaaaa = accc(player);
            if (HasCurio.has(init.book, player)){
                cir.setReturnValue(cir.getReturnValue() + aaaaa);
            }


            if (HasCurio.has(init.candle, player)){
                double aaa = (100 - ((player.getHealth() / player.getMaxHealth()) * 100)) / 100;
                double a = aaa / 4;

                cir.setReturnValue((float) (cir.getReturnValue() * (1 + a)));
            }
        }
    }

    @Unique
    private float accc(PlayerEntity player){
        int a = 0;
        List<ItemStack> stacks = new ArrayList<>();
        TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent)-> {
            trinketComponent.forEach((slotReference, itemStack) -> {
                if (itemStack.isEmpty()) {
                    stacks.add(itemStack);
                }
            });
        });
        for (ItemStack stack : stacks){
            if (stack.isEmpty()){
                a += 1;
            }
        }
        return a;
    }
    @Inject(method = "canWalkOnFluid", at = @At(value = "RETURN"), cancellable = true)
    private void mf$canWalkOnFluid(FluidState state, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity instanceof PlayerEntity player){
            if (HasCurio.has(init.cursecandle, player)){
                 if (state.isOf(Fluids.LAVA)){
                     cir.setReturnValue(true);
                 }
            }
        }
    }
    @Inject(method = "drop", at = @At(value = "RETURN"), cancellable = true)
    private void moon$drop(ServerWorld world, DamageSource source, CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (source.getAttacker() instanceof PlayerEntity player){
            if (HasCurio.has(init.doomtreasure, player)){
                if (livingEntity.getRandom().nextInt(3)== 1){
                    livingEntity.dropStack(new ItemStack(Items.EMERALD));
                }
                livingEntity.dropStack(new ItemStack(Items.IRON_NUGGET));
                livingEntity.dropStack(new ItemStack(Items.IRON_NUGGET));

                livingEntity.dropStack(new ItemStack(Items.GOLD_NUGGET));
                livingEntity.dropStack(new ItemStack(Items.GOLD_NUGGET));
                livingEntity.dropStack(new ItemStack(Items.GOLD_NUGGET));
            }
        }
    }
    @Inject(method = "onDeath", at = @At(value = "RETURN"), cancellable = true)
    private void moon$die(DamageSource damageSource, CallbackInfo ci){
        Entity entity = damageSource.getAttacker();
        LivingEntity li = (LivingEntity) (Object) this;
        IEntityDie.Break.invoker().die(li,damageSource);
        if (li instanceof PlayerEntity player){
            if (HasCurio.has(init.doomsoul, player)){
                Entity sou = damageSource.getAttacker();
                if (sou instanceof LivingEntity living){
                    if (living.getHealth() > living.getMaxHealth() * 0.3f){
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 3000, 1));
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 3000, 1));
                    }else {
                        living.damage(living.getDamageSources().magic(), living.getMaxHealth() * 0.29f);
                        living.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 3000, 1));
                    }
                }

            }
        }
        if (entity instanceof PlayerEntity player) {


            if (HasCurio.has(init.fissionreactor, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, itemStack) -> {
                        if (itemStack.isOf(init.fissionreactor)) {
                            if (itemStack.get(Data.CUSTOM_DATA)!=null) {
                                if (itemStack.get(Data.CUSTOM_DATA).getInt(fissionreactor.fission) <= 1000) {
                                    itemStack.get(Data.CUSTOM_DATA).putInt(fissionreactor.fission,
                                            itemStack.get(Data.CUSTOM_DATA).getInt(fissionreactor.fission) + 10);
                                }
                            }
                        }
                    });
                });
            }

            if (HasCurio.has(init.twistedorb, player)){
                LivingEntity living = (LivingEntity) (Object) this;
                ItemEntity item = new ItemEntity(living.getWorld(),living.getX(),living.getY(), living.getZ(), new ItemStack(Items.NETHERRACK));
                item.setNoGravity(true);
                item.setPickupDelayInfinite();

                item.addCommandTag("twisted");
                living.getWorld().spawnEntity(item);
            }



            if (HasCurio.has(init.goldbox, player)){
                LivingEntity livingEntity = (LivingEntity) (Object) this;
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent)->{
                    trinketComponent.forEach((slotReference, itemStack)->{
                        if (itemStack.isOf(init.goldbox)){
                            if (itemStack.get(Data.CUSTOM_DATA)!= null) {
                                if (itemStack.get(Data.CUSTOM_DATA).getInt(goldbox.gold) <= 1000) {
                                    itemStack.get(Data.CUSTOM_DATA).putInt(goldbox.gold,
                                            (int) (itemStack.get(Data.CUSTOM_DATA).getInt(goldbox.gold) + livingEntity.getMaxHealth()));
                                }
                            }
                        }
                    });
                });


            }
        }
    }
    @Unique
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> get() {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();
        modifierMultimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.of("generic_armor"),-0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.of("generic_armor_a"),-0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        return modifierMultimap;
    }
    @Inject(method = "tick", at = @At(value = "RETURN"), cancellable = true)
    private void mf$tick(CallbackInfo ci){
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity!= null) {
            if (livingEntity.hasStatusEffect(MoonFabricMod.blood)){
                livingEntity.getAttributes().addTemporaryModifiers(get());
            }else{
                livingEntity.getAttributes().removeModifiers(get());
            }

        }
    }
}

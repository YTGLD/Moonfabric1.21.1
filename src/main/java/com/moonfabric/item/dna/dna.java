package com.moonfabric.item.dna;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.data.BundleContentsComponent;
import com.moonfabric.HasCurio;
import com.moonfabric.init.AttReg;
import com.moonfabric.init.DNAItems;
import com.moonfabric.init.Data;
import com.moonfabric.init.init;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.*;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

public class dna extends TrinketItem {
    public dna() {
        super(new Item.Settings().maxCount(1).component(Data.BUNDLE_CONTENTS,
                BundleContentsComponent.DEFAULT).rarity(Rarity.UNCOMMON).fireproof());
    }
    public  static void Stater(LivingEntity  me ,ItemStack useStack,CallbackInfoReturnable<Integer> cir ){
        LivingEntity player =me;
        if (HasCurio.has(init.dna, player)) {
            TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                trinketComponent.forEach((slotReference, stack) -> {
                    if (stack.getItem() == (init.dna)){
                        BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
                        if (bundlecontents != null) {
                            bundlecontents.iterate().forEach((itemStack -> {

                                if (itemStack.isOf(DNAItems.cell_big_boom)) {
                                    int count = itemStack.getCount();
                                    if (useStack.getUseAction() ==UseAction.EAT){
                                        cir.setReturnValue((int) (cir.getReturnValue() * (1 - (count/100f))));
                                    }
                                }
                            }));
                        }
                    }
                });
            });
        }
    }
    public  static void Finish(LivingEntity  me ,ItemStack useStack){
        LivingEntity kl = me;
        if (kl instanceof PlayerEntity player) {
            if (HasCurio.has(init.dna, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (stack.getItem() == (init.dna)){
                            BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
                            if (bundlecontents != null) {
                                bundlecontents.iterate().forEach((itemStack -> {
                                    if (itemStack.isOf(DNAItems.cell_digestion)) {

                                        int count = itemStack.getCount();
                                        if (useStack.getUseAction() == UseAction.EAT) {
                                            player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel()+count/10);
                                            player.getHungerManager().setSaturationLevel(player.getHungerManager().getSaturationLevel()+count/10f);
                                        }
                                    }
                                }));
                            }
                        }
                    });
                });
            }
        }
    }
    public  static void hurt(DamageSource source, LivingEntity me, CallbackInfoReturnable<Float> cir){
        Entity p = me;
        if (p instanceof PlayerEntity player) {
            if (HasCurio.has(init.dna, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (stack.getItem() == (init.dna)){
                            BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
                            if (bundlecontents != null) {
                                bundlecontents.iterate().forEach((itemStack -> {
                                    if (itemStack.isOf(DNAItems.cell_inheritance)) {
                                        float s = itemStack.getCount();//64
                                        s/=100f;//0.64
                                        s/=3.2f;//0.2
                                        cir.setReturnValue(cir.getReturnValue()*(1-s));
                                    }
                                    if (itemStack.isOf(DNAItems.cell_cranial)) {
                                        float s = itemStack.getCount();//64
                                        s/=100f;//0.64
                                        if (source.isOf(DamageTypes.FALLING_ANVIL)
                                                && source.isOf(DamageTypes.FALLING_STALACTITE)
                                                && source.isOf(DamageTypes.FALLING_BLOCK)
                                                && source.isOf(DamageTypes.MOB_PROJECTILE))
                                        {
                                            cir.setReturnValue(cir.getReturnValue()*(1-s));
                                        }
                                    }

                                    if (itemStack.isOf(DNAItems.cell_compress)) {
                                        float s = itemStack.getCount();//64
                                        s/=100f;//0.64
                                        if (source.getSource() instanceof LivingEntity living){
                                            float dam = cir.getReturnValue() * s;
                                            living.damage(living.getDamageSources().dryOut(),dam);
                                        }
                                    }
                                    if (itemStack.isOf(DNAItems.cell_constant)) {
                                        if (!player.getItemCooldownManager().isCoolingDown(DNAItems.cell_constant)) {
                                            float s = itemStack.getCount();//64
                                            s /= 100f;//0.64
                                            player.timeUntilRegen = player.timeUntilRegen + ((int) (player.timeUntilRegen * s));
                                            player.getItemCooldownManager().set(DNAItems.cell_constant, player.timeUntilRegen);
                                        }
                                    }
                                }));
                            }
                        }
                    });
                });
            }
        }
        if (source.getSource() instanceof PlayerEntity player) {
            if (HasCurio.has(init.dna, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (stack.getItem() == (init.dna)){
                            BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
                            if (bundlecontents != null) {
                                bundlecontents.iterate().forEach((itemStack -> {
                                    if (itemStack.isOf(DNAItems.cell_acid)) {
                                        int count = itemStack.getCount();//64
                                        ItemStack head = me.getEquippedStack(EquipmentSlot.HEAD);
                                        if (!head.isEmpty()&&head.getMaxDamage()!=0){
                                            head.damage(count, me,EquipmentSlot.HEAD);
                                        }

                                        ItemStack CHEST = me.getEquippedStack(EquipmentSlot.CHEST);
                                        if (!CHEST.isEmpty()&&CHEST.getMaxDamage()!=0){
                                            CHEST.damage(count, me,EquipmentSlot.CHEST);
                                        }
                                        ItemStack LEGS = me.getEquippedStack(EquipmentSlot.LEGS);
                                        if (!LEGS.isEmpty()&&LEGS.getMaxDamage()!=0){
                                            LEGS.damage(count, me,EquipmentSlot.LEGS);
                                        }
                                        ItemStack FEET = me.getEquippedStack(EquipmentSlot.FEET);
                                        if (!FEET.isEmpty()&&FEET.getMaxDamage()!=0){
                                            FEET.damage(count,me,EquipmentSlot.HEAD);
                                        }
                                    }
                                }));
                            }
                        }
                    });
                });
            }
        }
    }
    public  static void dieD(LivingEntity  me,DamageSource source){
        Entity p = source.getSource();
        if (p instanceof PlayerEntity player) {
            if (HasCurio.has(init.dna, player)) {
                TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
                    trinketComponent.forEach((slotReference, stack) -> {
                        if (stack.getItem() == (init.dna)){
                            BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
                            if (bundlecontents != null) {
                                bundlecontents.iterate().forEach((itemStack -> {
                                    if (itemStack.isOf(DNAItems.cell_darwin)) {
                                        float count = itemStack.getCount();
                                        if (MathHelper.nextInt(Random.create(),1,2)==1){
                                            player.heal(count/8);
                                        }else {
                                            player.damage(player.getDamageSources().generic(),count/32);
                                        }
                                    }
                                    if (itemStack.isOf(DNAItems.cell_god)) {
                                        float count = itemStack.getCount();
                                        player.heal(count/32);
                                    }
                                }));
                            }
                        }
                    });
                });
            }
        }
    }
    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().addTemporaryModifiers(Head(stack));
        if (entity instanceof PlayerEntity player){
            BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
            if (bundlecontents != null) {
                bundlecontents.iterate().forEach((itemStack -> {
                    if (itemStack.getItem() ==(DNAItems.speed_metabolism)) {
                        int count = itemStack.getCount();
                        int a = count / 32;
                        if (player.getHungerManager().getSaturationLevel() < a&&player.getHungerManager().getFoodLevel()>18) {
                            player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() - 1);
                        }
                    }
                    if (itemStack.getItem() ==(DNAItems.cell_disorder)) {
                        int count = itemStack.getCount();
                        int a = count * 10;
                        if (!player.getWorld().isClient&&player.age % a == 0){
                            player.clearStatusEffects();
                        }
                    }
                    if (itemStack.getItem() ==(DNAItems.cell_eyes)) {
                        float count = itemStack.getCount();//64
                        count/=4;
                        Vec3d playerPos = player.getPos().add(0, 0.75, 0);
                        List<LivingEntity> entities = player.getWorld().getEntitiesByClass(LivingEntity.class, new Box(playerPos.x - count, playerPos.y - count, playerPos.z - count, playerPos.x + count, playerPos.y + count, playerPos.z + count), EntityPredicates.EXCEPT_SPECTATOR);

                        for (LivingEntity living : entities){
                            living.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,100,0,false,false));
                        }

                    }

                    if (itemStack.getItem() ==(DNAItems.cell_flu)) {
                        float count = itemStack.getCount();//64
                        count/=4;
                        Vec3d playerPos = player.getPos().add(0, 0.75, 0);
                        List<LivingEntity> entities = player.getWorld().getEntitiesByClass(LivingEntity.class, new Box(playerPos.x - count, playerPos.y - count, playerPos.z - count, playerPos.x + count, playerPos.y + count, playerPos.z + count), EntityPredicates.EXCEPT_SPECTATOR);

                        for (LivingEntity living : entities){
                            if (!(living ==(player))) {
                                living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1, false, false));
                                living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1, false, false));
                                living.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 100, 1, false, false));
                            }
                        }
                    }
                }));

            }
        }
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> multimap = HashMultimap.create();

        BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
        if (bundlecontents != null) {
            bundlecontents.iterate().forEach((itemStack -> {
                if (itemStack.getItem() == (DNAItems.atp_height)) {
                    int count = itemStack.getCount();
                    int a = count / 4;
                    multimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            a,
                            EntityAttributeModifier.Operation.ADD_VALUE));
                }

                if (itemStack.getItem() == (DNAItems.cell_off_on)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

                if (itemStack.getItem() == (DNAItems.cell_oxygen)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    count *= 0.5F;
                    multimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }


                if (itemStack.getItem() == (DNAItems.cell_in_water)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_VALUE));
                }

                if (itemStack.getItem() == (DNAItems.cell_break_down_water)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(AttReg.swiming, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

                if (itemStack.getItem() == (DNAItems.cell_in_air)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(EntityAttributes.GENERIC_JUMP_STRENGTH, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.getItem() == (DNAItems.cell_ground)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    count *= 2F;
                    multimap.put(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.getItem() == (DNAItems.cell_necrosis)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(AttReg.heal, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.getItem() == (DNAItems.cell_bone_add)) {
                    float count = itemStack.getCount();
                    count /= 4f;
                    multimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_VALUE));
                }
                if (itemStack.getItem() == (DNAItems.cell_sense)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(EntityAttributes.GENERIC_OXYGEN_BONUS, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_VALUE));

                    multimap.put(EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count*10,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.getItem() == (DNAItems.cell_synthesis)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.getItem() == (DNAItems.cell_putrefactive)) {
                    float count = itemStack.getCount();
                    count /= 100f;
                    multimap.put(EntityAttributes.GENERIC_BURNING_TIME, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            -count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }
                if (itemStack.getItem() == (DNAItems.cell_dna_suppression)) {
                    float count = itemStack.getCount();
                    count /= 100;
                    multimap.put(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

                if (itemStack.getItem() == (DNAItems.cell_preferential)) {
                    {
                        float count = itemStack.getCount();
                        count /= 100;
                        multimap.put(AttReg.heal, new EntityAttributeModifier(
                                Identifier.of("base_attack_damage_heal_cell_preferential" + this.getTranslationKey()),
                                count,
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                    }
                    {
                        float count = itemStack.getCount();
                        count /= 4;
                        multimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(
                                Identifier.of("base_attack_damage_max_health_cell_preferential" + this.getTranslationKey()),
                                count,
                                EntityAttributeModifier.Operation.ADD_VALUE));
                    }
                }
                if (itemStack.getItem() == (DNAItems.cell_chromosome)) {
                    float count = itemStack.getCount();
                    count /= 10;
                    multimap.put(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, new EntityAttributeModifier(
                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
                            count,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                }

            }));
        }
        return multimap;
    }

    private Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> Head(ItemStack stack){
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> multimap = HashMultimap.create();
//
//        BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);
//        if (bundlecontents != null) {
//            bundlecontents.iterate().forEach((itemStack -> {
//                if (itemStack.getItem() == (DNAItems.atp_height)) {
//                    int count = itemStack.getCount();
//                    int a = count / 4;
//                    multimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            a,
//                            EntityAttributeModifier.Operation.ADD_VALUE));
//                }
//
//                if (itemStack.getItem() == (DNAItems.cell_off_on)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//
//                if (itemStack.getItem() == (DNAItems.cell_oxygen)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    count *= 0.5F;
//                    multimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//
//
//                if (itemStack.getItem() == (DNAItems.cell_in_water)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_VALUE));
//                }
//
//                if (itemStack.getItem() == (DNAItems.cell_break_down_water)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(AttReg.swiming, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//
//                if (itemStack.getItem() == (DNAItems.cell_in_air)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(EntityAttributes.GENERIC_JUMP_STRENGTH, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_ground)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    count *= 2F;
//                    multimap.put(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_necrosis)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(AttReg.heal, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_bone_add)) {
//                    float count = itemStack.getCount();
//                    count /= 4f;
//                    multimap.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_VALUE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_sense)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(EntityAttributes.GENERIC_OXYGEN_BONUS, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_VALUE));
//
//                    multimap.put(EntityAttributes.PLAYER_SUBMERGED_MINING_SPEED, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count*10,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_synthesis)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_putrefactive)) {
//                    float count = itemStack.getCount();
//                    count /= 100f;
//                    multimap.put(EntityAttributes.GENERIC_BURNING_TIME, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            -count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//                if (itemStack.getItem() == (DNAItems.cell_dna_suppression)) {
//                    float count = itemStack.getCount();
//                    count /= 100;
//                    multimap.put(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//
//                if (itemStack.getItem() == (DNAItems.cell_preferential)) {
//                    {
//                        float count = itemStack.getCount();
//                        count /= 100;
//                        multimap.put(AttReg.heal, new EntityAttributeModifier(
//                                Identifier.of("base_attack_damage_heal_cell_preferential" + this.getTranslationKey()),
//                                count,
//                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                    }
//                    {
//                        float count = itemStack.getCount();
//                        count /= 4;
//                        multimap.put(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(
//                                Identifier.of("base_attack_damage_max_health_cell_preferential" + this.getTranslationKey()),
//                                count,
//                                EntityAttributeModifier.Operation.ADD_VALUE));
//                    }
//                }
//                if (itemStack.getItem() == (DNAItems.cell_chromosome)) {
//                    float count = itemStack.getCount();
//                    count /= 10;
//                    multimap.put(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, new EntityAttributeModifier(
//                            Identifier.of("base_attack_damage"+this.getTranslationKey()),
//                            count,
//                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
//                }
//
//            }));
//        }
        return multimap;
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().removeModifiers(Head(stack));
    }

    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT && slot.canTakePartial(player)) {
            BundleContentsComponent bundleContentsComponent = stack.get(Data.BUNDLE_CONTENTS);
            if (bundleContentsComponent == null) {
                return false;
            } else {
                BundleContentsComponent.Builder builder = new BundleContentsComponent.Builder(bundleContentsComponent);
                if (otherStack.isEmpty()) {
                    if (otherStack.getItem() instanceof DNAItem) {
                        ItemStack itemStack = builder.removeFirst();
                        if (itemStack != null) {
                            this.playRemoveOneSound(player);
                            cursorStackReference.set(itemStack);
                        }
                    }
                } else {
                    if (otherStack.getItem() instanceof DNAItem) {
                        int i = builder.add(otherStack);
                        if (i > 0) {
                            this.playInsertSound(player);
                        }
                    }
                }

                stack.set(Data.BUNDLE_CONTENTS, builder.build());
                return true;
            }
        } else {
            return false;
        }
    }
    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.1F, 0.8F, 0.5F);

    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }
    private static boolean dropAllBundledItems(ItemStack stack, PlayerEntity player) {
        BundleContentsComponent bundleContentsComponent = stack.get(Data.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null && !bundleContentsComponent.isEmpty()) {
            stack.set(Data.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);
            if (player instanceof ServerPlayerEntity) {
                bundleContentsComponent.iterateCopy().forEach((stackx) -> {
                    player.dropItem(stackx, true);
                });
            }

            return true;
        } else {
            return false;
        }
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (dropAllBundledItems(itemStack, user)) {
            this.playDropContentsSound(user);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            return TypedActionResult.success(itemStack, world.isClient());
        } else {
            return TypedActionResult.fail(itemStack);
        }
    }
    private void playRemoveOneSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    private void playDropContentsSound(Entity entity) {
        entity.playSound(SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, 0.8F, 0.8F + entity.getWorld().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        BundleContentsComponent bundlecontents = stack.get(Data.BUNDLE_CONTENTS);

        tooltip.add(Text.translatable("moonfabric.tooltip.dna.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.dna.2").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(""));
        if (bundlecontents != null) {
            bundlecontents.iterate().forEach((itemStack -> {
                tooltip.add(Text.translatable(itemStack.getTranslationKey()).append("："+ itemStack.getCount()).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
            }));
        }
    }
}

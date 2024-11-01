package com.moonfabric.item.common.NaNo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.moonfabric.hasCurio;
import com.moonfabric.init.Data;
import com.moonfabric.item.Ms.extend.doom;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class nanoheart extends doom {


    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity living) {
        double range = 18;
        living.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 300, 4,false,false));
        living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 300, 4,false,false));
        Vec3d srcVec = living.getEyePos();
        Vec3d vec3d = living.getPos();
        int r = 10;
        List<LivingEntity> list = living.getEntityWorld().getEntitiesByClass(LivingEntity.class,new Box(vec3d.x + r,vec3d.y + r,vec3d.z + r,vec3d.x - r,vec3d.y - r,vec3d.z - r), EntityPredicates.EXCEPT_SPECTATOR);
        for (LivingEntity livingEntity : list){

            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0,false,false));
        }

            Vec3d lookVec = living.getRotationVec(1.0F);
        Vec3d destVec = srcVec.add(lookVec.getX() * range, lookVec.getY() * range, lookVec.getZ() * range);
        float var9 = 1.0F;
        List<Entity> possibleList = living.getWorld().getOtherEntities(living, living.getBoundingBox().stretch(lookVec.getX() * range, lookVec.getY() * range, lookVec.getZ() * range).expand(var9, var9, var9));
        for (Entity entity : possibleList) {
            Box collisionBB = entity.getBoundingBox();
            Optional<Vec3d> interceptPos = collisionBB.raycast(srcVec, destVec);
            if (interceptPos.isPresent()) {
                if (entity instanceof LivingEntity liv) {
                    living.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 10, 4,false,false));
                    if (!liv.getWorld().isClient&&liv.age% 40 == 0){
                        liv.getWorld().playSound(null,liv.getX(), liv.getY(),liv.getZ(), SoundEvents.ENTITY_WARDEN_HEARTBEAT, SoundCategory.NEUTRAL,1.5F,1.5F);
                        liv.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100, 0,false,false));
                    }
                }
            }
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().addTemporaryModifiers(Modifiers(entity));
        if (stack.get(Data.CUSTOM_DATA) == null) {
            stack.set(Data.CUSTOM_DATA,new NbtCompound());
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        entity.getAttributes().removeModifiers(Modifiers(entity));
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> Modifiers(LivingEntity entity)
    {
        UUID uuid = UUID.fromString("2a17dfd1-129d-330b-9eb4-f363ef062eed");
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifierMultimap = HashMultimap.create();

        modifierMultimap.put(EntityAttributes.GENERIC_ATTACK_DAMAGE,new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),10, EntityAttributeModifier.Operation.ADD_VALUE));
        modifierMultimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),-0.4, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
        modifierMultimap.put(EntityAttributes.GENERIC_MAX_HEALTH,new EntityAttributeModifier(Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),20, EntityAttributeModifier.Operation.ADD_VALUE));


        return modifierMultimap;
    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player){
            if (hasCurio.has(this,player)){
                return false;
            }
        }
        return true;
    }
  @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.nanoheart.1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanoheart.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanoheart.3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("moonfabric.tooltip.nanoheart.4").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable(""));
    }
}

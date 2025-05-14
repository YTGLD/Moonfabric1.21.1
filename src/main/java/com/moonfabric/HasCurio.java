package com.moonfabric;

import com.moonfabric.Ievent.old.IFood;
import com.moonfabric.init.init;
import com.moonfabric.item.Ms.SNightmare;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;

public class HasCurio {
    public static final String Giant_Time ="Giant_Time";
    public static final String Giant_Boom ="Giant_Boom";
    public static final String Subspace_Giant ="Subspace_Giant";

    public static final String Bone_Giant = "Bone_Giant";
    public static final String Parasitic_cell_Giant = "Parasitic_cell_Giant";
    public static final String Disgusting__cell_Giant = "Disgusting__cell_Giant";


    public static boolean has(Item item , LivingEntity player){
        if (player!= null && !(TrinketsApi.getEntitySlots(player) == null)) {
            if (!TrinketsApi.getEntitySlots(player).isEmpty()) {
                if (!hasNightmare(init.nightmare_base,player)){
                    if (item instanceof SNightmare ) {
                        return false;
                    }
                }
                if (!hasNightmare(init.the_blood_book,player)){
                    if (item == init.owner_blood_speed_eye || item == init.owner_blood_eye) {
                        return true;
                    }

                }
                if (TrinketsApi.getTrinketComponent(player).isPresent()) {
                    return !TrinketsApi.getTrinketComponent(player).get().getEquipped(item).isEmpty();
                }
            }
        }
        return false;
    }
    public static boolean hasNightmare(Item item , LivingEntity player){
        if (player!= null && !(TrinketsApi.getEntitySlots(player) == null)) {
            if (!TrinketsApi.getEntitySlots(player).isEmpty()) {
                if (TrinketsApi.getTrinketComponent(player).isPresent()) {
                    return !TrinketsApi.getTrinketComponent(player).get().getEquipped(item).isEmpty();
                }
            }
        }
        return false;
    }
    public static <T extends TameableEntity> Optional<T> trySpawnMob(LivingEntity player, EntityType<T> entityType, SpawnReason reason, ServerWorld world, BlockPos pos, int tries, int horizontalRange, int verticalRange, LargeEntitySpawnHelper.Requirements requirements) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        TrinketsApi.getTrinketComponent(player).ifPresent((trinketComponent) -> {
            trinketComponent.forEach((slotReference, stack) -> {

            });
        });
        for(int i = 0; i < tries; ++i) {
            int j = MathHelper.nextBetween(world.random, -horizontalRange, horizontalRange);
            int k = MathHelper.nextBetween(world.random, -horizontalRange, horizontalRange);
            mutable.set(pos, j, verticalRange, k);
            if (world.getWorldBorder().contains(mutable) && findSpawnPos(world, verticalRange, mutable, requirements)) {
                T mobEntity = entityType.create(world, null, mutable, reason, false, false);
                if (mobEntity != null) {
                    mobEntity.setOwnerUuid(player.getUuid());

                    if (HasCurio.has(init.anaerobic_cell, player)) {
                        mobEntity.addCommandTag(Giant_Time);
                    }
                    if (HasCurio.has(init.giant_boom_cell, player)) {
                        mobEntity.addCommandTag(Giant_Boom);
                    }
                    if (HasCurio.has(init.subspace_cell, player)) {
                        mobEntity.addCommandTag(Subspace_Giant);
                    }
                    if (HasCurio.has(init.bone_cell, player)) {
                        mobEntity.addCommandTag(Bone_Giant);
                    }
                    if (HasCurio.has(init.parasitic_cell, player)) {
                        mobEntity.addCommandTag(Parasitic_cell_Giant);
                    }
                    if (HasCurio.has(init.disgusting_cells, player)) {
                        mobEntity.addCommandTag(Disgusting__cell_Giant);
                    }
                    mobEntity.setPose(EntityPose.EMERGING);
                    if (mobEntity.canSpawn(world, reason) && mobEntity.canSpawn(world)) {
                        world.spawnEntityAndPassengers(mobEntity);
                        return Optional.of(mobEntity);
                    }

                    mobEntity.discard();
                }
            }
        }

        return Optional.empty();
    }

    private static boolean findSpawnPos(ServerWorld world, int verticalRange, BlockPos.Mutable pos, LargeEntitySpawnHelper.Requirements requirements) {
        BlockPos.Mutable mutable = (new BlockPos.Mutable()).set(pos);
        BlockState blockState = world.getBlockState(mutable);

        for(int i = verticalRange; i >= -verticalRange; --i) {
            pos.move(Direction.DOWN);
            mutable.set(pos, Direction.UP);
            BlockState blockState2 = world.getBlockState(pos);
            if (requirements.canSpawnOn(world, pos, blockState2, mutable, blockState)) {
                pos.move(Direction.UP);
                return true;
            }

            blockState = blockState2;
        }

        return false;
    }

}

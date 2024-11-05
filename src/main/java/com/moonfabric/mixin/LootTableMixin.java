package com.moonfabric.mixin;

import com.moonfabric.init.DNAItems;
import com.moonfabric.init.LootTableEvent;
import com.moonfabric.init.init;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.rmi.registry.Registry;
import java.util.List;
import java.util.Optional;

@Mixin(LootTable.class)
public abstract class LootTableMixin {
    @Shadow public abstract LootContextType getType();

    @Shadow @Final private Optional<Identifier> randomSequenceId;

    @Inject(method = "generateLoot(Lnet/minecraft/loot/context/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", at = @At(value = "RETURN"), cancellable = true)
    private void generateLoot(LootContext context, CallbackInfoReturnable<ObjectArrayList<ItemStack>> cir){
        ObjectArrayList<ItemStack> objectArrayList = cir.getReturnValue();

        if (this.randomSequenceId.isPresent()){

            if (this.randomSequenceId.get().toString().contains("chests")&&
                    this.randomSequenceId.get().toString().contains("dungeon")||
                    this.randomSequenceId.get().toString().contains("treasure")||
                    this.randomSequenceId.get().toString().contains("underwater")||
                    this.randomSequenceId.get().toString().contains("desert")||
                    this.randomSequenceId.get().toString().contains("city")||
                    this.randomSequenceId.get().toString().contains("stronghold")||
                    this.randomSequenceId.get().toString().contains("village")||
                    this.randomSequenceId.get().toString().contains("mineshaft")){

                objectArrayList.add(LootTableEvent.addLootDNA(context,List.of(
                        DNAItems.atp_height,
                        DNAItems.cell_acid,
                        DNAItems.cell_big_boom,
                        DNAItems.cell_bone_add,
                        DNAItems.cell_break_down_water,
                        DNAItems.cell_chromosome,
                        DNAItems.cell_compress,
                        DNAItems.cell_constant,
                        DNAItems.cell_cranial,
                        DNAItems.cell_darwin,
                        DNAItems.cell_digestion,
                        DNAItems.cell_disorder,
                        DNAItems.cell_dna_suppression,
                        DNAItems.cell_eyes,
                        DNAItems.cell_flu,
                        DNAItems.cell_god,
                        DNAItems.cell_ground,
                        DNAItems.cell_in_air,
                        DNAItems.cell_in_water,
                        DNAItems.cell_inheritance,
                        DNAItems.cell_necrosis,
                        DNAItems.cell_off_on,
                        DNAItems.cell_oxygen,
                        DNAItems.cell_preferential,
                        DNAItems.cell_putrefactive,
                        DNAItems.cell_sense,
                        DNAItems.cell_synthesis,
                        DNAItems.speed_metabolism



                        ),50,init.dna, MathHelper.nextInt(Random.create(),4,12)));


                objectArrayList.add(LootTableEvent.addLoot(context,List.of(

                        init.nightmareanchor ,
                        init.nightmarecharm ,
                        init.nightmarerotten ,
                        init.nightmarestone

                ),10,init.nightmareeye).getDefaultStack());


                objectArrayList.add(LootTableEvent.addLoot(context,List.of(
                        init.ambush,
                        init.atpoverdose,
                        init.autolytic,
                        init.fermentation,
                        init.putrefactive,
                        init.regenerative


                ),15,init.necora).getDefaultStack());

                objectArrayList.add(LootTableEvent.addLootNecora(context,List.of(
                        init.cell
                ),35,init.necora, init.cell).getDefaultStack());

                objectArrayList.add(LootTableEvent.addLootNecora(context,List.of(
                        init.giant
                ),35,init.necora,init.giant).getDefaultStack());

                objectArrayList.add(LootTableEvent.addLoot(context,List.of(

                        init.cell_mummy,
                        init.cell_boom,
                        init.cell_calcification,
                        init.cell_blood,
                        init.adrenaline

                ),20,init.cell).getDefaultStack());

                objectArrayList.add(LootTableEvent.addLoot(context,List.of(

                        init.giant_nightmare,
                        init.anaerobic_cell,
                        init.giant_boom_cell,
                        init.subspace_cell,
                        init.parasitic_cell,
                        init.mother_cell,
                        init.disgusting_cells,
                        init.bone_cell

                ),15,init.giant).getDefaultStack());
            }
        }

        cir.setReturnValue(objectArrayList);

    }

}

package com.moonfabric.mixin;

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

            if (this.randomSequenceId.get().getPath().contains("dungeon")){

                objectArrayList.add(LootTableEvent.addLoot(context,List.of(

                        init.nightmareanchor ,
                        init.nightmarecharm ,
                        init.nightmarerotten ,
                        init.nightmarestone

                ),100,init.nightmareeye).getDefaultStack());
            }
        }

        cir.setReturnValue(objectArrayList);

    }

}

package com.moonfabric;

import com.google.common.collect.Multimap;
import com.moonfabric.init.init;
import com.moonfabric.item.INecora;
import com.moonfabric.item.Ms.ectoplasm;
import com.moonfabric.item.Ms.extend.ItemTir;
import com.moonfabric.item.Ms.nightmare;
import com.moonfabric.item.dna.DNAItem;
import com.moonfabric.item.necora;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.List;

public class ABook extends ItemTir {
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (user instanceof ServerPlayerEntity player) {
            if (FabricLoader.getInstance().isModLoaded("patchouli")) {
                PatchouliAPI.get().openBookGUI(player, Identifier.of(MoonFabricMod.MODID, "lexicon_moonfabric"));
            }else {
                player.sendMessage(Text.translatable("moonfabric.book.error").formatted(Formatting.RED));
            }
        }
        return super.use(world, user, hand);
    }

    public static void getTooltip(ItemStack stack, Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir){
        if (player!=null&&HasCurio.has(init.abook,player)){
            if (stack.getItem() instanceof DNAItem){
                List<Text> texts = cir.getReturnValue();
                texts.add(Text.translatable("book.moonfabric.dna").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                texts.add(Text.translatable("book.moonfabric.dna.1").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                cir.setReturnValue(texts);
            }
            if (stack.getItem() instanceof nightmare){
                List<Text> texts = cir.getReturnValue();
                texts.add(Text.translatable("book.moonfabric.nightmare").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                texts.add(Text.translatable("book.moonfabric.nightmare.1").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                cir.setReturnValue(texts);
            }
            if (stack.getItem() instanceof ectoplasm){
                List<Text> texts = cir.getReturnValue();
                texts.add(Text.translatable("book.moonfabric.ectoplasm").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                cir.setReturnValue(texts);
            }
            if (stack.getItem() instanceof INecora){
                List<Text> texts = cir.getReturnValue();
                texts.add(Text.translatable("book.moonfabric.necora.all").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                texts.add(Text.translatable("book.moonfabric.necora.all.1").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                texts.add(Text.translatable("book.moonfabric.necora.all.2").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                cir.setReturnValue(texts);
            }
            if (stack.getItem() instanceof necora){
                List<Text> texts = cir.getReturnValue();
                texts.add(Text.translatable("book.moonfabric.necora").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
                cir.setReturnValue(texts);
            }
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, net.minecraft.item.tooltip.TooltipType type) {
        tooltip.add(Text.translatable("moonfabric.tooltip.abook").setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0XFFDB7093))));
        tooltip.add(Text.translatable(""));
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier){
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        SlotAttributes.addSlotModifier(modifiers,"legs/belt",Identifier.of(String.valueOf(this.getOrCreateTranslationKey())),1, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
}

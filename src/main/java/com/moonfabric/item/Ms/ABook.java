package com.moonfabric.item.Ms;

import com.moonfabric.MoonFabricMod;
import com.moonfabric.item.Ms.extend.ItemTir;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import vazkii.patchouli.api.PatchouliAPI;

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
}

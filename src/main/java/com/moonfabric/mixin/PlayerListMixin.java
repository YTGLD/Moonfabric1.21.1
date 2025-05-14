package com.moonfabric.mixin;

import com.moonfabric.init.init;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ConnectedClientData;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerListMixin {
    @Inject(method = "onPlayerConnect", at = @At(value = "RETURN"))
    private void onPlayerConnect(ClientConnection connection, ServerPlayerEntity player, ConnectedClientData clientData, CallbackInfo ci){
        if (!player.getCommandTags().contains("nightmare")){
            player.giveItemStack(new ItemStack(init.nightmare_base));

            player.addCommandTag("nightmare");
        }
    }
}

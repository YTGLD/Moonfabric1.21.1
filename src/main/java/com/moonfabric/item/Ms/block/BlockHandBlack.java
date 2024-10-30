package com.moonfabric.item.Ms.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class BlockHandBlack extends Block {

    public BlockHandBlack() {
        super(FabricBlockSettings.create().hardness(4.0f));
    }
}

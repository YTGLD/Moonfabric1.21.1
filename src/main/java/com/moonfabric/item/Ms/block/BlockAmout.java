package com.moonfabric.item.Ms.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class BlockAmout extends Block {

    public BlockAmout() {
        super(FabricBlockSettings.create().hardness(4.0f));
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {



        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 0.5f);


    }
}

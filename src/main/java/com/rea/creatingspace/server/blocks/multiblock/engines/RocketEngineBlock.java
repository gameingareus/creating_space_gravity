package com.rea.creatingspace.server.blocks.multiblock.engines;

import com.rea.creatingspace.server.blocks.multiblock.MultiblockController;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class RocketEngineBlock extends MultiblockController {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        //builder.add(ACTIVE);
        super.createBlockStateDefinition(builder.add(ACTIVE));
    }
    public RocketEngineBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(ACTIVE, Boolean.valueOf(true)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context)
                .setValue(ACTIVE, Boolean.valueOf(true));
    }

    @Override
    public Direction getBlockRotation(Direction facing, MultiblockPartType part) {
        //if(part == MultiblockPart.MultiblockMainPart.MultiblockPartType.KINETIC) return facing.getCounterClockWise();
        return facing;
    }

}

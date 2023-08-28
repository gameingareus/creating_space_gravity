package com.rea.creatingspace.server.blockentities;


import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import static com.rea.creatingspace.server.blocks.RocketStarterBlock.GENERATING;

public class RocketStarterBlockEntity extends GeneratingKineticBlockEntity {
    public RocketStarterBlockEntity(BlockEntityType<?> type,BlockPos pos, BlockState state) {
        super(type,pos, state);
        setLazyTickRate(20);
    }

    @Override
    public void initialize() {
        super.initialize();
        if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed())
            updateGeneratedRotation();
    }

    @Override
    public float getGeneratedSpeed() {
        if (this.getBlockState().getValue(GENERATING)) {
            return 64 ;
        } else {
            return 0;
        }
    }

    @Override
    protected void notifyStressCapacityChange(float capacity) {
        super.notifyStressCapacityChange(capacity);
    }
}

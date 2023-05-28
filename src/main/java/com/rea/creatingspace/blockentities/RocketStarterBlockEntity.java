package com.rea.creatingspace.blockentities;


import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rea.creatingspace.blocks.RocketStarterBlock;

import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;

import com.simibubi.create.content.kinetics.motor.CreativeMotorBlock;
import com.simibubi.create.foundation.utility.AngleHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import static com.rea.creatingspace.blocks.RocketStarterBlock.GENERATING;

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
}

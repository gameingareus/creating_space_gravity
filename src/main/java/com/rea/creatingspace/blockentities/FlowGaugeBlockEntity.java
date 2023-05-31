package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.blocks.FlowGaugeBlock;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.PipeConnection;
import com.simibubi.create.content.fluids.pipes.IAxisPipe;
import com.simibubi.create.content.kinetics.gauge.GaugeBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.Lang;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.rea.creatingspace.blocks.FlowGaugeBlock.FACING;
import static java.lang.Math.abs;

public class FlowGaugeBlockEntity extends GaugeBlockEntity {
    public FlowGaugeBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }


    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(new FlowMeterFluidTransportBehaviour(this));
        registerAwardables(behaviours, FluidPropagator.getSharedTriggers());
    }


    public void tick(Level level, BlockPos pos, BlockState state, FlowGaugeBlockEntity flowGaugeBlockEntity) {
        flowGaugeBlockEntity.dialTarget  = 0.5f;
                /*abs(
                flowGaugeBlockEntity
                        .getBehaviour(FlowMeterFluidTransportBehaviour.TYPE)
                        .getProvidedOutwardFluid(state.getValue(FACING).getClockWise())
                        .getAmount());*/
        flowGaugeBlockEntity.setChanged(level,pos,state);
        flowGaugeBlockEntity.sendData();
        super.tick();
    }


    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        tooltip.add(componentSpacing.plainCopy().append(Lang.translateDirect("gui.gauge.info_header")));
        tooltip.add(Component.literal(String.valueOf(dialState)));

        return true;
    }

    public static class FlowMeterFluidTransportBehaviour extends FluidTransportBehaviour{

        public FlowMeterFluidTransportBehaviour(SmartBlockEntity be) {
            super(be);
        }

        @Override
        public boolean canHaveFlowToward(BlockState state, Direction direction) {
            return direction.getAxis() == state.getValue(FACING).getClockWise().getAxis();
        }

        @Nullable
        @Override
        public PipeConnection.Flow getFlow(Direction side) {
            return super.getFlow(side);
        }
    }
}

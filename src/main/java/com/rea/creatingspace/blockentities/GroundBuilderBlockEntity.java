package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.blocks.GroundBuilderBlock;
import com.rea.creatingspace.contraption.RocketContraption;
import com.rea.creatingspace.entities.RocketContraptionEntity;
import com.rea.creatingspace.init.DimensionInit;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.contraptions.AssemblyException;
import com.simibubi.create.content.contraptions.IDisplayAssemblyExceptions;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GroundBuilderBlockEntity extends SmartBlockEntity implements IDisplayAssemblyExceptions {


    boolean assembleNextTick;
    protected AssemblyException lastException;

    public GroundBuilderBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        registerAwardables(behaviours, AllAdvancements.CONTRAPTION_ACTORS);
    }


    @Override
    public void initialize() {
        super.initialize();
        if (!getBlockState().canSurvive(level, worldPosition))
            level.destroyBlock(worldPosition, true);
    }

    public void queueAssembly() {
        assembleNextTick = true;
    }

    @Override
    public void tick() {
        super.tick();

        if (level.isClientSide)
            return;

        if (assembleNextTick) {
            assemble();
            assembleNextTick = false;
        }
    }

    @Override
    public AssemblyException getLastAssemblyException() {
        return lastException;
    }

    private void assemble() {

        if (!(level.getBlockState(worldPosition)
                .getBlock() instanceof GroundBuilderBlock)) {
            return;
        }

        RocketContraption contraption = new RocketContraption();

        Direction direction = getBlockState().getValue(GroundBuilderBlock.FACING);



        try {
            lastException = null;
            if (!contraption.assemble(level, worldPosition.relative(direction)))
                return;

            sendData();
        } catch (AssemblyException e) {
            lastException = e;
            sendData();
            return;
        }

        if (contraption.containsBlockBreakers())
            award(AllAdvancements.CONTRAPTION_ACTORS);

        contraption.removeBlocksFromWorld(level, BlockPos.ZERO);

        ResourceKey<Level> destination;
        if (level.dimension() == Level.OVERWORLD){
            destination = DimensionInit.EARTH_ORBIT_KEY;
        }else {
            destination = Level.OVERWORLD;
        }

        RocketContraptionEntity rocketContraptionEntity = RocketContraptionEntity.create(level, contraption, destination);
        BlockPos anchor = worldPosition.relative(direction);
        rocketContraptionEntity.setPos(anchor.getX(), anchor.getY(), anchor.getZ());
        level.addFreshEntity(rocketContraptionEntity);

        AllSoundEvents.CONTRAPTION_ASSEMBLE.playOnServer(level, worldPosition);


    }

    @Override
    protected void write(CompoundTag compound, boolean clientPacket) {
        AssemblyException.write(compound, lastException);
        super.write(compound, clientPacket);
    }

    @Override
    protected void read(CompoundTag compound, boolean clientPacket) {
        lastException = AssemblyException.read(compound);
        super.read(compound, clientPacket);
    }
}


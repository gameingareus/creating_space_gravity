package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.blocks.MecanicalElectrolyzerBlock;
import com.rea.creatingspace.init.BlockEntityInit;
import com.rea.creatingspace.init.FluidInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MecanicalElectrolyzerBlockEntity extends BlockEntity {

    public MecanicalElectrolyzerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ELECTROLIZER.get(),pos, state);
    }

    //water
    private final LazyOptional<IFluidHandler> waterFluidOptional = LazyOptional.of(()-> this.WATER_TANK);
    private final FluidTank WATER_TANK  = new FluidTank(4000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER.getSource();
        }
    };
    //hydrogen
    private final LazyOptional<IFluidHandler> hydrogenFluidOptional = LazyOptional.of(()-> this.HYDROGEN_TANK);
    private final FluidTank HYDROGEN_TANK = new FluidTank(4000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_HYDROGEN.source.get();
        }
    };
    //oxygen

    private final LazyOptional<IFluidHandler> oxygenFluidOptional = LazyOptional.of(()-> this.OXYGEN_TANK);
    private final FluidTank OXYGEN_TANK = new FluidTank(4000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_OXYGEN.source.get();
        }
    };



    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.FLUID_HANDLER){
            Direction localDir = this.getBlockState().getValue(MecanicalElectrolyzerBlock.FACING);
            if (!(side == Direction.DOWN || side == Direction.UP)) {
                if (localDir == side.getOpposite()) {
                    return this.waterFluidOptional.cast();
                }
                if (localDir == side.getClockWise()) {
                    return this.hydrogenFluidOptional.cast();
                }
                if (localDir == side.getCounterClockWise()) {
                    return this.oxygenFluidOptional.cast();
                }
            }
        }
        return super.getCapability(cap, side);
    }

    public void tick(Level level, BlockPos pos, BlockState state, MecanicalElectrolyzerBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            if (hasRecipe(blockEntity)) {
                blockEntity.HYDROGEN_TANK.fill(new FluidStack(FluidInit.LIQUID_HYDROGEN.source.get(),50), IFluidHandler.FluidAction.EXECUTE);
                blockEntity.OXYGEN_TANK.fill(new FluidStack(FluidInit.LIQUID_OXYGEN.source.get(),50), IFluidHandler.FluidAction.EXECUTE);
                blockEntity.WATER_TANK.drain(100, IFluidHandler.FluidAction.EXECUTE);
            }
        }
    }
    private boolean hasRecipe(MecanicalElectrolyzerBlockEntity blockEntity) {
        boolean enoughWater = blockEntity.WATER_TANK.getFluidAmount()>100;
        boolean enoughSpaceInHTank = blockEntity.HYDROGEN_TANK.getSpace()>50;
        boolean enoughSpaceInOTank = blockEntity.OXYGEN_TANK.getSpace()>100;

        return enoughWater&&enoughSpaceInHTank&&enoughSpaceInOTank;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        HYDROGEN_TANK.readFromNBT(nbt);
        OXYGEN_TANK.readFromNBT(nbt);
        WATER_TANK.readFromNBT(nbt);

    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt = HYDROGEN_TANK.writeToNBT(nbt);
        nbt = OXYGEN_TANK.writeToNBT(nbt);
        nbt = WATER_TANK.writeToNBT(nbt);

        super.saveAdditional(nbt);
    }
}

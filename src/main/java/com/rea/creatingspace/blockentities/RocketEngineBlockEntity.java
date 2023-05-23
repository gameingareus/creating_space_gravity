package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.blocks.ChemicalSynthesizerBlock;
import com.rea.creatingspace.init.BlockEntityInit;
import com.rea.creatingspace.init.FluidInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RocketEngineBlockEntity extends BlockEntity {

    private int isp = 350*10; //seconds

    private int trust = 100000;//Newtons

    private int getConsumption(int density ) {
        return 5000*this.trust/(this.isp*density);
    }


    public RocketEngineBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.ENGINE.get(),pos, state);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        OXYGEN_TANK.readFromNBT(nbt);
        METHANE_TANK.readFromNBT(nbt);
    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt = OXYGEN_TANK.writeToNBT(nbt);
        nbt = METHANE_TANK.writeToNBT(nbt);
        super.saveAdditional(nbt);
    }



    //fluid
    private final LazyOptional<IFluidHandler> ofluidOptional = LazyOptional.of(()-> this.OXYGEN_TANK);
    private final FluidTank OXYGEN_TANK = new FluidTank(10000){
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
            //only south for oxygen input and north for methane output
            Direction localDir = this.getBlockState().getValue(ChemicalSynthesizerBlock.FACING);

            if (localDir == side){
                return this.ofluidOptional.cast();
            }
            if (localDir == side.getOpposite()){
                return this.mfluidOptional.cast();
            }
        }
        return super.getCapability(cap, side);
    }

    private final LazyOptional<IFluidHandler> mfluidOptional = LazyOptional.of(()-> this.METHANE_TANK);
    private final FluidTank METHANE_TANK  = new FluidTank(10000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_METHANE.source.get();
        }
    };


    public static void  tick(Level level, BlockPos pos, BlockState state, RocketEngineBlockEntity entity) {
        //verifying the recipe then craft methane
        if(level.isClientSide()){
            return ;
        }else {
            if (verifyFeeding(level, entity)) {
                entity.OXYGEN_TANK.drain(entity.getConsumption(FluidInit.LIQUID_METHANE.type.get().getDensity()), IFluidHandler.FluidAction.EXECUTE);
                entity.METHANE_TANK.drain(entity.getConsumption(FluidInit.LIQUID_OXYGEN.type.get().getDensity()), IFluidHandler.FluidAction.EXECUTE);

                setChanged(level, pos, state);

            }
        }
    }

    private static boolean verifyFeeding(Level level, BlockEntity entity) {
        boolean enoughMethane = ((RocketEngineBlockEntity)entity).METHANE_TANK.getFluidAmount() > ((RocketEngineBlockEntity) entity).getConsumption(FluidInit.LIQUID_METHANE.type.get().getDensity());
        boolean enoughOxygen = ((RocketEngineBlockEntity) entity).OXYGEN_TANK.getFluidAmount() > ((RocketEngineBlockEntity) entity).getConsumption(FluidInit.LIQUID_OXYGEN.type.get().getDensity());
        boolean commandedToFire = level.getDirectSignalTo(entity.getBlockPos())>0;

        return enoughMethane &&enoughOxygen&&commandedToFire;

    }
}

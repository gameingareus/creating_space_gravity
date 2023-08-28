package com.rea.creatingspace.server.blockentities;


import com.rea.creatingspace.init.ingameobject.FluidInit;
import com.rea.creatingspace.server.blocks.RocketStarterBlock;
import com.simibubi.create.content.kinetics.base.GeneratingKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.rea.creatingspace.server.blocks.RocketStarterBlock.GENERATING;

public class RocketStarterBlockEntity extends GeneratingKineticBlockEntity {
    public RocketStarterBlockEntity(BlockEntityType<?> type,BlockPos pos, BlockState state) {
        super(type,pos, state);
        setLazyTickRate(20);
    }
    @Override
    public void sendData() {
        if (syncCooldown > 0) {
            queuedSync = true;
            return;
        }
        super.sendData();
        queuedSync = false;
        syncCooldown = SYNC_RATE;
    }

    private static final int SYNC_RATE = 8;
    protected int syncCooldown;
    protected boolean queuedSync;

    @Override
    public void initialize() {
        super.initialize();
        if (!hasSource() || getGeneratedSpeed() > getTheoreticalSpeed())
            updateGeneratedRotation();
    }

    @Override
    public float getGeneratedSpeed() {
        if (this.getBlockState().getValue(GENERATING)) {
            return calculateSpeed(IFluidHandler.FluidAction.SIMULATE);
        } else {
            return 0;
        }
    }
    @Override
    protected void write(CompoundTag nbt, boolean clientPacket) {
        nbt = OXYGEN_TANK.writeToNBT(nbt);
        nbt = METHANE_TANK.writeToNBT(nbt);
        super.write(nbt, clientPacket);
    }

    @Override
    protected void read(CompoundTag nbt, boolean clientPacket) {
        super.read(nbt, clientPacket);
        OXYGEN_TANK.readFromNBT(nbt);
        METHANE_TANK.readFromNBT(nbt);
    }



    //fluid
    private final LazyOptional<IFluidHandler> ofluidOptional = LazyOptional.of(()-> this.OXYGEN_TANK);
    private final FluidTank OXYGEN_TANK = new FluidTank(1000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_OXYGEN.getSource();
        }
    };

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.FLUID_HANDLER){
            //only south for oxygen input and north for methane output
            Direction localDir = this.getBlockState().getValue(RocketStarterBlock.FACING);

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
    private final FluidTank METHANE_TANK  = new FluidTank(1000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_METHANE.getSource();
        }
    };


    public void tick(Level level, BlockPos pos, BlockState state, RocketStarterBlockEntity blockEntity) {
        if (!level.isClientSide()) {
            if (syncCooldown > 0) {
                syncCooldown--;
                if (syncCooldown == 0 && queuedSync)
                    sendData();
                }
            if(this.getBlockState().getValue(GENERATING)){
                int newSpeed = calculateSpeed(IFluidHandler.FluidAction.EXECUTE);
                //setSpeed(newSpeed);
                setChanged();
            }
        }

        super.tick();
    }

    private int calculateSpeed(IFluidHandler.FluidAction fluidAction) {
        int o2amount = OXYGEN_TANK.getFluidAmount();
        int methaneAmount = METHANE_TANK.getFluidAmount();
        int o2coef = 1000*2*16/FluidInit.LIQUID_OXYGEN.getType().getDensity();
        int methCoef = 1000*16/FluidInit.LIQUID_METHANE.getType().getDensity();
        int drainAmount;
        if (o2amount/o2coef > methaneAmount/methCoef){
            drainAmount = o2amount/o2coef;
        }
        else {
            drainAmount = methaneAmount/methCoef;
        }
        if (drainAmount == 0){
            return 0;
        }
        OXYGEN_TANK.drain(drainAmount, fluidAction);
        METHANE_TANK.drain(drainAmount, fluidAction);

        int newSpeed = drainAmount/1000*256;
        if (newSpeed == 0){
            return 1;
        }
        return newSpeed;
    }

}

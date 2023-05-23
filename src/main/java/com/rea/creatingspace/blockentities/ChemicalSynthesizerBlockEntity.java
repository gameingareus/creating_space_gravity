package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.blocks.ChemicalSynthesizerBlock;
import com.rea.creatingspace.init.BlockEntityInit;
import com.rea.creatingspace.init.FluidInit;
import com.rea.creatingspace.init.ItemInit;
import com.rea.creatingspace.menus.ChemicalSynthesizerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChemicalSynthesizerBlockEntity extends BlockEntity implements MenuProvider {

    //doesn't load item with a hopper, put unload them and work both way with Create's funnel so no problem
    private final ItemStackHandler inventory = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            ChemicalSynthesizerBlockEntity.this.setChanged();
            super.onContentsChanged(slot);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot){
                case 0 -> stack.getItem() == ItemInit.COAL_DUST.get();
                default -> super.isItemValid(slot, stack);
            };

        }
    };

    private final LazyOptional<IItemHandlerModifiable> itemOptional = LazyOptional.of(() -> this.inventory);



    private int progress = 0;
    private int maxProgress = 78;

    public static final Component TITLE = Component.translatable("container."+ CreatingSpace.MODID +".synthesizer");
    public ChemicalSynthesizerBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.SYNTHESIZER.get(),pos, state);
    }

    public static void  tick(Level level, BlockPos pos, BlockState state, ChemicalSynthesizerBlockEntity synthesizerBlockEntity) {
        //verifying the recipe then craft methane
        if(level.isClientSide()){
            return ;
        }
        if(hasRecipe(synthesizerBlockEntity)){
            synthesizerBlockEntity.progress++;
            setChanged(level,pos,state);

            if(synthesizerBlockEntity.progress >= synthesizerBlockEntity.maxProgress){
                craftFluid(synthesizerBlockEntity);
            }
        } else {
            synthesizerBlockEntity.resetProgress();
            setChanged(level,pos,state);
        }
    }

    private void resetProgress() {
        this.progress =0;
    }

    private static void craftFluid(ChemicalSynthesizerBlockEntity synthesizerBlockEntity) {
        if(hasRecipe(synthesizerBlockEntity)) {
            synthesizerBlockEntity.inventory.extractItem(0,1,false);
            synthesizerBlockEntity.HYDROGEN_TANK.drain(new FluidStack(FluidInit.LIQUID_HYDROGEN.source.get().getSource(),100), IFluidHandler.FluidAction.EXECUTE);
            synthesizerBlockEntity.METHANE_TANK.fill(new FluidStack(FluidInit.LIQUID_METHANE.source.get().getSource(),100), IFluidHandler.FluidAction.EXECUTE);
            //synthesizerBlockEntity.fluidHandler.set...
            synthesizerBlockEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(ChemicalSynthesizerBlockEntity synthesizerBlockEntity) {
        SimpleContainer inventory = new SimpleContainer(synthesizerBlockEntity.inventory.getSlots());

        for (int i = 0;i < synthesizerBlockEntity.inventory.getSlots();i++){
            inventory.setItem(i,synthesizerBlockEntity.inventory.getStackInSlot(i));
        }

        boolean hasHydrogenInTank = synthesizerBlockEntity.HYDROGEN_TANK.getFluidAmount()>=100;
        boolean methaneTankNotFull = synthesizerBlockEntity.METHANE_TANK.getSpace() >= 100;
        boolean hasCarbonInSlot = synthesizerBlockEntity.inventory.getStackInSlot(0).getItem() == ItemInit.COAL_DUST.get();
        return hasHydrogenInTank && hasCarbonInSlot && methaneTankNotFull;
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        inventory.deserializeNBT(nbt.getCompound("inventory"));
        HYDROGEN_TANK.readFromNBT(nbt);
        METHANE_TANK.readFromNBT(nbt);
    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", inventory.serializeNBT());
        nbt = HYDROGEN_TANK.writeToNBT(nbt);
        nbt = METHANE_TANK.writeToNBT(nbt);
        super.saveAdditional(nbt);
    }

    public void drop() {
        SimpleContainer inventory = new SimpleContainer(this.inventory.getSlots());
        for(int i = 0; i < this.inventory.getSlots(); i++){
            inventory.setItem(i, this.inventory.getStackInSlot(i));
        }
        Containers.dropContents(this.level,this.worldPosition,inventory);
    }

    /*@Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? this.itemOptional.cast() : super.getCapability(cap, side);
    }*/

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return this.itemOptional.cast();
        }
        if (cap == ForgeCapabilities.FLUID_HANDLER){
            //only south for hydrogen input and north for methane output
            Direction localDir = this.getBlockState().getValue(ChemicalSynthesizerBlock.FACING);

            if (localDir == side.getOpposite()){
                return this.hydrogenFluidOptional.cast();
            }
            if (localDir == side){
                return this.methaneFluidOptional.cast();
            }
        }
        return super.getCapability(cap, side);
    }





    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0-> ChemicalSynthesizerBlockEntity.this.progress;
                case 1-> ChemicalSynthesizerBlockEntity.this.maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0-> ChemicalSynthesizerBlockEntity.this.progress = value;
                case 1-> ChemicalSynthesizerBlockEntity.this.maxProgress = value;
                default -> {}
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    public ContainerData getContainerData() {
        return this.data;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ChemicalSynthesizerMenu(id, player.getInventory(),this.inventory,this.getBlockPos(),this.data);
    }


    //fluid


    private final LazyOptional<IFluidHandler> hydrogenFluidOptional = LazyOptional.of(()-> this.HYDROGEN_TANK);
    private final FluidTank HYDROGEN_TANK  = new FluidTank(2000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_HYDROGEN.source.get();
        }
    };

    private final LazyOptional<IFluidHandler> methaneFluidOptional = LazyOptional.of(()-> this.METHANE_TANK);
    private final FluidTank METHANE_TANK  = new FluidTank(2000){
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == FluidInit.LIQUID_METHANE.source.get();
        }

    };

    public int getMethaneAmount() {
        return this.METHANE_TANK.getFluidAmount();
    }

    public int getHydrogenAmount() {
        return this.HYDROGEN_TANK.getFluidAmount();
    }
/*
    public void setFluid(FluidStack stack) {
        this.HYDROGEN_TANK.setFluid(stack);

    }

    public FluidStack getFluidStack() {
        return this.HYDROGEN_TANK.getFluid();
    }
 */
}

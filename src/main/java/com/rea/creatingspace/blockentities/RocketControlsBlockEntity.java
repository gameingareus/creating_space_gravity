package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RocketControlsBlockEntity extends BlockEntity /*implements MenuProvider*/ {

    private int controls_id = 0;

    public static final Component TITLE = Component.translatable("container."+ CreatingSpace.MODID +".rocket_controls");
    public RocketControlsBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(/*BlockEntityInit.CONTROLS.get()*/type,pos, state);
    }

    public void tick() {

    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.controls_id = nbt.getInt("Controls_id");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Controls_id",this.controls_id);
    }


    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0-> RocketControlsBlockEntity.this.controls_id;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0-> RocketControlsBlockEntity.this.controls_id = value;
                default -> {}
            }
        }

        @Override
        public int getCount() {
            return 1;
        }
    };

    public ContainerData getContainerData() {
        return this.data;
    }

    /*@Override
    public Component getDisplayName() {
        return null;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return null;
    }*/
}

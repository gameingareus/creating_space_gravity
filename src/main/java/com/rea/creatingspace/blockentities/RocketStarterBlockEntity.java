package com.rea.creatingspace.blockentities;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.blocks.RocketControlsBlock;
import com.rea.creatingspace.blocks.RocketStarterBlock;
import com.rea.creatingspace.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RocketStarterBlockEntity extends BlockEntity {

    private int starter_id = 0;

    public static final Component TITLE = Component.translatable("container."+ CreatingSpace.MODID +".rocket_starter");
    public RocketStarterBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.STARTER.get(),pos, state);
    }

    public void tick( ) {
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.starter_id = nbt.getInt("Starter_id");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Starter_id",this.starter_id);
    }


    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0-> RocketStarterBlockEntity.this.starter_id;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0-> RocketStarterBlockEntity.this.starter_id = value;
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
}

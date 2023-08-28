package com.rea.creatingspace.server.blocks.multiblock.entity;

import com.rea.creatingspace.server.blocks.multiblock.engines.MediumEngineBlock;
import com.rea.creatingspace.server.blocks.multiblock.engines.SmallEngineBlock;
import com.rea.creatingspace.server.blocks.multiblock.util.IOBlockType;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public abstract class RocketEngineBlockEntity extends MultiblockBlockEntity implements MultiblockCapHandler {

    public abstract int getIsp(); //seconds

    public abstract int getTrust();//Newtons


    public RocketEngineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type,pos, state);

    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
    }


    @Override
    public <T> LazyOptional<T> getCaps(Capability<T> cap, IOBlockType type) {
        return LazyOptional.empty();
    }

    @Override
    public void addKinetic(Kinetic k) {

    }

    @Override
    public void dropInv() {

    }
    public abstract InteractionResult onClick(BlockState state,BlockPos pos,Player player, InteractionHand hand);


    public static class MediumEngine extends RocketEngineBlockEntity{
        @Override
        public int getIsp() {
            return 350;
        }

        @Override
        public int getTrust() {
            return 5000000;
        }

        public InteractionResult onClick(BlockState state,BlockPos pos,Player player, InteractionHand hand) {
            if (!level.isClientSide() && player.getItemInHand(hand).isEmpty()){
                level.setBlock(pos, state.cycle(MediumEngineBlock.ACTIVE), 3);
            }


            return InteractionResult.PASS;
        }
        public MediumEngine(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }
    }

    public static class SmallEngine extends RocketEngineBlockEntity{
        @Override
        public int getIsp() {
            return 350;
        }

        @Override
        public int getTrust() {
            return 100000;
        }

        public InteractionResult onClick(BlockState state,BlockPos pos,Player player, InteractionHand hand) {
            if (!level.isClientSide() && player.getItemInHand(hand).isEmpty()){
                level.setBlock(pos, state.cycle(SmallEngineBlock.ACTIVE), 3);
            }


            return InteractionResult.PASS;
        }

        public SmallEngine(BlockEntityType<?> type, BlockPos pos, BlockState state) {
            super(type, pos, state);
        }
    }
}

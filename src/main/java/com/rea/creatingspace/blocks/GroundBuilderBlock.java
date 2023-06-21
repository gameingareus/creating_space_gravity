package com.rea.creatingspace.blocks;

import com.rea.creatingspace.blockentities.GroundBuilderBlockEntity;
import com.rea.creatingspace.init.BlockEntityInit;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GroundBuilderBlock extends DirectionalBlock implements IBE<GroundBuilderBlockEntity> {
    public GroundBuilderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return super.getStateForPlacement(pContext).setValue(FACING, pContext.getNearestLookingDirection().getOpposite());
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()){
            if (player.getItemInHand(hand).isEmpty()){
                if (level.getBlockEntity(pos) instanceof GroundBuilderBlockEntity stationBlock) {
                    stationBlock.queueAssembly();

                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public Class<GroundBuilderBlockEntity> getBlockEntityClass() {
        return GroundBuilderBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends GroundBuilderBlockEntity> getBlockEntityType() {
        return BlockEntityInit.GROUND_STATION.get();
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() ? null : ($0,pos,$1,blockEntity) -> {
            if(blockEntity instanceof GroundBuilderBlockEntity stationBlock) {
                stationBlock.tick();
            }
        };
    }
}

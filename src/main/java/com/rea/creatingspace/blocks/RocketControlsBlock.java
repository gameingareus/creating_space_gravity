package com.rea.creatingspace.blocks;

import com.rea.creatingspace.blockentities.GroundBuilderBlockEntity;
import com.rea.creatingspace.blockentities.RocketControlsBlockEntity;
import com.rea.creatingspace.init.BlockEntityInit;
//import com.rea.creatingspace.menus.RocketControlsMenu;
import com.rea.creatingspace.screen.DestinationScreen;
import com.simibubi.create.content.trains.station.StationBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.gui.ScreenOpener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class RocketControlsBlock extends Block implements IBE<RocketControlsBlockEntity> {

    public RocketControlsBlock(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide()){
            if (player.getItemInHand(hand).isEmpty()){
                if (level.getBlockEntity(pos) instanceof RocketControlsBlockEntity controlsBlock) {
                    controlsBlock.queueAssembly();

                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
    @OnlyIn(value = Dist.CLIENT)
    protected void displayScreen(RocketControlsBlockEntity be, Player player) {
        if (!(player instanceof LocalPlayer))
            return;
        ScreenOpener.open(new DestinationScreen(be));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return switch (state.getValue(FACING)){
            case NORTH -> Shapes.box(0, 0, 0.375, 1, 0.875, 1);
            case SOUTH -> Shapes.box(0, 0, 0, 1, 0.875, 0.625);
            case WEST -> Shapes.box(0.375, 0, 0, 1, 0.875, 1);
            case EAST -> Shapes.box(0, 0, 0, 0.625, 0.875, 1);
            default -> Shapes.box(0, 0, 0.375, 1, 0.875, 1);
        };
    }

    //blockstate

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }



    //blockEntity

    @Override
    public Class<RocketControlsBlockEntity> getBlockEntityClass() {
        return RocketControlsBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends RocketControlsBlockEntity> getBlockEntityType() {
        return BlockEntityInit.CONTROLS.get();
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() ? null : ($0,pos,$1,blockEntity) -> {
            if(blockEntity instanceof RocketControlsBlockEntity controlsBlock) {
                controlsBlock.tick();
            }
        };
    }
}


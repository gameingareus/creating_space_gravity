package com.rea.creatingspace.server.blocks.multiblock.engines;

import com.rea.creatingspace.init.ingameobject.BlockEntityInit;
import com.rea.creatingspace.server.blocks.multiblock.entity.RocketEngineBlockEntity.*;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import static com.rea.creatingspace.server.blocks.multiblock.MultiblockPart.MultiblockMainPart.MultiblockPartType.BLANK;
import static com.rea.creatingspace.server.blocks.multiblock.MultiblockPart.MultiblockMainPart.MultiblockPartType.MAIN;


public class SmallEngineBlock extends RocketEngineBlock implements IBE<SmallEngine> {

	private static final MultiblockPartType[][][] LAYOUT = new MultiblockPartType[][][] {
		{
			{ BLANK}
		},
		{
			{ MAIN}
		}
	};

	public SmallEngineBlock(Properties properties) {
		super(properties);
	}

	@Override
	public Class<SmallEngine> getBlockEntityClass() {
		return SmallEngine.class;
	}

	@Override
	public BlockEntityType<? extends SmallEngine> getBlockEntityType() {
		return BlockEntityInit.SMALL_ENGINE.get();
	}

	@Override
	public MultiblockPartType[][][] getMultiblockLayout() {
		return LAYOUT;
	}

	@Override
	public Vec3i getStart(Direction facing) {
		return new Vec3i(0, 0, 0);
	}


	@Override
	public InteractionResult onActivate(BlockState state, Level level, BlockPos pos, Player player,
										InteractionHand hand, BlockHitResult pHit) {

		//switch from an active to a passive state
		return level.getBlockEntity(pos, getBlockEntityType()).map(te -> te.onClick(state, pos, player, hand)).orElse(InteractionResult.PASS);
	}

}

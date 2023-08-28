package com.rea.creatingspace.server.blocks.multiblock.engines;

import com.rea.creatingspace.init.ingameobject.BlockEntityInit;
import com.rea.creatingspace.server.blocks.multiblock.entity.RocketEngineBlockEntity;
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


public class MediumEngineBlock extends RocketEngineBlock implements IBE<RocketEngineBlockEntity.MediumEngine> {

	private static final MultiblockPartType[][][] LAYOUT = new MultiblockPartType[][][] {
		{
			{BLANK,   BLANK, BLANK},
			{BLANK, BLANK,    BLANK},
			{BLANK,   BLANK,    BLANK}
		},
		{
			{BLANK, BLANK,    BLANK},
			{BLANK, MAIN,     BLANK},
			{BLANK, BLANK, BLANK}
		},
			{
			{BLANK,   BLANK, BLANK},
			{BLANK, BLANK,    BLANK},
			{BLANK,   BLANK,    BLANK}
		}
	};

	public MediumEngineBlock(Properties pr) {
		super(pr);
		this.registerDefaultState(this.defaultBlockState().setValue(ACTIVE, true));
	}

	@Override
	public Class<RocketEngineBlockEntity.MediumEngine> getBlockEntityClass() {
		return RocketEngineBlockEntity.MediumEngine.class;
	}

	@Override
	public BlockEntityType<? extends RocketEngineBlockEntity.MediumEngine> getBlockEntityType() {
		return BlockEntityInit.MEDIUM_ENGINE.get();
	}

	@Override
	public MultiblockPartType[][][] getMultiblockLayout() {
		return LAYOUT;
	}

	@Override
	public Vec3i getStart(Direction facing) {
		return new Vec3i(-1, 0, -1);
	}
	@Override
	public InteractionResult onActivate(BlockState state, Level level, BlockPos pos, Player player,
			InteractionHand hand, BlockHitResult pHit) {

		//switch from an active to a passive state
		return level.getBlockEntity(pos, BlockEntityInit.MEDIUM_ENGINE.get()).map(te -> te.onClick(state,pos,player, hand)).orElse(InteractionResult.PASS);
	}


}

package com.rea.creatingspace.server.blocks.multiblock.entity;

import java.util.List;

import com.rea.creatingspace.server.blocks.multiblock.IOBlock;
import com.rea.creatingspace.server.blocks.multiblock.MultiblockPart;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;

import com.rea.creatingspace.server.blocks.multiblock.util.IOBlockType;

public class IOBlockEntity extends BlockEntity implements IHaveGoggleInformation {
	private IOBlockType type;
	private LazyOptional<?> cap;

	public IOBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState) {
		super(pType, pWorldPosition, pBlockState);
		type = pBlockState.getValue(IOBlock.TYPE);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(type.getCap() == cap) {
			if(this.cap == null) {
				BlockState state = level.getBlockState(worldPosition);
				BlockPos pos = worldPosition;
				for (int i = 0; i<5 && state.getBlock() instanceof MultiblockPart.MultiblockGhostPart; i++) {
					Direction d = ((MultiblockPart.MultiblockGhostPart)state.getBlock()).getParentDir(state);
					pos = pos.relative(d, 1);
					state = level.getBlockState(pos);
				}
				if (state.getBlock() instanceof MultiblockPart) {
					MultiblockPart d = (MultiblockPart) state.getBlock();
					if (d instanceof MultiblockPart.MultiblockGhostPart)this.cap = super.getCapability(cap, side);
					else {
						BlockEntity te = level.getBlockEntity(pos);
						if (te instanceof MultiblockCapHandler) {
							this.cap = ((MultiblockCapHandler)te).getCaps(cap, type);
						} else {
							this.cap = super.getCapability(cap, side);
						}
					}
				}
			}
			if (this.cap == null)return super.getCapability(cap, side);
			return this.cap.cast();
		}
		return super.getCapability(cap, side);
	}

	@Override
	public void setRemoved() {
		if(cap != null)cap.invalidate();
		super.setRemoved();
	}

	@Override
	public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
		BlockState state = level.getBlockState(worldPosition);
		BlockPos pos = worldPosition;
		for (int i = 0; i<5 && state.getBlock() instanceof MultiblockPart.MultiblockGhostPart; i++) {
			Direction d = ((MultiblockPart.MultiblockGhostPart)state.getBlock()).getParentDir(state);
			pos = pos.relative(d, 1);
			state = level.getBlockState(pos);
		}
		if (state.getBlock() instanceof MultiblockPart) {
			MultiblockPart d = (MultiblockPart) state.getBlock();
			if (d instanceof MultiblockPart.MultiblockGhostPart)return false;
			else {
				BlockEntity te = level.getBlockEntity(pos);
				if (te instanceof MultiblockCapHandler) {
					return ((MultiblockCapHandler)te).addToGoggleTooltip(tooltip, isPlayerSneaking);
				} else {
					return false;
				}
			}
		}
		return false;
	}
}

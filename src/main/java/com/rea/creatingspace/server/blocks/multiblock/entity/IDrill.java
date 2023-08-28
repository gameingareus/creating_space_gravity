package com.rea.creatingspace.server.blocks.multiblock.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public interface IDrill {
	boolean isActive();
	ItemStack getDrill();
	BlockPos getBelow();
}

package com.rae.creatingspace.client.renderer.blockentity;

import com.jozufozu.flywheel.backend.Backend;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rae.creatingspace.init.graphics.PartialModelInit;
import com.rae.creatingspace.server.blockentities.MechanicalElectrolyzerBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class MechanicalElectrolyserBlockRenderer extends KineticBlockEntityRenderer<MechanicalElectrolyzerBlockEntity> {

	public MechanicalElectrolyserBlockRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public boolean shouldRenderOffScreen(MechanicalElectrolyzerBlockEntity be) {
		return true;
	}

	@Override
	protected void renderSafe(MechanicalElectrolyzerBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
							  int light, int overlay) {
		super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

		if (Backend.canUseInstancing(be.getLevel()))
			return;

		BlockState blockState = be.getBlockState();
		float renderedHeadOffset =
				be.getRenderedHeadOffset(partialTicks);

		SuperByteBuffer headRender = CachedBufferer.partialFacing(PartialModelInit.ELECTROLYZER_HEAD, blockState,
				blockState.getValue(HORIZONTAL_FACING));
		headRender.translate(0, -renderedHeadOffset, 0)
				.light(light)
				.renderInto(ms, buffer.getBuffer(RenderType.solid()));
	}

	@Override
	protected BlockState getRenderedBlockState(MechanicalElectrolyzerBlockEntity be) {
		return shaft(getRotationAxisOf(be));
	}

}

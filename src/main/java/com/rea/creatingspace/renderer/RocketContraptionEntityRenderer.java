package com.rea.creatingspace.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rea.creatingspace.entities.RocketContraptionEntity;
import com.simibubi.create.content.contraptions.render.ContraptionEntityRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class RocketContraptionEntityRenderer extends ContraptionEntityRenderer<RocketContraptionEntity> {
    public RocketContraptionEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    /*public ResourceLocation getTextureLocation(RocketContraptionEntity entity) {
        return null;
    }*/

    @Override
    public boolean shouldRender(RocketContraptionEntity entity, Frustum clippingHelper, double cameraX, double cameraY,
                                double cameraZ) {
        if (entity.getContraption() == null)
            return false;
        if (!entity.isAliveOrStale())
            return false;
        if (!entity.isReadyForRender())
            return false;

        return super.shouldRender(entity, clippingHelper, cameraX, cameraY, cameraZ);
    }

    @Override
    public void render(RocketContraptionEntity entity, float yaw, float partialTicks, PoseStack ms, MultiBufferSource buffers,
                       int overlay) {
        super.render(entity, yaw, partialTicks, ms, buffers, overlay);
    }
}

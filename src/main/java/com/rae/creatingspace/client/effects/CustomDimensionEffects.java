package com.rae.creatingspace.client.effects;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.rae.creatingspace.CreatingSpace;
import com.rae.creatingspace.utilities.data.AccessibilityMatrixReader;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class CustomDimensionEffects extends DimensionSpecialEffects {

    private static final ResourceLocation SPACE_SKY_LOCATION = new ResourceLocation(CreatingSpace.MODID, "textures/environment/space_sky.png");
    private static final ResourceLocation EARTH_LOCATION = new ResourceLocation(CreatingSpace.MODID, "textures/environment/earth.png");
    private static final ResourceLocation MOON_LOCATION = new ResourceLocation(CreatingSpace.MODID, "textures/environment/moon.png");
    private static final ResourceLocation MARS_LOCATION = new ResourceLocation(CreatingSpace.MODID, "textures/environment/mars.png");
    private static final ResourceLocation SATURN_LOCATION = new ResourceLocation(CreatingSpace.MODID, "textures/environment/saturn.png");
    private static final ResourceLocation SUN_LOCATION = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation MOON_PHASES_LOCATION = new ResourceLocation("textures/environment/moon_phases.png");

    public CustomDimensionEffects(float cloudLevel, boolean hasGround, DimensionSpecialEffects.SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(cloudLevel, hasGround, skyType, forceBrightLightmap, constantAmbientLight);
    }

    @Override
    public Vec3 getBrightnessDependentFogColor(Vec3 vec, float brightness) {
        return vec;
    }

    @Override
    public boolean isFoggyAt(int p_108874_, int p_108875_) {
        return false;
    }

    protected abstract void renderAdditionalBodyEffects(PoseStack poseStack, Camera camera, ResourceLocation bodyTexture);

    @OnlyIn(Dist.CLIENT)
    public static class GenericCelestialOrbitEffect extends CustomDimensionEffects {
        private final ResourceLocation bodyTexture;
        private boolean renderSun = true;

        public GenericCelestialOrbitEffect(ResourceLocation dimensionKey) {
            super(Float.NaN, false, SkyType.NONE, false, false);
            this.bodyTexture = AccessibilityMatrixReader.getTextureForDimension(dimensionKey);
        }
        public void setRenderSun(boolean renderSun) {
            this.renderSun = renderSun;
        }

        @Override
        public boolean renderClouds(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, double camX, double camY, double camZ, Matrix4f projectionMatrix) {
            return true;
        }

        @Override
        public boolean renderSnowAndRain(ClientLevel level, int ticks, float partialTick, LightTexture lightTexture, double camX, double camY, double camZ) {
            return true;
        }

        @Override
        public boolean tickRain(ClientLevel level, int ticks, Camera camera) {
            return true;
        }

        public boolean renderSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
            BufferBuilder bufferbuilder = renderSpaceSky(level, ticks, partialTick, poseStack, camera, projectionMatrix, isFoggy, setupFog);

            poseStack.pushPose();
            float size = 30.0F;
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
            Entity entity = camera.getEntity(); // Get the entity associated with the camera
            poseStack.mulPose(Vector3f.XP.rotationDegrees(entity.level.getGameTime() % 24000L / 24000.0F * 360.0F));


            Matrix4f matrix4f = poseStack.last().pose();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, bodyTexture);
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilder.vertex(matrix4f, -size, 100.0F, -size).uv(0.0F, 0.0F).endVertex();
            bufferbuilder.vertex(matrix4f, size, 100.0F, -size).uv(1.0F, 0.0F).endVertex();
            bufferbuilder.vertex(matrix4f, size, 100.0F, size).uv(1.0F, 1.0F).endVertex();
            bufferbuilder.vertex(matrix4f, -size, 100.0F, size).uv(0.0F, 1.0F).endVertex();
            BufferUploader.drawWithShader(bufferbuilder.end());
            poseStack.popPose();

            if (renderSun) {
                renderAdditionalBodyEffects(poseStack, camera, bodyTexture);
            }

            RenderSystem.depthMask(true);
            RenderSystem.disableBlend();

            return true;
        }

        @Override
        protected void renderAdditionalBodyEffects(PoseStack poseStack, Camera camera, ResourceLocation bodyTexture) {
            // Render Sun
            float sunSize = 20.0F; // Adjust the size as needed

            // Sun rotation
            poseStack.pushPose();
            poseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
            Entity entity = camera.getEntity(); // Get the entity associated with the camera
            poseStack.mulPose(Vector3f.XP.rotationDegrees(entity.level.getGameTime() % 24000L / 24000.0F * 360.0F));


            Matrix4f matrix4fSun = poseStack.last().pose();

            // Render Sun
            RenderSystem.setShaderTexture(0, SUN_LOCATION);

            BufferBuilder bufferbuilderSun = Tesselator.getInstance().getBuilder();
            bufferbuilderSun.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
            bufferbuilderSun.vertex(matrix4fSun, -sunSize, 100.0F, -sunSize).uv(0.0F, 0.0F).endVertex();
            bufferbuilderSun.vertex(matrix4fSun, sunSize, 100.0F, -sunSize).uv(1.0F, 0.0F).endVertex();
            bufferbuilderSun.vertex(matrix4fSun, sunSize, 100.0F, sunSize).uv(1.0F, 1.0F).endVertex();
            bufferbuilderSun.vertex(matrix4fSun, -sunSize, 100.0F, sunSize).uv(0.0F, 1.0F).endVertex();
            BufferUploader.drawWithShader(bufferbuilderSun.end());

            poseStack.popPose();  // Pop Sun transformation
        }
    }

    // ... Define other subclasses (EarthOrbitEffects, MarsOrbitEffects, etc.) similarly as above
    @OnlyIn(Dist.CLIENT)
    public static class EarthOrbitEffects extends GenericCelestialOrbitEffect {
        public EarthOrbitEffects() {
            super(EARTH_LOCATION);
        }

        @Override
        protected void renderAdditionalBodyEffects(PoseStack poseStack, Camera camera, ResourceLocation bodyTexture) {
            // Render Earth-specific effects
            // ...

        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class MarsOrbitEffects extends GenericCelestialOrbitEffect {
        public MarsOrbitEffects() {
            super(MARS_LOCATION);
        }

        @Override
        protected void renderAdditionalBodyEffects(PoseStack poseStack, Camera camera, ResourceLocation bodyTexture) {
            // Render Mars-specific effects
            // ...

        }
    }
    @OnlyIn(Dist.CLIENT)
    public static class MoonOrbitEffect extends GenericCelestialOrbitEffect {
        public MoonOrbitEffect() {
            super(MOON_LOCATION);
        }

        @Override
        protected void renderAdditionalBodyEffects(PoseStack poseStack, Camera camera, ResourceLocation bodyTexture) {
            //moon specific
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class MoonEffect extends GenericCelestialOrbitEffect {
        public MoonEffect() {
            super(MOON_LOCATION);
        }
        @Override
        protected void renderAdditionalBodyEffects(PoseStack poseStack, Camera camera, ResourceLocation bodyTexture) {
            // Add Moon-specific rendering here
        }
    }

    private static BufferBuilder renderSpaceSky(ClientLevel level, int ticks, float partialTick, PoseStack poseStack, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, SPACE_SKY_LOCATION);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        for (int i = 0; i < 6; ++i) {
            poseStack.pushPose();
            if (i == 1) {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
            }

            if (i == 2) {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            }

            if (i == 3) {
                poseStack.mulPose(Vector3f.XP.rotationDegrees(180.0F));
            }

            if (i == 4) {
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
            }

            if (i == 5) {
                poseStack.mulPose(Vector3f.ZP.rotationDegrees(-90.0F));
            }

            int l = i % 3;
            int i1 = i / 4 % 2;
            float col_begin = (float) (l + 0) / 3.0F;
            float l_begin = (float) (i1 + 0) / 2.0F;
            float col_end = (float) (l + 1) / 3.0F;
            float l_end = (float) (i1 + 1) / 2.0F;

            float size = 100.0F;
            float distance = 100.0F;
            Matrix4f matrix4f = poseStack.last().pose();
            bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
            bufferbuilder.vertex(matrix4f, -size, -distance, -size).uv(col_end, l_end).color(255, 255, 255, 255).endVertex();
            bufferbuilder.vertex(matrix4f, -size, -distance, size).uv(col_begin, l_end).color(255, 255, 255, 255).endVertex();
            bufferbuilder.vertex(matrix4f, size, -distance, size).uv(col_begin, l_begin).color(255, 255, 255, 255).endVertex();
            bufferbuilder.vertex(matrix4f, size, -distance, -size).uv(col_end, l_begin).color(255, 255, 255, 255).endVertex();
            BufferUploader.drawWithShader(bufferbuilder.end());
            poseStack.popPose();
        }

        return bufferbuilder;
    }
}

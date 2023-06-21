package com.rea.creatingspace.effects;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class CustomDimensionEffects extends DimensionSpecialEffects {

    public CustomDimensionEffects(float cloudLevel, boolean hasGround, SkyType skyType, boolean forceBrightLightmap, boolean constantAmbientLight) {
        super(cloudLevel, hasGround, skyType, forceBrightLightmap, constantAmbientLight);
    }


    @OnlyIn(Dist.CLIENT)
    public static class SpaceEffects extends CustomDimensionEffects{

        public SpaceEffects() {
            super(Float.NaN, false, SkyType.NONE, true, false);
        }

        @Override
        public Vec3 getBrightnessDependentFogColor(Vec3 vec, float p_108879_) {
            return vec;
        }

        @Override
        public boolean isFoggyAt(int p_108874_, int p_108875_) {
            return false;
        }
    }
}

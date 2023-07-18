package com.rea.creatingspace.init;

import com.rea.creatingspace.effects.CustomDimensionEffects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DimensionEffectInit {

    public DimensionEffectInit() {
    }
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
        event.register(DimensionInit.EARTH_ORBIT_TYPE.location(),new CustomDimensionEffects.EarthOrbitEffects());
        event.register(DimensionInit.MOON_ORBIT_TYPE.location(),new CustomDimensionEffects.MoonOrbitEffect());
        event.register(DimensionInit.MOON_TYPE.location(),new CustomDimensionEffects.MoonEffect());

    }
}

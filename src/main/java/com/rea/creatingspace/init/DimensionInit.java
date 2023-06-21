package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.registries.ForgeRegistries;

public class DimensionInit {

    public static final ResourceKey<Level> EARTH_ORBIT_KEY =
            ResourceKey.create(Registry.DIMENSION_REGISTRY,
                    new ResourceLocation(CreatingSpace.MODID,"earth_orbit"));
    public static final ResourceKey<DimensionType> SPACE_TYPE =
            ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY,
                    new ResourceLocation(CreatingSpace.MODID,"space"));
    public static double SpaceSpawnHeight = 64; //make the rocket came from the top rather than hard tp -> for future version
    public static double PlanetSpawnHeight = 290;

    public static void register() {
        System.out.println("Registering Dimension for : "+ CreatingSpace.MODID);
    }

    public static float gravity(ResourceKey<DimensionType> dimension) {
        if(dimension == SPACE_TYPE){
            return 0f;
        }
        else {
            return 1f;
        }
    }
}



package com.rea.creatingspace.server.worldgen.dimension;

import com.rea.creatingspace.init.worldgen.PlacedFeatureInit;
import com.rea.creatingspace.server.worldgen.CustomCarvers;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

import static net.minecraft.data.worldgen.BiomeDefaultFeatures.addDefaultOres;


public class MoonDimension {
    public static Biome moonPlains() {
        MobSpawnSettings mobspawnsettings = (new MobSpawnSettings.Builder())
                .addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings
                                .SpawnerData(EntityType.SKELETON, 20, 5, 5))

                .addMobCharge(EntityType.SKELETON, 0.7D, 0.15D)
                .build();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                (new BiomeGenerationSettings.Builder())
                        .addCarver(GenerationStep.Carving.AIR, CustomCarvers.MOON_CAVE);

        addMoonDefaultOres(biomegenerationsettings$builder);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(0)
                        .skyColor(0)
                        .backgroundMusic(
                                Musics.createGameMusic(
                                        SoundEvents.MUSIC_GAME))
                                .build())
                .mobSpawnSettings(mobspawnsettings)
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }

    public static void addMoonDefaultOres(BiomeGenerationSettings.Builder builder) {
        
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeatureInit.NICKEL_MOON_ORE.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, PlacedFeatureInit.ALUMINUM_MOON_ORE.getHolder().get());
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES,PlacedFeatureInit.COBALT_MOON_ORE.getHolder().get());

    }
}

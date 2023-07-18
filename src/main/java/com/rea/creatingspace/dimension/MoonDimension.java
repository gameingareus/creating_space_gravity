package com.rea.creatingspace.dimension;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;


public class MoonDimension {//will not be added imediatly, it's just an exemple for the moon -> need first to add regolith to the blocks
    public static Biome moonPlains() {
        double d0 = 0.7D;
        double d1 = 0.15D;
        MobSpawnSettings mobspawnsettings = (new MobSpawnSettings.Builder())
                .addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings
                                .SpawnerData(EntityType.SKELETON, 20, 5, 5))
                .addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings
                                .SpawnerData(EntityType.GHAST, 50, 4, 4))
                .addSpawn(
                        MobCategory.MONSTER,
                        new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 1, 4, 4))
                .addSpawn(
                        MobCategory.CREATURE,
                        new MobSpawnSettings.SpawnerData(EntityType.STRIDER, 60, 1, 2))
                .addMobCharge(EntityType.SKELETON, 0.7D, 0.15D)
                .addMobCharge(EntityType.GHAST, 0.7D, 0.15D)
                .addMobCharge(EntityType.ENDERMAN, 0.7D, 0.15D)
                .addMobCharge(EntityType.STRIDER, 0.7D, 0.15D)
                .build();

        BiomeGenerationSettings.Builder biomegenerationsettings$builder =
                (new BiomeGenerationSettings.Builder())
                        .addCarver(GenerationStep.Carving.AIR, Carvers.NETHER_CAVE)
                        .addFeature(
                                GenerationStep.Decoration.VEGETAL_DECORATION,
                                MiscOverworldPlacements.SPRING_LAVA)
                        .addFeature(
                                GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                                NetherPlacements.BASALT_PILLAR)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.SPRING_OPEN)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.PATCH_FIRE)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.PATCH_SOUL_FIRE)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.GLOWSTONE_EXTRA)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.GLOWSTONE)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.PATCH_CRIMSON_ROOTS)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                OrePlacements.ORE_MAGMA)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                NetherPlacements.SPRING_CLOSED)
                        .addFeature(
                                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                                OrePlacements.ORE_SOUL_SAND);

        BiomeDefaultFeatures.addNetherDefaultOres(biomegenerationsettings$builder);

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .temperature(2.0F)
                .downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(0)
                        .skyColor(0)
                        .ambientParticle(
                                new AmbientParticleSettings(ParticleTypes.ASH, 0.00625F))
                        .ambientLoopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                        .ambientMoodSound(
                                new AmbientMoodSettings(
                                        SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD,
                                        6000, 8, 2.0D))
                        .ambientAdditionsSound(
                                new AmbientAdditionsSettings(
                                        SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS,
                                        0.0111D))
                        .backgroundMusic(
                                Musics.createGameMusic(
                                        SoundEvents.MUSIC_BIOME_SOUL_SAND_VALLEY))
                                .build())
                .mobSpawnSettings(mobspawnsettings)
                .generationSettings(biomegenerationsettings$builder.build())
                .build();
    }
}

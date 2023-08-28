package com.rea.creatingspace.init.worldgen;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.server.worldgen.dimension.MoonDimension;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;


public class BiomesInit {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, CreatingSpace.MODID);

    public static final RegistryObject<Biome> MOON_PLAINS = BIOMES.register("moon_plains", ()-> MoonDimension.moonPlains());

    public static final RegistryObject<Biome> SPACE = BIOMES.register("space",()-> space());

    public static Biome space() {
        BiomeGenerationSettings.Builder biomegenerationsettings$builder = new BiomeGenerationSettings.Builder();
        Music music = Musics.createGameMusic(SoundEvents.MUSIC_GAME);
        return biome(Biome.Precipitation.NONE, 0.5F, 0.5F, new MobSpawnSettings.Builder(), biomegenerationsettings$builder, music);
    }

    protected static int calculateSkyColor(float p_194844_) {
        float $$1 = p_194844_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }
    private static Biome biome(Biome.Precipitation p_236664_, float p_236665_, float p_236666_, MobSpawnSettings.Builder p_236667_, BiomeGenerationSettings.Builder p_236668_, @Nullable Music p_236669_) {
        return biome(p_236664_, p_236665_, p_236666_, 4159204, 329011, p_236667_, p_236668_, p_236669_);
    }

    private static Biome biome(Biome.Precipitation p_236655_, float p_236656_, float p_236657_, int p_236658_, int p_236659_, MobSpawnSettings.Builder p_236660_, BiomeGenerationSettings.Builder p_236661_, @Nullable Music p_236662_) {
        return (new Biome.BiomeBuilder()).precipitation(p_236655_).temperature(p_236656_).downfall(p_236657_).specialEffects((new BiomeSpecialEffects.Builder()).waterColor(p_236658_).waterFogColor(p_236659_).fogColor(12638463).skyColor(calculateSkyColor(p_236656_)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(p_236662_).build()).mobSpawnSettings(p_236660_.build()).generationSettings(p_236661_.build()).build();
    }

    public static void register(IEventBus bus) {
        BIOMES.register(bus);
    }


}

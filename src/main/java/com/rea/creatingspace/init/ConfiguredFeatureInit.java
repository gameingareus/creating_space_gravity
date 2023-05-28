package com.rea.creatingspace.init;

import com.google.common.base.Suppliers;
import com.rea.creatingspace.CreatingSpace;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ConfiguredFeatureInit {
    public static final DeferredRegister<ConfiguredFeature<?,?>>
            CONFIGURED_FEATURES = DeferredRegister.create(
                    Registry.CONFIGURED_FEATURE_REGISTRY, CreatingSpace.MODID);

    private static final Supplier<List<OreConfiguration.TargetBlockState>>
            NICKEL_OVERWORLD_REPLACEMENT = Suppliers.memoize(
                    ()->List.of(
                            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.NICKEL_ORE.get().defaultBlockState()),
                            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_NICKEL_ORE.get().defaultBlockState())
                    )
            );

    public static final RegistryObject<ConfiguredFeature<?,?>> NICKEL_OVERWORLD_ORE =
            CONFIGURED_FEATURES.register(
            "nickel_overworld_ore",
            ()-> new ConfiguredFeature<>(
                    Feature.ORE,
                    new OreConfiguration(NICKEL_OVERWORLD_REPLACEMENT.get(),9)));

    public static void register(IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
    }


}

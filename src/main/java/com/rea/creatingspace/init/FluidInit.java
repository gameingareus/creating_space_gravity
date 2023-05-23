package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.base.FluidRegistryContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CreatingSpace.MODID);

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreatingSpace.MODID);

    public static final FluidRegistryContainer LIQUID_METHANE =
            new FluidRegistryContainer(
                    "liquid_methane",
                    FluidType.Properties.create().temperature(90).density(423),
                    () -> FluidRegistryContainer.createExtension(
                            new FluidRegistryContainer.ClientExtensions(
                                    CreatingSpace.MODID,
                                    "liquid_methane")
                                    .still("liquid_methane","fluids")
                                    .flowing("liquid_methane","fluids")
                                    .overlay("liquid_methane","fluids")
                                    .fogColor(0.75f,0.21f,0.5f)),
                    BlockBehaviour.Properties.copy(Blocks.WATER),
                    new Item.Properties().tab(CreatingSpace.MINERALS_TAB).stacksTo(1));

    public static final FluidRegistryContainer LIQUID_OXYGEN =
            new FluidRegistryContainer(
                    "liquid_oxygen",
                    FluidType.Properties.create().temperature(90).density(1141),
                    () -> FluidRegistryContainer.createExtension(
                            new FluidRegistryContainer.ClientExtensions(
                                    CreatingSpace.MODID,
                                    "liquid_oxygen")
                                    .still("liquid_oxygen","fluids")
                                    .flowing("liquid_oxygen","fluids")
                                    .overlay("liquid_oxygen","fluids")
                                    .fogColor(0.08f,0.55f,0.81f)),
                    BlockBehaviour.Properties.copy(Blocks.WATER),
                    new Item.Properties().tab(CreatingSpace.MINERALS_TAB).stacksTo(1));

    public static final FluidRegistryContainer LIQUID_HYDROGEN =
            new FluidRegistryContainer(
                    "liquid_hydrogen",
                    FluidType.Properties.create().temperature(10).density(70),
                    () -> FluidRegistryContainer.createExtension(
                            new FluidRegistryContainer.ClientExtensions(
                                    CreatingSpace.MODID,
                                    "liquid_hydrogen")
                                    .still("liquid_hydrogen","fluids")
                                    .flowing("liquid_hydrogen","fluids")
                                    .overlay("liquid_hydrogen","fluids")
                                    .fogColor(0.69f,0.34f,0.96f)),
                    BlockBehaviour.Properties.copy(Blocks.WATER),
                    new Item.Properties().tab(CreatingSpace.MINERALS_TAB).stacksTo(1));

}


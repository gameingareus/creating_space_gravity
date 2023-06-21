package com.rea.creatingspace.init;


import com.rea.creatingspace.CreatingSpace;
import com.simibubi.create.AllFluids;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidInteractionRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.Nullable;
import java.util.function.Consumer;

import static com.rea.creatingspace.CreatingSpace.REGISTRATE;

public class FluidInit {

    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_METHANE =
            REGISTRATE.standardFluid("liquid_methane",(p,s,t)-> new ColorFluidAttributes(p,s,t,0x66C03580))
                    .properties(b-> b.viscosity(1000).temperature(90).density(423))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(4)
                            .slopeFindDistance(6)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

                          /*fogColor(0.75f,0.21f,0.5f))*/


    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_OXYGEN =
            REGISTRATE.standardFluid(
                    "liquid_oxygen",(p,s,t)-> new ColorFluidAttributes(p,s,t,0x66158dd0))
                            .properties(b-> b.viscosity(1000).temperature(90).density(1141))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(4)
                            .slopeFindDistance(6)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

                    /*fogColor(0.08f,0.55f,0.81f))*/


    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_HYDROGEN =
            REGISTRATE.standardFluid(
                    "liquid_hydrogen",(p,s,t)-> new ColorFluidAttributes(p,s,t,0x66b056f5))
                    .properties(b-> b.viscosity(1000).temperature(10).density(70))
                    .fluidProperties(p -> p.levelDecreasePerBlock(1)
                            .tickRate(4)
                            .slopeFindDistance(6)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register()
                    ;

/*fogColor(0.69f,0.34f,0.96f))*/


    public static abstract class TintedFluidType extends FluidType {

        protected static final int NO_TINT = 0xffffffff;
        private ResourceLocation stillTexture;
        private ResourceLocation flowingTexture;

        public TintedFluidType(Properties properties, ResourceLocation stillTexture, ResourceLocation flowingTexture) {
            super(properties);
            this.stillTexture = CreatingSpace.resource(stillTexture.getPath());
            this.flowingTexture =  CreatingSpace.resource(flowingTexture.getPath());
        }

        @Override
        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
            consumer.accept(new IClientFluidTypeExtensions() {

                @Override
                public ResourceLocation getStillTexture() {
                    return stillTexture;
                }

                @Override
                public ResourceLocation getFlowingTexture() {
                    return flowingTexture;
                }

                @Override
                public int getTintColor(FluidStack stack) {
                    return TintedFluidType.this.getTintColor(stack);
                }

                @Override
                public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                    return TintedFluidType.this.getTintColor(state, getter, pos);
                }



            });
        }

        protected abstract int getTintColor(FluidStack stack);

        protected abstract int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos);

    }

    private static class ColorFluidAttributes extends TintedFluidType {

        private int tint = 0x00ffffff;

        public ColorFluidAttributes(Properties properties, ResourceLocation stillTexture,
                                      ResourceLocation flowingTexture,int tint) {
            super(properties, stillTexture, flowingTexture);
            setTintColor(tint);
        }

        public void setTintColor(int newTint){
            tint = newTint;
        }

        @Override
        protected int getTintColor(FluidStack stack) {
            return 0xff000000+ Integer.parseInt(
                    Integer.toHexString(tint).substring(2), 16);
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return tint;
        }

    }

    public static void register() {}

    public static void registerFluidInteractions() {

        FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                LIQUID_HYDROGEN.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource()) {
                        return Blocks.OBSIDIAN.defaultBlockState();
                    } else {
                        return Blocks.COBBLESTONE.defaultBlockState();
                    }
                }
        ));
        FluidInteractionRegistry.addInteraction(ForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                LIQUID_HYDROGEN.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource()) {
                        return Blocks.ICE.defaultBlockState();
                    } else {
                        return Blocks.SNOW_BLOCK.defaultBlockState();
                    }
                }
        ));

        FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                LIQUID_OXYGEN.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource()) {
                        return Blocks.OBSIDIAN.defaultBlockState();
                    } else {
                        return Blocks.COBBLESTONE.defaultBlockState();
                    }
                }
        ));
        FluidInteractionRegistry.addInteraction(ForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                LIQUID_OXYGEN.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource()) {
                        return Blocks.ICE.defaultBlockState();
                    } else {
                        return Blocks.SNOW_BLOCK.defaultBlockState();
                    }
                }
        ));

        FluidInteractionRegistry.addInteraction(ForgeMod.LAVA_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                LIQUID_METHANE.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource()) {
                        return Blocks.OBSIDIAN.defaultBlockState();
                    } else {
                        return Blocks.COBBLESTONE.defaultBlockState();
                    }
                }
        ));
        FluidInteractionRegistry.addInteraction(ForgeMod.WATER_TYPE.get(), new FluidInteractionRegistry.InteractionInformation(
                LIQUID_METHANE.get().getFluidType(),
                fluidState -> {
                    if (fluidState.isSource()) {
                        return Blocks.ICE.defaultBlockState();
                    } else {
                        return Blocks.SNOW_BLOCK.defaultBlockState();
                    }
                }
        ));
    }
}


package com.rea.creatingspace.init;


import com.rea.creatingspace.CreatingSpace;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Consumer;

import static com.rea.creatingspace.CreatingSpace.REGISTRATE;

public class FluidInit {

    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_METHANE =
            REGISTRATE.standardFluid("liquid_methane",NoColorFluidAttributes::new)
                    .properties(b-> b.viscosity(1000).temperature(90).density(423))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

                          /*fogColor(0.75f,0.21f,0.5f))*/


    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_OXYGEN =
            REGISTRATE.standardFluid("liquid_oxygen",NoColorFluidAttributes::new)
                            .properties(b-> b.viscosity(1000).temperature(90).density(1141))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

                    /*fogColor(0.08f,0.55f,0.81f))*/


    public static final FluidEntry<ForgeFlowingFluid.Flowing> LIQUID_HYDROGEN =
            REGISTRATE.standardFluid(
                    "liquid_hydrogen",NoColorFluidAttributes::new)
                    .properties(b-> b.viscosity(1000).temperature(10).density(70))
                    .fluidProperties(p -> p.levelDecreasePerBlock(2)
                            .tickRate(25)
                            .slopeFindDistance(3)
                            .explosionResistance(100f))
                    .source(ForgeFlowingFluid.Source::new)
                    .bucket()
                    .build()
                    .register();

/*fogColor(0.69f,0.34f,0.96f))*/


    public static abstract class TintedFluidType extends FluidType {

        protected static final int NO_TINT = 0xffffffff;
        private ResourceLocation stillTexture;
        private ResourceLocation flowingTexture;

        public TintedFluidType(Properties properties, ResourceLocation stillTexture, ResourceLocation flowingTexture) {
            super(properties);
            this.stillTexture = new ResourceLocation(CreatingSpace.MODID,stillTexture.getPath());
            this.flowingTexture = new ResourceLocation(CreatingSpace.MODID,flowingTexture.getPath());
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


    private static class NoColorFluidAttributes extends TintedFluidType {

        public NoColorFluidAttributes(Properties properties, ResourceLocation stillTexture,
                                      ResourceLocation flowingTexture) {
            super(properties, stillTexture, flowingTexture);
        }

        @Override
        protected int getTintColor(FluidStack stack) {
            return NO_TINT;
        }

        @Override
        public int getTintColor(FluidState state, BlockAndTintGetter world, BlockPos pos) {
            return 0x00ffffff;
        }

    }

    public static void register() {}

}


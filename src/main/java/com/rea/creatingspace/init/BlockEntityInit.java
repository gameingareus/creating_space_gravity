package com.rea.creatingspace.init;

import com.rea.creatingspace.blockentities.*;
import com.rea.creatingspace.renderer.MechanicalElectrolyserBlockRenderer;
import com.rea.creatingspace.renderer.RocketStarterBlockRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.simibubi.create.content.kinetics.base.ShaftRenderer;
import com.simibubi.create.content.kinetics.base.ShaftInstance;
import static com.rea.creatingspace.CreatingSpace.REGISTRATE;

public class BlockEntityInit {
    public static final BlockEntityEntry<RocketControlsBlockEntity> CONTROLS =
            REGISTRATE.blockEntity("controls", RocketControlsBlockEntity::new)
            .validBlocks(BlockInit.ROCKET_CONTROLS)
            .register();

    public static final BlockEntityEntry<RocketStarterBlockEntity> STARTER =
            REGISTRATE.blockEntity("starter", RocketStarterBlockEntity::new )
                    .instance(() -> ShaftInstance::new, false)
                    .validBlocks(BlockInit.EXPLOSIVE_STARTER)
                    .renderer(() -> RocketStarterBlockRenderer::new)
                    .register();

    public static final BlockEntityEntry<ChemicalSynthesizerBlockEntity> SYNTHESIZER =
            REGISTRATE.blockEntity("synthesizer", ChemicalSynthesizerBlockEntity::new)
                    .validBlocks(BlockInit.CHEMICAL_SYNTHESIZER)
                    .register();

    public static final BlockEntityEntry<RocketEngineBlockEntity> ENGINE =
            REGISTRATE.blockEntity(
                    "engine",RocketEngineBlockEntity::new)
                    .validBlocks(BlockInit.SMALL_ROCKET_ENGINE)
                    .register();


    public static final BlockEntityEntry<MecanicalElectrolyzerBlockEntity> ELECTROLIZER =
            REGISTRATE.blockEntity(
                    "electrolizer", MecanicalElectrolyzerBlockEntity::new)
                    .instance(()-> ShaftInstance::new)
                    .validBlocks( BlockInit.MECHANICAL_ELECTROLYZER)
                    .renderer(()-> MechanicalElectrolyserBlockRenderer::new)
                    .register();

    public static void register() {}
}

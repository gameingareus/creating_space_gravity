package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.blockentities.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CreatingSpace.MODID);

    public static final RegistryObject<BlockEntityType<RocketControlsBlockEntity>> CONTROLS =
            BLOCK_ENTITIES.register(
                    "controls",
                    () -> BlockEntityType.Builder.of(RocketControlsBlockEntity::new,
                            BlockInit.ROCKET_CONTROLS.get()).build(null)
            );

    public static final RegistryObject<BlockEntityType<RocketStarterBlockEntity>> STARTER =
            BLOCK_ENTITIES.register(
                    "starter",
                    () -> BlockEntityType.Builder.of(RocketStarterBlockEntity::new,
                            BlockInit.EXPLOSIVE_STARTER.get()).build(null)
            );

    public static final RegistryObject<BlockEntityType<ChemicalSynthesizerBlockEntity>> SYNTHESIZER =
            BLOCK_ENTITIES.register(
                    "synthesizer",
                    () -> BlockEntityType.Builder.of(ChemicalSynthesizerBlockEntity::new,
                            BlockInit.CHEMICAL_SYNTHESIZER.get()).build(null)
            );

    public static final RegistryObject<BlockEntityType<RocketEngineBlockEntity>> ENGINE =
            BLOCK_ENTITIES.register(
                    "engine",
                    () -> BlockEntityType.Builder.of(RocketEngineBlockEntity::new,
                            BlockInit.SMALL_ROCKET_ENGINE.get()).build(null)
            );

    public static final RegistryObject<BlockEntityType<MecanicalElectrolyzerBlockEntity>> ELECTROLIZER =
            BLOCK_ENTITIES.register(
                    "electrolizer",
                    () -> BlockEntityType.Builder.of(MecanicalElectrolyzerBlockEntity::new,
                            BlockInit.MECHANICAL_ELECTROLYZER.get()).build(null)
            );

}

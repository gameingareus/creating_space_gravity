package com.rea.creatingspace.init.ingameobject;

import com.rea.creatingspace.server.blocks.*;
import com.rea.creatingspace.server.blocks.multiblock.engines.MediumEngineBlock;
import com.rea.creatingspace.server.blocks.multiblock.engines.SmallEngineBlock;
import com.rea.creatingspace.server.items.MultiBlockItem;
import com.rea.creatingspace.server.blocks.multiblock.IOBlock;
import com.rea.creatingspace.server.blocks.multiblock.KineticInputBlock;
import com.rea.creatingspace.server.blocks.multiblock.MultiblockBlock;
import com.rea.creatingspace.server.blocks.multiblock.MultiblockPart;
import com.rea.creatingspace.init.CreativeModeTabsInit;
import com.rea.creatingspace.init.graphics.SpriteShiftInit;
import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.Tags;

import static com.rea.creatingspace.CreatingSpace.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;
import static com.simibubi.create.foundation.data.TagGen.tagBlockAndItem;

public class BlockInit {

    //just blocks
    public static final BlockEntry<Block> CLAMPS = REGISTRATE
            .block("clamps",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p -> p.strength(1.0f).noOcclusion())
            .item()
            .properties(p -> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<CasingBlock> ROCKET_CASING = REGISTRATE
            .block("rocket_casing",CasingBlock::new)
            .properties(p-> p
                    .color(MaterialColor.COLOR_BLUE))
            .transform(BuilderTransformers.casing(() -> SpriteShiftInit.ROCKET_CASING))
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .build()
            .register();

    public static final BlockEntry<Block> MOON_STONE = REGISTRATE
            .block("moon_stone",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> MOON_REGOLITH = REGISTRATE
            .block("moon_regolith",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f))
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> MOON_SURFACE_REGOLITH = REGISTRATE
            .block("moon_surface_regolith",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f))
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();


    //ores
    public static final BlockEntry<Block> NICKEL_ORE = REGISTRATE.block(
                    "nickel_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(Tags.Blocks.ORES)
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .transform(tagBlockAndItem("ores/nickel", "ores_in_ground/stone"))
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .build()
            .register();


    public static final BlockEntry<Block> DEEPSLATE_NICKEL_ORE = REGISTRATE.block(
                    "deepslate_nickel_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> MOON_NICKEL_ORE = REGISTRATE.block(
                    "moon_nickel_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> RAW_NICKEL_BLOCK = REGISTRATE.block(
                    "raw_nickel_block",Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> MOON_COBALT_ORE = REGISTRATE.block(
                    "moon_cobalt_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_DIAMOND_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();
    public static final BlockEntry<Block> RAW_COBALT_BLOCK = REGISTRATE.block(
                    "raw_cobalt_block",Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();
    public static final BlockEntry<Block> MOON_ALUMINUM_ORE = REGISTRATE.block(
                    "moon_aluminium_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();
    public static final BlockEntry<Block> RAW_ALUMINIUM_BLOCK = REGISTRATE.block(
                    "raw_aluminium_block",Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    //machinery


    public static final BlockEntry<MultiblockBlock> GHOST_BLOCK = REGISTRATE.block("multiblock", MultiblockBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(MultiblockPart.propsGhost())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getBuilder("multiblock_ghost").texture("particle", new ResourceLocation("create:block/brass_casing"))))
            .lang("Multiblock")
            .register();
    public static final BlockEntry<SmallEngineBlock> SMALL_ROCKET_ENGINE = REGISTRATE
            .block("small_rocket_engine", SmallEngineBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(MultiblockPart.props())
            .transform(axeOrPickaxe())
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.standardModel(c, p)))

            .item(MultiBlockItem::new)
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();
    public static final BlockEntry<MediumEngineBlock> MEDIUM_ROCKET_ENGINE = REGISTRATE
            .block("medium_rocket_engine", MediumEngineBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).dynamicShape().noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item(MultiBlockItem::new)
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<RocketControlsBlock> ROCKET_CONTROLS = REGISTRATE.block(
            "rocket_controls", RocketControlsBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).dynamicShape().noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    /*public static final BlockEntry<GroundBuilderBlock> GROUND_STATION = REGISTRATE.block(
                    "ground_station", GroundBuilderBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).noOcclusion())
            .item()
            .transform(customItemModel())
            .register();*/

    public static final BlockEntry<RocketStarterBlock> EXPLOSIVE_STARTER =REGISTRATE.block(
            "explosive_starter",RocketStarterBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(BlockStressDefaults.setCapacity(1024))
            .transform(BlockStressDefaults.setGeneratorSpeed(RocketStarterBlock::getSpeedRange))
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();


    public static final BlockEntry<ChemicalSynthesizerBlock> CHEMICAL_SYNTHESIZER = REGISTRATE.block(
            "chemical_synthesizer", ChemicalSynthesizerBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();


    public static final BlockEntry<MechanicalElectrolyzerBlock> MECHANICAL_ELECTROLYZER = REGISTRATE.block(
            "mechanical_electrolyzer", MechanicalElectrolyzerBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(BlockStressDefaults.setImpact(10))
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<FlowGaugeBlock> FLOW_METER = REGISTRATE
            .block("flow_meter", FlowGaugeBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p -> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p -> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .transform(customItemModel())
            .register();
    public static final BlockEntry<KineticInputBlock> KINETIC_INPUT = REGISTRATE.block("kinetic_input", KineticInputBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(MultiblockPart.propsGhost())
            .transform(BlockStressDefaults.setImpact(16))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getBuilder("kinetic_in").texture("particle", new ResourceLocation("create:block/brass_casing"))))
            .lang("Multiblock Rotational Input")
            .register();
    public static final BlockEntry<IOBlock> IO_BLOCK = REGISTRATE.block("io_block", IOBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(MultiblockPart.propsGhost())
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .transform(TagGen.pickaxeOnly())
            .blockstate((ctx, prov) -> prov.simpleBlock(ctx.getEntry(), prov.models().getBuilder("io_block").texture("particle", new ResourceLocation("create:block/brass_casing"))))
            .lang("Multiblock IO")
            .register();


    public static void register() {}

}

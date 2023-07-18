package com.rea.creatingspace.init;

import com.rea.creatingspace.AllCreativeModeTabs;
import com.rea.creatingspace.blocks.*;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.common.Tags;

import static com.rea.creatingspace.CreatingSpace.REGISTRATE;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.*;

public class BlockInit {
    static {
        REGISTRATE.creativeModeTab(() -> AllCreativeModeTabs.COMPONENT_TAB);
        REGISTRATE.creativeModeTab(() -> AllCreativeModeTabs.MACHINE_TAB);
        REGISTRATE.creativeModeTab(() -> AllCreativeModeTabs.MINERALS_TAB);
    }

    //just blocks
    public static final BlockEntry<Block> CLAMPS = REGISTRATE
            .block("clamps",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p -> p.strength(1.0f).noOcclusion())
            .item()
            .properties(p -> p.tab(AllCreativeModeTabs.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> MOON_STONE = REGISTRATE
            .block("moon_stone",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<Block> MOON_REGOLITH = REGISTRATE
            .block("moon_regolith",Block::new).initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MINERALS_TAB))
            .transform(customItemModel())
            .register();


    //ores
    public static final BlockEntry<Block> NICKEL_ORE = REGISTRATE.block(
                    "nickel_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .tag(Tags.Blocks.ORES)
            .transform(tagBlockAndItem("ores/nickel", "ores_in_ground/stone"))
            .properties(p-> p.tab(AllCreativeModeTabs.MINERALS_TAB))
            .build()
            .register();


    public static final BlockEntry<Block> DEEPSLATE_NICKEL_ORE = REGISTRATE.block(
                    "deepslate_nickel_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    /*public static final BlockEntry<Block> MOON_NICKEL_ORE = REGISTRATE.block(
                    "moon_nickel_ore", Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MINERALS_TAB))
            .transform(customItemModel())
            .register();*/

    public static final BlockEntry<Block> RAW_NICKEL_BLOCK = REGISTRATE.block(
                    "raw_nickel_block",Block::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).requiresCorrectToolForDrops())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MINERALS_TAB))
            .transform(customItemModel())
            .register();

    //machinery
    public static final BlockEntry<RocketEngineBlock> SMALL_ROCKET_ENGINE = REGISTRATE
            .block("small_rocket_engine",RocketEngineBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).dynamicShape().noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<RocketControlsBlock> ROCKET_CONTROLS = REGISTRATE.block(
            "rocket_controls", RocketControlsBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).dynamicShape().noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MACHINE_TAB))
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
            .properties(p-> p.tab(AllCreativeModeTabs.MACHINE_TAB))
            .transform(customItemModel())
            .register();


    public static final BlockEntry<ChemicalSynthesizerBlock> CHEMICAL_SYNTHESIZER = REGISTRATE.block(
            "chemical_synthesizer", ChemicalSynthesizerBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MACHINE_TAB))
            .transform(customItemModel())
            .register();


    public static final BlockEntry<MechanicalElectrolyzerBlock> MECHANICAL_ELECTROLYZER = REGISTRATE.block(
            "mechanical_electrolyzer", MechanicalElectrolyzerBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p-> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(BlockStressDefaults.setImpact(10))
            .transform(axeOrPickaxe())
            .item()
            .properties(p-> p.tab(AllCreativeModeTabs.MACHINE_TAB))
            .transform(customItemModel())
            .register();

    public static final BlockEntry<FlowGaugeBlock> FLOW_METER = REGISTRATE
            .block("flow_meter", FlowGaugeBlock::new)
            .initialProperties(()-> Blocks.STONE)
            .properties(p -> p.strength(1.0f).noOcclusion().requiresCorrectToolForDrops())
            .transform(axeOrPickaxe())
            .item()
            .properties(p -> p.tab(AllCreativeModeTabs.MACHINE_TAB))
            .transform(customItemModel())
            .register();



    public static void register() {}

}

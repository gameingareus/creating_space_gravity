package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.blocks.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            CreatingSpace.MODID);

    //rebuild of BlockInit with create's way

    //just blocks
    public static final RegistryObject<Block> CLAMPS = blockregister(
            "clamps",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.0f)
                    .noOcclusion()),
            new Item.Properties()
                    .tab(CreatingSpace.MACHINE_TAB));

    //machinery
    public static final RegistryObject<RocketEngineBlock> SMALL_ROCKET_ENGINE = blockregister(
            "small_rocket_engine",
            ()-> new RocketEngineBlock(BlockBehaviour.Properties.of(Material.BARRIER)
                    .strength(1.0f)
                    .dynamicShape()
                    .noOcclusion()
    ),
            new Item.Properties()
                    .tab(CreatingSpace.MACHINE_TAB));
    public static final RegistryObject<RocketControlsBlock> ROCKET_CONTROLS = blockregister(
            "rocket_controls",
            ()-> new RocketControlsBlock(BlockBehaviour.Properties.of(Material.BARRIER)
                    .strength(1.0f)
                    .dynamicShape()
                    .noOcclusion()),
            new Item.Properties()
                    .tab(CreatingSpace.MACHINE_TAB));

    public static final RegistryObject<RocketStarterBlock> EXPLOSIVE_STARTER = blockregister(
            "explosive_starter",
            ()-> new RocketStarterBlock(BlockBehaviour.Properties.of(Material.BARRIER)
                    .strength(1.0f)
                    .noOcclusion()),
            new Item.Properties()
                    .tab(CreatingSpace.MACHINE_TAB));

    public static final RegistryObject<ChemicalSynthesizerBlock> CHEMICAL_SYNTHESIZER = blockregister(
            "chemical_synthesizer",
            ()-> new ChemicalSynthesizerBlock(BlockBehaviour.Properties.of(Material.BARRIER)
                    .strength(1.0f)
                    .noOcclusion()),
            new Item.Properties()
                    .tab(CreatingSpace.MACHINE_TAB));

    public static final RegistryObject<MecanicalElectrolyzerBlock> MECHANICAL_ELECTROLYZER = blockregister(
            "mechanical_electrolyzer",
            ()-> new MecanicalElectrolyzerBlock(BlockBehaviour.Properties.of(Material.BARRIER)
                    .strength(1.0f)
                    .noOcclusion()),
            new Item.Properties()
                    .tab(CreatingSpace.MACHINE_TAB));

    //ore
    public static final RegistryObject<Block> NICKEL_ORE = blockregister(
            "nickel_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.0f)
                    .requiresCorrectToolForDrops()),
            new Item.Properties()
                    .tab(CreatingSpace.MINERALS_TAB));

    public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = blockregister(
            "deepslate_nickel_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.0f)
                    .requiresCorrectToolForDrops()),
            new Item.Properties()
                    .tab(CreatingSpace.MINERALS_TAB));

    public static final RegistryObject<Block> RAW_NICKEL_BLOCK = blockregister(
            "raw_nickel_block",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.0f)
                    .requiresCorrectToolForDrops()),
            new Item.Properties()
                    .tab(CreatingSpace.MINERALS_TAB));
    //exemple
    /*
    public static final RegistryObject<FlowerBlock> GENERIC_FLOWER = blockregister(
            "generic_flower",
            ()-> new FlowerBlock(MobEffects.BLINDNESS,
                    200,
                    BlockBehaviour.Properties.copy(Blocks.SUNFLOWER)),
                new Item.Properties()
                        .tab(CreativeModeTab.TAB_DECORATIONS)
        );
*/
    //methods
    //auto creation of blockitem
    private static <T extends Block> RegistryObject<T> blockregister(
            String name, Supplier<T> supplier, Item.Properties properties)
    {
        RegistryObject<T> block = BLOCKS.register(name,supplier);
        ItemInit.ITEMS.register(name,() -> new BlockItem(block.get(), properties));
        return block;
    }

    //fuel
    private static <T extends Block> RegistryObject<T> blockfuelregister(
            String name, Supplier<T> supplier, Item.Properties properties, int burntime) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties) {
            @Override
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                return burntime;
            }
        });
        return block;
    }

    //sub classes

    public static class Tags {
        public static final TagKey<Block> NEEDS_GENERIC_TOOL = BlockTags.create(
                new ResourceLocation(CreatingSpace.MODID, "mineable/needs_generic_tool"));
        public static final TagKey<Item> GENERIC_ITEM_TAG = ItemTags.create(
                new ResourceLocation(CreatingSpace.MODID,"item_tag"));
    }
}

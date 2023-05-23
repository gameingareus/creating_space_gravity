package com.rea.creatingspace;

import com.rea.creatingspace.init.*;
import com.rea.creatingspace.screen.ChemicalSynthesizerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(CreatingSpace.MODID)

public class CreatingSpace {
    public static final String MODID = "creatingspace" ;

    public CreatingSpace() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
        PaintingInit.PAINTINGS.register(bus);
        ConfiguredFeatureInit.CONFIGURED_FEATURES.register(bus);
        PlacedFeatureInit.PLACED_FEATURES.register(bus);
        FluidInit.FLUID_TYPES.register(bus);
        FluidInit.FLUIDS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
        MenuInit.MENUS.register(bus);

    }

    public static final CreativeModeTab MACHINE_TAB = new CreativeModeTab("machine_tab") {
        @Override
        public ItemStack makeIcon() {
            return BlockItem.byBlock(BlockInit.EXPLOSIVE_STARTER.get()).getDefaultInstance();
        }
    };
    public static final CreativeModeTab COMPONENT_TAB = new CreativeModeTab("component_tab") {
        @Override
        public ItemStack makeIcon() {
            return ItemInit.INJECTOR.get().getDefaultInstance();
        }
    };
    public static final CreativeModeTab MINERALS_TAB = new CreativeModeTab("minerals_tab") {
        @Override
        public ItemStack makeIcon() {
            return BlockItem.byBlock(BlockInit.NICKEL_ORE.get()).getDefaultInstance();
        }
    };

}


package com.rea.creatingspace;

import com.rea.creatingspace.init.*;
//import com.rea.creatingspace.screen.ChemicalSynthesizerScreen;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.foundation.data.CreateRegistrate;
//import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.ResourceLocation;
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

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);


    public CreatingSpace() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRATE.registerEventListeners(bus);

        ItemInit.register();
        BlockInit.register();
        BlockEntityInit.register();
        EntityInit.register();
        FluidInit.register();



        DimensionInit.register();

        PaintingInit.register(bus);
        ConfiguredFeatureInit.register(bus);
        PlacedFeatureInit.register(bus);
        //MenuInit.MENUS.register(bus);

        bus.addListener(CreatingSpace::init);

    }
    public static void init(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {

            FluidInit.registerFluidInteractions();
        });
    }

    public static ResourceLocation resource(String path){
        return new ResourceLocation(MODID,path);
    }
}


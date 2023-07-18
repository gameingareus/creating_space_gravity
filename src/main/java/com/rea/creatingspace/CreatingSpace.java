package com.rea.creatingspace;

import com.rea.creatingspace.init.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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

        PonderInit.register();

        bus.addListener(CreatingSpace::init);
        bus.register(DimensionEffectInit.class);

    }
    public static void init(final FMLCommonSetupEvent event) {
        PacketInit.registerPackets();

        event.enqueueWork(() -> {

            FluidInit.registerFluidInteractions();
        });
    }

    public static ResourceLocation resource(String path){
        return new ResourceLocation(MODID,path);
    }
}


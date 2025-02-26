package com.rae.creatingspace.server.armor;

import com.rae.creatingspace.server.armor.OxygenBacktankItem;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

public class OxygenBacktankCurios {
    public static void init(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(OxygenBacktankCurios::onInterModEnqueue);
        modEventBus.addListener(OxygenBacktankCurios::onClientSetup);
    }

    private static void onInterModEnqueue(final InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BACK.getMessageBuilder()
                .build());
    }

    private static void onClientSetup(final FMLClientSetupEvent event) {
        // TODO
    }
}
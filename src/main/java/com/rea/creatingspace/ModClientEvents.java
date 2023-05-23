package com.rea.creatingspace;

import com.rea.creatingspace.init.MenuInit;
import com.rea.creatingspace.screen.ChemicalSynthesizerScreen;
import com.rea.creatingspace.screen.RocketControlsScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CreatingSpace.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
@SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
    MenuScreens.register(MenuInit.SYNTHESIZER.get(), ChemicalSynthesizerScreen::new);
    MenuScreens.register(MenuInit.ROCKET_CONTROLS.get(), RocketControlsScreen::new);

}

}

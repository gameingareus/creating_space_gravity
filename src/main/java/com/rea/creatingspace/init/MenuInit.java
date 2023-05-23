package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.menus.RocketControlsMenu;
import com.rea.creatingspace.menus.ChemicalSynthesizerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class MenuInit {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CreatingSpace.MODID);

    public static final RegistryObject<MenuType<RocketControlsMenu>> ROCKET_CONTROLS =
            MENUS.register(
                    "rocket_controls",
                    ()-> new MenuType<>(RocketControlsMenu::getClientMenu));

    public static final RegistryObject<MenuType<ChemicalSynthesizerMenu>> SYNTHESIZER =
            MENUS.register(
                    "synthesizer",
                    ()-> new MenuType<>(ChemicalSynthesizerMenu::getClientMenu));
}

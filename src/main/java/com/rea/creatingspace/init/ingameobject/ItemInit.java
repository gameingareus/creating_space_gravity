package com.rea.creatingspace.init.ingameobject;

import com.rea.creatingspace.init.CreativeModeTabsInit;
import com.rea.creatingspace.server.items.CryoHandTank;
import com.simibubi.create.foundation.item.CombustibleItem;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import static com.rea.creatingspace.CreatingSpace.REGISTRATE;

public class ItemInit {

    public static final ItemEntry<CryoHandTank> HAND_TANK = REGISTRATE.item(
            "hand_tank", p -> new CryoHandTank(p,1000))
            .properties(p-> p.tab(CreativeModeTabsInit.MACHINE_TAB))
            .register();

    //component
    public static final ItemEntry<Item> INJECTOR = REGISTRATE.item(
            "injector",Item::new)
            .properties(p -> p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    public static final ItemEntry<Item> INCOMPLETE_INJECTOR = REGISTRATE.item(
            "incomplete_injector",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    public static final ItemEntry<Item> INJECTOR_GRID = REGISTRATE.item(
            "injector_grid",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    public static final ItemEntry<Item> INCOMPLET_INJECTOR_GRID = REGISTRATE.item(
            "incomplete_injector_grid",Item::new)
            .register();

    public static final ItemEntry<Item> COPPER_COIL = REGISTRATE.item(
            "copper_coil",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> BASIC_CATALYST = REGISTRATE.item(
            "basic_catalyst",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();




    public static final ItemEntry<CombustibleItem> STARTER_CHARGE = REGISTRATE.item(
        "starter_charge", CombustibleItem::new)
            .onRegister(i -> i.setBurnTime(500))
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    public static final ItemEntry<CombustibleItem> COAL_DUST = REGISTRATE.item(
            "coal_dust", CombustibleItem::new)
            .onRegister(i -> i.setBurnTime(500))
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();
    public static final ItemEntry<Item> STURDY_PROPELLER = REGISTRATE.item(
            "sturdy_propeller",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> COMBUSTION_CHAMBER = REGISTRATE.item(
            "combustion_chamber",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> BELL_NOZZLE = REGISTRATE.item(
            "bell_nozzle",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    //food
    //exemple -> arn't registered...
    public static final ItemEntry<Item> SPACE_FOOD = REGISTRATE.item(
            "space_food",Item::new)
            .properties(p->p.food(new FoodProperties.Builder()
                        .alwaysEat()
                        .nutrition(4)
                        .saturationMod(1f)
                        .fast()
                        .effect(()->
                                    new MobEffectInstance(MobEffects.NIGHT_VISION,
                                            72000
                                            ,1)
                            , 1.0f)
                        .build())
            )
            .register();

    //minerals

    //nickel.json
    public static final ItemEntry<Item> RAW_NICKEL = REGISTRATE.item(
            "raw_nickel",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> CRUSHED_NICKEL_ORE = REGISTRATE.item(
            "crushed_nickel_ore",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    public static final ItemEntry<Item> NICKEL_DUST = REGISTRATE.item(
                    "nickel_dust",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> NICKEL_INGOT = REGISTRATE.item(
            "nickel_ingot",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> NICKEL_NUGGET = REGISTRATE.item(
            "nickel_nugget",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();



    public static final ItemEntry<Item> NICKEL_SHEET = REGISTRATE.item(
            "nickel_sheet",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    //aluminium

    public static final ItemEntry<Item> RAW_ALUMINIUM = REGISTRATE.item(
                    "raw_aluminium",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> CRUSHED_ALUMINIUM_ORE = REGISTRATE.item(
                    "crushed_aluminium_ore",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> ALUMINIUM_INGOT = REGISTRATE.item(
                    "aluminium_ingot",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> ALUMINIUM_NUGGET = REGISTRATE.item(
                    "aluminium_nugget",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();



    public static final ItemEntry<Item> ALUMINIUM_SHEET = REGISTRATE.item(
                    "aluminium_sheet",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();

    //cobalt

    public static final ItemEntry<Item> RAW_COBALT = REGISTRATE.item(
                    "raw_cobalt",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> CRUSHED_COBALT_ORE = REGISTRATE.item(
                    "crushed_cobalt_ore",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> COBALT_INGOT = REGISTRATE.item(
                    "cobalt_ingot",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    public static final ItemEntry<Item> COBALT_NUGGET = REGISTRATE.item(
                    "cobalt_nugget",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();



    public static final ItemEntry<Item> COBALT_SHEET = REGISTRATE.item(
                    "cobalt_sheet",Item::new)
            .properties(p->p.tab(CreativeModeTabsInit.COMPONENT_TAB))
            .register();


    //sub classes
    

    public static void register() {}
}

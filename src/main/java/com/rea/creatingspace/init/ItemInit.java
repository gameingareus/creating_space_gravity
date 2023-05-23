package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.base.FuelItem;
import com.rea.creatingspace.base.ModArmorMaterial;
import com.rea.creatingspace.items.AdvancedItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS ,
            CreatingSpace.MODID);

    //component
    public static final RegistryObject<Item> INJECTOR = ITEMS.register(
            "injector",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    public static final RegistryObject<Item> INCOMPLETE_INJECTOR = ITEMS.register(
            "incomplete_injector",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INJECTOR_GRID = ITEMS.register(
            "injector_grid",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    public static final RegistryObject<Item> INCOMPLET_INJECTOR_GRID = ITEMS.register(
            "incomplete_injector_grid",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_COIL = ITEMS.register(
            "copper_coil",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));


    public static final RegistryObject<Item> BASIC_CATALYST = ITEMS.register(
            "basic_catalyst",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    public static final RegistryObject<Item> NICKEL_DUST = ITEMS.register(
            "nickel_dust",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));


    public static final RegistryObject<Item> STARTER_CHARGE = ITEMS.register(
        "starter_charge",
        ()-> new FuelItem(new Item.Properties()
                .tab(CreatingSpace.COMPONENT_TAB), 500));

    public static final RegistryObject<Item> COAL_DUST = ITEMS.register(
            "coal_dust",
            ()-> new FuelItem(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB), 400));
    public static final RegistryObject<Item> STURDY_PROPELLER = ITEMS.register(
            "sturdy_propeller",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)) );

    public static final RegistryObject<Item> COMBUSTION_CHAMBER = ITEMS.register(
            "combustion_chamber",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)) );

    public static final RegistryObject<Item> BELL_NOZZLE = ITEMS.register(
            "bell_nozzle",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)) );

    //food
    //exemple -> arn't registered...
    public static final RegistryObject<Item> SPACE_FOOD = ITEMS.register(
            "space_food",
            ()-> new Item(new Item.Properties()
                    .food(Foods.SPACE_FOOD)));

    //minerals

    //nickel
    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register(
            "raw_nickel",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    public static final RegistryObject<Item> CRUSHED_NICKEL_ORE = ITEMS.register(
            "crushed_nickel_ore",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register(
            "nickel_ingot",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    public static final RegistryObject<Item> NICKEL_NUGGET = ITEMS.register(
            "nickel_nugget",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));


    public static final RegistryObject<Item> NICKEL_SHEET = ITEMS.register(
            "nickel_sheet",
            ()-> new Item(new Item.Properties()
                    .tab(CreatingSpace.COMPONENT_TAB)));

    //advanceditem
    public static final RegistryObject<AdvancedItem> GENERIC_ADVANCED_ITEM = ITEMS.register(
            "generic_advanced_item",
            ()-> new AdvancedItem(new Item.Properties()));


    //tools
    //generic -> exemples, aren't register in creativetab
    public static final RegistryObject<SwordItem> GENERIC_SWORD = ITEMS.register(
            "generic_sword",
            ()-> new SwordItem(
                    ToolTiers.GENERIC_TIER,
                    5,
                    3.5f,
                    new Item.Properties()
                            ));
    public static final RegistryObject<PickaxeItem> GENERIC_PICKAXE = ITEMS.register(
            "generic_pickaxe",
            ()-> new PickaxeItem(
                    ToolTiers.GENERIC_TIER,
                    2,
                    3.5f,
                    new Item.Properties()
                            ));



    //armors
    //generic -> exemple, aren't register in creativetag
    public static final RegistryObject<ArmorItem> GENERIC_ARMOR_HEAD = ITEMS.register(
            "generic_armor_head",
            ()-> new ArmorItem(
                    ArmorTiers.GENERIC_ARMOR,
                    EquipmentSlot.HEAD,
                    new Item.Properties()
                            ));
    public static final RegistryObject<ArmorItem> GENERIC_ARMOR_CHEST = ITEMS.register(
            "generic_armor_chest",
            ()-> new ArmorItem(
                    ArmorTiers.GENERIC_ARMOR,
                    EquipmentSlot.CHEST,
                    new Item.Properties()
                            ));
    public static final RegistryObject<ArmorItem> GENERIC_ARMOR_LEGS = ITEMS.register(
            "generic_armor_legs",
            ()-> new ArmorItem(
                    ArmorTiers.GENERIC_ARMOR,
                    EquipmentSlot.LEGS,
                    new Item.Properties()
                            ));
    public static final RegistryObject<ArmorItem> GENERIC_ARMOR_FEET = ITEMS.register(
            "generic_armor_feet",
            ()-> new ArmorItem(
                    ArmorTiers.GENERIC_ARMOR,
                    EquipmentSlot.FEET,
                    new Item.Properties()
                            ));


    //sub classes
    public static class Foods {
        public static final FoodProperties SPACE_FOOD =
                new FoodProperties
                        .Builder()
                        .alwaysEat()
                        .nutrition(4)
                        .saturationMod(1f)
                        .fast()
                        .effect(()->
                                new MobEffectInstance(MobEffects.NIGHT_VISION,
                                        72000
                                        ,1)
                                , 1.0f)
                        .build();
    }
    public static class ToolTiers {
        public static final Tier GENERIC_TIER = new ForgeTier(
                2,
                800,
                1.5f,
                3,
                350,
                BlockInit.Tags.NEEDS_GENERIC_TOOL,
                ()-> Ingredient.of(ItemInit.NICKEL_INGOT.get()));
    }
    public static class ArmorTiers {
        public static final ArmorMaterial GENERIC_ARMOR = new ModArmorMaterial(
                "generic_armor",
                200,
                new int[] { 3, 5, 7, 4 },
                300,
                SoundEvents.ARMOR_EQUIP_GENERIC,
                0.0f,
                0.0f,
                ()-> Ingredient.of(ItemInit.NICKEL_INGOT.get()));
    }
}

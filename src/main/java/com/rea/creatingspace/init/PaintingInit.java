package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PaintingInit {
    public  static final DeferredRegister<PaintingVariant> PAINTINGS = DeferredRegister.create(
            ForgeRegistries.PAINTING_VARIANTS,
            CreatingSpace.MODID);

    public static final RegistryObject<PaintingVariant> BLANK_PAINTING = PAINTINGS.register(
            "blank_painting",
            () -> new PaintingVariant(16,16)
            );
}

package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.simibubi.create.AllSoundEvents.*;
import net.minecraft.resources.ResourceLocation;


public class SoundEventInit {



    private static SoundEntryBuilder create(String name) {
        return create(CreatingSpace.resource(name));
    }

    public static SoundEntryBuilder create(ResourceLocation id) {
        return new SoundEntryBuilder(id);
    }


}

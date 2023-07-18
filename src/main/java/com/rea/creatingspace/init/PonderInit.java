package com.rea.creatingspace.init;

import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.ponders.FluidScene;
import com.rea.creatingspace.ponders.RocketScene;
import com.simibubi.create.foundation.ponder.PonderRegistrationHelper;
import com.simibubi.create.infrastructure.ponder.AllPonderTags;

public class PonderInit {

    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper(CreatingSpace.MODID);

    public static void register(){
        // Register storyboards here
        // (!) Added entries require re-launch
        // (!) Modifications inside storyboard methods only require re-opening the ui
        System.out.println("registering ponders");
        HELPER.forComponents(BlockInit.CHEMICAL_SYNTHESIZER)
                .addStoryBoard("chemical_synthesizer/chemical_synthesizer", FluidScene::chemicalSynthesizer);//AllPonderTags.FLUIDS);

        HELPER.forComponents(BlockInit.SMALL_ROCKET_ENGINE,BlockInit.ROCKET_CONTROLS)
                .addStoryBoard("rocket/rocket_building", RocketScene::rocketBuild);
    }
}

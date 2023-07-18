package com.rea.creatingspace.contraption;

import com.rea.creatingspace.blockentities.RocketEngineBlockEntity;
import com.rea.creatingspace.init.BlockInit;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.AssemblyException;
import com.simibubi.create.content.contraptions.ContraptionType;
import com.simibubi.create.content.contraptions.TranslatingContraption;
import com.simibubi.create.content.contraptions.render.ContraptionLighter;
import com.simibubi.create.content.contraptions.render.NonStationaryLighter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.Pair;

public class RocketContraption extends TranslatingContraption {

    private int trust = 0;
    private int dryMass = 0;

    private int propellantConsumption = 0;
    public RocketContraption() {

    }
    @Override
    public boolean assemble(Level level, BlockPos pos) throws AssemblyException {

        if (!searchMovedStructure(level, pos, null)) {
            return false;
        }
        startMoving(level);
        return true;
    }

    @Override
    protected void addBlock(BlockPos pos, Pair<StructureTemplate.StructureBlockInfo, BlockEntity> pair) {
        Block blockAdded = pair.getLeft().state.getBlock();
        if (blockAdded.defaultBlockState()== BlockInit.SMALL_ROCKET_ENGINE.getDefaultState()){
            this.trust += 10000;
            this.propellantConsumption += 10000/(300*9.81*20)*50;
        }
        if (blockAdded.defaultBlockState()== AllBlocks.FLUID_TANK.getDefaultState()){
            this.dryMass += 20;//look on a json file the density ?
        } else {
            this.dryMass += 1000;
        }

        super.addBlock(pos, pair);
    }

    @Override //to allow the intial block to be a part of the contraption
    protected boolean isAnchoringBlockAt(BlockPos pos) {
        return false;
    }

    @Override
    public ContraptionType getType() {
        return CSContraptionType.ROCKET;
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public ContraptionLighter<?> makeLighter() {
        return new NonStationaryLighter<>(this);
    }


    public float getDryMass(){
        return this.dryMass;
    }

    public float getTrust(){
        return this.trust;
    }
    public int getPropellantConsumption(){
        return  this.propellantConsumption;
    }
}

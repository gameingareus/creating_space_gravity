package com.rea.creatingspace.server.contraption;

import com.mojang.logging.LogUtils;
import com.rea.creatingspace.server.blocks.multiblock.entity.RocketEngineBlockEntity;
import com.rea.creatingspace.server.blocks.multiblock.MultiblockBlock;
import com.rea.creatingspace.server.blocks.multiblock.MultiblockController;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.AssemblyException;
import com.simibubi.create.content.contraptions.ContraptionType;
import com.simibubi.create.content.contraptions.TranslatingContraption;
import com.simibubi.create.content.contraptions.render.ContraptionLighter;
import com.simibubi.create.content.contraptions.render.NonStationaryLighter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Queue;
import java.util.Set;

public class RocketContraption extends TranslatingContraption {
    private static final Logger LOGGER = LogUtils.getLogger();

    private int trust = 0;
    private int dryMass = 0;

    private int propellantConsumption = 0;
    public RocketContraption() {

    }
    @Override
    public boolean assemble(Level level, BlockPos pos) throws AssemblyException {
        LOGGER.info("rocket is assembling");

        if (!searchMovedStructure(level, pos, null)) {
            return false;
        }
        startMoving(level);
        LOGGER.info("has finish assembling");

        return true;
    }

    @Override
    protected void addBlock(BlockPos pos, Pair<StructureTemplate.StructureBlockInfo, BlockEntity> pair) {
        LOGGER.info("try to add a Block");
        Block blockAdded = pair.getLeft().state.getBlock();
        BlockEntity blockEntityAdded = pair.getRight();
        LOGGER.info("got the block");
        BlockPos localPos = pos.subtract(anchor);

        if (blockEntityAdded instanceof RocketEngineBlockEntity engineBlockEntity){

            LOGGER.info("adding an engine");

            this.trust += engineBlockEntity.getTrust();
            this.propellantConsumption += engineBlockEntity.getTrust()/(engineBlockEntity.getIsp()*9.81*20)*50;

            //VoxelShape shape = engineBlock.makeShape();

            //bounds.minmax(shape.bounds().move(localPos));
        }
        if (blockAdded.defaultBlockState()== AllBlocks.FLUID_TANK.getDefaultState()){
            LOGGER.info("adding a tank");

            this.dryMass += 20;//look on a json file the density ?
        } else {
            LOGGER.info("adding a normal block");

            this.dryMass += 1000;
        }

        super.addBlock(pos, pair);


        LOGGER.info("finished adding the block");

    }

    @Override
    protected boolean moveBlock(Level world, @Nullable Direction forcedDirection, Queue<BlockPos> frontier, Set<BlockPos> visited) throws AssemblyException {
        /*BlockPos pos = frontier.poll();
        if (pos == null)
            return false;

        BlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof MultiblockBlock || state.getBlock() instanceof MultiblockBlock) {
            visited.add(pos);
            addMultiblock(pos, frontier, visited, state);
            addBlock(pos, capture(world, pos));
        }
        else{
            frontier.add(pos);
        }*/
        return super.moveBlock(world, forcedDirection, frontier, visited);
    }

    private void addMultiblock(BlockPos pos, Queue<BlockPos> frontier, Set<BlockPos> visited, BlockState state){
        Block engineBlock = state.getBlock();

        if (engineBlock instanceof MultiblockController multiblockController){
            Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            Vec3i s = multiblockController.getSize(facing);
            for(int x = 0;x<s.getX();x++) {
                for(int y = 0;y<s.getY();y++) {
                    for(int z = 0;z<s.getZ();z++) {
                        BlockPos offset = pos.offset(x, y, z);
                        if (!visited.contains(offset))
                            frontier.add(offset);
                    }
                }
            }
        }
        if (engineBlock instanceof MultiblockBlock multiblockBlock){

            BlockPos offset = pos.relative(multiblockBlock.getParentDir(state));
            if (!visited.contains(offset))
                frontier.add(offset);
        }
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

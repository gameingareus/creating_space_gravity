package com.rae.creatingspace.server.blockentities;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.infrastructure.config.AllConfigs;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class GravityGeneratorBlockEntity extends KineticBlockEntity {

    private boolean trying = false;
    private boolean automaticRetry = false;
    private int range = 10;


    public void setTrying(boolean trying) {
        this.trying = trying;
    }

    public void setAutomaticRetry(boolean automaticRetry) {
        this.automaticRetry = automaticRetry;
        setChanged();
        sendData();
    }

    public boolean isAutomaticRetry() {
        return automaticRetry;
    }

    public boolean isTrying() {
        return trying;
    }

    public GravityGeneratorBlockEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
        super(p_155228_, p_155229_, p_155230_);
    }








    @Override
    protected void read(CompoundTag nbt, boolean clientPacket) {
        super.read(nbt, clientPacket);
        automaticRetry = nbt.getBoolean("automaticRetry");
        trying = nbt.getBoolean("isTrying");
        range = nbt.getInt("range");
    }



    @Override
    protected void write(CompoundTag nbt, boolean clientPacket) {
        nbt.putBoolean("automaticRetry",automaticRetry);
        nbt.putBoolean("isTrying", trying);
        nbt.putInt("range",range);
        super.write(nbt, clientPacket);
    }


    private List<Long> ListPosToLong(HashSet<BlockPos> blockPosList) {

        ArrayList<Long> posList = new ArrayList<>();
        for (BlockPos pos : blockPosList) {
            posList.add(pos.asLong());
        }
        return  posList;
    }
    private HashSet<BlockPos> ListLongToPos(long[] blockPosList) {

        HashSet<BlockPos> posList = new HashSet<>();
        for (Long posLong : blockPosList) {
            posList.add(BlockPos.of(posLong));
        }
        return  posList;
    }

    public static int speedRequirement(int nbrOfBlock){

        int requirement = Math.max(nbrOfBlock/20,16);

        return Math.min(requirement, AllConfigs.server().kinetics.maxRotationSpeed.get());
    }


    public boolean isEntityWithinRange(LivingEntity entity) {
        BlockPos entityPos = entity.blockPosition();
        return entityPos.closerThan(this.getBlockPos(), this.range);
    }

    public int getRange() {
        return this.range;
    }
}

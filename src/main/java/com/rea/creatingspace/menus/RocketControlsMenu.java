package com.rea.creatingspace.menus;

import com.rea.creatingspace.blockentities.RocketControlsBlockEntity;
import com.rea.creatingspace.init.BlockInit;
import com.rea.creatingspace.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;

public class RocketControlsMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    protected RocketControlsMenu(int id, Inventory playerInventory, BlockPos pos, ContainerData data){
        super(MenuInit.ROCKET_CONTROLS.get(),id);
        this.levelAccess = ContainerLevelAccess.create(playerInventory.player.getLevel(),pos);
        this.data = data;
        /*
        final int slotSizePlus2 = 18;
        final int startX = 8;
        final int stratY = 86;
        final int hotbarY = 142;

        for(int row = 0;row <3 ;++row){
            for(int colomn = 0;colomn<9;++colomn){
                addSlot(new Slot(
                        playerInventory,
                        colomn+row*9 +9,
                        startX+colomn*slotSizePlus2,
                        stratY+row*slotSizePlus2));
            }
        }

        for(int colomn = 0;colomn<9;++colomn){
            addSlot(new Slot(
                    playerInventory,
                    colomn,
                    startX+colomn*slotSizePlus2,
                    hotbarY));
        }*/

        this.addDataSlots(this.data);
    }

    public static RocketControlsMenu getClientMenu(int id, Inventory playerInventory){
        return new RocketControlsMenu(id,playerInventory,BlockPos.ZERO,new SimpleContainerData(1));
    }

    public static MenuConstructor getServerMenu(RocketControlsBlockEntity blockEntity, BlockPos pos){
        return (id,playerInventory,player) -> new RocketControlsMenu(id, playerInventory,pos, blockEntity.getContainerData());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess,player, BlockInit.ROCKET_CONTROLS.get());
    }

    public ContainerData getData() {
        return data;
    }
}

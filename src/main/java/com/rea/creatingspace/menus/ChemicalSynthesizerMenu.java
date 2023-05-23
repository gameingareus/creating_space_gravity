package com.rea.creatingspace.menus;

import com.rea.creatingspace.blockentities.ChemicalSynthesizerBlockEntity;
import com.rea.creatingspace.init.BlockInit;
import com.rea.creatingspace.init.MenuInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ChemicalSynthesizerMenu extends AbstractContainerMenu {

    /*public ChemicalSynthesizerMenu(int id, Inventory inventory, FriendlyByteBuf extraData) {
       this(id,inventory,inventory.player.level.getBlockEntity(extraData.readBlockPos()),new SimpleContainerData(2));
    }*/

    //public final ChemicalSynthesizerBlockEntity blockEntity;
    private final ContainerLevelAccess levelAccess;
    private final ContainerData data;

    //private final ChemicalSynthesizerBlockEntity blockEntity;

    private int methaneAmount = 0;

    private int hydrogeneAmount = 0;


    public ChemicalSynthesizerMenu(int id, Inventory playerInventory, IItemHandler slots , BlockPos pos, ContainerData data){
        super(MenuInit.SYNTHESIZER.get(),id);
        //this.blockEntity = (ChemicalSynthesizerBlockEntity) playerInventory.player.level.getBlockEntity(pos) /*entity*/;
        this.levelAccess = ContainerLevelAccess.create(playerInventory.player.getLevel(),pos);
        this.data = data;
        //this.methaneAmount = ((ChemicalSynthesizerBlockEntity) playerInventory.player.level.getBlockEntity(pos)).getMethaneAmount();
        //this.hydrogeneAmount = ((ChemicalSynthesizerBlockEntity) playerInventory.player.level.getBlockEntity(pos)).getHydrogeneAmount();

        addPlayerInventoryHotbar(playerInventory);
        addSlot(new SlotItemHandler(slots,0,58,35));

        if (!playerInventory.player.getLevel().isClientSide()){
            this.methaneAmount = ((ChemicalSynthesizerBlockEntity) playerInventory.player.level.getBlockEntity(pos)).getMethaneAmount();
            this.hydrogeneAmount = ((ChemicalSynthesizerBlockEntity) playerInventory.player.level.getBlockEntity(pos)).getHydrogenAmount();
        }
        /*
        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            this.addSlot(new SlotItemHandler(handler,0,57,34));
        });*/

        addDataSlots(data);
    }

    public static ChemicalSynthesizerMenu getClientMenu(int id, Inventory playerInventory){
        return new ChemicalSynthesizerMenu(id,playerInventory,new ItemStackHandler(1),BlockPos.ZERO,new SimpleContainerData(2));
    }

    public static MenuConstructor getServerMenu(ChemicalSynthesizerBlockEntity synthesizerBlockEntity, BlockPos pos){
        return (id,playerInventory,player) -> new ChemicalSynthesizerMenu(id, playerInventory,synthesizerBlockEntity.getInventory(),pos, synthesizerBlockEntity.getContainerData());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.levelAccess,player, BlockInit.CHEMICAL_SYNTHESIZER.get());
    }

    public ContainerData getData() {
        return data;
    }

    private void addPlayerInventoryHotbar(Inventory playerInventory){
        final int slotSizePlus2 = 18;
        final int startX = 8;
        final int startY = 86;
        final int hotbarY = 144;

        for(int row = 0;row <3 ;++row){
            for(int colomn = 0;colomn<9;++colomn){
                addSlot(new Slot(
                        playerInventory,
                        colomn+row*9 +9,
                        startX+colomn*slotSizePlus2,
                        startY+row*slotSizePlus2));
            }
        }

        for(int colomn = 0;colomn<9;++colomn){
            addSlot(new Slot(
                    playerInventory,
                    colomn,
                    startX+colomn*slotSizePlus2,
                    hotbarY));
        }

        this.addDataSlots(this.data);
    }

    public boolean isCrafting() {
        return data.get(0)>0 ;
    }


    public int  getMethaneAmount(){
        return this.methaneAmount;
    }
    public int getHydrogeneAmount(){
        return this.hydrogeneAmount;
    }

    }

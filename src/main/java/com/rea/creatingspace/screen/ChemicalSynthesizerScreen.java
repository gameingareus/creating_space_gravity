package com.rea.creatingspace.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.menus.ChemicalSynthesizerMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ChemicalSynthesizerScreen extends AbstractContainerScreen<ChemicalSynthesizerMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CreatingSpace.MODID,"textures/gui/chemical_synthesizer_gui.png");

    public ChemicalSynthesizerScreen(ChemicalSynthesizerMenu synthesizerMenu, Inventory inventory, Component data) {
        super(synthesizerMenu, inventory, data);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;

        this.blit(stack,x,y,0,0,imageWidth,imageHeight);

        renderHydrogeneTank(stack,x,y);
        renderMethaneTank(stack,x,y);


    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
    }

    private void renderHydrogeneTank(PoseStack stack,int x,int y ){
        int height = getTankImageHeight(menu.getHydrogeneAmount(),2000,38);
        blit(stack,
                x + 22,
                y + 66-height,
                176,
                38-height,
                16,height);

    }

    private void renderMethaneTank(PoseStack stack,int x,int y){
        int height = getTankImageHeight(menu.getMethaneAmount(),2000,38);
        blit(stack,
                x + 140,
                y + 65 -height,
                192,
                38 -height,
                16, height);
    }

    public int getTankImageHeight(int quantity,int capacity,int size) {

        return capacity != 0 && quantity != 0 ? quantity * size / capacity : 0;
    }
}

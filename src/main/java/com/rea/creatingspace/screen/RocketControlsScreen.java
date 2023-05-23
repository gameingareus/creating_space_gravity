package com.rea.creatingspace.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.rea.creatingspace.CreatingSpace;
import com.rea.creatingspace.menus.ChemicalSynthesizerMenu;
import com.rea.creatingspace.menus.RocketControlsMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RocketControlsScreen extends AbstractContainerScreen<RocketControlsMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CreatingSpace.MODID,"textures/gui/rocket_controls_gui.png");

    public RocketControlsScreen(RocketControlsMenu synthesizerMenu, Inventory inventory, Component data) {
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

    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
    }
}

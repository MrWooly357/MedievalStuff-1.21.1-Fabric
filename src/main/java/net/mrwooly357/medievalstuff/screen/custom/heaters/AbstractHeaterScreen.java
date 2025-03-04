package net.mrwooly357.medievalstuff.screen.custom.heaters;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class AbstractHeaterScreen<T extends AbstractHeaterScreenHandler> extends HandledScreen<T> {
    private final Identifier backgroundTexture;

    public AbstractHeaterScreen(T handler, PlayerInventory inventory, Text title, Identifier backgroundTexture) {
        super(handler, inventory, title);
        this.backgroundTexture = backgroundTexture;
    }


    @Override
    protected void init() {
        super.init();
        titleX = x;
        titleY = y;
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, backgroundTexture);

        context.drawTexture(backgroundTexture, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}

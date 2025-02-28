package net.mrwooly357.medievalstuff.screen.custom.heaters;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.mrwooly357.medievalstuff.MedievalStuff;

public abstract class AbstractHeaterScreen<T extends AbstractHeaterScreenHandler> extends HandledScreen<T> {
    private final Identifier backgroundTexture;
    private final Identifier litProgressTexture;

    public AbstractHeaterScreen(
            T handler,
            PlayerInventory inventory,
            Text title,
            Identifier backgroundTexture,
            Identifier litProgressTexture
    ) {
        super(handler, inventory, title);
        this.backgroundTexture = backgroundTexture;
        this.litProgressTexture = litProgressTexture;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, backgroundTexture);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(backgroundTexture, x, y, 0, 0, backgroundWidth, backgroundHeight);
        if (this.handler.isBurning()) {
            int l = MathHelper.ceil(handler.getFuelProgress() * 13.0) + 1;
            context.drawGuiTexture(litProgressTexture, 8, 20, 0, 20 - l, this.x + 56, this.y + 36 + 20 - l, 8, l);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}

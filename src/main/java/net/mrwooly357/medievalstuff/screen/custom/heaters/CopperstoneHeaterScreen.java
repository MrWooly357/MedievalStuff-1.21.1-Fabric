package net.mrwooly357.medievalstuff.screen.custom.heaters;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;

public class CopperstoneHeaterScreen extends AbstractHeaterScreen<CopperstoneHeaterScreenHandler> {
    private static final Identifier BACKGROUND_TEXTURE = Identifier.of(
            MedievalStuff.MOD_ID, "textures/gui/heaters/copperstone_heater_gui.png");
    private static final Identifier LIT_PROGRESS_TEXTURE = Identifier.of(
            MedievalStuff.MOD_ID, "textures/gui/heaters/heater_lit_progress.png");


    public CopperstoneHeaterScreen(CopperstoneHeaterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, BACKGROUND_TEXTURE, LIT_PROGRESS_TEXTURE);
    }
}
package net.mrwooly357.medievalstuff.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreenHandler;

public class ModScreenHandlers {

    public static final ScreenHandlerType<CopperstoneHeaterScreenHandler> COPPERSTONE_HEATER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MedievalStuff.MOD_ID, "copperstone_heater_screen_handler"),
                    new ExtendedScreenHandlerType<>(CopperstoneHeaterScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void registerScreenHandlers() {
        MedievalStuff.LOGGER.info("Registering Mod Screen Handlers for " + MedievalStuff.MOD_ID);
    }
}

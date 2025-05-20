package net.mrwooly357.medievalstuff.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.screen.custom.heaters.CopperstoneHeaterScreenHandler;

public class ModScreenHandlerTypes {

    public static final ScreenHandlerType<CopperstoneHeaterScreenHandler> COPPERSTONE_HEATER_SCREEN_HANDLER = register(
            "copperstone_heater_screen_handler", new ExtendedScreenHandlerType<>(
                    CopperstoneHeaterScreenHandler::new, BlockPos.PACKET_CODEC
            )
    );


    private static <T extends ScreenHandler> ScreenHandlerType<T> register(String name, ScreenHandlerType<T> screenHandlerType) {
        return Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MedievalStuff.MOD_ID, name), screenHandlerType);
    }

    public static void init() {
        MedievalStuff.LOGGER.info("Initializing " + MedievalStuff.MOD_ID + " screen handlers");
    }
}

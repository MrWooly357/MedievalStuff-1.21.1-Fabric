package net.mrwooly357.medievalstuff.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.custom.forge_controllers.CopperstoneForgeControllerBlockEntity;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.CopperstoneHeaterBlockEntity;
import net.mrwooly357.medievalstuff.block.entity.custom.tanks.CopperTankBlockEntity;

public class ModBlockEntities {

    public static final BlockEntityType<CopperstoneHeaterBlockEntity> COPPERSTONE_HEATER_BE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(MedievalStuff.MOD_ID, "copperstone_heater_be"),
            BlockEntityType.Builder.create(CopperstoneHeaterBlockEntity::new, ModBlocks.COPPERSTONE_HEATER).build(null)
            );

    public static final BlockEntityType<CopperTankBlockEntity> COPPER_TANK_BE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(MedievalStuff.MOD_ID, "copper_tank_be"),
            BlockEntityType.Builder.create(CopperTankBlockEntity::new, ModBlocks.COPPER_TANK).build(null)
    );

    public static final BlockEntityType<CopperstoneForgeControllerBlockEntity> COPPERSTONE_FORGE_CONTROLLER_BE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, Identifier.of(MedievalStuff.MOD_ID, "copperstone_forge_controller_be"),
            BlockEntityType.Builder.create(CopperstoneForgeControllerBlockEntity::new, ModBlocks.COPPERSTONE_FORGE_CONTROLLER).build(null)
    );


    public static void registerModBlockEntities() {
        MedievalStuff.LOGGER.info("Registering mod block entities for " + MedievalStuff.MOD_ID);
    }
}

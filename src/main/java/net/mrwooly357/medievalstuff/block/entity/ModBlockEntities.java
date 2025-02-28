package net.mrwooly357.medievalstuff.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;
import net.mrwooly357.medievalstuff.block.entity.custom.heaters.CopperstoneHeaterBlockEntity;

public class ModBlockEntities {

    public static final BlockEntityType<CopperstoneHeaterBlockEntity> COPPERSTONE_HEATER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(MedievalStuff.MOD_ID, "copperstone_heater_be"),
                    BlockEntityType.Builder.create(CopperstoneHeaterBlockEntity::new, ModBlocks.COPPERSTONE_HEATER).build(null));


    public static void registerBlockEntities() {
        MedievalStuff.LOGGER.info("Registering Block Entities for " + MedievalStuff.MOD_ID);
    }
}

package net.mrwooly357.medievalstuff.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.mob.passive.jelly.JellyEntity;
import net.mrwooly357.medievalstuff.entity.projectile.khopesh.ThrownCopperKhopeshEntity;

public class ModEntities {
    public static final EntityType<JellyEntity> JELLY = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MedievalStuff.MOD_ID, "jelly"),
            EntityType.Builder.create(JellyEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.8F, 1.0F)
                    .build()
    );
    public static final EntityType<ThrownCopperKhopeshEntity> THROWN_COPPER_KHOPESH = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MedievalStuff.MOD_ID, "thrown_copper_khopesh"),
            EntityType.Builder.<ThrownCopperKhopeshEntity>create(ThrownCopperKhopeshEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .build()
    );


    public static void registerModEntities() {
        MedievalStuff.LOGGER.info("Registering mod entities for " + MedievalStuff.MOD_ID);
    }
}
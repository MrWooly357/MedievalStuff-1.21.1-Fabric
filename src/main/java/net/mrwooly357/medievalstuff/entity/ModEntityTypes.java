package net.mrwooly357.medievalstuff.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight.FallenKnightEntity;
import net.mrwooly357.medievalstuff.entity.mob.passive.jelly.JellyEntity;
import net.mrwooly357.medievalstuff.entity.projectile.khopesh.ThrownCopperKhopeshEntity;
import net.mrwooly357.medievalstuff.registry.EntityTypeRegistryHelper;

public class ModEntityTypes {

    public static final EntityType<JellyEntity> JELLY = register(
            "jelly", EntityType.Builder.create(JellyEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.8F, 1.0F)
                    .build()
    );
    public static final EntityType<ThrownCopperKhopeshEntity> THROWN_COPPER_KHOPESH = register(
            "thrown_copper_khopesh", EntityType.Builder.<ThrownCopperKhopeshEntity>create(ThrownCopperKhopeshEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5F, 0.5F)
                    .build()
    );
    public static final EntityType<FallenKnightEntity> FALLEN_KNIGHT = register(
            "fallen_knight", EntityType.Builder.create(FallenKnightEntity::new, SpawnGroup.MONSTER)
                    .dimensions(0.75F, 2.0F)
                    .build()
    );


    private static <T extends Entity> EntityType<T> register(String name, EntityType<T> entityType) {
        return EntityTypeRegistryHelper.register(Identifier.of(MedievalStuff.MOD_ID, name), entityType);
    }

    public static void init() {
        MedievalStuff.LOGGER.info("Initializing " + MedievalStuff.MOD_ID + " entity types");
    }
}
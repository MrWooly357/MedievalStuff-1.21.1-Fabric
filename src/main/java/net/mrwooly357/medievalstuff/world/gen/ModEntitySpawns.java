package net.mrwooly357.medievalstuff.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;
import net.mrwooly357.medievalstuff.entity.ModEntityTypes;
import net.mrwooly357.medievalstuff.entity.mob.hostile.fallen_knight.FallenKnightEntity;
import net.mrwooly357.medievalstuff.util.ModTags;

public class ModEntitySpawns {

    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                SpawnGroup.CREATURE, ModEntityTypes.JELLY, 100,  2, 5
        );
        SpawnRestriction.register(ModEntityTypes.JELLY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, AnimalEntity::isValidNaturalSpawn
        );

        BiomeModifications.addSpawn(BiomeSelectors.tag(ModTags.Biomes.FALLEN_KNIGHT_SPAWNABLE),
                SpawnGroup.MONSTER, ModEntityTypes.FALLEN_KNIGHT, 50,  1, 2
        );
        SpawnRestriction.register(ModEntityTypes.FALLEN_KNIGHT, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, FallenKnightEntity::canSpawnInDark
        );
    }
}

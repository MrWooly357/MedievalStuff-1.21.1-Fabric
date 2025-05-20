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

public class ModEntitySpawns {

    public static void addSpawns() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST),
                SpawnGroup.CREATURE, ModEntityTypes.JELLY, 100,  2, 5
        );
        SpawnRestriction.register(ModEntityTypes.JELLY, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, AnimalEntity::isValidNaturalSpawn
        );
    }
}

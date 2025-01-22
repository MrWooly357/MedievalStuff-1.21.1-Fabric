package net.mrwooly357.medievalstuff.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;

import java.util.List;
// CF -> PF
public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> LUMISHROOM_PLACED_KEY = registerKey("lumishroom_placed");

    public static final RegistryKey<PlacedFeature> GLOOMY_STONE_VEIN_PLACED_KEY = registerKey("gloomy_stone_vein_placed");
    public static final RegistryKey<PlacedFeature> SMALL_SILVER_ORE_PLACED_KEY = registerKey("small_silver_ore_placed");
    public static final RegistryKey<PlacedFeature> SILVER_ORE_PLACED_KEY = registerKey("silver_ore_placed");
    public static final RegistryKey<PlacedFeature> BIG_SILVER_ORE_PLACED_KEY = registerKey("big_silver_ore_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, LUMISHROOM_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LUMISHROOM_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                        PlacedFeatures.createCountExtraModifier(1, 0.01f, 1), ModBlocks.LUMISHROOM
                ));


        register(context, GLOOMY_STONE_VEIN_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.GLOOMY_STONE_VEIN_KEY),
                ModOrePlacement.modifiersWithCount(1,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-15), YOffset.fixed(150))));

        register(context, SMALL_SILVER_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SMALL_SILVER_ORE_KEY),
                ModOrePlacement.modifiersWithCount(14,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-60), YOffset.fixed(275))));
        register(context, SILVER_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SILVER_ORE_KEY),
                ModOrePlacement.modifiersWithCount(11,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-70), YOffset.fixed(225))));
        register(context, BIG_SILVER_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BIG_SILVER_ORE_KEY),
                ModOrePlacement.modifiersWithCount(8,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80), YOffset.fixed(175))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(MedievalStuff.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
                                                                                   RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                                                                   PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}

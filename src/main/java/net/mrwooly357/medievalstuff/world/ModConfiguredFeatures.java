package net.mrwooly357.medievalstuff.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SILVER_ORE_KEY = registerKey("small_silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MEDIUM_SILVER_ORE_KEY = registerKey("medium_silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_SILVER_ORE_KEY = registerKey("big_silver_ore");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreFeatureConfig.Target> overworldSilverOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SILVER_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_SILVER_ORE.getDefaultState()));


        register(context, SMALL_SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 3));
        register(context, MEDIUM_SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 5));
        register(context, BIG_SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 7));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MedievalStuff.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

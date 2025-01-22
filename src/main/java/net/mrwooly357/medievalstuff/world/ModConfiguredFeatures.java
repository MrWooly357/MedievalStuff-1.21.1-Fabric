package net.mrwooly357.medievalstuff.world;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.mrwooly357.medievalstuff.MedievalStuff;
import net.mrwooly357.medievalstuff.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOOMY_STONE_VEIN_KEY = registerKey("gloomy_stone_vein_key");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SILVER_ORE_KEY = registerKey("small_silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SILVER_ORE_KEY = registerKey("silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_SILVER_ORE_KEY = registerKey("big_silver_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LUMISHROOM_KEY = registerKey("lumishroom");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {

        BlockPredicate blockPredicate = BlockPredicate.matchingBlocks(
          ModBlocks.LUMISHROOM
        );

        register(context, LUMISHROOM_KEY,
                Feature.HUGE_FUNGUS,
                new HugeFungusFeatureConfig(
                Blocks.GRASS_BLOCK.getDefaultState(),
                ModBlocks.LUMISHROOM_LOG.getDefaultState(),
                ModBlocks.LUMISHROOM_CAP.getDefaultState(),
                Blocks.SHROOMLIGHT.getDefaultState(),
                blockPredicate,
                        false
                )
        );


        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);


        List<OreFeatureConfig.Target> overworldGloomyStoneVeins =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.GLOOMY_STONE.getDefaultState()));


        List<OreFeatureConfig.Target> overworldSilverOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.SILVER_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_SILVER_ORE.getDefaultState()));



        register(context, GLOOMY_STONE_VEIN_KEY, Feature.ORE, new OreFeatureConfig(overworldGloomyStoneVeins, 64));

        register(context, SMALL_SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 4));
        register(context, SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 7));
        register(context, BIG_SILVER_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldSilverOres, 10));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(MedievalStuff.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}

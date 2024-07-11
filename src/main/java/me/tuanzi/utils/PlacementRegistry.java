package me.tuanzi.utils;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class PlacementRegistry {
    //orePlace
    public static final RegistryKey<PlacedFeature> TIMESPACE_ORE_PLACEMENT = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            new Identifier(MODID, "timespace_ore_placement"));
    public static final RegistryKey<PlacedFeature> STELLAR_PROMISE_ORE_PLACEMENT = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
            new Identifier(MODID, "stellar_promise_ore_placement"));

    public PlacementRegistry() {
        printDebugLog("加载放置地物...");
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                STELLAR_PROMISE_ORE_PLACEMENT);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                TIMESPACE_ORE_PLACEMENT);
    }
}

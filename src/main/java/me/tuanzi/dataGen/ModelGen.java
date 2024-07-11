package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import static me.tuanzi.blocks.BlockRegisty.*;
import static me.tuanzi.items.ItemsRegisty.*;
import static me.tuanzi.items.ToolRegistry.*;

public class ModelGen extends FabricModelProvider {

    public ModelGen(FabricDataOutput output) {
        super(output);
    }
    //block
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(LIFT);
        blockStateModelGenerator.registerSimpleCubeAll(ANGEL_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(SORTER);
        blockStateModelGenerator.registerSimpleCubeAll(TIMESPACE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(STELLAR_PROMISE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
//        itemModelGenerator.register(LIFT_ITEM, Models.CUBE_ALL);
        itemModelGenerator.register(EMERALD_APPLE, Models.GENERATED);
        itemModelGenerator.register(SOUL_GEM, Models.GENERATED);
        itemModelGenerator.register(MOVE_VILLAGER, Models.GENERATED);
        itemModelGenerator.register(MAGNET, Models.GENERATED);
        itemModelGenerator.register(ANGEL_WINGS_CORE, Models.GENERATED);
        itemModelGenerator.register(SACRED_FEATHERS, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_1, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_2, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_3, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_4, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_5, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_6, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_7, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_8, Models.GENERATED);
        itemModelGenerator.register(COMPRESSED_COPPER_9, Models.GENERATED);
        itemModelGenerator.register(DRAW_SOMETHING, Models.GENERATED);
        itemModelGenerator.register(DRAW_BLUE_SOMETHING, Models.GENERATED);
        itemModelGenerator.register(DRAW_PURPLE_SOMETHING, Models.GENERATED);
        itemModelGenerator.register(DRAW_GOLDEN_SOMETHING, Models.GENERATED);
        itemModelGenerator.register(DREAM_KEY, Models.GENERATED);
        itemModelGenerator.register(TEN_DREAM_KEY, Models.GENERATED);
        itemModelGenerator.register(STELLAR_PROMISE, Models.GENERATED);
        itemModelGenerator.register(TEN_STELLAR_PROMISE, Models.GENERATED);
        itemModelGenerator.register(ANGEL_BLOCK_FRAMEWORK, Models.GENERATED);
        itemModelGenerator.register(REPLICATORS, Models.GENERATED);
        itemModelGenerator.register(VAST_STAR, Models.GENERATED);
        itemModelGenerator.register(TIMESPACE_SHARDS, Models.GENERATED);
        itemModelGenerator.register(TIMESPACE_GEM, Models.GENERATED);
        itemModelGenerator.register(DEATH_SCROLL, Models.GENERATED);
        itemModelGenerator.register(NETHERITE_HAMMER, Models.GENERATED);
        //tools
        itemModelGenerator.register(DRAGON_SWORD, Models.HANDHELD);
        itemModelGenerator.register(STARDUST_WAND, Models.HANDHELD);
        itemModelGenerator.register(GAMBLING_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ANTIMATTER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(TIMESPACE_AXE, Models.HANDHELD);
        itemModelGenerator.register(TIMESPACE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(TIMESPACE_HOE, Models.HANDHELD);
        itemModelGenerator.register(TIMESPACE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(TIMESPACE_SHOVEL, Models.HANDHELD);
        //test
        itemModelGenerator.register(TEST_ITEM, Models.HANDHELD);
    }


}

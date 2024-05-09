package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import static me.tuanzi.SakuraServer.*;

public class ModelGen extends FabricModelProvider {

    public ModelGen(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(LIFT);
        blockStateModelGenerator.registerSimpleCubeAll(ANGEL_BLOCK);
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
        itemModelGenerator.register(ANGEL_BLOCK_FRAMEWORK, Models.GENERATED);
        itemModelGenerator.register(DRAGON_SWORD, Models.HANDHELD);
        itemModelGenerator.register(STARDUST_WAND, Models.HANDHELD);
        itemModelGenerator.register(TEST_ITEM, Models.HANDHELD);
    }


}

package me.tuanzi.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import static me.tuanzi.utils.registry.BlockRegistry.COCOA_BEANS_BLOCK;
import static me.tuanzi.utils.registry.BlockRegistry.COMPRESSED_HAY_BLOCK_1;
import static me.tuanzi.utils.registry.ItemRegistry.*;

public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(COMPRESSED_HAY_BLOCK_1);
        blockStateModelGenerator.registerSimpleCubeAll(COCOA_BEANS_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(DEBUG_ITEM, Models.GENERATED);
        itemModelGenerator.register(RESURRECTION_AMULET, Models.GENERATED);
        itemModelGenerator.register(BRONZE_INGOT, Models.GENERATED);

        itemModelGenerator.register(POISONOUS_POTATO_BUNDLE, Models.GENERATED);
        itemModelGenerator.register(POTATO_BUNDLE, Models.GENERATED);

        itemModelGenerator.register(BEETROOT_BUNDLE, Models.GENERATED);
        itemModelGenerator.register(CARROT_BUNDLE, Models.GENERATED);
        itemModelGenerator.register(SWEET_BERRIES_BUNDLE, Models.GENERATED);
        itemModelGenerator.register(COCOA_BEANS_BUNDLE, Models.GENERATED);

    }
}

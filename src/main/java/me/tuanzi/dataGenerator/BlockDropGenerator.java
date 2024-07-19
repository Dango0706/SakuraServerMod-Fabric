package me.tuanzi.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static me.tuanzi.utils.registry.BlockRegistry.COCOA_BEANS_BLOCK;
import static me.tuanzi.utils.registry.BlockRegistry.COMPRESSED_HAY_BLOCK_1;

public class BlockDropGenerator extends FabricBlockLootTableProvider {


    public BlockDropGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     * Implement this method to add block drops.
     *
     * <p>Use the range of {@link BlockLootTableGenerator#addDrop} methods to generate block drops.
     */
    @Override
    public void generate() {
        addDrop(COMPRESSED_HAY_BLOCK_1,drops(COMPRESSED_HAY_BLOCK_1));
        addDrop(COCOA_BEANS_BLOCK,drops(COCOA_BEANS_BLOCK));
    }
}

package me.tuanzi.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

import static me.tuanzi.utils.Utils.id;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Implement this method and then use {@link FabricTagProvider#getOrCreateTagBuilder} to get and register new tag builders.
     *
     * @param wrapperLookup
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getTagBuilder(BlockTags.HOE_MINEABLE).add(
                id("compressed_hay_block_1")
        );
        getTagBuilder(BlockTags.SWORD_EFFICIENT).add(
                id("cocoa_beans_block")
        );
        getTagBuilder(BlockTags.AXE_MINEABLE).add(
                id("cocoa_beans_block")
        );
    }
}

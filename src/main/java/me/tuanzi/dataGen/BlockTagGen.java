package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static me.tuanzi.utils.Constants.MODID;
import static net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager.getBlockTag;

public class BlockTagGen extends FabricTagProvider.BlockTagProvider {

    public BlockTagGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Implement this method and then use {@link FabricTagProvider#getOrCreateTagBuilder} to get and register new tag builders.
     *
     * @param arg
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                new Identifier(MODID, "lift")
        );
        getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                new Identifier(MODID, "angel_block")
        );
        getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                new Identifier(MODID, "sorter")
        );
        getTagBuilder(getBlockTag(4)).add(
                new Identifier(MODID, "timespace_ore")
        );
        getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                new Identifier(MODID, "timespace_ore")
        );
        getTagBuilder(getBlockTag(5)).add(
                new Identifier(MODID, "stellar_promise_ore")
        );
        getTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                new Identifier(MODID, "stellar_promise_ore")
        );

    }


}

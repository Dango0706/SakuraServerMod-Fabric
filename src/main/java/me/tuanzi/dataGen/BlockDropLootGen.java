package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import static me.tuanzi.SakuraServer.*;

public class BlockDropLootGen extends FabricBlockLootTableProvider {

    public BlockDropLootGen(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    /**
     * Implement this method to add block drops.
     *
     * <p>Use the range of {@link BlockLootTableGenerator#addDrop} methods to generate block drops.
     */
    @Override
    public void generate() {
        //电梯
        addDrop(LIFT,drops(LIFT));
        addDrop(ANGEL_BLOCK,drops(ANGEL_BLOCK));
        addDrop(SORTER,drops(SORTER));
        addDrop(TIMESPACE_ORE,drops(TIMESPACE_SHARDS));
        addDrop(STELLAR_PROMISE_ORE,drops(STELLAR_PROMISE));
    }
}

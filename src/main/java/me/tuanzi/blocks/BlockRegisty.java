package me.tuanzi.blocks;

import me.tuanzi.blocks.angel_block.AngelBlock;
import me.tuanzi.blocks.angel_block.AngelBlockEntity;
import me.tuanzi.blocks.lift.Lift;
import me.tuanzi.blocks.sorter.Sorter;
import me.tuanzi.blocks.sorter.SorterBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class BlockRegisty {
    //block
    public static final Block LIFT = new Lift(FabricBlockSettings.create().strength(4.0f).requiresTool());
    public static final Block ANGEL_BLOCK = new AngelBlock(FabricBlockSettings.create().strength(75, 3500).requiresTool());
    public static final Block SORTER = new Sorter(FabricBlockSettings.create().strength(15, 1200).nonOpaque().requiresTool());
    public static final Block TIMESPACE_ORE = new Block(FabricBlockSettings.create().strength(50, 1200).nonOpaque().requiresTool());
    public static final Block STELLAR_PROMISE_ORE = new Block(FabricBlockSettings.create().strength(50, 1200).nonOpaque().requiresTool());
    public static BlockEntityType<AngelBlockEntity> ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;
    public static BlockEntityType<SorterBlockEntity> SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;

    public BlockRegisty() {
        printDebugLog("加载方块...");
        Registry.register(Registries.BLOCK, new Identifier(MODID, "lift"), LIFT);
        Registry.register(Registries.BLOCK, new Identifier(MODID, "angel_block"), ANGEL_BLOCK);
        ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MODID, "angel_block_entity"),
                FabricBlockEntityTypeBuilder.create(AngelBlockEntity::new, ANGEL_BLOCK).build()
        );
        Registry.register(Registries.BLOCK, new Identifier(MODID, "sorter"), SORTER);
        SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier(MODID, "sorter_block_entity"),
                FabricBlockEntityTypeBuilder.create(SorterBlockEntity::new, SORTER).build()
        );
        Registry.register(Registries.BLOCK, new Identifier(MODID, "timespace_ore"), TIMESPACE_ORE);
        Registry.register(Registries.BLOCK, new Identifier(MODID, "stellar_promise_ore"), STELLAR_PROMISE_ORE);

    }
}

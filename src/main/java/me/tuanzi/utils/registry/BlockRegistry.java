package me.tuanzi.utils.registry;

import me.tuanzi.blocks.utils.SakuraBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

import static me.tuanzi.utils.Utils.id;

public class BlockRegistry {

    public static final Block COMPRESSED_HAY_BLOCK_1 = new Block(AbstractBlock.Settings.create().strength(0.5F).sounds(BlockSoundGroup.GRASS));
    public static final Block COCOA_BEANS_BLOCK = new Block(AbstractBlock.Settings.create().strength(0.2F, 3.0F).sounds(BlockSoundGroup.WOOD));

    public BlockRegistry() {
        registerBlocks(id("compressed_hay_block_1"),COMPRESSED_HAY_BLOCK_1);
        registerBlocks(id("cocoa_beans_block"),COCOA_BEANS_BLOCK);
    }

    public static void registerBlocks(Identifier id, Block block) {
        registerBlocks(id,block,null,null);
    }
    public static void registerBlocks(Identifier id, Block block, List<Text> tooltip) {
        registerBlocks(id,block,tooltip,null);
    }
    public static void registerBlocks(Identifier id, Block block, List<Text> tooltip,List<Text> shiftTooltip) {
        Registry.register(Registries.ITEM,id,new SakuraBlockItem(block,new Item.Settings()).setTooltip(tooltip)).setNeedShiftTooltip(shiftTooltip);
        Registry.register(Registries.BLOCK,id,block);
    }

}

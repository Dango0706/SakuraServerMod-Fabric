package me.tuanzi.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import static me.tuanzi.items.ItemUtils.getColor;

public class SakuraBlockItem extends BlockItem {
    private int Rarity = 1;

    public SakuraBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public SakuraBlockItem(Block block, Settings settings, int rarity) {
        super(block, settings);
        Rarity = rarity;
    }

    public int getRarity() {
        return Rarity;
    }

    public void setRarity(int rarity) {
        Rarity = rarity;
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return Text.empty().append(super.getName()).withColor(getColor(getRarity()));
    }


}

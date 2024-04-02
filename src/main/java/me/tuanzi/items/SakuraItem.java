package me.tuanzi.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SakuraItem extends Item {
    private int Rarity = 0;
    private ArrayList<Text> Desc = new ArrayList<>();

    public SakuraItem(Settings settings) {
        super(settings);
    }

    public int getRarity() {
        return Rarity;
    }

    public void setRarity(int rarity) {
        Rarity = rarity;
    }

    public ArrayList<Text> getDesc() {
        return Desc;
    }

    public void setDesc(ArrayList<Text> desc) {
        Desc = desc;
    }




    public int getColor() {
        if (this.Rarity == 0) {
            return 0xAAAAAA;
        } else if (this.Rarity == 1) {
            return 0x55FF55;
        } else if (this.Rarity == 2) {
            return 0x55FFFF;
        } else if (this.Rarity == 3) {
            return 0x5555FF;
        } else if (this.Rarity == 4) {
            return 0xFFFF55;
        } else if (this.Rarity == 5) {
            return 0xFFAA00;
        } else {
            return 0xAAAAAA;
        }
    }

    /**
     * Called by the client to append tooltips to an item. Subclasses can override
     * this and add custom tooltips to {@code tooltip} list.
     *
     * @param stack
     * @param world
     * @param tooltip the list of tooltips to show
     * @param context
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Desc.size() != 0)
            tooltip.addAll(Desc);
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return Text.empty().append(super.getName()).withColor(getColor());
    }
}

package me.tuanzi.items.utils;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static me.tuanzi.items.utils.ItemUtils.getColor;

public class SakuraItem extends Item {
    private int Rarity = 0;
    private ArrayList<Text> Desc = new ArrayList<>();

    public SakuraItem(Settings settings) {
        super(settings);
    }

    public int getRarity() {
        return Rarity;
    }

    public SakuraItem setRarity(int rarity) {
        Rarity = rarity;
        return this;
    }

    public ArrayList<Text> getDesc() {
        return Desc;
    }

    public SakuraItem setDesc(ArrayList<Text> desc) {
        Desc = desc;
        return this;
    }

    public SakuraItem setDesc(Text... desc) {
        Desc.addAll(Arrays.stream(desc).toList());
        return this;
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
        //clinet only
        //Screen.hasShiftDown()
        if (getDesc().size() != 0)
            tooltip.addAll(getDesc());
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return Text.empty().append(super.getName()).withColor(getColor(getRarity()));
    }


    public void inInventoryTick(PlayerEntity player, World world, ItemStack itemStack) {

    }

    public void itemEntityTick(ItemEntity itemEntity, World world, ItemStack itemStack){

    }


}

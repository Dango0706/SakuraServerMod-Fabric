package me.tuanzi.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SakuraSword extends SwordItem {

    private int Rarity = 0;
    private ArrayList<Text> Desc = new ArrayList<>();


    public SakuraSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed - 4.0f, settings);
    }

    public SakuraSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int rarity, Text... desc) {
        super(toolMaterial, attackDamage, attackSpeed - 4.0f, settings);
        Rarity = rarity;
        Desc.addAll(Arrays.stream(desc).toList());
    }

    public SakuraSword(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, int rarity) {
        super(toolMaterial, attackDamage, attackSpeed - 4.0f, settings);
        Rarity = rarity;
    }

    public int getRarity() {
        return Rarity;
    }

    public SakuraSword setRarity(int rarity) {
        Rarity = rarity;
        return this;
    }

    public ArrayList<Text> getDesc() {
        return Desc;
    }

    public SakuraSword setDesc(ArrayList<Text> desc) {
        Desc = desc;
        return this;
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (getDesc().size() != 0)
            tooltip.addAll(getDesc());
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public Text getName(ItemStack itemStack) {
        return Text.empty().append(super.getName()).withColor(getColor());
    }


}

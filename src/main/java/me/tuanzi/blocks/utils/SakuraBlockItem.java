package me.tuanzi.blocks.utils;

import me.tuanzi.utils.Constant;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;

import static me.tuanzi.utils.KeyConstant.PRESS_SHIFT_GET_MORE_INFO;

public class SakuraBlockItem extends BlockItem {
    public SakuraBlockItem(Block block, Settings settings) {
        super(block, settings);
    }
    //提示
    List<Text> tooltip;
    //点击shift后显示的提示.
    List<Text> needShiftTooltip;


    public List<Text> getTooltip() {
        return tooltip;
    }

    public SakuraBlockItem setTooltip(List<Text> tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public SakuraBlockItem setTooltip(Text... tooltip) {
        return setTooltip(Arrays.stream(tooltip).toList());
    }

    public List<Text> getNeedShiftTooltip() {
        return needShiftTooltip;
    }

    public SakuraBlockItem setNeedShiftTooltip(List<Text> needShiftTooltip) {
        this.needShiftTooltip = needShiftTooltip;
        return this;
    }

    public SakuraBlockItem setNeedShiftTooltip(Text... needShiftTooltip) {
        return setNeedShiftTooltip(Arrays.stream(needShiftTooltip).toList());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //shift增加更多
        if(!Constant.DEBUG){
            //判断是否是空
            if(getNeedShiftTooltip() != null){
                //看看里面有无内容(无需点shift的内容)
                if (!getTooltip().isEmpty())
                    //添加.
                    tooltip.addAll(getTooltip());
                //点shift是否为空
                if(getNeedShiftTooltip() != null){
                    //看看里面有无内容
                    if (!getNeedShiftTooltip().isEmpty()) {
                        //没点shift,则显示按shift显示更多
                        if (!Screen.hasShiftDown()) {
                            tooltip.add(Text.keybind(PRESS_SHIFT_GET_MORE_INFO).withColor(0x778899));
                        } else {
                            //否则添加shift内容
                            tooltip.addAll(getNeedShiftTooltip());
                        }
                    }
                }
            }
            super.appendTooltip(stack, context, tooltip, type);
        }

    }

}

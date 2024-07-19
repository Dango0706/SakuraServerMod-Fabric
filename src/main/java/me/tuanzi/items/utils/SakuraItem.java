package me.tuanzi.items.utils;

import me.tuanzi.utils.Constant;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;

import static me.tuanzi.utils.KeyConstant.PRESS_SHIFT_GET_MORE_INFO;

public class SakuraItem extends Item {

    //提示
    List<Text> tooltip;
    //点击shift后显示的提示.
    List<Text> needShiftTooltip;

    public SakuraItem(Settings settings) {
        super(settings);
    }

    public List<Text> getTooltip() {
        return tooltip;
    }

    public SakuraItem setTooltip(List<Text> tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public SakuraItem setTooltip(Text... tooltip) {
        return setTooltip(Arrays.stream(tooltip).toList());
    }

    public List<Text> getNeedShiftTooltip() {
        return needShiftTooltip;
    }

    public SakuraItem setNeedShiftTooltip(List<Text> needShiftTooltip) {
        this.needShiftTooltip = needShiftTooltip;
        return this;
    }

    public SakuraItem setNeedShiftTooltip(Text... needShiftTooltip) {
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

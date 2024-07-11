package me.tuanzi.items.tools;

import me.tuanzi.items.utils.CanNotWithMending;
import me.tuanzi.items.utils.CanNotWithVeinMine;
import me.tuanzi.items.utils.SakuraPickaxe;
import net.minecraft.text.Text;

import static me.tuanzi.items.ToolRegistry.EPIC_TOOLS;

public class AntimatterPickaxe extends SakuraPickaxe implements CanNotWithMending, CanNotWithVeinMine {

    public AntimatterPickaxe() {
        super(EPIC_TOOLS, 5, 1.2F, new Settings().maxDamage(35),EPIC_TOOLS.getRarity(),
                Text.literal("挖掘一个方块!开始你的豪赌!"),
                Text.literal("掉落物非原本掉落物,而是所有物品随机掉落"),
                Text.literal("你会掉啥我也不知道~")
                );
    }


}

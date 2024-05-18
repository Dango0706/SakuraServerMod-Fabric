package me.tuanzi.items.tools;

import me.tuanzi.items.utils.SakuraPickaxe;
import me.tuanzi.items.utils.CanNotWithMending;
import me.tuanzi.items.utils.CanNotWithVeinMine;
import net.minecraft.item.Item;
import net.minecraft.text.Text;

import static me.tuanzi.SakuraServer.EPIC_TOOLS;

public class GamblingPickaxe extends SakuraPickaxe implements CanNotWithMending, CanNotWithVeinMine {

    public GamblingPickaxe() {
        super(EPIC_TOOLS, 5, 1.2F, new Item.Settings().maxDamage(35),EPIC_TOOLS.getRarity(),
                Text.literal("挖掘一个方块!开始你的豪赌!"),
                Text.literal("所有!或者一无所有!"),
                Text.literal("挖掘时有50%的概率获得双倍或者直接损坏他"),
                Text.literal("偷偷告诉你,还有极小概率获得最高1024倍掉落物!")
                );
    }


}

package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import static me.tuanzi.SakuraServer.*;

public class ChineseLangGen extends FabricLanguageProvider {
    public ChineseLangGen(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    /**
     * Implement this method to register languages.
     *
     * <p>Call {@link TranslationBuilder#add(String, String)} to add a translation.
     *
     * @param translationBuilder
     */
    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        //block
        translationBuilder.add(LIFT, "电梯");
        //item
        translationBuilder.add(EMERALD_APPLE,"绿宝石苹果");
        //enchantment
        translationBuilder.add(SOUL_BOUND, "§6灵魂绑定");
        translationBuilder.add(VEIN_MINE, "§a连锁挖矿");
        translationBuilder.add("enchantment.sakura_server.vein_mine.need_food", "你打算饿死你自己?吃点东西在连锁挖矿吧!");
        //effect
        translationBuilder.add(LIFT_CD, "电梯使用冷却");
        //itemGroup
        translationBuilder.add("itemGroup.sakura_server.item_group", "樱花服务器");
        //command
        translationBuilder.add("commands.sakura_server.error.not_player", "此命令非玩家发出.");
        translationBuilder.add("commands.sakura_server.removeLore.ok", "成功清除身上%d件物品的lore信息.");
        //keyBind
        translationBuilder.add("key.category.sakura_server", "樱花服务器");
        translationBuilder.add("key.sakura_server.vein_mine", "连锁挖矿");
        //statistics
        translationBuilder.add("stat.sakura_server.highest_damage", "造成最高的一次伤害");
        translationBuilder.add("stat.sakura_server.highest_hurt", "受到最高的一次伤害");
        translationBuilder.add("stat.sakura_server.lift_all", "使用电梯总次数");
        translationBuilder.add("stat.sakura_server.lift_up", "使用电梯上升的次数");
        translationBuilder.add("stat.sakura_server.lift_down", "使用电梯下降的次数");
        translationBuilder.add("stat.sakura_server.vein_mine_count", "连锁挖掘方块总数量");
    }
}

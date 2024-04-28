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
        translationBuilder.add(EMERALD_APPLE, "绿宝石苹果");
        translationBuilder.add(SOUL_GEM, "灵魂宝石");
        translationBuilder.add(MOVE_VILLAGER, "村民移动者");
        translationBuilder.add(DRAGON_SWORD, "龙剑");
        translationBuilder.add(STARDUST_WAND, "星光魔杖");
        translationBuilder.add("item.sakura_server.soul_gem.fail.not_owner", "您不是他的主人,无法绑定!");
        translationBuilder.add("item.sakura_server.soul_gem.fail.not_tamed", "他还没被驯服呢!无法绑定");
        translationBuilder.add("item.sakura_server.soul_gem.fail.has_entity", "这个灵魂石已经有绑定了!");
        translationBuilder.add("item.sakura_server.soul_gem.fail.not_found", "在周围40格内,并没有发现您绑定的生物呢~");
        translationBuilder.add("item.sakura_server.soul_gem.respawn", "复活吧!我的爱人!!");
        translationBuilder.add("item.sakura_server.soul_gem.unbound", "解绑成功!");
        translationBuilder.add("item.sakura_server.soul_gem.found", "你的狗狗在x:%d,y:%d,z:%d,他正在闪闪发光~");
        translationBuilder.add("item.sakura_server.soul_gem.uuid", "已绑定生物,UUID:");
        translationBuilder.add("item.sakura_server.soul_gem.dead", "你绑定的生物死亡了!!他的灵魂石闪闪发光...");
        translationBuilder.add("item.sakura_server.villager_mover.fail.has_villager", "这个移动器已经有一个村民了!");
        translationBuilder.add("item.sakura_server.villager_mover.saved","成功保存村民!");
        translationBuilder.add("item.sakura_server.villager_mover.profession", "村民职业:");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc1", "技能:龙吼");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc2", "右键使用,CD:15s,持续时间:10s");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc3", "消耗当前生命值的30%,获得龙吼.");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc4", "龙吼:+4攻击力,+80%攻击速度,+30%移动速度");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc5", "缓慢恢复20%生命值上限的生命值.");
        translationBuilder.add("item.sakura_server.dragon_sword.skill.desc6", "切换武器后失效.");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc1", "技能:星辉之力");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc2", "右键使用,CD:30s,持续时间:10s");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc3", "给予半径5格的生物10s断愈效果,且造成8点伤害");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc4", "给自己的宠物恢复8点生命值!");
        translationBuilder.add("item.sakura_server.stardust_wand.skill.desc5", "蹲下则对其他玩家以及其他玩家的宠物也生效!");
        //enchantment
        translationBuilder.add(SOUL_BOUND, "§6灵魂绑定");
        translationBuilder.add(VEIN_MINE, "§a连锁挖矿");
        translationBuilder.add("enchantment.sakura_server.vein_mine.need_food", "你打算饿死你自己?吃点东西在连锁挖矿吧!");
        //effect
        translationBuilder.add(LIFT_CD, "电梯使用冷却");
        translationBuilder.add(DRAGON_SWORD_EFFECT, "龙怒");
        translationBuilder.add(HEALBANE, "断愈");
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

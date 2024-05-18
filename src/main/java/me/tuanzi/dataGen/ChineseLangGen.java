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
        translationBuilder.add(ANGEL_BLOCK, "天使方块");
        translationBuilder.add(SORTER, "物品分类者");
        translationBuilder.add(TIMESPACE_ORE, "时空矿石");
        translationBuilder.add(STELLAR_PROMISE_ORE, "星辰之石");
        translationBuilder.add("block.sakura_server.angel_block.on", "现在启动范围内飞行!");
        translationBuilder.add("block.sakura_server.angel_block.off", "现在关闭范围内飞行!");
        translationBuilder.add("block.sakura_server.angel_block.range", "飞行范围设置为:%d");
        translationBuilder.add("block.sakura_server.sorter.range", "自动整理箱子范围设置为:%d");
        //item
        translationBuilder.add(EMERALD_APPLE, "绿宝石苹果");
        translationBuilder.add(SOUL_GEM, "灵魂宝石");
        translationBuilder.add(MOVE_VILLAGER, "村民移动者");
        translationBuilder.add(MAGNET, "磁铁");
        translationBuilder.add(ANGEL_WINGS_CORE, "天使之翼核心");
        translationBuilder.add(SACRED_FEATHERS, "神圣之羽");
        translationBuilder.add(COMPRESSED_COPPER_1, "一级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_2, "二级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_3, "三级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_4, "四级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_5, "五级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_6, "六级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_7, "七级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_8, "八级压缩铜锭");
        translationBuilder.add(COMPRESSED_COPPER_9, "九级压缩铜锭");
        translationBuilder.add(ANGEL_BLOCK_FRAMEWORK, "天使方块框架");
        translationBuilder.add(DREAM_KEY, "梦境之钥");
        translationBuilder.add(TEN_DREAM_KEY, "10连梦境之钥");
        translationBuilder.add(STELLAR_PROMISE, "星辰之诺");
        translationBuilder.add(TEN_STELLAR_PROMISE, "10连星辰之诺");
        translationBuilder.add(DRAGON_SWORD, "龙剑");
        translationBuilder.add(STARDUST_WAND, "星光魔杖");
        translationBuilder.add(GAMBLING_PICKAXE, "赌狗镐");
        translationBuilder.add(ANTIMATTER_PICKAXE, "反物质镐");
        translationBuilder.add(REPLICATORS, "复制者");
        translationBuilder.add(VAST_STAR, "浩瀚之星");
        translationBuilder.add(TIMESPACE_SHARDS, "时空碎片");
        translationBuilder.add(TIMESPACE_GEM, "时空宝石");
        translationBuilder.add(TIMESPACE_AXE, "时空斧");
        translationBuilder.add(TIMESPACE_PICKAXE, "时空镐");
        translationBuilder.add(TIMESPACE_SWORD, "时空剑");
        translationBuilder.add(TIMESPACE_SHOVEL, "时空锹");
        translationBuilder.add(TIMESPACE_HOE, "时空锄");
        translationBuilder.add(DEATH_SCROLL, "死亡卷轴");
        translationBuilder.add(NETHERITE_HAMMER, "下界合金锤");
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
        translationBuilder.add("item.sakura_server.magnet.desc", "右键控制开关,潜行右键开关粒子显示");
        translationBuilder.add("item.sakura_server.replicators.desc", "失去了某些力量,只能复制出最原始的物品.");
        translationBuilder.add("item.sakura_server.death_scroll.not_death", "你没有死亡过哦~");
        translationBuilder.add("item.sakura_server.death_scroll.not_dimension", "维度不同,无法传送!");
        translationBuilder.add("item.sakura_server.dream_key.desc", "哇哦~你获得了梦想的钥匙,右键进行单抽!");
        translationBuilder.add("item.sakura_server.ten_dream_key.desc", "哇哦~你获得了梦想的钥匙,右键进行十连抽!");
        translationBuilder.add("item.sakura_server.stellar_promise.desc", "哇哦~你获得了星辰的馈赠,右键进行单抽!");
        translationBuilder.add("item.sakura_server.ten_stellar_promise.desc", "哇哦~你获得了星辰的馈赠,右键进行十连抽!");
        translationBuilder.add("item.sakura_server.see_pool", "潜行右键以查看卡池信息.");
        //customName
        translationBuilder.add("item.sakura_server.infinite_firework", "无限火箭");
        //enchantment
        translationBuilder.add(SOUL_BOUND, "§6灵魂绑定");
        translationBuilder.add(VEIN_MINE, "§a连锁挖矿");
        translationBuilder.add("enchantment.sakura_server.vein_mine.need_food", "你打算饿死你自己?吃点东西在连锁挖矿吧!");
        translationBuilder.add(ADVANCED_SHARPNESS, "强化锋利");
        translationBuilder.add(ADVANCED_SMITE, "强化亡灵杀手");
        translationBuilder.add(ADVANCED_BANE_OF_ARTHROPODS, "强化节肢杀手");
        translationBuilder.add(ADVANCED_POWER, "强化力量");
        translationBuilder.add(EDUCATION, "教育");
        //effect
        translationBuilder.add(LIFT_CD, "电梯使用冷却");
        translationBuilder.add(DRAGON_SWORD_EFFECT, "龙怒");
        translationBuilder.add(HEALBANE, "断愈");
        translationBuilder.add(ANGEl_WINGS, "天使之翼");
        translationBuilder.add("effect.sakura_server.angle_wings_warning", "§c警告:§r您的飞行时间所剩无几!!还有:§4%d秒!!!");
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
        translationBuilder.add("stat.sakura_server.draw_count", "抽卡总数");
        translationBuilder.add("stat.sakura_server.break_block_count", "破坏方块总数");
        translationBuilder.add("stat.sakura_server.total_damage_taken", "受到的总伤害");
        translationBuilder.add("stat.sakura_server.total_online_time", "总在线时长(分钟)");
        translationBuilder.add("stat.sakura_server.total_damage_caused", "造成的总伤害");
        translationBuilder.add("stat.sakura_server.total_player_damage_taken", "受到来自玩家的总伤害");
        translationBuilder.add("stat.sakura_server.total_player_damage_caused", "对玩家造成的总伤害");
        translationBuilder.add("stat.sakura_server.place_block_count", "放置方块的数量");
        translationBuilder.add("stat.sakura_server.draw_blue_count", "抽到3星物品的数量");
        translationBuilder.add("stat.sakura_server.draw_purple_count", "抽到4星物品的数量");
        translationBuilder.add("stat.sakura_server.draw_golden_count", "抽到5星物品的数量");

        //pool
        translationBuilder.add("pool.up_golden", "§6=====此卡池金色Up!=====");
        translationBuilder.add("pool.golden", "§e=====此卡池金色=====");
        translationBuilder.add("pool.up_purple", "=====此卡池紫色Up!=====");
        translationBuilder.add("pool.purple", "§d=====此卡池紫色Up!=====");
        translationBuilder.add("pool.blue", "§b=====此卡池其余物品=====");
    }
}

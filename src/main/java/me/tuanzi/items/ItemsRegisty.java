package me.tuanzi.items;

import me.tuanzi.items.display.DrawBlueSomething;
import me.tuanzi.items.display.DrawGoldenSomething;
import me.tuanzi.items.display.DrawPurpleSomething;
import me.tuanzi.items.display.DrawSomething;
import me.tuanzi.items.foods.EmeraldApple;
import me.tuanzi.items.functional.*;
import me.tuanzi.items.msic.NetheriteHammer;
import me.tuanzi.items.utils.SakuraBlockItem;
import me.tuanzi.items.utils.SakuraItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static me.tuanzi.blocks.BlockRegisty.*;
import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class ItemsRegisty {
    //item
    public static final Item EMERALD_APPLE = new EmeraldApple();
    public static final Item TEST_ITEM = new TestItem(new FabricItemSettings());
    public static final Item SOUL_GEM = new SoulGem(new FabricItemSettings().maxCount(64)).setRarity(5);
    public static final Item MOVE_VILLAGER = new MoveVillager(new FabricItemSettings().maxCount(1)).setRarity(3);
    public static final Item MAGNET = new Magnet(new FabricItemSettings().maxCount(1).maxDamage(350)).setRarity(2).setDesc(Text.translatable("item.sakura_server.magnet.desc"));
    public static final Item ANGEL_WINGS_CORE = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(4);
    public static final Item SACRED_FEATHERS = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item COMPRESSED_COPPER_1 = new SakuraItem(new FabricItemSettings()).setRarity(2);
    public static final Item COMPRESSED_COPPER_2 = new SakuraItem(new FabricItemSettings()).setRarity(2);
    public static final Item COMPRESSED_COPPER_3 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_4 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_5 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_6 = new SakuraItem(new FabricItemSettings()).setRarity(3);
    public static final Item COMPRESSED_COPPER_7 = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item COMPRESSED_COPPER_8 = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item COMPRESSED_COPPER_9 = new SakuraItem(new FabricItemSettings()).setRarity(4);
    public static final Item ANGEL_BLOCK_FRAMEWORK = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(4);
    public static final Item DRAW_SOMETHING = new DrawSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DRAW_BLUE_SOMETHING = new DrawBlueSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DRAW_PURPLE_SOMETHING = new DrawPurpleSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DRAW_GOLDEN_SOMETHING = new DrawGoldenSomething(new FabricItemSettings().maxCount(1).fireproof());
    public static final Item DREAM_KEY = new DreamKey(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.dream_key.desc"), Text.translatable("item.sakura_server.see_pool"));
    public static final Item TEN_DREAM_KEY = new TenDreamKey(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.ten_dream_key.desc"), Text.translatable("item.sakura_server.see_pool"));
    public static final Item STELLAR_PROMISE = new StellarPromise(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.stellar_promise.desc"), Text.translatable("item.sakura_server.see_pool"));
    public static final Item TEN_STELLAR_PROMISE = new TenStellarPromise(new FabricItemSettings().fireproof()).setRarity(5)
            .setDesc(Text.translatable("item.sakura_server.ten_stellar_promise.desc"), Text.translatable("item.sakura_server.see_pool"));
    public static final Item REPLICATORS = new SakuraItem(new FabricItemSettings().fireproof())
            .setRarity(5).setDesc(Text.translatable("item.sakura_server.replicators.desc"), Text.translatable("item.sakura_server.see_pool"));
    public static final Item VAST_STAR = new VastStar(new FabricItemSettings().fireproof()).setRarity(5);
    public static final Item TIMESPACE_SHARDS = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(3);
    public static final Item TIMESPACE_GEM = new SakuraItem(new FabricItemSettings().fireproof()).setRarity(3);
    public static final Item DEATH_SCROLL = new DeathScroll(new FabricItemSettings()).setRarity(3);
    public static final Item NETHERITE_HAMMER = new NetheriteHammer(new FabricItemSettings().fireproof().maxCount(1).maxDamage(500)).setRarity(3);

    public ItemsRegisty() {
        printDebugLog("加载物品...");
        Registry.register(Registries.ITEM, new Identifier(MODID, "lift"), new SakuraBlockItem(LIFT, new FabricItemSettings(), 2));
        Registry.register(Registries.ITEM, new Identifier(MODID, "angel_block"), new SakuraBlockItem(ANGEL_BLOCK, new FabricItemSettings().fireproof(), 5));
        Registry.register(Registries.ITEM, new Identifier(MODID, "sorter"),
                new SakuraBlockItem(SORTER, new FabricItemSettings(), 2));
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_ore"), new SakuraBlockItem(TIMESPACE_ORE, new Item.Settings().fireproof(), 3));
        Registry.register(Registries.ITEM, new Identifier(MODID, "stellar_promise_ore"), new SakuraBlockItem(STELLAR_PROMISE_ORE, new Item.Settings().fireproof(), 5));
        Registry.register(Registries.ITEM, new Identifier(MODID, "emerald_apple"), EMERALD_APPLE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "soul_gem"), SOUL_GEM);
        Registry.register(Registries.ITEM, new Identifier(MODID, "villager_mover"), MOVE_VILLAGER);
        Registry.register(Registries.ITEM, new Identifier(MODID, "magnet"), MAGNET);
        Registry.register(Registries.ITEM, new Identifier(MODID, "angel_wings_core"), ANGEL_WINGS_CORE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "sacred_feathers"), SACRED_FEATHERS);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_1"), COMPRESSED_COPPER_1);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_2"), COMPRESSED_COPPER_2);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_3"), COMPRESSED_COPPER_3);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_4"), COMPRESSED_COPPER_4);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_5"), COMPRESSED_COPPER_5);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_6"), COMPRESSED_COPPER_6);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_7"), COMPRESSED_COPPER_7);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_8"), COMPRESSED_COPPER_8);
        Registry.register(Registries.ITEM, new Identifier(MODID, "compressed_copper_9"), COMPRESSED_COPPER_9);
        Registry.register(Registries.ITEM, new Identifier(MODID, "angel_block_framework"), ANGEL_BLOCK_FRAMEWORK);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_something"), DRAW_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_blue_something"), DRAW_BLUE_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_purple_something"), DRAW_PURPLE_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "draw_golden_something"), DRAW_GOLDEN_SOMETHING);
        Registry.register(Registries.ITEM, new Identifier(MODID, "dream_key"), DREAM_KEY);
        Registry.register(Registries.ITEM, new Identifier(MODID, "ten_dream_key"), TEN_DREAM_KEY);
        Registry.register(Registries.ITEM, new Identifier(MODID, "stellar_promise"), STELLAR_PROMISE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "ten_stellar_promise"), TEN_STELLAR_PROMISE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "replicators"), REPLICATORS);
        Registry.register(Registries.ITEM, new Identifier(MODID, "vast_star"), VAST_STAR);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_shards"), TIMESPACE_SHARDS);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_gem"), TIMESPACE_GEM);
        Registry.register(Registries.ITEM, new Identifier(MODID, "death_scroll"), DEATH_SCROLL);
        Registry.register(Registries.ITEM, new Identifier(MODID, "netherite_hammer"), NETHERITE_HAMMER);


    }
}

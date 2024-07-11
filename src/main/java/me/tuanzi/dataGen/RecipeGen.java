package me.tuanzi.dataGen;

import com.google.common.collect.Lists;
import me.tuanzi.items.utils.CanNotBeCopy;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import static me.tuanzi.blocks.BlockRegisty.*;
import static me.tuanzi.items.ItemsRegisty.*;
import static me.tuanzi.items.ToolRegistry.*;
import static me.tuanzi.utils.Constants.MODID;

public class RecipeGen extends FabricRecipeProvider {

    public RecipeGen(FabricDataOutput output) {
        super(output);
    }


    /**
     * Implement this method and then use the range of methods in {@link RecipeProvider} or from one of the recipe json factories such as {@link ShapedRecipeJsonBuilder} or {@link ShapelessRecipeJsonBuilder}.
     *
     * @param exporter
     */
    @Override
    public void generate(RecipeExporter exporter) {
        //电梯
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, LIFT)
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .input('a', Items.IRON_BLOCK)
                .input('b', Items.ENDER_PEARL)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.ENDER_PEARL),
                        FabricRecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
                .offerTo(exporter);
        //绿宝石苹果
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, EMERALD_APPLE)
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .input('a', Items.EMERALD)
                .input('b', Items.APPLE)
                .criterion(FabricRecipeProvider.hasItem(Items.EMERALD),
                        FabricRecipeProvider.conditionsFromItem(Items.EMERALD))
                .criterion(FabricRecipeProvider.hasItem(Items.APPLE),
                        FabricRecipeProvider.conditionsFromItem(Items.APPLE))
                .offerTo(exporter);
        //灵魂宝石
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SOUL_GEM)
                .pattern("aca")
                .pattern("cbc")
                .pattern("aca")
                .input('a', Items.DIAMOND)
                .input('b', Items.NETHER_STAR)
                .input('c', Items.EMERALD)
                .criterion(FabricRecipeProvider.hasItem(Items.EMERALD),
                        FabricRecipeProvider.conditionsFromItem(Items.EMERALD))
                .criterion(FabricRecipeProvider.hasItem(Items.APPLE),
                        FabricRecipeProvider.conditionsFromItem(Items.APPLE))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHER_STAR),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHER_STAR))
                .offerTo(exporter);
        //磁铁
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, MAGNET)
                .pattern("acb")
                .pattern("a  ")
                .pattern("acb")
                .input('a', Items.IRON_INGOT)
                .input('b', Items.IRON_BLOCK)
                .input('c', Items.REDSTONE_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.REDSTONE_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.REDSTONE_BLOCK))
                .offerTo(exporter);
        //天使之翼核心
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ANGEL_WINGS_CORE)
                .pattern("aba")
                .pattern("cdc")
                .pattern("aba")
                .input('a', Items.DRAGON_BREATH)
                .input('b', Items.END_CRYSTAL)
                .input('c', Items.ELYTRA)
                .input('d', Items.NETHER_STAR)
                .criterion(FabricRecipeProvider.hasItem(Items.DRAGON_BREATH),
                        FabricRecipeProvider.conditionsFromItem(Items.DRAGON_BREATH))
                .criterion(FabricRecipeProvider.hasItem(Items.END_CRYSTAL),
                        FabricRecipeProvider.conditionsFromItem(Items.END_CRYSTAL))
                .criterion(FabricRecipeProvider.hasItem(Items.ELYTRA),
                        FabricRecipeProvider.conditionsFromItem(Items.ELYTRA))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHER_STAR),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHER_STAR))
                .offerTo(exporter);
        //神圣之羽
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, SACRED_FEATHERS)
                .pattern(" a ")
                .pattern("bcb")
                .pattern("def")
                .input('a', Items.ENDER_PEARL)
                .input('b', Items.AMETHYST_SHARD)
                .input('c', Items.FEATHER)
                .input('d', Items.NETHERITE_SCRAP)
                .input('e', Items.NETHER_STAR)
                .input('f', Items.EXPERIENCE_BOTTLE)
                .criterion(FabricRecipeProvider.hasItem(Items.ENDER_PEARL),
                        FabricRecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
                .criterion(FabricRecipeProvider.hasItem(Items.AMETHYST_SHARD),
                        FabricRecipeProvider.conditionsFromItem(Items.AMETHYST_SHARD))
                .criterion(FabricRecipeProvider.hasItem(Items.FEATHER),
                        FabricRecipeProvider.conditionsFromItem(Items.FEATHER))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_SCRAP),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_SCRAP))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHER_STAR),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHER_STAR))
                .criterion(FabricRecipeProvider.hasItem(Items.EXPERIENCE_BOTTLE),
                        FabricRecipeProvider.conditionsFromItem(Items.EXPERIENCE_BOTTLE))
                .offerTo(exporter);
        //天使方块框架
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ANGEL_BLOCK_FRAMEWORK)
                .pattern("aaa")
                .pattern("bcb")
                .pattern("ded")
                .input('a', Items.DIAMOND_BLOCK)
                .input('b', Items.NETHERITE_BLOCK)
                .input('c', Items.RESPAWN_ANCHOR)
                .input('d', Items.DRAGON_HEAD)
                .input('e', COMPRESSED_COPPER_6)
                .criterion(FabricRecipeProvider.hasItem(Items.DIAMOND_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.DIAMOND_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.RESPAWN_ANCHOR),
                        FabricRecipeProvider.conditionsFromItem(Items.RESPAWN_ANCHOR))
                .criterion(FabricRecipeProvider.hasItem(Items.DRAGON_HEAD),
                        FabricRecipeProvider.conditionsFromItem(Items.DRAGON_HEAD))
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_6),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_6))
                .offerTo(exporter);
        //天使方块
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ANGEL_BLOCK)
                .pattern(" a ")
                .pattern("bcb")
                .pattern("ddd")
                .input('a', ANGEL_BLOCK_FRAMEWORK)
                .input('b', SACRED_FEATHERS)
                .input('c', ANGEL_WINGS_CORE)
                .input('d', Items.HONEYCOMB)
                .criterion(FabricRecipeProvider.hasItem(ANGEL_BLOCK_FRAMEWORK),
                        FabricRecipeProvider.conditionsFromItem(ANGEL_BLOCK_FRAMEWORK))
                .criterion(FabricRecipeProvider.hasItem(SACRED_FEATHERS),
                        FabricRecipeProvider.conditionsFromItem(SACRED_FEATHERS))
                .criterion(FabricRecipeProvider.hasItem(ANGEL_WINGS_CORE),
                        FabricRecipeProvider.conditionsFromItem(ANGEL_WINGS_CORE))
                .criterion(FabricRecipeProvider.hasItem(Items.HONEYCOMB),
                        FabricRecipeProvider.conditionsFromItem(Items.HONEYCOMB))
                .offerTo(exporter);
        //抽卡
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, DREAM_KEY)
                .pattern(" ab")
                .pattern("cda")
                .pattern("ec ")
                .input('a', Items.RAW_GOLD_BLOCK)
                .input('b', Items.NETHERITE_INGOT)
                .input('c', Items.LAPIS_BLOCK)
                .input('d', Items.NETHER_STAR)
                .input('e', Items.DIAMOND_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Items.DIAMOND_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.DIAMOND_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHER_STAR),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHER_STAR))
                .criterion(FabricRecipeProvider.hasItem(Items.LAPIS_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.LAPIS_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.RAW_GOLD_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.RAW_GOLD_BLOCK))
                .offerTo(exporter, another(DREAM_KEY, 20));
        //下界合金锤
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, NETHERITE_HAMMER)
                .pattern("aab")
                .pattern(" c ")
                .pattern(" c ")
                .input('a', Items.NETHERITE_INGOT)
                .input('b', Items.NETHERITE_BLOCK)
                .input('c', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);

        //无序
        //光源方块
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LIGHT)
                .input(Items.TORCH)
                .criterion(FabricRecipeProvider.hasItem(Items.TORCH),
                        FabricRecipeProvider.conditionsFromItem(Items.TORCH))
                .offerTo(exporter);
        //村民移动者
        //1.20.4-beta4 暂时移除
/*        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, MOVE_VILLAGER)
                .input(Items.EMERALD)
                .criterion(FabricRecipeProvider.hasItem(Items.EMERALD),
                        FabricRecipeProvider.conditionsFromItem(Items.EMERALD))
                .offerTo(exporter);*/
        //压缩铜锭
        decomposed(COMPRESSED_COPPER_1, Items.COPPER_INGOT, 4, exporter);
        decomposed(COMPRESSED_COPPER_2, COMPRESSED_COPPER_1, 4, exporter);
        decomposed(COMPRESSED_COPPER_3, COMPRESSED_COPPER_2, 4, exporter);
        decomposed(COMPRESSED_COPPER_4, COMPRESSED_COPPER_3, 4, exporter);
        decomposed(COMPRESSED_COPPER_5, COMPRESSED_COPPER_4, 4, exporter);
        decomposed(COMPRESSED_COPPER_6, COMPRESSED_COPPER_5, 4, exporter);
        decomposed(COMPRESSED_COPPER_7, COMPRESSED_COPPER_6, 4, exporter);
        decomposed(COMPRESSED_COPPER_8, COMPRESSED_COPPER_7, 4, exporter);
        //压缩铜锭9
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_9)
                .input(COMPRESSED_COPPER_8, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_8),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_8))
                .offerTo(exporter);
        //物品分类者
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, SORTER)
                .input(Items.STICKY_PISTON)
                .input(Items.ENDER_PEARL)
                .input(Items.TARGET)
                .input(Items.LAVA_BUCKET)
                .input(Items.REDSTONE_BLOCK)
                .input(Items.DISPENSER)
                .input(Items.COMPARATOR)
                .input(Items.BARREL)
                .input(Items.HOPPER_MINECART)
                .criterion(FabricRecipeProvider.hasItem(Items.STICKY_PISTON),
                        FabricRecipeProvider.conditionsFromItem(Items.STICKY_PISTON))
                .criterion(FabricRecipeProvider.hasItem(Items.ENDER_PEARL),
                        FabricRecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
                .criterion(FabricRecipeProvider.hasItem(Items.TARGET),
                        FabricRecipeProvider.conditionsFromItem(Items.TARGET))
                .criterion(FabricRecipeProvider.hasItem(Items.LAVA_BUCKET),
                        FabricRecipeProvider.conditionsFromItem(Items.LAVA_BUCKET))
                .criterion(FabricRecipeProvider.hasItem(Items.REDSTONE_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.REDSTONE_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.DISPENSER),
                        FabricRecipeProvider.conditionsFromItem(Items.DISPENSER))
                .criterion(FabricRecipeProvider.hasItem(Items.COMPARATOR),
                        FabricRecipeProvider.conditionsFromItem(Items.COMPARATOR))
                .criterion(FabricRecipeProvider.hasItem(Items.BARREL),
                        FabricRecipeProvider.conditionsFromItem(Items.BARREL))
                .criterion(FabricRecipeProvider.hasItem(Items.HOPPER_MINECART),
                        FabricRecipeProvider.conditionsFromItem(Items.HOPPER_MINECART))
                .offerTo(exporter);
        //复制者
        int a = 0;
        for (Item item : Registries.ITEM.stream().toList()) {
            if (item == REPLICATORS || item == Items.AIR || item instanceof CanNotBeCopy)
                continue;
            a++;
            //复制
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, item, 2)
                    .input(item)
                    .input(REPLICATORS)
                    .criterion(FabricRecipeProvider.hasItem(REPLICATORS),
                            FabricRecipeProvider.conditionsFromItem(REPLICATORS))
                    .criterion(FabricRecipeProvider.hasItem(item),
                            FabricRecipeProvider.conditionsFromItem(item))
                    .offerTo(exporter, another(REPLICATORS, a));
        }
        //抽卡道具互换
        decomposed(TEN_DREAM_KEY, TEN_STELLAR_PROMISE, 1, exporter);
        decomposed(DREAM_KEY, STELLAR_PROMISE, 1, exporter);
        //5浩瀚之星合1dream_Key
        same(VAST_STAR, DREAM_KEY, 5, 1, 2, exporter);
        //合成十连
        same(DREAM_KEY, TEN_DREAM_KEY, 9, 1, 2, exporter);
        same(STELLAR_PROMISE, TEN_STELLAR_PROMISE, 9, 1, 2, exporter);
        //时空宝石
        //todo
/*        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TIMESPACE_GEM, 1)
                .input(TIMESPACE_SHARDS)
                .input(Items.DIAMOND)
                .criterion(FabricRecipeProvider.hasItem(TIMESPACE_SHARDS),
                        FabricRecipeProvider.conditionsFromItem(TIMESPACE_SHARDS))
                .criterion(FabricRecipeProvider.hasItem(Items.DIAMOND),
                        FabricRecipeProvider.conditionsFromItem(Items.DIAMOND))
                .offerTo(exporter, another(TIMESPACE_GEM, 1));*/
        //todo:时空宝石,暂时
        same(TIMESPACE_SHARDS, TIMESPACE_GEM, 2, 1, 2, exporter);
        //todo:暂时 会覆盖附魔.
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TIMESPACE_AXE)
                .input(Items.NETHERITE_AXE, 1)
                .input(TIMESPACE_GEM, 1)
                .input(Ingredient.ofItems(NETHERITE_HAMMER))
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_AXE),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_AXE))
                .criterion(FabricRecipeProvider.hasItem(TIMESPACE_GEM),
                        FabricRecipeProvider.conditionsFromItem(TIMESPACE_GEM))
                .criterion(FabricRecipeProvider.hasItem(NETHERITE_HAMMER),
                        FabricRecipeProvider.conditionsFromItem(NETHERITE_HAMMER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TIMESPACE_HOE)
                .input(Items.NETHERITE_HOE, 1)
                .input(TIMESPACE_GEM, 1)
                .input(NETHERITE_HAMMER, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_HOE),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_HOE))
                .criterion(FabricRecipeProvider.hasItem(TIMESPACE_GEM),
                        FabricRecipeProvider.conditionsFromItem(TIMESPACE_GEM))
                .criterion(FabricRecipeProvider.hasItem(NETHERITE_HAMMER),
                        FabricRecipeProvider.conditionsFromItem(NETHERITE_HAMMER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TIMESPACE_SHOVEL)
                .input(Items.NETHERITE_SHOVEL, 1)
                .input(TIMESPACE_GEM, 1)
                .input(NETHERITE_HAMMER, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_SHOVEL),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_SHOVEL))
                .criterion(FabricRecipeProvider.hasItem(TIMESPACE_GEM),
                        FabricRecipeProvider.conditionsFromItem(TIMESPACE_GEM))
                .criterion(FabricRecipeProvider.hasItem(NETHERITE_HAMMER),
                        FabricRecipeProvider.conditionsFromItem(NETHERITE_HAMMER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TIMESPACE_SWORD)
                .input(Items.NETHERITE_SWORD, 1)
                .input(TIMESPACE_GEM, 1)
                .input(NETHERITE_HAMMER, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_SWORD),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_SWORD))
                .criterion(FabricRecipeProvider.hasItem(TIMESPACE_GEM),
                        FabricRecipeProvider.conditionsFromItem(TIMESPACE_GEM))
                .criterion(FabricRecipeProvider.hasItem(NETHERITE_HAMMER),
                        FabricRecipeProvider.conditionsFromItem(NETHERITE_HAMMER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TIMESPACE_PICKAXE)
                .input(Items.NETHERITE_PICKAXE, 1)
                .input(TIMESPACE_GEM, 1)
                .input(NETHERITE_HAMMER, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.NETHERITE_PICKAXE),
                        FabricRecipeProvider.conditionsFromItem(Items.NETHERITE_PICKAXE))
                .criterion(FabricRecipeProvider.hasItem(TIMESPACE_GEM),
                        FabricRecipeProvider.conditionsFromItem(TIMESPACE_GEM))
                .criterion(FabricRecipeProvider.hasItem(NETHERITE_HAMMER),
                        FabricRecipeProvider.conditionsFromItem(NETHERITE_HAMMER))
                .offerTo(exporter);

        //死亡卷轴
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, DEATH_SCROLL)
                .input(Items.ROTTEN_FLESH, 1)
                .input(Items.ENDER_PEARL, 1)
                .input(Items.PAPER, 1)
                .criterion(FabricRecipeProvider.hasItem(Items.ROTTEN_FLESH),
                        FabricRecipeProvider.conditionsFromItem(Items.ROTTEN_FLESH))
                .criterion(FabricRecipeProvider.hasItem(Items.ENDER_PEARL),
                        FabricRecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
                .criterion(FabricRecipeProvider.hasItem(Items.PAPER),
                        FabricRecipeProvider.conditionsFromItem(Items.PAPER))
                .offerTo(exporter);

        //熔炼
        //矿熔炼
        RecipeProvider.offerSmelting(exporter, Util.make(Lists.newArrayList(), list -> {
            list.add(TIMESPACE_ORE);
        }), RecipeCategory.MISC, TIMESPACE_SHARDS, 0.45f, 300, "abc");
        RecipeProvider.offerSmelting(exporter, Util.make(Lists.newArrayList(), list -> {
            list.add(STELLAR_PROMISE_ORE);
        }), RecipeCategory.MISC, STELLAR_PROMISE, 0.45f, 300, "abc");
        //锻造
//        SmithingTrimRecipeJsonBuilder.create(null, Ingredient.ofItems(Items.NETHERITE_AXE), Ingredient.ofItems(TIMESPACE_GEM), RecipeCategory.MISC)
//                .offerTo(exporter, another(TIMESPACE_AXE,200));
    }

    public Identifier another(Item item) {
        return another(item, 1);
    }

    public Identifier another(Item item, int count) {
        return new Identifier(MODID, getItemPath(item) + "_" + count);
    }

    private void decomposed(Item block, Item ingot, int count, RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, block)
                .input(ingot, count)
                .criterion(FabricRecipeProvider.hasItem(ingot),
                        FabricRecipeProvider.conditionsFromItem(ingot))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ingot, count)
                .input(block)
                .criterion(FabricRecipeProvider.hasItem(block),
                        FabricRecipeProvider.conditionsFromItem(block))
                .offerTo(exporter, another(block));
    }

    private void same(Item input, Item output, int count, int craftCount, int jsonCount, RecipeExporter exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, craftCount)
                .input(input, count)
                .criterion(FabricRecipeProvider.hasItem(input),
                        FabricRecipeProvider.conditionsFromItem(input))
                .offerTo(exporter, another(output, jsonCount));

    }

    private void same_craft(Item input, Item output, int count, int craftCount, RecipeExporter exporter) {
        same(input, output, count, craftCount, 1, exporter);
    }

    private void same(Item input, Item output, int count, RecipeExporter exporter) {
        same(input, output, count, 1, 1, exporter);
    }

    private void same_json(Item input, Item output, int count, int jsonCount, RecipeExporter exporter) {
        same(input, output, count, 1, jsonCount, exporter);
    }


}

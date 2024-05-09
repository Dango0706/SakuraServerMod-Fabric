package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import static me.tuanzi.SakuraServer.*;

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
        /*
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_1)
                .input(Items.COPPER_INGOT, 4)
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_1),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_1))
                .offerTo(exporter);
        //压缩铜锭1
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_1, 4)
                .input(COMPRESSED_COPPER_2)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_2),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_2))
                .offerTo(exporter, another(COMPRESSED_COPPER_1));
        //压缩铜锭2
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_2)
                .input(COMPRESSED_COPPER_1, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_1),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_1))
                .offerTo(exporter);
        //压缩铜锭2
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_2, 4)
                .input(COMPRESSED_COPPER_3)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_3),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_3))
                .offerTo(exporter, another(COMPRESSED_COPPER_2));
        //压缩铜锭3
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_3)
                .input(COMPRESSED_COPPER_2, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_2),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_2))
                .offerTo(exporter);
        //压缩铜锭3
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_3, 4)
                .input(COMPRESSED_COPPER_4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_4),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_4))
                .offerTo(exporter, another(COMPRESSED_COPPER_3));
        //压缩铜锭4
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_4)
                .input(COMPRESSED_COPPER_3, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_3),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_3))
                .offerTo(exporter);
        //压缩铜锭4
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_4, 4)
                .input(COMPRESSED_COPPER_5)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_5),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_5))
                .offerTo(exporter, another(COMPRESSED_COPPER_4));
        //压缩铜锭5
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_5)
                .input(COMPRESSED_COPPER_4, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_4),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_4))
                .offerTo(exporter);
        //压缩铜锭5
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_5, 4)
                .input(COMPRESSED_COPPER_6)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_6),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_6))
                .offerTo(exporter, another(COMPRESSED_COPPER_5));
        //压缩铜锭6
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_6)
                .input(COMPRESSED_COPPER_5, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_5),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_5))
                .offerTo(exporter);
        //压缩铜锭6
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_6, 4)
                .input(COMPRESSED_COPPER_7)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_7),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_7))
                .offerTo(exporter, another(COMPRESSED_COPPER_6));
        //压缩铜锭7
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_7)
                .input(COMPRESSED_COPPER_6, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_6),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_6))
                .offerTo(exporter);
        //压缩铜锭7
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_7, 4)
                .input(COMPRESSED_COPPER_8)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_8),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_8))
                .offerTo(exporter, another(COMPRESSED_COPPER_7));
        //压缩铜锭8
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_8)
                .input(COMPRESSED_COPPER_7, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_7),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_7))
                .offerTo(exporter);
        //压缩铜锭8
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_8, 4)
                .input(COMPRESSED_COPPER_9)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_9),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_9))
                .offerTo(exporter, another(COMPRESSED_COPPER_8));*/
        //压缩铜锭9
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, COMPRESSED_COPPER_9)
                .input(COMPRESSED_COPPER_8, 4)
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_COPPER_8),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_COPPER_8))
                .offerTo(exporter);


    }

    public Identifier another(Item item) {
        return new Identifier(MODID, getItemPath(item) + "_1");
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


}

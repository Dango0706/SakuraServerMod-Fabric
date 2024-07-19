package me.tuanzi.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static me.tuanzi.utils.Utils.id;
import static me.tuanzi.utils.registry.BlockRegistry.COCOA_BEANS_BLOCK;
import static me.tuanzi.utils.registry.BlockRegistry.COMPRESSED_HAY_BLOCK_1;
import static me.tuanzi.utils.registry.ItemRegistry.*;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        //复生护符
        //农夫版
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, RESURRECTION_AMULET)
                .pattern(" ab")
                .pattern("cde")
                .pattern("fgh")
                .input('a', Items.IRON_INGOT)
                .input('b', Items.GOLD_INGOT)
                .input('c', POISONOUS_POTATO_BUNDLE)
                .input('d', COMPRESSED_HAY_BLOCK_1)
                .input('e', BEETROOT_BUNDLE)
                .input('f', SWEET_BERRIES_BUNDLE)
                .input('g', CARROT_BUNDLE)
                .input('h', COCOA_BEANS_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.GOLD_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.GOLD_INGOT))
                .criterion(FabricRecipeProvider.hasItem(POISONOUS_POTATO_BUNDLE),
                        FabricRecipeProvider.conditionsFromItem(POISONOUS_POTATO_BUNDLE))
                .criterion(FabricRecipeProvider.hasItem(COMPRESSED_HAY_BLOCK_1),
                        FabricRecipeProvider.conditionsFromItem(COMPRESSED_HAY_BLOCK_1))
                .criterion(FabricRecipeProvider.hasItem(BEETROOT_BUNDLE),
                        FabricRecipeProvider.conditionsFromItem(BEETROOT_BUNDLE))
                .criterion(FabricRecipeProvider.hasItem(SWEET_BERRIES_BUNDLE),
                        FabricRecipeProvider.conditionsFromItem(SWEET_BERRIES_BUNDLE))
                .criterion(FabricRecipeProvider.hasItem(CARROT_BUNDLE),
                        FabricRecipeProvider.conditionsFromItem(CARROT_BUNDLE))
                .criterion(FabricRecipeProvider.hasItem(COCOA_BEANS_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(COCOA_BEANS_BLOCK))
                .offerTo(exporter, another(RESURRECTION_AMULET, 1));
        //矿工版
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, RESURRECTION_AMULET)
                .pattern(" ab")
                .pattern("cdc")
                .pattern("fcf")
                .input('a', BRONZE_INGOT)
                .input('b', Items.GOLD_INGOT)
                .input('c', Items.DIAMOND)
                .input('d', Items.REDSTONE_BLOCK)
                .input('f', Items.ANCIENT_DEBRIS)
                .criterion(FabricRecipeProvider.hasItem(BRONZE_INGOT),
                        FabricRecipeProvider.conditionsFromItem(BRONZE_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.GOLD_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.GOLD_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.DIAMOND),
                        FabricRecipeProvider.conditionsFromItem(Items.DIAMOND))
                .criterion(FabricRecipeProvider.hasItem(Items.REDSTONE_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(Items.REDSTONE_BLOCK))
                .criterion(FabricRecipeProvider.hasItem(Items.ANCIENT_DEBRIS),
                        FabricRecipeProvider.conditionsFromItem(Items.ANCIENT_DEBRIS))
                .offerTo(exporter, another(RESURRECTION_AMULET, 2));
        //青铜 暂时为铁+铜
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, BRONZE_INGOT)
                .input(Items.IRON_INGOT)
                .input(Items.COPPER_INGOT)
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
                .offerTo(exporter);
        //毒马铃薯捆
        nineInOne(exporter, Items.POISONOUS_POTATO, POISONOUS_POTATO_BUNDLE);
        //马铃薯捆
        nineInOne(exporter, Items.POTATO, POTATO_BUNDLE);
        //压缩干草捆
        nineInOne(exporter, Items.HAY_BLOCK, COMPRESSED_HAY_BLOCK_1.asItem());
        //甜菜捆
        nineInOne(exporter, Items.BEETROOT, BEETROOT_BUNDLE);
        //胡萝卜捆
        nineInOne(exporter, Items.CARROT, CARROT_BUNDLE);
        //甜浆果捆
        nineInOne(exporter, Items.SWEET_BERRIES, SWEET_BERRIES_BUNDLE);
        //可可豆捆
        nineInOne(exporter, Items.COCOA_BEANS, COCOA_BEANS_BUNDLE);
        //可可豆块
        nineInOne(exporter, COCOA_BEANS_BUNDLE, COCOA_BEANS_BLOCK.asItem(), 1);


    }

    //9合1
    private void nineInOne(RecipeExporter exporter, ItemConvertible ingot, ItemConvertible block) {
        nineInOne(exporter, ingot, block, 0);
    }

    //9合1
    private void nineInOne(RecipeExporter exporter, ItemConvertible ingot, ItemConvertible block, int id) {
        packet(exporter, ingot, block, 9, id);
    }

    //count合1
    private void packet(RecipeExporter exporter, ItemConvertible ingot, ItemConvertible block, int count) {
        //count个锭合成一个块
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, block)
                .input(ingot, count)
                .criterion(FabricRecipeProvider.hasItem(ingot), FabricRecipeProvider.conditionsFromItem(ingot))
                .offerTo(exporter);
        //一个块分解成count个锭
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ingot, count)
                .input(block)
                .criterion(FabricRecipeProvider.hasItem(block), FabricRecipeProvider.conditionsFromItem(block))
                .offerTo(exporter);

    }

    private void packet(RecipeExporter exporter, ItemConvertible ingot, ItemConvertible block, int count, int id) {
        //count个锭合成一个块
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, block)
                .input(ingot, count)
                .criterion(FabricRecipeProvider.hasItem(ingot), FabricRecipeProvider.conditionsFromItem(ingot))
                .offerTo(exporter, another((Item) block, id));
        //一个块分解成count个锭
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ingot, count)
                .input(block)
                .criterion(FabricRecipeProvider.hasItem(block), FabricRecipeProvider.conditionsFromItem(block))
                .offerTo(exporter, another((Item) ingot, id));

    }

    //其余的id
    private Identifier another(Item item, int count) {
        return id(getItemPath(item) + "_" + count);
    }

    //其他的id
    private Identifier another(Item item) {
        return another(item, 1);
    }

}

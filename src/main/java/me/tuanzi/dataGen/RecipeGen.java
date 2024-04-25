package me.tuanzi.dataGen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

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
        //光源方块
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.LIGHT)
                .input(Items.TORCH)
                .criterion(FabricRecipeProvider.hasItem(Items.TORCH),
                        FabricRecipeProvider.conditionsFromItem(Items.LIGHT)).criterion(FabricRecipeProvider.hasItem(Items.TORCH),
                        FabricRecipeProvider.conditionsFromItem(Items.TORCH)).offerTo(exporter);

    }

}
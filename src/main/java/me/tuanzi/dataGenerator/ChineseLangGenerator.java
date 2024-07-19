package me.tuanzi.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static me.tuanzi.utils.KeyConstant.*;
import static me.tuanzi.utils.registry.BlockRegistry.COCOA_BEANS_BLOCK;
import static me.tuanzi.utils.registry.BlockRegistry.COMPRESSED_HAY_BLOCK_1;
import static me.tuanzi.utils.registry.ItemRegistry.*;

public class ChineseLangGenerator extends FabricLanguageProvider {
    public ChineseLangGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "zh_cn", registryLookup);
    }

    /**
     * Implement this method to register languages.
     *
     * <p>Call {@link TranslationBuilder#add(String, String)} to add a translation.
     *
     * @param registryLookup
     * @param translationBuilder
     */
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        //item
        translationBuilder.add(DEBUG_ITEM,"开发者工具");
        //复生护符
        translationBuilder.add(RESURRECTION_AMULET,"复生护符");
        translationBuilder.add(RESURRECTION_AMULET_INFO,"复活时消耗30级经验保存物品栏.");
        translationBuilder.add(RESURRECTION_AMULET_SHIFT1,"在古老的传说中.");
        translationBuilder.add(RESURRECTION_AMULET_SHIFT2,"复生护符能让持有者在死亡后重生");
        translationBuilder.add(RESURRECTION_AMULET_SHIFT3,"继续未完成的使命.");
        translationBuilder.add(RESURRECTION_AMULET_NOT_EXP,"啊哦,好像你无法发动护符的力量呢~");

        translationBuilder.add(BRONZE_INGOT,"青铜");
        //捆/压缩
        translationBuilder.add(POISONOUS_POTATO_BUNDLE,"毒马铃薯捆");
        translationBuilder.add(POTATO_BUNDLE,"马铃薯捆");
        translationBuilder.add(COMPRESSED_HAY_BLOCK_1,"一级压缩干草捆");
        translationBuilder.add(BEETROOT_BUNDLE,"甜菜捆");
        translationBuilder.add(SWEET_BERRIES_BUNDLE,"甜浆果捆");
        translationBuilder.add(CARROT_BUNDLE,"胡萝卜捆");
        translationBuilder.add(COCOA_BEANS_BUNDLE,"可可豆捆");
        translationBuilder.add(COCOA_BEANS_BLOCK,"可可豆块");
        //constant
        translationBuilder.add(PRESS_SHIFT_GET_MORE_INFO,"<按shift显示更多..>");
        //itemGroup
        translationBuilder.add(ITEM_GROUP_NAME,"樱花服务器");
        translationBuilder.add(BUNDLE_AND_COMPRESSED_ITEM_GROUP,"樱花服务器-压缩物品");


    }
}

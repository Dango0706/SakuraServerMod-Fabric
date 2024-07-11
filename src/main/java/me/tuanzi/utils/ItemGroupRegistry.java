package me.tuanzi.utils;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static me.tuanzi.blocks.BlockRegisty.*;
import static me.tuanzi.items.ItemsRegisty.*;
import static me.tuanzi.items.ToolRegistry.*;
import static me.tuanzi.items.utils.ItemUtils.getAllModEnchantmentBooks;
import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class ItemGroupRegistry {

    //itemGroup
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(LIFT))
            .displayName(Text.translatable("itemGroup.sakura_server.item_group"))
            .entries((context, entries) -> {
/*                for (Item allModItem : getAllModItems()) {
                    entries.add(new ItemStack(allModItem));
                }*/
                entries.add(new ItemStack(LIFT));
                entries.add(new ItemStack(ANGEL_BLOCK));
                entries.add(new ItemStack(SORTER));
                entries.add(new ItemStack(EMERALD_APPLE));
                entries.add(new ItemStack(SOUL_GEM));
                entries.add(new ItemStack(DRAGON_SWORD));
                entries.add(new ItemStack(STARDUST_WAND));
                entries.add(new ItemStack(GAMBLING_PICKAXE));
                entries.add(new ItemStack(ANTIMATTER_PICKAXE));
                entries.add(new ItemStack(MOVE_VILLAGER));
                entries.add(new ItemStack(MAGNET));
                entries.add(new ItemStack(ANGEL_WINGS_CORE));
                entries.add(new ItemStack(SACRED_FEATHERS));
                entries.add(new ItemStack(COMPRESSED_COPPER_1));
                entries.add(new ItemStack(COMPRESSED_COPPER_2));
                entries.add(new ItemStack(COMPRESSED_COPPER_3));
                entries.add(new ItemStack(COMPRESSED_COPPER_4));
                entries.add(new ItemStack(COMPRESSED_COPPER_5));
                entries.add(new ItemStack(COMPRESSED_COPPER_6));
                entries.add(new ItemStack(COMPRESSED_COPPER_7));
                entries.add(new ItemStack(COMPRESSED_COPPER_8));
                entries.add(new ItemStack(COMPRESSED_COPPER_9));
                entries.add(new ItemStack(ANGEL_BLOCK_FRAMEWORK));
                entries.add(new ItemStack(DRAW_SOMETHING));
                entries.add(new ItemStack(DRAW_BLUE_SOMETHING));
                entries.add(new ItemStack(DRAW_PURPLE_SOMETHING));
                entries.add(new ItemStack(DRAW_GOLDEN_SOMETHING));
                entries.add(new ItemStack(DREAM_KEY));
                entries.add(new ItemStack(TEN_DREAM_KEY));
                entries.add(new ItemStack(STELLAR_PROMISE_ORE));
                entries.add(new ItemStack(STELLAR_PROMISE));
                entries.add(new ItemStack(TEN_STELLAR_PROMISE));
                entries.add(new ItemStack(REPLICATORS));
                entries.add(new ItemStack(VAST_STAR));
                entries.add(new ItemStack(TIMESPACE_SHARDS));
                entries.add(new ItemStack(TIMESPACE_GEM));
                entries.add(new ItemStack(TIMESPACE_ORE));
                entries.add(new ItemStack(TIMESPACE_SHOVEL));
                entries.add(new ItemStack(TIMESPACE_PICKAXE));
                entries.add(new ItemStack(TIMESPACE_HOE));
                entries.add(new ItemStack(TIMESPACE_AXE));
                entries.add(new ItemStack(TIMESPACE_SWORD));
                entries.add(new ItemStack(DEATH_SCROLL));
                entries.add(new ItemStack(NETHERITE_HAMMER));
                entries.add(new ItemStack(TEST_ITEM));
                for (ItemStack allModEnchantmentBook : getAllModEnchantmentBooks()) {
                    entries.add(allModEnchantmentBook);
                }
            })
            .build();

    public ItemGroupRegistry() {
        printDebugLog("加载创造Tab...");
        Registry.register(Registries.ITEM_GROUP, new Identifier(MODID, "item_group"), ITEM_GROUP);
    }
}

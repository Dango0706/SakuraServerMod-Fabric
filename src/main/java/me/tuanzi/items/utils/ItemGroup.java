package me.tuanzi.items.utils;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static me.tuanzi.utils.KeyConstant.BUNDLE_AND_COMPRESSED_ITEM_GROUP;
import static me.tuanzi.utils.KeyConstant.ITEM_GROUP_NAME;
import static me.tuanzi.utils.Utils.id;
import static me.tuanzi.utils.registry.BlockRegistry.COCOA_BEANS_BLOCK;
import static me.tuanzi.utils.registry.BlockRegistry.COMPRESSED_HAY_BLOCK_1;
import static me.tuanzi.utils.registry.ItemRegistry.*;

public class ItemGroup {

    public static final net.minecraft.item.ItemGroup DEAFULT = FabricItemGroup.builder()
            .icon(() -> new ItemStack(DEBUG_ITEM))
            .displayName(Text.keybind(ITEM_GROUP_NAME))
            .entries((c , e) -> {
                e.add(new ItemStack(DEBUG_ITEM));
                e.add(new ItemStack(RESURRECTION_AMULET));
                e.add(new ItemStack(BRONZE_INGOT));
            })
            .build();
    public static final net.minecraft.item.ItemGroup BUNDLE_AND_COMPRESSED = FabricItemGroup.builder()
            .icon(() -> new ItemStack(CARROT_BUNDLE))
            .displayName(Text.keybind(BUNDLE_AND_COMPRESSED_ITEM_GROUP))
            .entries((c , e) -> {
                e.add(new ItemStack(POISONOUS_POTATO_BUNDLE));
                e.add(new ItemStack(COMPRESSED_HAY_BLOCK_1));
                e.add(new ItemStack(BEETROOT_BUNDLE));
                e.add(new ItemStack(SWEET_BERRIES_BUNDLE));
                e.add(new ItemStack(CARROT_BUNDLE));
                e.add(new ItemStack(POTATO_BUNDLE));
                e.add(new ItemStack(COCOA_BEANS_BUNDLE));
                e.add(new ItemStack(COCOA_BEANS_BLOCK));
            })
            .build();

    public ItemGroup() {
        Registry.register(Registries.ITEM_GROUP, id("item_group"), DEAFULT);
        Registry.register(Registries.ITEM_GROUP, id("bundle_and_compressed"), BUNDLE_AND_COMPRESSED);
    }
}

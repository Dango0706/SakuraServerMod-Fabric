package me.tuanzi.utils.registry;

import me.tuanzi.items.utils.SakuraItem;
import me.tuanzi.items.functional.DebugItem;
import me.tuanzi.items.functional.ResurrectionAmulet;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import static me.tuanzi.utils.KeyConstant.*;
import static me.tuanzi.utils.Utils.id;

public class ItemRegistry {

    public static final DebugItem DEBUG_ITEM = new DebugItem(new Item.Settings());
    public static final SakuraItem RESURRECTION_AMULET = new ResurrectionAmulet(new Item.Settings()).setTooltip(Text.keybind(RESURRECTION_AMULET_INFO)).setNeedShiftTooltip(Text.translatable(RESURRECTION_AMULET_SHIFT1),Text.keybind(RESURRECTION_AMULET_SHIFT2),Text.translatable(RESURRECTION_AMULET_SHIFT3));
    public static final SakuraItem BRONZE_INGOT = new SakuraItem(new Item.Settings());
    //捆
    public static final SakuraItem POISONOUS_POTATO_BUNDLE = new SakuraItem(new Item.Settings());
    public static final SakuraItem BEETROOT_BUNDLE = new SakuraItem(new Item.Settings());
    public static final SakuraItem SWEET_BERRIES_BUNDLE = new SakuraItem(new Item.Settings());
    public static final SakuraItem CARROT_BUNDLE = new SakuraItem(new Item.Settings());
    public static final SakuraItem POTATO_BUNDLE = new SakuraItem(new Item.Settings());
    public static final SakuraItem COCOA_BEANS_BUNDLE = new SakuraItem(new Item.Settings());

    public ItemRegistry() {

        Registry.register(Registries.ITEM, id("debug_item"), DEBUG_ITEM);
        Registry.register(Registries.ITEM, id("resurrection_amulet"), RESURRECTION_AMULET);
        Registry.register(Registries.ITEM, id("bronze_ingot"), BRONZE_INGOT);
        //BUNDLE_AND_COMPRESSED
        Registry.register(Registries.ITEM, id("poisonous_potato_bundle"), POISONOUS_POTATO_BUNDLE);
        Registry.register(Registries.ITEM, id("beetroot_bundle"), BEETROOT_BUNDLE);
        Registry.register(Registries.ITEM, id("sweet_berries_bundle"), SWEET_BERRIES_BUNDLE);
        Registry.register(Registries.ITEM, id("carrot_bundle"), CARROT_BUNDLE);
        Registry.register(Registries.ITEM, id("potato_bundle"), POTATO_BUNDLE);
        Registry.register(Registries.ITEM, id("cocoa_beans_bundle"), COCOA_BEANS_BUNDLE);

    }
}

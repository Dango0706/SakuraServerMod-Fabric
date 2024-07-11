package me.tuanzi.items;

import me.tuanzi.items.swords.DragonSword;
import me.tuanzi.items.swords.StardustWand;
import me.tuanzi.items.tools.AntimatterPickaxe;
import me.tuanzi.items.tools.GamblingPickaxe;
import me.tuanzi.items.utils.SakuraToolMaterial;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.tuanzi.items.ItemsRegisty.TIMESPACE_GEM;
import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;


public class ToolRegistry {
    //toolMaterial
    public static final SakuraToolMaterial RARE_TOOLS = new SakuraToolMaterial(3);
    public static final SakuraToolMaterial EPIC_TOOLS = new SakuraToolMaterial(4);
    public static final SakuraToolMaterial LEG_TOOLS = new SakuraToolMaterial(5);
    public static final SakuraToolMaterial TIMESPACE_MATERIAL = new SakuraToolMaterial(3, 5, 2568,12.0f, Ingredient.ofItems(TIMESPACE_GEM));
    //TimeSpace
    public static final SwordItem TIMESPACE_SWORD = new SwordItem(TIMESPACE_MATERIAL, 10, 1.6f - 4.0f, new Item.Settings().fireproof());
    public static final PickaxeItem TIMESPACE_PICKAXE = new PickaxeItem(TIMESPACE_MATERIAL, 8, 1.2f - 4.0f, new Item.Settings().fireproof());
    public static final AxeItem TIMESPACE_AXE = new AxeItem(TIMESPACE_MATERIAL, 12, 1.0f - 4.0f, new Item.Settings().fireproof());
    public static final ShovelItem TIMESPACE_SHOVEL = new ShovelItem(TIMESPACE_MATERIAL, 8.5f, 1.0f - 4.0f, new Item.Settings().fireproof());
    public static final HoeItem TIMESPACE_HOE = new HoeItem(TIMESPACE_MATERIAL, 2, 4.0f - 4.0f, new Item.Settings().fireproof());
    //sword
    public static final SwordItem DRAGON_SWORD = new DragonSword();
    public static final SwordItem STARDUST_WAND = new StardustWand();
    //tool
    public static final PickaxeItem GAMBLING_PICKAXE = new GamblingPickaxe();
    public static final PickaxeItem ANTIMATTER_PICKAXE = new AntimatterPickaxe();

    public ToolRegistry() {
        printDebugLog("加载工具...");
        Registry.register(Registries.ITEM, new Identifier(MODID, "dragon_sword"), DRAGON_SWORD);
        Registry.register(Registries.ITEM, new Identifier(MODID, "stardust_wand"), STARDUST_WAND);
        Registry.register(Registries.ITEM, new Identifier(MODID, "gambling_pickaxe"), GAMBLING_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "antimatter_pickaxe"), ANTIMATTER_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_sword"), TIMESPACE_SWORD);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_axe"), TIMESPACE_AXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_pickaxe"), TIMESPACE_PICKAXE);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_shovel"), TIMESPACE_SHOVEL);
        Registry.register(Registries.ITEM, new Identifier(MODID, "timespace_hoe"), TIMESPACE_HOE);
    }
}

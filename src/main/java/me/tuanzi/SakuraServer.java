package me.tuanzi;

import me.tuanzi.blocks.lift.Lift;
import me.tuanzi.effects.LiftCDEffect;
import me.tuanzi.enchantments.SoulBound;
import me.tuanzi.enchantments.VeinMine;
import me.tuanzi.items.foods.EmeraldApple;
import me.tuanzi.utils.CommandRegister;
import me.tuanzi.utils.EventRegister;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import static me.tuanzi.network.C2SChannel.registerC2SPackets;

public class SakuraServer implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.

    public static final String MODID = "sakura_server";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
    //block
    public static final Block LIFT = new Lift(FabricBlockSettings.create().strength(4.0f).requiresTool());
    //item
    public static final Item EMERALD_APPLE = new EmeraldApple();

    //effect
    public static final StatusEffect LIFT_CD = new LiftCDEffect();
    //itemGroup
    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(LIFT))
            .displayName(Text.translatable("itemGroup.sakura_server.item_group"))
            .entries((context, entries) -> {
                entries.add(new ItemStack(LIFT));
                entries.add(new ItemStack(EMERALD_APPLE));
            })
            .build();
    //enchantment
    public static Enchantment SOUL_BOUND = new SoulBound(Enchantment.Rarity.RARE, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static Enchantment VEIN_MINE = new VeinMine(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    //stats
    public static final Identifier HIGHEST_DAMAGE = new Identifier(MODID,"highest_damage");
    public static final Identifier HIGHEST_HURT = new Identifier(MODID,"highest_hurt");
    public static final Identifier LIFT_ALL = new Identifier(MODID,"lift_all");
    public static final Identifier LIFT_UP = new Identifier(MODID,"lift_up");
    public static final Identifier LIFT_DOWN = new Identifier(MODID,"lift_down");
    public static final Identifier VEIN_MINE_COUNT = new Identifier(MODID,"vein_mine_count");


    public static void printLog(String log) {
        // prevent usage if the Instance is not run in a development environment
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;

        // customize that message however you want...
        LOGGER.info("[DEBUG][" + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis()) + "]{" + log + "}");
    }

    @Override
    public void onInitialize() {

        //reg
        //block
        Registry.register(Registries.BLOCK, new Identifier(MODID, "lift"), LIFT);
        //item
        Registry.register(Registries.ITEM, new Identifier(MODID, "lift"), new BlockItem(LIFT, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "emerald_apple"), EMERALD_APPLE);
        //enchantment
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "soul_bound"), SOUL_BOUND);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "vein_mine"), VEIN_MINE);
        //itemGroup
        Registry.register(Registries.ITEM_GROUP, new Identifier(MODID, "item_group"), ITEM_GROUP);
        //effect
        Registry.register(Registries.STATUS_EFFECT, new Identifier(MODID, "lift_cd"), LIFT_CD);
        //statistics
        Registry.register(Registries.CUSTOM_STAT,"highest_damage", HIGHEST_DAMAGE);
        Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT,"highest_hurt", HIGHEST_HURT);
        Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT,"lift_all", LIFT_ALL);
        Stats.CUSTOM.getOrCreateStat(LIFT_ALL, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT,"lift_up",LIFT_UP);
        Stats.CUSTOM.getOrCreateStat(LIFT_UP, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT,"lift_down", LIFT_DOWN);
        Stats.CUSTOM.getOrCreateStat(LIFT_DOWN, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT,"vein_mine_count", VEIN_MINE_COUNT);
        Stats.CUSTOM.getOrCreateStat(VEIN_MINE_COUNT, StatFormatter.DEFAULT);
        //event
        new EventRegister();
        //command
        new CommandRegister();
        //network
        registerC2SPackets();
        printLog("加载成功!");
    }


}
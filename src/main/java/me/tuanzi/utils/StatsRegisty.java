package me.tuanzi.utils;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class StatsRegisty {

    //stats
    public static final Identifier HIGHEST_DAMAGE = new Identifier(MODID, "highest_damage");
    public static final Identifier HIGHEST_HURT = new Identifier(MODID, "highest_hurt");
    public static final Identifier LIFT_ALL = new Identifier(MODID, "lift_all");
    public static final Identifier LIFT_UP = new Identifier(MODID, "lift_up");
    public static final Identifier LIFT_DOWN = new Identifier(MODID, "lift_down");
    public static final Identifier VEIN_MINE_COUNT = new Identifier(MODID, "vein_mine_count");
    public static final Identifier DRAW_COUNT = new Identifier(MODID, "draw_count");
    public static final Identifier DRAW_GOLDEN_COUNT = new Identifier(MODID, "draw_golden_count");
    public static final Identifier DRAW_PURPLE_COUNT = new Identifier(MODID, "draw_purple_count");
    public static final Identifier DRAW_BLUE_COUNT = new Identifier(MODID, "draw_blue_count");
    public static final Identifier BREAK_BLOCK_COUNT = new Identifier(MODID, "break_block_count");
    public static final Identifier TOTAL_DAMAGE_TAKEN = new Identifier(MODID, "total_damage_taken");
    public static final Identifier TOTAL_ONLINE_TIME = new Identifier(MODID, "total_online_time");
    public static final Identifier TOTAL_DAMAGE_CAUSED = new Identifier(MODID, "total_damage_caused");
    public static final Identifier TOTAL_PLAYER_DAMAGE_TAKEN = new Identifier(MODID, "total_player_damage_taken");
    public static final Identifier TOTAL_PLAYER_DAMAGE_CAUSED = new Identifier(MODID, "total_player_damage_caused");
    public static final Identifier PLACE_BLOCK_COUNT = new Identifier(MODID, "place_block_count");

    public StatsRegisty() {
        printDebugLog("加载统计...");
        Registry.register(Registries.CUSTOM_STAT, "highest_damage", HIGHEST_DAMAGE);
        Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "highest_hurt", HIGHEST_HURT);
        Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "lift_all", LIFT_ALL);
        Stats.CUSTOM.getOrCreateStat(LIFT_ALL, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "lift_up", LIFT_UP);
        Stats.CUSTOM.getOrCreateStat(LIFT_UP, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "lift_down", LIFT_DOWN);
        Stats.CUSTOM.getOrCreateStat(LIFT_DOWN, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "vein_mine_count", VEIN_MINE_COUNT);
        Stats.CUSTOM.getOrCreateStat(VEIN_MINE_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_count", DRAW_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_golden_count", DRAW_GOLDEN_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_GOLDEN_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_purple_count", DRAW_PURPLE_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_PURPLE_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "draw_blue_count", DRAW_BLUE_COUNT);
        Stats.CUSTOM.getOrCreateStat(DRAW_BLUE_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "break_block_count", BREAK_BLOCK_COUNT);
        Stats.CUSTOM.getOrCreateStat(BREAK_BLOCK_COUNT, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "total_damage_taken", TOTAL_DAMAGE_TAKEN);
        Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_TAKEN, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "total_online_time", TOTAL_ONLINE_TIME);
        Stats.CUSTOM.getOrCreateStat(TOTAL_ONLINE_TIME, StatFormatter.DEFAULT);
        Registry.register(Registries.CUSTOM_STAT, "total_damage_caused", TOTAL_DAMAGE_CAUSED);
        Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_CAUSED, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "total_player_damage_taken", TOTAL_PLAYER_DAMAGE_TAKEN);
        Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_TAKEN, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "total_player_damage_caused", TOTAL_PLAYER_DAMAGE_CAUSED);
        Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_CAUSED, StatFormatter.DIVIDE_BY_TEN);
        Registry.register(Registries.CUSTOM_STAT, "place_block_count", PLACE_BLOCK_COUNT);
        Stats.CUSTOM.getOrCreateStat(PLACE_BLOCK_COUNT, StatFormatter.DEFAULT);
    }
}

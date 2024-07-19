package me.tuanzi.utils;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static me.tuanzi.utils.Constant.LOGGER;
import static me.tuanzi.utils.Constant.MOD_ID;

public class Utils {

    //生成id
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
    //输出debug报告
    public static void printDebug(Object msg) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment())
            LOGGER.info("[Debug]" + msg.toString());
    }

    //遍历所有身上的物品,包括盔甲与主副手.
    public static ArrayList<ItemStack> getPlayerInv(PlayerEntity entity) {
        ArrayList<ItemStack> inventory = new ArrayList<>();
        if (!entity.getWorld().isClient) {
            inventory.addAll(entity.getInventory().main);
            inventory.addAll(entity.getInventory().armor);
            inventory.addAll(entity.getInventory().offHand);
            inventory.add(entity.getMainHandStack());
        } else {
            return inventory;
        }
        return inventory;
    }

    //获取当前经验值总量
    public static int getExperience(PlayerEntity entity) {
        int points;
        int level = entity.experienceLevel;
        if (level >= 32) {
            points = (int) (4.5 * level * level - 162.5 * level + 2220);
        } else if (level >= 17) {
            points = (int) (2.5 * level * level - 40.5 * level + 360);
        } else {
            points = 2 * level + 7;
        }
        return points + entity.totalExperience;
    }

}

package me.tuanzi.utils;

import net.fabricmc.loader.api.FabricLoader;

import java.text.SimpleDateFormat;

import static me.tuanzi.utils.Constants.LOGGER;

public class LoggerUtils {

    public static void printDebugLog(String log) {
        // prevent usage if the Instance is not run in a development environment
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
        // customize that message however you want...
        LOGGER.info("[DEBUG][" + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis()) + "]{" + log + "}");
    }

    public static void printInfoLog(String log) {
        LOGGER.info("[INFO][" + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis()) + "]{" + log + "}");
    }

    public static void printWarnLog(String log) {
        LOGGER.warn("[WARN][" + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis()) + "]{" + log + "}");
    }

    public static void printErrorLog(String log) {
        LOGGER.error("[ERROR][" + new SimpleDateFormat("hh:mm:ss").format(System.currentTimeMillis()) + "]{" + log + "}");
    }

}

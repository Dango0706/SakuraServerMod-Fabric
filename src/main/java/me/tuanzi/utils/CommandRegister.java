package me.tuanzi.utils;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static me.tuanzi.commands.RemoveNBT.removeLore;
import static me.tuanzi.commands.ServerStates.serverStates;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class CommandRegister {

    public CommandRegister() {
        printDebugLog("加载命令...");
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> removeLore(dispatcher));
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> serverStates(dispatcher));
    }
}

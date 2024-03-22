package me.tuanzi.utils;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static me.tuanzi.commands.RemoveNBT.removeLore;

public class CommandRegister {

    public CommandRegister() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> removeLore(dispatcher));
    }
}

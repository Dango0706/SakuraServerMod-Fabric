package me.tuanzi.bedrock;

import org.geysermc.event.subscribe.Subscribe;
import org.geysermc.geyser.api.event.EventRegistrar;
import org.geysermc.geyser.api.event.lifecycle.GeyserPostInitializeEvent;

import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class GeyserPerInit implements EventRegistrar {
    @Subscribe
    public void onGeyserPostInitializeEvent(GeyserPostInitializeEvent event) {
        printDebugLog("哈哈哈,Geyser已加载!");
    }




}

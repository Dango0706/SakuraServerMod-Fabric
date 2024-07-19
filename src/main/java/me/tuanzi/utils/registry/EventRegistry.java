package me.tuanzi.utils.registry;

import me.tuanzi.events.player.PlayerTickEvent;
import me.tuanzi.items.functional.events.FunctionalItemEvent;
import me.tuanzi.utils.DevelpomentUtils;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class EventRegistry {

    public EventRegistry() {

        PlayerTickEvent.EVENT.register(new DevelpomentUtils());
        ServerPlayerEvents.AFTER_RESPAWN.register(new FunctionalItemEvent());
        ServerLivingEntityEvents.ALLOW_DEATH.register(new FunctionalItemEvent());
    }
}

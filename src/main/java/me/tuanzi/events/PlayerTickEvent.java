package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public interface PlayerTickEvent {

    Event<PlayerTickEvent> EVENT = EventFactory.createArrayBacked(PlayerTickEvent.class,
            (listeners) -> (player) -> {
                for (PlayerTickEvent listener : listeners) {
                    listener.tick(player);
                }
            }
    );

    void tick(PlayerEntity player);
}

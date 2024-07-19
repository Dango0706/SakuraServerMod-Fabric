package me.tuanzi.events.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;

public interface PlayerTickEvent {

    Event<PlayerTickEvent> EVENT = EventFactory.createArrayBacked(PlayerTickEvent.class,
            (listeners) -> (player) -> {
                for (PlayerTickEvent tickEvent : listeners){
                    tickEvent.onPlayerTick(player);
                }
            }
    );

    void onPlayerTick(PlayerEntity player);

}

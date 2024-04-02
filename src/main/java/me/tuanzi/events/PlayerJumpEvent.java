package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerJumpEvent {

    Event<PlayerJumpEvent> EVENT = EventFactory.createArrayBacked(PlayerJumpEvent.class,
            (listeners) -> (player) -> {
                for (PlayerJumpEvent listener : listeners) {
                    ActionResult result = listener.interact(player);

                    if (result != ActionResult.PASS) {
                        return result;
                    }

                }
                return ActionResult.PASS;
            }
    );

    ActionResult interact(PlayerEntity player);
}

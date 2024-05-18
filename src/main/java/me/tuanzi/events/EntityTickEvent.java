package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;

public interface EntityTickEvent {

    Event<EntityTickEvent> EVENT = EventFactory.createArrayBacked(EntityTickEvent.class,
            (listeners) -> (entity) -> {
                for (EntityTickEvent listener : listeners) {
                    listener.tick(entity);
                }
            }
    );

    void tick(Entity entity);

}

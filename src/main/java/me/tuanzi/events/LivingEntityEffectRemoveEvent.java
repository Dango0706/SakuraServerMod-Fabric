package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public interface LivingEntityEffectRemoveEvent {

    Event<LivingEntityEffectRemoveEvent> EVENT = EventFactory.createArrayBacked(LivingEntityEffectRemoveEvent.class,
            (listeners) -> (entity, effect) -> {
                for (LivingEntityEffectRemoveEvent listener : listeners) {
                    listener.remove(entity, effect);
                }
            }
    );

    void remove(LivingEntity entity, StatusEffectInstance effectInstance);
}

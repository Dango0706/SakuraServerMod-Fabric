package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

/*
*
*   true则取消该时间.
*
* */
public interface LivingEntityHealEvent {
    Event<LivingEntityHealEvent> EVENT = EventFactory.createArrayBacked(LivingEntityHealEvent.class,
            (listeners) -> (entity, amount) -> {
                for (LivingEntityHealEvent listener : listeners) {
                    return listener.heal(entity, amount);
                }
                return false;
            }
    );

    boolean heal(LivingEntity entity, float amount);
}

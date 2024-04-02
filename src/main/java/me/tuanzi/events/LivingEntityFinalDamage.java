package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

/*
 * 计算盔甲与药水效果后最终造成的伤害.
 *
 *
 * */
public interface LivingEntityFinalDamage {

    Event<LivingEntityFinalDamage> EVENT = EventFactory.createArrayBacked(LivingEntityFinalDamage.class
            , (listeners) -> (livingEntity, source, amount) -> {
                for (LivingEntityFinalDamage listener : listeners) {
                    listener.applyDamage(livingEntity, source, amount);
                }
            });

    void applyDamage(LivingEntity livingEntity, DamageSource source, float amount);


}

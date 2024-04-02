package me.tuanzi.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

public class LivingEntityModifyAppliedDamage {

    public static final Event<LivingEntityModifyAppliedDamage.BEFORE_EFFECT> EFFECT = EventFactory.createArrayBacked(LivingEntityModifyAppliedDamage.BEFORE_EFFECT.class
            , (listeners) -> (livingEntity, source, damage/*, effectLevel*/) -> {
                for (LivingEntityModifyAppliedDamage.BEFORE_EFFECT listener : listeners) {
                    listener.modifyAppliedDamageEffect(livingEntity, source, damage/*, effectLevel*/);
                }
            }
    );
    public static final Event<LivingEntityModifyAppliedDamage.BEFORE_ENCHANTMENT> PROTECTION = EventFactory.createArrayBacked(LivingEntityModifyAppliedDamage.BEFORE_ENCHANTMENT.class
            , (listeners) -> (livingEntity, source, damage/*, protectionLevel*/) -> {
                for (LivingEntityModifyAppliedDamage.BEFORE_ENCHANTMENT listener : listeners) {
                    listener.modifyAppliedDamageProtection(livingEntity, source, damage/*, protectionLevel*/);
                }
            }
    );

    public interface BEFORE_EFFECT {
        void modifyAppliedDamageEffect(LivingEntity livingEntity, DamageSource source, float amount/*, int effectLevel*/);
    }

    public interface BEFORE_ENCHANTMENT {
        void modifyAppliedDamageProtection(LivingEntity livingEntity, DamageSource source, float amount/*, int protectionLevel*/);
    }



}

package me.tuanzi.utils;

import me.tuanzi.Test;
import me.tuanzi.blocks.lift.LiftEvent;
import me.tuanzi.enchantments.events.SoulBoundEvent;
import me.tuanzi.enchantments.events.VeinMineEvent;
import me.tuanzi.events.LivingEntityFinalDamage;
import me.tuanzi.events.LivingEntityModifyAppliedDamage;
import me.tuanzi.events.PlayerJumpEvent;
import me.tuanzi.events.PlayerTickEvent;
import me.tuanzi.stats.DamageStats;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class EventRegister {
    public EventRegister() {
        //item

        //block
        PlayerJumpEvent.EVENT.register(new LiftEvent());
        PlayerTickEvent.EVENT.register(new LiftEvent());
        //enchantment
        //灵魂绑定
        ServerPlayerEvents.AFTER_RESPAWN.register(new SoulBoundEvent());
        ServerLivingEntityEvents.ALLOW_DEATH.register(new SoulBoundEvent());
        //连锁
        PlayerBlockBreakEvents.AFTER.register(new VeinMineEvent());
        //stats
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new DamageStats());

        //test
        LivingEntityFinalDamage.EVENT.register(new Test());
        LivingEntityModifyAppliedDamage.EFFECT.register(new Test());
        LivingEntityModifyAppliedDamage.PROTECTION.register(new Test());
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new Test());
    }
}

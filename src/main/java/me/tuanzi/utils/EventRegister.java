package me.tuanzi.utils;

import me.tuanzi.Test;
import me.tuanzi.blocks.lift.LiftEvent;
import me.tuanzi.effects.events.EffectsEvents;
import me.tuanzi.enchantments.events.SoulBoundEvent;
import me.tuanzi.enchantments.events.VeinMineEvent;
import me.tuanzi.events.*;
import me.tuanzi.fix.events.BlockBreak;
import me.tuanzi.items.events.FunctionalItemEvents;
import me.tuanzi.stats.DamageStats;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.loader.api.FabricLoader;

public class EventRegister {
    public EventRegister() {
        //item
        UseItemCallback.EVENT.register(new FunctionalItemEvents());
        ServerLivingEntityEvents.AFTER_DEATH.register(new FunctionalItemEvents());
        //block
        PlayerJumpEvent.EVENT.register(new LiftEvent());
        PlayerTickEvent.EVENT.register(new LiftEvent());
        //effect
        LivingEntityHealEvent.EVENT.register(new EffectsEvents());
        LivingEntityEffectRemoveEvent.EVENT.register(new EffectsEvents());
        //enchantment
        //灵魂绑定
        ServerPlayerEvents.AFTER_RESPAWN.register(new SoulBoundEvent());
        ServerLivingEntityEvents.ALLOW_DEATH.register(new SoulBoundEvent());

        //连锁
        PlayerBlockBreakEvents.AFTER.register(new VeinMineEvent());
        //stats
        ServerLivingEntityEvents.ALLOW_DAMAGE.register(new DamageStats());
        //fix
        AttackBlockCallback.EVENT.register(new BlockBreak());

        //test
        if(FabricLoader.getInstance().isDevelopmentEnvironment()){
            LivingEntityFinalDamage.EVENT.register(new Test());
            LivingEntityModifyAppliedDamage.EFFECT.register(new Test());
            LivingEntityModifyAppliedDamage.PROTECTION.register(new Test());
            ServerLivingEntityEvents.ALLOW_DAMAGE.register(new Test());
        }
    }
}

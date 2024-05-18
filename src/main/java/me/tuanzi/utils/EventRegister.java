package me.tuanzi.utils;

import me.tuanzi.Test;
import me.tuanzi.bedrock.PlayerJoinEvent;
import me.tuanzi.blocks.lift.LiftEvent;
import me.tuanzi.effects.events.EffectsEvents;
import me.tuanzi.enchantments.events.SoulBoundEvent;
import me.tuanzi.enchantments.events.VeinMineEvent;
import me.tuanzi.events.*;
import me.tuanzi.features.events.BlockBreak;
import me.tuanzi.features.events.PlayerDeath;
import me.tuanzi.items.events.FunctionalItemEvents;
import me.tuanzi.items.events.ToolItemEvents;
import me.tuanzi.stats.events.DamageStats;
import me.tuanzi.stats.events.PlayerBreakBlock;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;

public class EventRegister {
    public EventRegister() {
        //item
        UseItemCallback.EVENT.register(new FunctionalItemEvents());
        ServerLivingEntityEvents.AFTER_DEATH.register(new FunctionalItemEvents());
        PlayerTickEvent.EVENT.register(new FunctionalItemEvents());
        EntityTickEvent.EVENT.register(new FunctionalItemEvents());
        PlayerBlockBreakEvents.BEFORE.register(new ToolItemEvents());
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
        PlayerBlockBreakEvents.AFTER.register(new PlayerBreakBlock());
        PlayerTickEvent.EVENT.register(new PlayerBreakBlock());
        //feature
        AttackBlockCallback.EVENT.register(new BlockBreak());
        ServerLivingEntityEvents.AFTER_DEATH.register(new PlayerDeath());
        //geyser
        ServerPlayConnectionEvents.JOIN.register(new PlayerJoinEvent());
        //test
        if(FabricLoader.getInstance().isDevelopmentEnvironment()){
            LivingEntityFinalDamage.EVENT.register(new Test());
            LivingEntityModifyAppliedDamage.EFFECT.register(new Test());
            LivingEntityModifyAppliedDamage.PROTECTION.register(new Test());
            ServerLivingEntityEvents.ALLOW_DAMAGE.register(new Test());
            PlayerTickEvent.EVENT.register(new Test());
        }
    }
}

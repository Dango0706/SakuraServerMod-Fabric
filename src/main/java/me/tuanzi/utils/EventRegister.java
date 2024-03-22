package me.tuanzi.utils;

import me.tuanzi.enchantments.events.SoulBoundEvent;
import me.tuanzi.enchantments.events.VeinMineEvent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public class EventRegister {
    public EventRegister() {
        //灵魂绑定
        ServerPlayerEvents.AFTER_RESPAWN.register(new SoulBoundEvent());
        ServerLivingEntityEvents.ALLOW_DEATH.register(new SoulBoundEvent());
        //连锁
        PlayerBlockBreakEvents.AFTER.register(new VeinMineEvent());
    }
}

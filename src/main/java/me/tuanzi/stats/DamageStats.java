package me.tuanzi.stats;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;

import static me.tuanzi.SakuraServer.HIGHEST_DAMAGE;
import static me.tuanzi.SakuraServer.HIGHEST_HURT;

public class DamageStats implements ServerLivingEntityEvents.AllowDamage {

    /**
     * Called when a living entity is going to take damage. Can be used to cancel the damage entirely.
     *
     * <p>The amount corresponds to the "incoming" damage amount, before armor and other mitigations have been applied.
     *
     * @param entity the entity
     * @param source the source of the damage
     * @param amount the amount of damage that the entity will take (before mitigations)
     * @return true if the damage should go ahead, false to cancel the damage.
     */
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {

        if (entity instanceof ServerPlayerEntity player) {
            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT)) < Math.round(amount * 10.0F)) {
//                player.increaseStat(HIGHEST_HURT,Math.round(amount * 10.0F));
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT), Math.round(amount * 10.0F));
            }
        }
        if (source.getAttacker() instanceof ServerPlayerEntity player) {
            if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE)) < Math.round(amount * 10.0F)) {
                player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE), Math.round(amount * 10.0F));
            }
        }

        return true;
    }
}

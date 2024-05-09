package me.tuanzi.stats.events;

import me.tuanzi.stats.StateSaverAndLoader;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

import static me.tuanzi.SakuraServer.*;

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
        World world = entity.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(serverWorld.getServer());
            if (entity instanceof ServerPlayerEntity player) {
                serverState.totalDamageTaken += amount;
                player.getStatHandler().increaseStat(player, Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_TAKEN), Math.round(amount * 10.0F));
                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT)) < Math.round(amount * 10.0F)) {
                    player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(HIGHEST_HURT), Math.round(amount * 10.0F));
                }
            }
            if (source.getAttacker() instanceof ServerPlayerEntity player) {
                serverState.totalDamageCaused += amount;
                player.getStatHandler().increaseStat(player, Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_CAUSED), Math.round(amount * 10.0F));
                if (entity instanceof ServerPlayerEntity serverPlayer) {
                    player.getStatHandler().increaseStat(player, Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_CAUSED), Math.round(amount * 10.0F));
                    serverState.totalPlayerDamageTaken += amount;
                    serverState.totalPlayerDamageCaused += amount;
                    serverPlayer.getStatHandler().increaseStat(serverPlayer, Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_TAKEN), Math.round(amount * 10.0F));
                }
                if (player.getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE)) < Math.round(amount * 10.0F)) {
                    player.getStatHandler().setStat(player, Stats.CUSTOM.getOrCreateStat(HIGHEST_DAMAGE), Math.round(amount * 10.0F));
                }
            }
        }


        return true;
    }
}

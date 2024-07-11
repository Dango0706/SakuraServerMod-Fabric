package me.tuanzi.stats.events;

import me.tuanzi.events.PlayerTickEvent;
import me.tuanzi.stats.StateSaverAndLoader;
import me.tuanzi.utils.LivingEntityCustomNbt;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static me.tuanzi.utils.StatsRegisty.BREAK_BLOCK_COUNT;
import static me.tuanzi.utils.StatsRegisty.TOTAL_ONLINE_TIME;


public class PlayerBreakBlock implements PlayerBlockBreakEvents.After, PlayerTickEvent  {

    /**
     * Called after a block is successfully broken.
     *
     * @param world       the world where the block was broken
     * @param player      the player who broke the block
     * @param pos         the position where the block was broken
     * @param state       the block state <strong>before</strong> the block was broken
     * @param blockEntity the block entity of the broken block, can be {@code null}
     */
    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (world instanceof ServerWorld serverWorld) {
            player.increaseStat(BREAK_BLOCK_COUNT, 1);
            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(serverWorld.getServer());
            serverState.totalBreakBlock += 1L;
        }

    }

    @Override
    public void tick(PlayerEntity player) {
        World world = player.getWorld();
        if (world instanceof ServerWorld serverWorld) {
            LivingEntityCustomNbt livingEntityCustomNbt = (LivingEntityCustomNbt) player;
            StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(serverWorld.getServer());
            NbtCompound nbtCompound = livingEntityCustomNbt.customNbt();
            nbtCompound.putInt("onlineTick", nbtCompound.getInt("onlineTick") + 1);
            if (nbtCompound.getInt("onlineTick") >= 1200) {
                nbtCompound.putInt("onlineTick", 0);
                serverState.totalOnlineTime += 1L;
                player.increaseStat(TOTAL_ONLINE_TIME, 1);
            }

        }
    }
}

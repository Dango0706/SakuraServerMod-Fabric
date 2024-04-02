package me.tuanzi.blocks.lift;

import me.tuanzi.events.PlayerJumpEvent;
import me.tuanzi.events.PlayerTickEvent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import static me.tuanzi.SakuraServer.*;

public class LiftEvent implements PlayerJumpEvent, PlayerTickEvent {

    @Override
    public ActionResult interact(PlayerEntity player) {
        if (!player.getWorld().isClient) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            //up
            int x = serverPlayer.getBlockX();
            int y = serverPlayer.getBlockY();
            int z = serverPlayer.getBlockZ();
            ServerWorld world = serverPlayer.getServerWorld();
            if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == LIFT && !serverPlayer.hasStatusEffect(LIFT_CD)) {
                for (int i = 2; i < 100; i++) {
                    if (world.getBlockState(new BlockPos(x, y + i, z)).getBlock() == LIFT) {
                        serverPlayer.teleport(world, serverPlayer.getX(), y + 1 + i, serverPlayer.getZ(), serverPlayer.getYaw(), serverPlayer.getPitch());
                        player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1, 1);
                        world.playSound(player, x, y + 1 + i, z, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1, 1);
                        serverPlayer.addStatusEffect(new StatusEffectInstance(LIFT_CD, 20, 0));
                        player.increaseStat(LIFT_ALL,1);
                        player.increaseStat(LIFT_UP,1);
                        return ActionResult.PASS;
                    }
                }
            }
        }

        return ActionResult.PASS;
    }

    @Override
    public void tick(PlayerEntity player) {
        if (player.isSneaky() && !player.getWorld().isClient) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            //down
            int x = serverPlayer.getBlockX();
            int y = serverPlayer.getBlockY();
            int z = serverPlayer.getBlockZ();
            ServerWorld world = serverPlayer.getServerWorld();
            if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == LIFT && !serverPlayer.hasStatusEffect(LIFT_CD)) {
                for (int i = 2; i < 100; i++) {
                    if (world.getBlockState(new BlockPos(x, y - i, z)).getBlock() == LIFT) {
                        serverPlayer.teleport(world, serverPlayer.getX(), y + 1 - i, serverPlayer.getZ(), serverPlayer.getYaw(), serverPlayer.getPitch());
                        player.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1, 1);
                        world.playSound(player, x, y + 1 - i, z, SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.BLOCKS, 1, 1);
                        serverPlayer.addStatusEffect(new StatusEffectInstance(LIFT_CD, 20, 0));
                        player.increaseStat(LIFT_ALL,1);
                        player.increaseStat(LIFT_DOWN,1);
                        return;
                    }
                }
            }
        }
    }
}

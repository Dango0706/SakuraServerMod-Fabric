package me.tuanzi.bedrock;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.geyser.api.GeyserApi;

import static me.tuanzi.SakuraServer.*;
import static me.tuanzi.bedrock.BedrockPlayerUtils.BEDROCK_UUIDS;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class PlayerJoinEvent implements ServerPlayConnectionEvents.Join {

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        ServerPlayerEntity serverPlayer = handler.getPlayer();
        if (hasGeyser) {
            if (FloodgateApi.getInstance().isFloodgatePlayer(serverPlayer.getUuid()) || GeyserApi.api().isBedrockPlayer(serverPlayer.getUuid())) {
                printDebugLog("是手机版玩家!!");
                BEDROCK_UUIDS.add(serverPlayer.getUuid());
            }
        }

        printDebugLog(serverPlayer.getUuid().toString());

    }
}

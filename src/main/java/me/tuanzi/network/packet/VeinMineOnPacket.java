package me.tuanzi.network.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class VeinMineOnPacket {

    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
//        player.sendMessage(Text.literal("连锁挖矿启动中."));
//        System.out.println(buf.getBoolean(0));
        if (buf.getBoolean(0)) {
            player.addCommandTag("VeinMineOn");
        } else {
            player.removeCommandTag("VeinMineOn");
        }

    }

}

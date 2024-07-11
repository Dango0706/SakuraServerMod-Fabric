package me.tuanzi.network;

import me.tuanzi.network.packet.VeinMineOnPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class C2SChannel {

    public static final Identifier VEIN_MINE_ON_ID = new Identifier(MODID,"vein_mine_on");
//    public static final Identifier VEIN_MINE_OFF_ID = new Identifier(MODID,"vein_mine");

    public static void registerC2SPackets(){
        printDebugLog("加载Client To Server网络包...");
        ServerPlayNetworking.registerGlobalReceiver(VEIN_MINE_ON_ID, VeinMineOnPacket::receive);
//        ServerPlayNetworking.registerGlobalReceiver(VEIN_MINE_OFF_ID, VeinMineOffPacket::receive);
    }


}

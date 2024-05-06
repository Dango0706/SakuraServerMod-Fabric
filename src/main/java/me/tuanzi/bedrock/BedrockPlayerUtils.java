package me.tuanzi.bedrock;

import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.UUID;

public class BedrockPlayerUtils {

    public static ArrayList<UUID> BEDROCK_UUIDS = new ArrayList<>();

    public static boolean isBedrockPlayer(PlayerEntity player) {
        return BEDROCK_UUIDS.contains(player.getUuid());
    }

}

package me.tuanzi.key.vein_mine;

import me.tuanzi.network.C2SChannel;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

import static me.tuanzi.SakuraServer.MODID;

public class KeyInputHandler {

    public static final String KEY_CATEGORY = "key.category." + MODID;
    public static final String VEIN_MINE_KEY = "key." + MODID + ".vein_mine";

    public static KeyBinding veinmineKey;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null)
                return;

            if (veinmineKey.isPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeBoolean(true);
                ClientPlayNetworking.send(C2SChannel.VEIN_MINE_ON_ID, buf);
/*                if(!OnUUID.contains(client.player.getUuid()))
                    OnUUID.add(client.player.getUuid());*/
            } else {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeBoolean(false);
                ClientPlayNetworking.send(C2SChannel.VEIN_MINE_ON_ID, buf);
                /*OnUUID.remove(client.player.getUuid());*/
            }
        });
    }

    public static void register() {
        veinmineKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                VEIN_MINE_KEY,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_APOSTROPHE,
                KEY_CATEGORY
        ));

        registerKeyInputs();

    }


}

package me.tuanzi.utils;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class SoundRegistry {
    //sounds
    public static final Identifier DRAGON_ROAR = new Identifier(MODID, "dragon_roar");
    public static SoundEvent DRAGON_ROAR_SOUND = SoundEvent.of(DRAGON_ROAR);
    public static final Identifier MAGNET_USE = new Identifier(MODID, "magnet");
    public static SoundEvent MAGNET_USE_SOUND = SoundEvent.of(MAGNET_USE);

    public SoundRegistry() {
        printDebugLog("加载声音...");
        Registry.register(Registries.SOUND_EVENT, DRAGON_ROAR, DRAGON_ROAR_SOUND);
        Registry.register(Registries.SOUND_EVENT, MAGNET_USE, MAGNET_USE_SOUND);
    }
}

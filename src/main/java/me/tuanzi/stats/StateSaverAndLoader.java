package me.tuanzi.stats;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.World;

import static me.tuanzi.SakuraServer.MODID;

public class StateSaverAndLoader extends PersistentState {

    private static final Type<StateSaverAndLoader> type = new Type<>(
            StateSaverAndLoader::new,
            StateSaverAndLoader::createFromNbt,
            null
    );
    public Long totalBreakBlock = 0L;
    public Long totalPlaceBlock = 0L;
    public Long totalOnlineTime = 0L;
    public Double totalDamageTaken = 0.0;
    public Double totalDamageCaused = 0.0;
    public Double totalPlayerDamageTaken = 0.0;
    public Double totalPlayerDamageCaused = 0.0;


    public static StateSaverAndLoader createFromNbt(NbtCompound tag) {
        StateSaverAndLoader state = new StateSaverAndLoader();
        state.totalBreakBlock = tag.getLong("totalBreakBlock");
        state.totalPlaceBlock = tag.getLong("totalPlaceBlock");
        state.totalOnlineTime = tag.getLong("totalOnlineTime");
        state.totalDamageTaken = tag.getDouble("totalDamageTaken");
        state.totalDamageCaused = tag.getDouble("totalDamageCaused");
        state.totalPlayerDamageTaken = tag.getDouble("totalPlayerDamageTaken");
        state.totalPlayerDamageCaused = tag.getDouble("totalPlayerDamageCaused");
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putLong("totalBreakBlock", totalBreakBlock);
        nbt.putLong("totalPlaceBlock", totalPlaceBlock);
        nbt.putLong("totalOnlineTime", totalOnlineTime);
        nbt.putDouble("totalDamageTaken", totalDamageTaken);
        nbt.putDouble("totalDamageCaused", totalDamageCaused);
        nbt.putDouble("totalPlayerDamageTaken", totalPlayerDamageTaken);
        nbt.putDouble("totalPlayerDamageCaused", totalPlayerDamageCaused);
        return nbt;
    }

    public static StateSaverAndLoader getServerState(MinecraftServer server) {
        // (Note: arbitrary choice to use 'World.OVERWORLD' instead of 'World.END' or 'World.NETHER'.  Any work)
        PersistentStateManager persistentStateManager = server.getWorld(World.OVERWORLD).getPersistentStateManager();

        // The first time the following 'getOrCreate' function is called, it creates a brand new 'StateSaverAndLoader' and
        // stores it inside the 'PersistentStateManager'. The subsequent calls to 'getOrCreate' pass in the saved
        // 'StateSaverAndLoader' NBT on disk to our function 'StateSaverAndLoader::createFromNbt'.
        StateSaverAndLoader state = persistentStateManager.getOrCreate(type, MODID);

        // If state is not marked dirty, when Minecraft closes, 'writeNbt' won't be called and therefore nothing will be saved.
        // Technically it's 'cleaner' if you only mark state as dirty when there was actually a change, but the vast majority
        // of mod writers are just going to be confused when their data isn't being saved, and so it's best just to 'markDirty' for them.
        // Besides, it's literally just setting a bool to true, and the only time there's a 'cost' is when the file is written to disk when
        // there were no actual change to any of the mods state (INCREDIBLY RARE).
        state.markDirty();
        return state;
    }

}

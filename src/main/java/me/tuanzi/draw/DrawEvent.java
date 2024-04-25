package me.tuanzi.draw;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

public abstract class DrawEvent {

    private final int weight;

    public DrawEvent(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    abstract public Boolean isUp();
    abstract public DrawRarity getRarity();
    abstract public void event(ServerWorld world, PlayerEntity player);


}

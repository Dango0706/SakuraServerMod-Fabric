package me.tuanzi.draw.events;

import me.tuanzi.draw.DrawEvent;
import me.tuanzi.draw.DrawRarity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;

public class DropDiamond extends DrawEvent {

    public DropDiamond() {
        super(5);
    }

    @Override
    public Boolean isUp() {
        return false;
    }

    @Override
    public DrawRarity getRarity() {
        return DrawRarity.NORMAL;
    }

    @Override
    public void event(ServerWorld world, PlayerEntity player) {
            world.spawnEntity(new ItemEntity(world,player.getBlockX(),player.getY(),player.getZ(),new ItemStack(Items.DIAMOND,54)));

    }
}

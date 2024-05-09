package me.tuanzi.features.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;

public class PlayerDeath implements ServerLivingEntityEvents.AfterDeath {

    /**
     * Called when a living entity dies. The death cannot be canceled at this point.
     *
     * @param entity       the entity
     * @param damageSource the source of the fatal damage
     */
    @Override
    public void afterDeath(LivingEntity entity, DamageSource damageSource) {
        if (entity instanceof PlayerEntity player && player.getRandom().nextInt(100) < 20) {
            //todo:将原版base64加密解密赋值在nbt上.
            ItemStack itemStack = new ItemStack(Items.PLAYER_HEAD);
            NbtCompound nbtCompound = new NbtCompound();
            NbtCompound nbtCompound1 = new NbtCompound();
            nbtCompound1.putString("Name", player.getName().getString());
            nbtCompound1.putUuid("Id", player.getUuid());
            nbtCompound.put("SkullOwner", nbtCompound1);
            itemStack.setNbt(nbtCompound);
            ItemEntity item = new ItemEntity(player.getWorld(), player.getX(), player.getY(), player.getZ(), itemStack);
            player.getWorld().spawnEntity(item);
        }
    }
}

package me.tuanzi.items.display;

import me.tuanzi.items.utils.SakuraItem;
import me.tuanzi.utils.LivingEntityCustomNbt;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.joml.Vector3f;

import static me.tuanzi.items.ItemsRegisty.*;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class DrawSomething extends SakuraItem {
    public static final String DRAW_RARITY = "DrawRarity";
    public static final String DRAW_RESULT = "DrawResult";

    public DrawSomething(Settings settings) {
        super(settings);
    }

    @Override
    public void itemEntityTick(ItemEntity itemEntity, World world, ItemStack itemStack) {
        if (world instanceof ServerWorld serverWorld) {
            //缓缓漂浮
            if(itemEntity.getVelocity().x !=0 || itemEntity.getVelocity().z !=0)
                itemEntity.setVelocity(0,itemEntity.getVelocity().y,0);
            itemEntity.addVelocity(0, 0.043, 0);
            itemEntity.velocityModified = true;
            LivingEntityCustomNbt customNbt1 = (LivingEntityCustomNbt) itemEntity;
            NbtCompound nbtCompound = customNbt1.customNbt();
            int rarity = nbtCompound.getInt(DRAW_RARITY);
            // 获取或初始化 tick 计数器
            int tickCount = nbtCompound.getInt("TickCounter");
            int tickCount2 = nbtCompound.getInt("TickCounter2");
            // 递增 tick 计数器
            tickCount++;
            tickCount2++;

            if (tickCount >= 5) {
                ParticleEffect particleEffect = new DustParticleEffect(new Vector3f(1, 1, 1), 1);
                serverWorld.spawnParticles(particleEffect, itemEntity.getX(), itemEntity.getY() + 0.5, itemEntity.getZ(), 300, 1.3, 0.5, 1.3, 0.02);
                serverWorld.playSound(null, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.3f, (1.0f + rarity / 10.0f));
                tickCount = 0;
            }
            //45tick后 显示出什么品质.
            if (tickCount2 >= 45) {
                if (rarity == 1) {
                    itemEntity.setStack(new ItemStack(DRAW_BLUE_SOMETHING));
                }
                if (rarity == 2) {
                    itemEntity.setStack(new ItemStack(DRAW_PURPLE_SOMETHING));
                }
                if (rarity == 3) {
                    itemEntity.setStack(new ItemStack(DRAW_GOLDEN_SOMETHING));
                }
                if (rarity == 0) {
                    printDebugLog("错误,抽卡失败.给定参数值错误.");
                    itemEntity.kill();
                }
                serverWorld.playSound(null, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, 1.0f, (1.0f + rarity / 10.0f));
                tickCount2 = 0;

                /*                customNbt1 = (LivingEntityCustomNbt) itemEntity;
                NbtCompound nbtCompound = new NbtCompound();
                result.writeNbt(nbtCompound);
                customNbt1.customNbt().put(DRAW_RESULT,nbtCompound);*/
            }
            nbtCompound.putInt("TickCounter",tickCount);
            nbtCompound.putInt("TickCounter2",tickCount2);
        }
    }
}

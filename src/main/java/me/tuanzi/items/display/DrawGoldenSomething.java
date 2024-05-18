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

import static me.tuanzi.items.display.DrawSomething.DRAW_RESULT;

public class DrawGoldenSomething extends SakuraItem {

    public DrawGoldenSomething(Settings settings) {
        super(settings);
    }

    @Override
    public void itemEntityTick(ItemEntity itemEntity, World world, ItemStack itemStack) {
        if (world instanceof ServerWorld serverWorld) {
            LivingEntityCustomNbt customNbt1 = (LivingEntityCustomNbt) itemEntity;
            NbtCompound nbtCompound = customNbt1.customNbt();
            // 获取或初始化 tick 计数器
            int tickCount = nbtCompound.getInt("TickCounter");
            int tickCount2 = nbtCompound.getInt("TickCounter2");
            // 递增 tick 计数器
            tickCount++;
            tickCount2++;
            //缓缓漂浮
            itemEntity.addVelocity(0, 0.043, 0);
            itemEntity.velocityModified = true;
            if (tickCount >= 5) {
                tickCount = 0;
                ParticleEffect particleEffect = new DustParticleEffect(new Vector3f(1, 219.0f / 255, 30.0f / 255), 1);
                serverWorld.spawnParticles(particleEffect, itemEntity.getX(), itemEntity.getY() + 0.5, itemEntity.getZ(), 300, 1.3, 0.5, 1.3, 0.02);
            }
            if (tickCount2 >= 30) {
                tickCount2 = 0;
                ItemStack result = ItemStack.fromNbt(customNbt1.customNbt().getCompound(DRAW_RESULT));
                itemEntity.setStack(result);
                itemEntity.setPickupDelay(0);
                serverWorld.playSound(null, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), SoundEvents.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, SoundCategory.PLAYERS, 1.0f, 1.0f);
            }
            nbtCompound.putInt("TickCounter", tickCount);
            nbtCompound.putInt("TickCounter2", tickCount2);
        }
    }
}

package me.tuanzi.items.display;

import me.tuanzi.items.utils.SakuraItem;
import me.tuanzi.utils.LivingEntityCustomNbt;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class DrawPurpleSomething extends SakuraItem {
    public DrawPurpleSomething(Settings settings) {
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
                ParticleEffect particleEffect = new DustParticleEffect(new Vector3f(1, 0, 1), 1);
                serverWorld.spawnParticles(particleEffect, itemEntity.getX(), itemEntity.getY() + 0.5, itemEntity.getZ(), 300, 1.3, 0.5, 1.3, 0.02);
            }
            DrawBlueSomething.getItemStack(itemEntity, serverWorld, customNbt1, nbtCompound, tickCount, tickCount2);

        }
    }

}

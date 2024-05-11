package me.tuanzi.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.ChestType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockUtils {

    public static List<BlockEntity> getBlockEntities(World world, Box box) {
        List<BlockEntity> blockEntities = new ArrayList<>();
        for (int x = (int) box.minX; x < box.maxX; x++) {
            for (int y = (int) box.minY; y < box.maxY; y++) {
                for (int z = (int) box.minZ; z < box.maxZ; z++) {
                    BlockEntity blockEntity = world.getBlockEntity(new BlockPos(x, y, z));
                    if (blockEntity != null) {
                        blockEntities.add(blockEntity);
                    }
                }
            }
        }
        return blockEntities;
    }

    public static List<? extends BlockEntity> getBlockEntities(World world, Box box, BlockEntityType<? extends BlockEntity> blockEntityType) {
        List<BlockEntity> blockEntities = new ArrayList<>();
        for (int x = (int) box.minX; x < box.maxX; x++) {
            for (int y = (int) box.minY; y < box.maxY; y++) {
                for (int z = (int) box.minZ; z < box.maxZ; z++) {
                    Optional<? extends BlockEntity> blockEntity = world.getBlockEntity(new BlockPos(x, y, z), blockEntityType);
                    blockEntity.ifPresent(blockEntities::add);
                }
            }
        }
        return blockEntities;
    }

    @Nullable
    public static BlockPos getAnotherChest(World world, BlockPos pos) {
        BlockPos pos1 = pos;
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getBlock() instanceof ChestBlock) {
            ChestType chestType = blockState.get(Properties.CHEST_TYPE);
            Direction face = blockState.get(HorizontalFacingBlock.FACING);
            if (chestType != ChestType.SINGLE) {
                /*
                 * 北右: x-
                 * 北左: x+
                 * 南右: x+
                 * 南左: x-
                 * 西右: z+
                 * 西左: z-
                 * 东右: z-
                 * 东左: z+
                 * */
                if (chestType == ChestType.LEFT) {
                    if (face == Direction.NORTH) {
                        pos1 = pos.add(Direction.EAST.getVector());
                    }
                    if (face == Direction.SOUTH) {
                        pos1 = pos.add(Direction.WEST.getVector());
                    }
                    if (face == Direction.WEST) {
                        pos1 = pos.add(Direction.SOUTH.getVector());
                    }
                    if (face == Direction.EAST) {
                        pos1 = pos.add(Direction.NORTH.getVector());
                    }
                } else {
                    if (face == Direction.NORTH) {
                        pos1 = pos.add(Direction.WEST.getVector());
                    }
                    if (face == Direction.SOUTH) {
                        pos1 = pos.add(Direction.EAST.getVector());
                    }
                    if (face == Direction.WEST) {
                        pos1 = pos.add(Direction.NORTH.getVector());
                    }
                    if (face == Direction.EAST) {
                        pos1 = pos.add(Direction.SOUTH.getVector());
                    }
                }
                return pos1;
            }
        }
        return null;
    }

    public static void displayRange(Box box, ServerWorld world, ParticleEffect particleEffect) {
        // 假设 box 是您的立方体对象，world 是您的世界对象，block 是您想放置的方块类型
        int minX = (int) box.minX, minY = (int) box.minY, minZ = (int) box.minZ;
        int maxX = (int) box.maxX, maxY = (int) box.maxY, maxZ = (int) box.maxZ;
        // 放置底部和顶部的边框
        for (int x = minX; x <= maxX; x++) {
            world.spawnParticles(particleEffect, x, minY, minZ, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, x, minY, maxZ, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, x, maxY, minZ, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, x, maxY, maxZ, 1, 0, 0, 0, 0.01);
        }
        // 放置四个侧面的边框
        for (int y = minY; y <= maxY; y++) {
            world.spawnParticles(particleEffect, minX, y, minZ, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, maxX, y, minZ, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, minX, y, maxZ, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, maxX, y, maxZ, 1, 0, 0, 0, 0.01);
        }
        for (int z = minZ; z <= maxZ; z++) {
            world.spawnParticles(particleEffect, minX, minY, z, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, maxX, minY, z, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, minX, maxY, z, 1, 0, 0, 0, 0.01);
            world.spawnParticles(particleEffect, maxX, maxY, z, 1, 0, 0, 0, 0.01);
        }


        /*for (int x = (int) box.minX; x <= box.maxX; x++) {
            for (int y = (int) box.minY; y <= box.maxX; y++) {
                for (int z = (int) box.minZ; z <= box.maxZ; z++) {
                    // 检查是否为边框坐标
                    if (x == box.minX || x == box.maxX || y == box.minY || y == box.maxY || z == box.minZ || z == box.maxZ) {
                        // 在边框坐标放置方块
                        world.spawnParticles(particleEffect, x, y, z, 1, 0, 0, 0, 0);
                    }
                }
            }
        }*/
    }

}

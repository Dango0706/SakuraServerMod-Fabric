package me.tuanzi.enchantments.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static me.tuanzi.SakuraServer.MODID;
import static me.tuanzi.SakuraServer.VEIN_MINE;

public class VeinMineEvent implements PlayerBlockBreakEvents.After {

    private final Map<UUID, Boolean> use = new HashMap<>();


    /**
     * 参考:<a href="https://github.com/Enaium-FabricMC/OneKeyMiner/blob/master/modern/_1.20.3/src/main/kotlin/cn/enaium/onekeyminer/util/block.kt">https://github.com/Enaium-FabricMC/OneKeyMiner/blob/master/modern/_1.20.3/src/main/kotlin/cn/enaium/onekeyminer/util/block.kt</a>
     *
     * @param world     the world
     * @param centerPos the center pos
     * @param limit     the limit
     * @param state     the state
     * @return the set
     */
    public static Set<BlockPos> findBlocks(World world, BlockPos centerPos, int limit, BlockState state) {
        // Create a HashSet to hold the BlockPos objects representing found blocks
        Set<BlockPos> blockList = new HashSet<>();
        // Get the name of the block at the center position
        String centerBlockName = state.getBlock().getTranslationKey();
        // Create a LinkedList to function as a queue for the breadth-first search algorithm
        Queue<BlockPos> queue = new LinkedList<>();
        // Create a HashSet to hold visited positions to prevent revisiting them during the search
        Set<BlockPos> visited = new HashSet<>();
        // Add the center position to the queue to start the search
        queue.offer(centerPos);
        // Loop while the queue is not empty and the limit on found blocks has not been reached
        while (!queue.isEmpty() && blockList.size() < limit) {
            // Save the current size of the queue to process all positions added during the previous iteration of the loop
            int queueSize = queue.size();
            // Loop through all positions added during the previous iteration of the loop, and stop if the limit on found blocks has been reached
            for (int i = 0; i < queueSize && blockList.size() < limit; i++) {
                // Retrieve and remove the first position in the queue
                BlockPos currentPos = queue.poll();
                // If the block at the current position matches the center block, add it to the list of found blocks and mark it as visited
                if (queue.size() == 0 || getName(world, currentPos).equals(centerBlockName)) {
                    blockList.add(currentPos);
                    visited.add(currentPos);
                    // Check all adjacent blocks for new positions to search
                    for (int xOffset = -1; xOffset <= 1; xOffset++) {
                        for (int yOffset = -1; yOffset <= 1; yOffset++) {
                            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                                // Ignore the current position and only add unvisited adjacent positions
                                if (xOffset != 0 || yOffset != 0 || zOffset != 0) {
                                    BlockPos adjacentPos = currentPos.add(xOffset, yOffset, zOffset);
                                    if (!visited.contains(adjacentPos)) {
                                        queue.offer(adjacentPos);
                                        visited.add(adjacentPos);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // If the block at the current position does not match the center block, mark it as visited without adding it to the found list
                    visited.add(currentPos);
                }
            }
        }
        // Return the set of found blocks
        return blockList;
    }

    public static String getName(World world, BlockPos pos) {
        // Implement a method to get the name of the block at the given position in the world
        return world.getBlockState(pos).getBlock().getTranslationKey();
    }

    /**
     * Called after a block is successfully broken.
     *
     * @param world       the world where the block was broken
     * @param player1     the player who broke the block
     * @param pos         the position where the block was broken
     * @param state       the block state <strong>before</strong> the block was broken
     * @param blockEntity the block entity of the broken block, can be {@code null}
     */
    @Override
    public void afterBlockBreak(World world, PlayerEntity player1, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (!world.isClient()) {
            ServerPlayerEntity player = (ServerPlayerEntity) player1;
            ServerWorld serverWorld = (ServerWorld) world;
            //如果不包括 则给他添加
            if(!use.containsKey(player.getUuid())){
                use.put(player.getUuid(), true);
            }
            //等级
            if (EnchantmentHelper.getLevel(VEIN_MINE, player.getInventory().getMainHandStack()) > 0) {
                //是否按下
                boolean isPressed = false;
                for (String s : player.getCommandTags()) {
                    if (s.equals("VeinMineOn")) {
                        isPressed = true;
                        break;
                    }
                }
                if (isPressed) {
                    //检查饱食度
                    if (player.getHungerManager().getFoodLevel() > 0 || player.isCreative()) {
                        if (use.get(player.getUuid())) {
                            //避免无限递归导致崩溃.
                            use.put(player.getUuid(), false);
                            veinMine(serverWorld, player, pos, state);
                            use.put(player.getUuid(), true);
                        }
                    }else {
                        player.sendMessage(Text.translatable("enchantment." + MODID + ".vein_mine.need_food"));
                    }
                }
            }
        }

    }

    public boolean veinMine(ServerWorld world, ServerPlayerEntity player, BlockPos pos, BlockState state) {
        //最大数量
        int maxCount = EnchantmentHelper.getLevel(VEIN_MINE,player.getMainHandStack()) * 16;
        //需要破坏的方块
        Set<BlockPos> minedBlock = new HashSet<>(findBlocks(world, pos, maxCount, state));
        //破坏数量计数
        int i = 0;
        //try break
        ServerPlayerInteractionManager serverPlayerInteractionManager = new ServerPlayerInteractionManager(player);
        //疲劳度
        float exhaustion = 0;
        //没有可以连锁的,返回false
        if(minedBlock.size() ==0)
            return false;
        for (BlockPos blockPos : minedBlock) {
            //计数
            if (i < maxCount) {
                //检查饱食度
                if (player.getHungerManager().getFoodLevel() > 0 || player.isCreative()) {
                    //不是创造
                    if(!player.isCreative()){
                        //增加疲劳度
                        exhaustion += 0.7f - EnchantmentHelper.getLevel(VEIN_MINE,player.getMainHandStack()) * 0.1f;
                        //疲劳度+原有的>4了
                        while (exhaustion + player.getHungerManager().getExhaustion() >= 4) {
                            //减4
                            exhaustion -= 4;
                            //减饱食度或饱和
                            if (player.getHungerManager().getSaturationLevel() > 0) {
                                player.getHungerManager().setSaturationLevel(player.getHungerManager().getSaturationLevel() - 1);
                            } else {
                                player.getHungerManager().setFoodLevel(player.getHungerManager().getFoodLevel() - 1);
                            }
                        }
                        //<4了那就加上去
                        player.getHungerManager().setExhaustion(exhaustion + player.getHungerManager().getExhaustion());
                    }
                    //破坏方块
                    //如果尝试破坏失败,则返回false.
                    if(!serverPlayerInteractionManager.tryBreakBlock(blockPos))
                        return false;
                    //加一
                    i++;
                }else{
                    //饱食度在挖掘过程中不够了,中断挖掘.
                    player.sendMessage(Text.translatable("enchantment." + MODID + ".vein_mine.need_food"));
                    return false;
                }
            }
        }
        return true;
    }


}

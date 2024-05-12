package me.tuanzi;

import me.tuanzi.events.LivingEntityFinalDamage;
import me.tuanzi.events.LivingEntityModifyAppliedDamage;
import me.tuanzi.events.PlayerTickEvent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static me.tuanzi.SakuraServer.printDebugLog;

public class Test implements PlayerTickEvent, ServerLivingEntityEvents.AllowDamage, LivingEntityModifyAppliedDamage.BEFORE_ENCHANTMENT, LivingEntityModifyAppliedDamage.BEFORE_EFFECT, LivingEntityFinalDamage {


    private Queue<BlockPos> blocksToCheck;
    private List<BlockPos> foundBlocks;
    private int batchSize;
    private Box searchArea;

    public static void main(String[] args) {
        float amount = 10;
        float f;
        float g;
        float h;
        int i;
        int j;
        int level = 2;
        g = amount;
        i = (level + 1) * 5;
        j = 25 - (i);
        f = amount * j;
        amount = Math.max(f / 25.0f, 0.0f);
        h = g - amount;
        /*
        * 4.0
        100.0
        10.0
        6.0
        15
        10
        * */
        System.out.println(amount);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
        System.out.println(i);
        System.out.println(j);
        if ((h = (g = amount) - (amount = Math.max((f = amount * (float) (j = 25 - (i = (level + 1) * 5))) / 25.0f, 0.0f))) > 0.0f) {
/*            System.out.println(amount);
            System.out.println(f);
            System.out.println(g);
            System.out.println(h);
            System.out.println(i);
            System.out.println(j);*/


        }
    }

    /**
     * Called when a living entity is going to take damage. Can be used to cancel the damage entirely.
     *
     * <p>The amount corresponds to the "incoming" damage amount, before armor and other mitigations have been applied.
     *
     * @param entity the entity
     * @param source the source of the damage
     * @param amount the amount of damage that the entity will take (before mitigations)
     * @return true if the damage should go ahead, false to cancel the damage.
     */
    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        printDebugLog("计算前:" + amount);
        return true;
    }

    @Override
    public void modifyAppliedDamageProtection(LivingEntity livingEntity, DamageSource source, float amount) {
        printDebugLog("附魔前:" + amount);
    }

    @Override
    public void modifyAppliedDamageEffect(LivingEntity livingEntity, DamageSource source, float amount) {
        printDebugLog("药水前:" + amount);
    }

    @Override
    public void applyDamage(LivingEntity livingEntity, DamageSource source, float amount) {
        printDebugLog("最终:" + amount);
    }

    @Override
    public void tick(PlayerEntity player) {
        if (!player.getWorld().isClient && player.getMainHandStack().isOf(Items.DIAMOND)) {
            Box area = new Box(player.getBlockPos()).expand(50);

            this.searchArea = area;
            this.batchSize = 2500;
            this.blocksToCheck = new LinkedList<>();
            this.foundBlocks = new ArrayList<>();

            // 初始化blocksToCheck队列
            for (int x = (int) area.minX; x <= area.maxX; x++) {
                for (int y = (int) area.minY; y <= area.maxY; y++) {
                    for (int z = (int) area.minZ; z <= area.maxZ; z++) {
                        blocksToCheck.add(new BlockPos(x, y, z));
                    }
                }
            }

            int processed = 0;
            while (!blocksToCheck.isEmpty() && processed < batchSize) {
                BlockPos current = blocksToCheck.poll();
                // 检查方块逻辑
                if (checkBlock(player.getWorld(), current)) {
                    foundBlocks.add(current);
                }
                processed++;
            }

            if (blocksToCheck.isEmpty()) {
                // 所有方块检查完毕
                for (BlockPos foundBlock : foundBlocks) {
                    System.out.println(foundBlock);
                }
            }

        }
    }

    private boolean checkBlock(World world, BlockPos pos) {
        // 实现你的方块检查逻辑
        // 返回true如果方块是你想要添加到列表中的

        BlockState blockState = world.getBlockState(pos);
        return blockState.isOf(Blocks.CHEST);
    }


}

package me.tuanzi.items.events;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static me.tuanzi.SakuraServer.ANTIMATTER_PICKAXE;
import static me.tuanzi.SakuraServer.GAMBLING_PICKAXE;

public class ToolItemEvents implements PlayerBlockBreakEvents.Before {

    static ArrayList<Item> allItems = new ArrayList<>();

    static {
        for (Item item : Registries.ITEM) {
            allItems.add(item);
        }
    }

    /**
     * Called before a block is broken and allows cancelling the block breaking.
     *
     * <p>Implementations should not modify the world or assume the block break has completed or failed.</p>
     *
     * @param world       the world in which the block is broken
     * @param player      the player breaking the block
     * @param pos         the position at which the block is broken
     * @param state       the block state <strong>before</strong> the block is broken
     * @param blockEntity the block entity <strong>before</strong> the block is broken, can be {@code null}
     * @return {@code false} to cancel block breaking action, or {@code true} to pass to next listener
     */
    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (world instanceof ServerWorld serverWorld) {
            if(player.isCreative() || player.isSpectator())
                return true;
            ItemStack hand = player.getMainHandStack();
            if (hand.isOf(GAMBLING_PICKAXE)) {
                List<ItemStack> drop = Block.getDroppedStacks(state, serverWorld, pos, blockEntity, player, hand);
                world.breakBlock(pos, false);
//                hand.damage(1, player, player1 -> player1.sendToolBreakStatus(player1.getActiveHand()));
                if (player.getRandom().nextBoolean()) {
                    //10次赌博
                    //你最高可以翻2的10次方倍!
                    //每次翻倍的概率皆为4.5%
                    //失败则终止翻倍.
                    int count = 2;
                    for (int i = 0; i < 10; i++) {
                        if (player.getRandom().nextDouble() < 0.045) {
                            count *= 2;
                        } else {
                            break;
                        }
                    }
                    for (ItemStack itemStack : drop) {
                        itemStack.setCount(itemStack.getCount() * count);
                        ItemEntity item = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack);
                        world.spawnEntity(item);
                    }
                } else {
                    player.sendMessage(Text.literal("赌博失败!!方块永久消失了.."));
                }
            }
            if(hand.isOf(ANTIMATTER_PICKAXE)){
                world.breakBlock(pos, false);
                ItemStack itemStack1 = new ItemStack(allItems.get(player.getRandom().nextInt(allItems.size())));
                ItemEntity item1 = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack1);
                world.spawnEntity(item1);
            }

        }
        return true;
    }
}

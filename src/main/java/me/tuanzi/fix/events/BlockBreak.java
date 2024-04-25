package me.tuanzi.fix.events;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockBreak implements AttackBlockCallback {


    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {

        if (!world.isClient && !player.isCreativeLevelTwoOp()) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockState blockState = serverWorld.getBlockState(pos);
            if (blockState.getBlock() == Blocks.LIGHT) {
                serverWorld.breakBlock(pos, true);
                ItemEntity item = new ItemEntity(serverWorld, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.LIGHT));
                serverWorld.spawnEntity(item);
            }
        }

        return ActionResult.PASS;
    }
}

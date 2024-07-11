package me.tuanzi.blocks.angel_block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import static me.tuanzi.blocks.BlockRegisty.ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;
import static me.tuanzi.blocks.angel_block.AngelBlock.ANGEL_BLOCK_RANGE;
import static me.tuanzi.blocks.angel_block.AngelBlock.ANGEL_BLOCK_USED;
import static me.tuanzi.effects.EffectRegistry.ANGEl_WINGS;

public class AngelBlockEntity extends BlockEntity {


    public AngelBlockEntity(BlockPos pos, BlockState state) {
        super(ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity entity) {
        if (entity instanceof AngelBlockEntity) {
            if (state.get(ANGEL_BLOCK_USED)) {
                if (world.getTime() % 80L == 0L && !world.isClient()) {
                    Box box = new Box(pos).expand(state.get(ANGEL_BLOCK_RANGE));
                    List<PlayerEntity> list = world.getNonSpectatingEntities(PlayerEntity.class, box);
                    for (PlayerEntity playerEntity : list) {
                        playerEntity.addStatusEffect(new StatusEffectInstance(ANGEl_WINGS, 20 * 17, 0, true, true));
                    }
                }
            }
        }
    }

}

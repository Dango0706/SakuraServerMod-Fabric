package me.tuanzi.blocks.sorter;

import com.mojang.serialization.MapCodec;
import me.tuanzi.blocks.angel_block.AngelBlock;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RedstoneView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static me.tuanzi.SakuraServer.SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;

public class Sorter extends BlockWithEntity {

    public static final BooleanProperty SORTER_USED = BooleanProperty.of("sorter_used");
    public static final BooleanProperty SORTER_DISPLAY_RANGE = BooleanProperty.of("sorter_display_range");
    public static final IntProperty SORTER_RANGE = IntProperty.of("sorter_range", 10, 120);

    public Sorter(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(SORTER_USED, false).with(SORTER_RANGE, 50).with(SORTER_DISPLAY_RANGE,false));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return Sorter.createCodec(Sorter::new);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int range = world.getBlockState(pos).get(SORTER_RANGE);
        if (player.isSneaking() && player.hasPermissionLevel(2)) {
            if (range == 10) {
                world.setBlockState(pos, state.with(SORTER_RANGE, 30));
            } else if (range == 30) {
                world.setBlockState(pos, state.with(SORTER_RANGE, 50));
            } else if (range == 50) {
                world.setBlockState(pos, state.with(SORTER_RANGE, 70));
            } else if (range == 70) {
                world.setBlockState(pos, state.with(SORTER_RANGE, 90));
            } else if (range == 90) {
                world.setBlockState(pos, state.with(SORTER_RANGE, 120));
            } else {
                world.setBlockState(pos, state.with(SORTER_RANGE, 10));
            }
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1.2f);
            range = world.getBlockState(pos).get(SORTER_RANGE);
            if (player instanceof ServerPlayerEntity serverPlayer)
                serverPlayer.sendMessage(Text.translatable("block.sakura_server.sorter.range", range));
        }
        return ActionResult.SUCCESS;
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SORTER_USED);
        builder.add(SORTER_RANGE);
        builder.add(SORTER_DISPLAY_RANGE);
        super.appendProperties(builder);
    }

    /**
     * {@return a new block entity instance}
     *
     * <p>For example:
     * <pre>{@code
     * @Override
     * public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
     *   return new MyBlockEntity(pos, state);
     * }
     * }</pre>
     *
     * @param pos
     * @param state
     * @implNote While this is marked as nullable, in practice this should never return
     * {@code null}. {@link PistonExtensionBlock} is the only block in vanilla that
     * returns {@code null} inside the implementation.
     */
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SorterBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return AngelBlock.validateTicker(type, SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, SorterBlockEntity::tick);
    }

    /**
     * Called when a neighboring block is updated. This method should be overridden
     * to perform an action with a side effect, most notably an activation of a redstone
     * component. This can also be used to perform an action changing block states of
     * other blocks, such as {@link SpongeBlock} which absorbs water.
     *
     * <p>To replace the state of the block itself, override {@link #getStateForNeighborUpdate}
     * instead.
     *
     * <p>This method is called on both the logical client and logical server, so take caution
     * when overriding this method. The logical side can be checked using {@link
     * World#isClient}.
     *
     * @param state
     * @param world
     * @param pos
     * @param sourceBlock
     * @param sourcePos
     * @param notify
     * @see #getStateForNeighborUpdate
     * @see RedstoneView#isReceivingRedstonePower
     * @deprecated Consider calling {@link AbstractBlockState#neighborUpdate} instead. See <a href="#deprecated-methods">why these methods are deprecated</a>.
     */
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        world.setBlockState(pos, state.with(SORTER_USED, world.isReceivingRedstonePower(pos)), Block.NOTIFY_NEIGHBORS);
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }


}

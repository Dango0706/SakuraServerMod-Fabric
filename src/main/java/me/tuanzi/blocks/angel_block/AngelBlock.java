package me.tuanzi.blocks.angel_block;

import com.mojang.serialization.MapCodec;
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
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static me.tuanzi.SakuraServer.ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;

public class AngelBlock extends BlockWithEntity {

    public static final BooleanProperty ANGEL_BLOCK_USED = BooleanProperty.of("angel_block_used");
    public static final IntProperty ANGEL_BLOCK_RANGE = IntProperty.of("angel_block_range", 10, 50);


    public AngelBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(ANGEL_BLOCK_USED, false).with(ANGEL_BLOCK_RANGE, 50));
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return AngelBlock.createCodec(AngelBlock::new);
    }

    /**
     * Appends block state properties to this block. To use this, override and call {@link
     * StateManager.Builder#add} inside the method. See {@link
     * Properties} for the list of pre-defined properties.
     *
     * @param builder
     */
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ANGEL_BLOCK_USED);
        builder.add(ANGEL_BLOCK_RANGE);
        super.appendProperties(builder);
    }

    /**
     * Called when this block is used by a player.
     * This, by default, is bound to using the right mouse button.
     *
     * <p>This method is called on both the logical client and logical server, so take caution when overriding this method.
     * The logical side can be checked using {@link World#isClient() world.isClient()}.
     *
     * <p>If the action result is successful on a logical client, then the action will be sent to the logical server for processing.
     *
     * @param state
     * @param world
     * @param pos
     * @param player
     * @param hand
     * @param hit
     * @return an action result that specifies if using the block was successful.
     * @deprecated Consider calling {@link AbstractBlockState#onUse} instead. See <a href="#deprecated-methods">why these methods are deprecated</a>.
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        boolean used = world.getBlockState(pos).get(ANGEL_BLOCK_USED);
        int range = world.getBlockState(pos).get(ANGEL_BLOCK_RANGE);
        if (player.isSneaking()) {
            if (range == 10) {
                world.setBlockState(pos, state.with(ANGEL_BLOCK_RANGE, 30));
            } else if (range == 30) {
                world.setBlockState(pos, state.with(ANGEL_BLOCK_RANGE, 50));
            } else {
                world.setBlockState(pos, state.with(ANGEL_BLOCK_RANGE, 10));
            }
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1.2f);
            range = world.getBlockState(pos).get(ANGEL_BLOCK_RANGE);
            if (player instanceof ServerPlayerEntity serverPlayer)
                serverPlayer.sendMessage(Text.translatable("block.sakura_server.angel_block.range", range));
        } else {
            world.setBlockState(pos, state.with(ANGEL_BLOCK_USED, !used));
            player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, !used ? 1.2f : 0.5f);
            if (player instanceof ServerPlayerEntity serverPlayer)
                serverPlayer.sendMessage(Text.translatable(!used ? "block.sakura_server.angel_block.on" : "block.sakura_server.angel_block.off"));

        }
        return ActionResult.SUCCESS;
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
        return new AngelBlockEntity(pos, state);
    }

    /**
     * {@return the "ticker" for the block's block entity, or {@code null} if
     * the block entity does not need to be ticked}
     *
     * <p>Ticker is a functional interface called every tick to tick the block entity
     * on both the client and the server.
     *
     * <p>Tickers should validate that the passed {@code type} is the one this block expects,
     * and return {@code null} if it isn't. This is to prevent crashes in rare cases where a
     * mismatch occurs between the position's block and block entity. {@link
     * BlockWithEntity#validateTicker} can be used to implement the check.
     *
     * <p>Example:
     *
     * <pre>{@code
     * public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
     *   if (type != YourMod.MY_BLOCK_ENTITY_TYPE) return null;
     *   // This should be a static method usable as a BlockEntityTicker.
     *   return YourBlockEntity::tick;
     * }
     * }</pre>
     *
     * @param world
     * @param state
     * @param type
     */
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return AngelBlock.validateTicker(type, ANGEL_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, AngelBlockEntity::tick);
    }

    /**
     * {@return the block's render type (invisible, animated, model)}
     *
     * @param state
     * @apiNote {@link BlockWithEntity} overrides this to return {@link BlockRenderType#INVISIBLE};
     * therefore, custom blocks extending that class must override it again to render the block.
     * @deprecated Consider calling {@link AbstractBlockState#getRenderType} instead. See <a href="#deprecated-methods">why these methods are deprecated</a>.
     */
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    /**
     * @param state
     * @param world
     * @param pos
     * @param context
     * @deprecated Consider calling {@link AbstractBlockState#getCollisionShape(BlockView, BlockPos, ShapeContext)} instead. See <a href="#deprecated-methods">why these methods are deprecated</a>.
     */
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
}

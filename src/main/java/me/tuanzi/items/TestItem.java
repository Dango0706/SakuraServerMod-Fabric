package me.tuanzi.items;

import me.tuanzi.items.utils.SakuraItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TestItem extends SakuraItem {


    public TestItem(Settings settings) {
        super(settings);
    }

    /**
     * Called when the player uses (or starts using) the item.
     * The use action, by default, is bound to the right mouse button.
     * This method checks the player's hunger when the item is a food, and will
     * {@linkplain TypedActionResult#pass pass} in all other cases by default.
     *
     * <p>If the item {@linkplain #getMaxUseTime can be used for multiple ticks}, then
     * this will only be called when the player starts using it. After that,
     * {@link #usageTick} is called every tick until the player {@linkplain #finishUsing
     * finishes using the item}.
     *
     * <p>This method is called on both the logical client and logical server, so take caution when overriding this method.
     * The logical side can be checked using {@link World#isClient() world.isClient()}.
     *
     * @param world the world the item was used in
     * @param user  the player who used the item
     * @param hand  the hand used
     * @return a typed action result that specifies whether using the item was successful.
     * The action result contains the new item stack that the player's hand will be set to.
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
            Vec3d vec3d = serverPlayer.getVelocity();
            vec3d.add(1,0,0);
            serverPlayer.addVelocity(1,0,0);
//            serverPlayer.setVelocity(vec3d);
            serverPlayer.velocityModified = true;
//            return TypedActionResult.consume(serverPlayer.getActiveItem());
        }
        return super.use(world, user, hand);
    }
}

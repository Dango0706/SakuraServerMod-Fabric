package me.tuanzi.items.functional;

import me.tuanzi.items.utils.SakuraItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EnderPaper extends SakuraItem {

    public EnderPaper(Settings settings) {
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
        ItemStack itemStack = user.getStackInHand(hand);

        if(!world.isClient()){
            NbtCompound nbtCompound = new NbtCompound();
        }

        return TypedActionResult.success(itemStack);

    }
}

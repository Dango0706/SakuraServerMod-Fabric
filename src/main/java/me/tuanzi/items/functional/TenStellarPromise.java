package me.tuanzi.items.functional;

import me.tuanzi.items.utils.CanNotBeCopy;
import me.tuanzi.items.utils.SakuraItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static me.tuanzi.draw.Draw.*;
import static me.tuanzi.items.utils.ItemUtils.searchPool;

public class TenStellarPromise extends SakuraItem implements CanNotBeCopy {

    public TenStellarPromise(Settings settings) {
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
        if(!world.isClient){
            if(user.isSneaking()){
                searchPool(user, weaponUpGoldPool, weaponGoldPool, weaponUpPurplePool, weaponPurplePool);
            }else{
                pullTen(user, 1);
                if(!user.isCreative() || !user.isSpectator())
                    user.getStackInHand(hand).decrement(1);
            }

        }

        return super.use(world, user, hand);
    }


}

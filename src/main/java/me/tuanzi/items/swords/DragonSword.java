package me.tuanzi.items.swords;

import me.tuanzi.items.SakuraSword;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static me.tuanzi.SakuraServer.*;

public class DragonSword extends SakuraSword {

    public DragonSword() {
        super(LEG_TOOLS, 8, 1.6f, new Settings(), LEG_TOOLS.getRarity(),
                Text.translatable("item.sakura_server.dragon_sword.skill.desc1").withColor(0xefecc3),
                Text.translatable("item.sakura_server.dragon_sword.skill.desc2").withColor(0xefecc3),
                Text.translatable("item.sakura_server.dragon_sword.skill.desc3").withColor(0xefecc3),
                Text.translatable("item.sakura_server.dragon_sword.skill.desc4").withColor(0xefecc3),
                Text.translatable("item.sakura_server.dragon_sword.skill.desc5").withColor(0xefecc3),
                Text.translatable("item.sakura_server.dragon_sword.skill.desc6").withColor(0xefecc3));
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
        if (!world.isClient) {
            if (!user.getItemCooldownManager().isCoolingDown(itemStack.getItem()) && !user.isCreative() && !user.isSpectator()) {
                user.getItemCooldownManager().set(itemStack.getItem(), 20 * 16);
                user.setHealth(user.getHealth() * 0.7f);
                world.playSound(null,
                        user.getBlockX(),
                        user.getBlockY(),
                        user.getBlockZ(),
                        DRAGON_ROAR_SOUND,
                        SoundCategory.PLAYERS,
                        1f,
                        1f
                );
                user.addStatusEffect(new StatusEffectInstance(DRAGON_SWORD_EFFECT, 20 * 10, 0));
            }

        }
        return super.use(world, user, hand);
    }
}

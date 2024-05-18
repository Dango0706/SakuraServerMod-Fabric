package me.tuanzi.items.functional;

import me.tuanzi.items.utils.SakuraItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Vanishable;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

import static me.tuanzi.SakuraServer.MAGNET_USE_SOUND;

public class Magnet extends SakuraItem implements Vanishable {

    public Magnet(Settings settings) {
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
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            stack.getOrCreateNbt().putBoolean("spawnParticles", !stack.getOrCreateNbt().getBoolean("spawnParticles"));
        } else {
            stack.getOrCreateNbt().putBoolean("enable", !stack.getOrCreateNbt().getBoolean("enable"));
            world.playSound(null, user.getBlockPos(), MAGNET_USE_SOUND, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }

        return super.use(world, user, hand);
    }

    @Override
    public void inInventoryTick(PlayerEntity player, World world, ItemStack itemStack) {
        if (!world.isClient() && itemStack.getOrCreateNbt().getBoolean("enable")) {
            if (new Random().nextInt(20 * 60) == 76)
                itemStack.damage(1, player, player1 -> player1.sendToolBreakStatus(player1.getActiveHand()));
            ArrayList<ItemEntity> itemEntities;
            double x = player.getX();
            double y = player.getY();
            double z = player.getZ();
            Box box = new Box(x, y, z, x + 1, y + 1, z + 1).expand(10);
            itemEntities = (ArrayList<ItemEntity>) world.getEntitiesByType(EntityType.ITEM, box, Entity::isAlive);
            for (ItemEntity item : itemEntities) {
                if (!item.cannotPickup()) {
                    Vec3d itemPos = item.getPos().add(0,-0.35,0);
                    Vec3d playerPos = player.getPos().add(0,0.3,0);
                    Vec3d direction = playerPos.subtract(itemPos).normalize();
                    item.setVelocity(direction.multiply(0.35));
                    item.velocityModified = true;
                    if (itemStack.getOrCreateNbt().getBoolean("spawnParticles"))
                        ((ServerWorld) world).spawnParticles(
                                ParticleTypes.GLOW, item.getX(), item.getY() + 0.3, item.getZ(), 1, 0.15, 0.15, 0.15, 0);
                }
            }
        }
    }

    /**
     * Checks if the glint effect should be applied when the item is rendered.
     *
     * <p>By default, returns true if the item has enchantments.
     *
     * @param stack
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.getOrCreateNbt().getBoolean("enable");
    }
}

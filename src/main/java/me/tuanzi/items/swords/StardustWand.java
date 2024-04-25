package me.tuanzi.items.swords;

import me.tuanzi.items.SakuraSword;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static me.tuanzi.SakuraServer.HEALBANE;
import static me.tuanzi.SakuraServer.LEG_TOOLS;
import static net.minecraft.util.UseAction.SPEAR;

public class StardustWand extends SakuraSword {

    public StardustWand() {
        super(LEG_TOOLS, 8, 1.6f, new Settings(), LEG_TOOLS.getRarity(),
                Text.translatable("item.sakura_server.stardust_wand.skill.desc1").withColor(0x4f8fba),
                Text.translatable("item.sakura_server.stardust_wand.skill.desc2").withColor(0x4f8fba),
                Text.translatable("item.sakura_server.stardust_wand.skill.desc3").withColor(0x4f8fba),
                Text.translatable("item.sakura_server.stardust_wand.skill.desc4").withColor(0x4f8fba),
                Text.translatable("item.sakura_server.stardust_wand.skill.desc5").withColor(0x4f8fba));
    }

    /**
     * Called on both the client and the server when an entity stops using an item
     * before reaching the {@linkplain #getMaxUseTime maximum use time}. If the time was
     * reached, {@link #finishUsing} is called instead.
     *
     * <p>This method is called on both the logical client and logical server, so take caution
     * when overriding this method. The logical side can be checked using {@link
     * World#isClient}.
     *
     * <p>{@code user} might not be a player in some cases. For example, this occurs when
     * an entity uses a crossbow.
     *
     * @param stack
     * @param world
     * @param user
     * @param remainingUseTicks
     */
    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);

    }

    /**
     * Called when an entity finishes using the item, such as eating food or drinking a potion.
     * This method handles eating food by default.
     *
     * <p>This method is called on both the logical client and logical server, so take caution
     * when overriding this method. The logical side can be checked using {@link
     * World#isClient}.
     *
     * <p>{@code user} might not be a player in some cases. For example, this occurs when a fox
     * eats food or when a wandering trader drinks milk.
     *
     * @param stack
     * @param world
     * @param user
     * @return the new item stack after using the item
     */
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient()) {
            if (user instanceof PlayerEntity player && !player.getItemCooldownManager().isCoolingDown(stack.getItem()) && !player.isCreative() && !player.isSpectator()) {
                UUID uuid = player.getUuid();
                double x = player.getX();
                double y = player.getY();
                double z = player.getZ();
                List<Entity> entities = world.getOtherEntities(user, new Box(x - 5, y - 5, z - 5, x + 5, y + 5, z + 5));
                entities.removeIf(entity -> entity.getUuid().equals(uuid));
                entities.removeIf(entity -> !(entity instanceof LivingEntity));
                boolean b = player.getPose() == EntityPose.CROUCHING;
                //潜行加玩家
                if (!b) {
                    entities.removeIf(entity -> entity instanceof PlayerEntity);
                }
                for (Entity entity : entities) {
                    if (entity instanceof LivingEntity livingEntity) {
                        if (livingEntity instanceof TameableEntity tameableEntity) {
                            if (tameableEntity.isOwner(player)) {
                                tameableEntity.heal(8.0f);
                                world.addParticle(ParticleTypes.HEART, tameableEntity.getX(), tameableEntity.getY(), tameableEntity.getZ(), 1, 1, 1);
                            } else {
                                if (tameableEntity.getOwner() instanceof PlayerEntity && b) {
                                    livingEntity.addStatusEffect(new StatusEffectInstance(HEALBANE, 20 * 10, 0));
                                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 10, 0));
                                    livingEntity.damage(world.getDamageSources().playerAttack(player), 8.0f);
                                }
                                if (!(tameableEntity.getOwner() instanceof PlayerEntity)) {
                                    livingEntity.addStatusEffect(new StatusEffectInstance(HEALBANE, 20 * 10, 0));
                                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 10, 0));
                                    livingEntity.damage(world.getDamageSources().playerAttack(player), 8.0f);
                                }
                            }
                        } else {
                            livingEntity.addStatusEffect(new StatusEffectInstance(HEALBANE, 20 * 10, 0));
                            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 10, 0));
                            livingEntity.damage(world.getDamageSources().playerAttack(player), 8.0f);
                        }

                    }
                }
                world.playSound(null, x, y, z, SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 1.0f, 1.0f);
                player.getItemCooldownManager().set(stack.getItem(), 20 * 30);
                return stack;
            }
        }
        return super.finishUsing(stack, world, user);
    }

    /**
     * {@return the use action the item should perform}
     *
     * @param stack
     */
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return SPEAR;
    }

    /**
     * {@return the maximum use (right-click) time of this item, in ticks}
     * Once a player has used an item for said number of ticks, they stop using it, and {@link Item#finishUsing} is called.
     *
     * @param stack
     */
    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
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
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }
}

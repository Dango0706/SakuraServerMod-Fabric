package me.tuanzi.items.functional;

import me.tuanzi.items.SakuraItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MoveVillager extends SakuraItem {

    public MoveVillager(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            return stack.getNbt().getBoolean("hasVillager");
        }
        return false;
    }

    /**
     * Called by the client to append tooltips to an item. Subclasses can override
     * this and add custom tooltips to {@code tooltip} list.
     *
     * @param stack
     * @param world
     * @param tooltip the list of tooltips to show
     * @param context
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().getBoolean("hasVillager")) {
                NbtCompound villager = stack.getSubNbt("villagers");
                assert villager != null;
                NbtCompound villagerData = villager.getCompound("VillagerData");
                String pro = villagerData.getString("profession");
                tooltip.add(Text.translatable("item.sakura_server.villager_mover.profession").
                        append(Text.translatable("entity.minecraft.villager." + pro.split(":")[1])));
            }
        }

        super.appendTooltip(stack, world, tooltip, context);

    }

    /**
     * Called on both the client and the server when a player uses the item on an entity.
     *
     * <p>This method is called on both the logical client and logical server, so take caution
     * when overriding this method. The logical side can be checked using {@link
     * World#isClient}.
     *
     * <p>This should be used if the item can be used on multiple types of entities,
     * such as name tags or saddles.
     *
     * @param stack
     * @param user
     * @param entity
     * @param hand
     * @return the action result
     */
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!user.getWorld().isClient() && entity instanceof VillagerEntity villager) {
            if (stack.getOrCreateNbt().getBoolean("hasVillager")) {
                user.sendMessage(Text.translatable("item.sakura_server.villager_mover.fail.has_villager"));
                return ActionResult.CONSUME;
            } else {
                NbtCompound villagerCompound = new NbtCompound();
                villager.writeNbt(villagerCompound);
                stack.setSubNbt("villagers", villagerCompound);
                stack.getOrCreateNbt().putBoolean("hasVillager", true);
                user.sendMessage(Text.translatable("item.sakura_server.villager_mover.saved"));
                user.getWorld().playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, 1);
                villager.remove(Entity.RemovalReason.KILLED);
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }

    /**
     * Called when an item is used on a block.
     *
     * <p>This method is called on both the logical client and logical server, so take caution when using this method.
     * The logical side can be checked using {@link World#isClient() context.getWorld().isClient()}.
     *
     * @param context the usage context
     * @return an action result that specifies if using the item on a block was successful.
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack itemStack = context.getStack();
        BlockPos blockPos = context.getBlockPos();
        Direction direction = context.getSide();
        World world = context.getWorld();
        if (!world.isClient() && itemStack.hasNbt()) {
            assert itemStack.getNbt() != null;
            if (itemStack.getNbt().getBoolean("hasVillager")) {
                ServerWorld serverWorld = (ServerWorld) world;
                VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, world);
                NbtCompound villagerCompound = itemStack.getSubNbt("villagers");
                villager.readNbt(villagerCompound);
                double x = blockPos.getX() + direction.getOffsetX() + 0.5;
                double y = blockPos.getY() + direction.getOffsetY();
                double z = blockPos.getZ() + direction.getOffsetZ() + 0.5;
                villager.teleport(serverWorld, x, y, z, null, 1, 1);
                serverWorld.spawnEntity(villager);
                world.playSound(null, x, y, z, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1, 1);
                itemStack.getNbt().remove("hasVillager");
                itemStack.removeSubNbt("villagers");
                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }
}


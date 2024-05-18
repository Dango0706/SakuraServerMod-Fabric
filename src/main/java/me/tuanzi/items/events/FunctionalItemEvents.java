package me.tuanzi.items.events;

import me.tuanzi.events.EntityTickEvent;
import me.tuanzi.events.PlayerTickEvent;
import me.tuanzi.items.utils.SakuraItem;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static me.tuanzi.SakuraServer.SOUL_GEM;
import static me.tuanzi.SakuraServer.printDebugLog;

public class FunctionalItemEvents implements UseItemCallback, ServerLivingEntityEvents.AfterDeath, PlayerTickEvent, EntityTickEvent {

    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!player.isSpectator() && !world.isClient && !player.getItemCooldownManager().isCoolingDown(SOUL_GEM)) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            //是灵魂石
            if (itemStack.isOf(SOUL_GEM)) {
                int x = player.getBlockX();
                int y = player.getBlockY();
                int z = player.getBlockZ();
                //检查nbt是否有entity
                if (itemStack.hasNbt() && itemStack.getNbt().getBoolean("hasEntity")) {
                    //检查生物是否死亡
                    if (itemStack.getNbt().getBoolean("entityDeath")) {
                        //死亡,则复活吧,我的爱人!
                        WolfEntity wolfEntity = new WolfEntity(EntityType.WOLF, world);
                        wolfEntity.teleport(x, y, z);
                        wolfEntity.setTamed(true);
                        wolfEntity.setOwner(player);
                        wolfEntity.setUuid(itemStack.getNbt().getUuid("UUID"));
                        wolfEntity.setCollarColor(DyeColor.byId(itemStack.getNbt().getInt("dyeColor")));
                        if (itemStack.getNbt().contains("displayName"))
                            wolfEntity.setCustomName(Text.literal(itemStack.getNbt().getString("displayName")));
                        world.spawnEntity(wolfEntity);
                        serverPlayer.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE), SoundCategory.PLAYERS, x, y, z, 1, 1, world.getRandom().nextLong()));
                        player.getItemCooldownManager().set(SOUL_GEM, 20 * 120);
                        player.sendMessage(Text.translatable("item.sakura_server.soul_gem.respawn"));
                        itemStack.getNbt().putBoolean("entityDeath", false);
                        player.setStackInHand(hand, itemStack);
                    } else {
                        //未死亡,则搜索他
                        UUID uuid = itemStack.getNbt().getUuid("UUID");
                        List<WolfEntity> entities = world.getEntitiesByType(TypeFilter.instanceOf(WolfEntity.class)
                                , Box.enclosing(new BlockPos(x - 40, y - 40, z - 40), new BlockPos(x + 40, y + 40, z + 40))
                                , TameableEntity::isTamed
                        );
                        if (entities.size() > 0) {
                            for (WolfEntity entity : entities) {
                                if (entity.getUuid().equals(uuid)) {
                                    serverPlayer.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.ENTITY_ARROW_HIT_PLAYER), SoundCategory.PLAYERS, x, y, z, 1, 1, world.getRandom().nextLong()));
                                    entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 20 * 10, 0));
                                    player.sendMessage(Text.translatable("item.sakura_server.soul_gem.found", entity.getBlockX(), entity.getBlockY(), entity.getBlockZ()));
                                }
                            }
                        } else {
                            player.sendMessage(Text.translatable("item.sakura_server.soul_gem.fail.not_found"));
                        }

                    }

                }
            }

        }
        return TypedActionResult.pass(itemStack);
    }

    /**
     * Called when a living entity dies. The death cannot be canceled at this point.
     *
     * @param entity       the entity
     * @param damageSource the source of the fatal damage
     */
    @Override
    public void afterDeath(LivingEntity entity, DamageSource damageSource) {
        if (entity instanceof WolfEntity wolf) {
            if (wolf.isTamed() && wolf.getOwner() instanceof PlayerEntity player && !wolf.getWorld().isClient) {
                UUID uuid = wolf.getUuid();
                //todo
/*                ServerWorld world = (ServerWorld) wolf.getWorld();
                ItemStack itemStack1;
                for (int i = -1000000; i < 1000000; i++) {
                    for (int j = -64; j < 1000; j++) {
                        for (int k = -1000000; k < 1000000 ; k++) {
                           BlockEntity blockEntity = world.getBlockEntity(new BlockPos(i,j,k));
                           if(blockEntity instanceof ChestBlockEntity chestBlockEntity){
                               chestBlockEntity.
                           }
                        }
                    }
                }*/

                for (int i = 0; i < 200; i++) {
                    if (player.getInventory().getStack(i).isOf(SOUL_GEM)) {
                        ItemStack itemStack = player.getInventory().getStack(i);
                        if (itemStack.hasNbt() && itemStack.getNbt().getBoolean("hasEntity")) {
                            if (itemStack.getNbt().getUuid("UUID").equals(uuid)) {
                                itemStack.getNbt().putInt("dyeColor", wolf.getCollarColor().getId());
                                if (wolf.hasCustomName())
                                    itemStack.getNbt().putString("displayName", wolf.getDisplayName().getString());
                                player.sendMessage(Text.translatable("item.sakura_server.soul_gem.dead"));
                                printDebugLog(uuid.toString());
                                itemStack.getNbt().putBoolean("entityDeath", true);
                                player.getInventory().setStack(i, itemStack);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void tick(PlayerEntity player) {
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getStack(i).getItem() instanceof SakuraItem item) {
                item.inInventoryTick(player, player.getWorld(), player.getInventory().getStack(i));
            }
        }
        if (player.getOffHandStack().getItem() instanceof SakuraItem item) {
            item.inInventoryTick(player, player.getWorld(), player.getOffHandStack());
        }

        for (int i = 100; i < 104; i++) {
            if (player.getInventory().getStack(i).getItem() instanceof SakuraItem item) {
                item.inInventoryTick(player, player.getWorld(), player.getInventory().getStack(i));
            }
        }


    }

    @Override
    public void tick(Entity entity) {

    }
}

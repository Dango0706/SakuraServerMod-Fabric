package me.tuanzi.enchantments.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static me.tuanzi.SakuraServer.SOUL_BOUND;

public class SoulBoundEvent implements ServerPlayerEvents.AfterRespawn, ServerLivingEntityEvents.AllowDeath {
    public static Map<String, ArrayList<ItemStack>> SoulBoundItems = new HashMap<>();

    /**
     * Called when player data is copied to a new player.
     *
     * @param oldPlayer the old player
     * @param newPlayer the new player
     * @param alive     whether the old player is still alive
     */
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        if (!newPlayer.getWorld().isClient()) {
            String name = newPlayer.getName().getString();
            if (SoulBoundItems.containsKey(name)) {
                ArrayList<ItemStack> itemStacks = new ArrayList<>(SoulBoundItems.get(name));
                for (ItemStack itemStack : itemStacks) {
                    newPlayer.giveItemStack(itemStack);
                }
                SoulBoundItems.remove(name);
            }
        }
    }

    /**
     * Called when a living entity takes fatal damage (before totems of undying can take effect).
     *
     * @param entity       the entity
     * @param damageSource the source of the fatal damage
     * @param damageAmount the amount of damage that has killed the entity
     * @return true if the death should go ahead, false to cancel the death.
     */
    @Override
    public boolean allowDeath(LivingEntity entity, DamageSource damageSource, float damageAmount) {
        if (!entity.getWorld().isClient) {
            //检测世界规则
            if (entity.getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY))
                return true;
            //检测不死图腾
            for (Hand hand : Hand.values()) {
                ItemStack itemStack2 = entity.getStackInHand(hand);
                if (itemStack2.isOf(Items.TOTEM_OF_UNDYING))
                    return true;
            }
            //检测灵魂绑定
            if (entity instanceof ServerPlayerEntity serverPlayer) {
                //记录位置
                ArrayList<Integer> slots = new ArrayList<>();
                //灵魂绑定的物品
                ArrayList<ItemStack> soulBoundItems = new ArrayList<>();
                //先获取所有掉落物
                for (int i = 0; i < 200; i++) {
                    if (serverPlayer.getInventory().getStack(i).getItem() != Items.AIR) {
                        //记录物品位置
                        slots.add(i);
                    }
                }
                for (int i : slots) {
                    ItemStack itemStack = serverPlayer.getInventory().getStack(i);
                    //如果有灵魂绑定,则清除并添加进列表里
                    if (EnchantmentHelper.getLevel(SOUL_BOUND, itemStack) > 0) {
                        soulBoundItems.add(itemStack);
                        serverPlayer.getInventory().removeStack(i);
                    }
                }
                SoulBoundItems.put(serverPlayer.getName().getString(), soulBoundItems);
            }
        }
        return true;
    }



}

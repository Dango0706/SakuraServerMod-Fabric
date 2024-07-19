package me.tuanzi.items.functional.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.world.GameRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static me.tuanzi.utils.KeyConstant.RESURRECTION_AMULET_NOT_EXP;
import static me.tuanzi.utils.Utils.getPlayerInv;
import static me.tuanzi.utils.registry.ItemRegistry.RESURRECTION_AMULET;

public class FunctionalItemEvent implements ServerPlayerEvents.AfterRespawn, ServerLivingEntityEvents.AllowDeath {
    public static Map<String, ArrayList<ItemStack>> KeepItems = new HashMap<>();
    public static Map<String ,Integer> KeepExp = new HashMap<>();
    public static Map<String ,Integer> KeepLevel = new HashMap<>();
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
            boolean has = false;
            //检测
            for (ItemStack itemStack: getPlayerInv(serverPlayer)){
                if(itemStack.isOf(RESURRECTION_AMULET)){
                    has = true;
                    break;
                }
            }
            //有
            if(has){
                //有无30级
                if(serverPlayer.experienceLevel >=30){
                    //扣除
                    serverPlayer.setExperienceLevel(serverPlayer.experienceLevel -= 30);
                    //保存剩余的经验值
                    KeepExp.put(serverPlayer.getName().getString(),serverPlayer.totalExperience);
                    KeepLevel.put(serverPlayer.getName().getString(),serverPlayer.experienceLevel);
                    //清除经验值
                    serverPlayer.setExperienceLevel(0);
                    serverPlayer.setExperiencePoints(0);
                    //遍历添加
                    ArrayList<ItemStack> arrayList = new ArrayList<>();
                    //是否删除过?
                    boolean isDecrement = false;
                    for (ItemStack stack : getPlayerInv(serverPlayer)){
                        //如果是自己,则数量-1 且为删除过.
                        if(stack.isOf(RESURRECTION_AMULET) && !isDecrement){
                            stack.decrement(1);
                            isDecrement = true;
                        }
                        arrayList.add(stack);
                        //设置无法掉落(?)
//                        stack.setCount(0);
                    }
                    //put进map中
                    KeepItems.put(serverPlayer.getName().getString(),arrayList);
                }else{
                    //等级不足提示
                    serverPlayer.sendMessage(Text.keybind(RESURRECTION_AMULET_NOT_EXP).withColor(0xe6395));
                }
            }
        }

        return true;
    }

    /**
     * Called after player a has been respawned.
     *
     * @param oldPlayer the old player
     * @param newPlayer the new player
     * @param alive     whether the old player is still alive
     */
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        //检测是不是服务器
        if(!newPlayer.getWorld().isClient){
            String name = newPlayer.getName().getString();
            //包含名称,给复活的玩家发物品
            if(KeepItems.containsKey(name)){
                ArrayList<ItemStack> arrayList = KeepItems.get(name);
                for (ItemStack itemStack : arrayList) {
                    newPlayer.giveItemStack(itemStack);
                }
                KeepItems.remove(name);
            }
            //发经验值.
            if(KeepExp.containsKey(name)){
                newPlayer.addExperience(KeepExp.get(name));
                KeepExp.remove(name);
            }
            if(KeepLevel.containsKey(name)){
                newPlayer.setExperienceLevel(KeepLevel.get(name));
                KeepLevel.remove(name);
            }
        }
    }
}

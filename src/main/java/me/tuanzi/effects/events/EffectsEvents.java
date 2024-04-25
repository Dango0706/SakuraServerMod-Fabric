package me.tuanzi.effects.events;

import me.tuanzi.events.LivingEntityEffectRemoveEvent;
import me.tuanzi.events.LivingEntityHealEvent;
import me.tuanzi.utils.LivingEntityCustomNbt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;

import static me.tuanzi.SakuraServer.HEALBANE;

public class EffectsEvents implements LivingEntityHealEvent, LivingEntityEffectRemoveEvent {
    @Override
    public boolean heal(LivingEntity entity, float amount) {
        if (!entity.getWorld().isClient()) {
            if (entity.hasStatusEffect(HEALBANE)) {
                LivingEntityCustomNbt livingEntityCustomNbt = (LivingEntityCustomNbt) entity;
                NbtCompound nbtCompound = livingEntityCustomNbt.customNbt();
                float h = nbtCompound.getFloat("Healbane_heal");
                if(h > 0 ){
                    h += amount;
                    nbtCompound.putFloat("Healbane_heal" , h);
                }else{
                    nbtCompound.putFloat("Healbane_heal", amount);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(LivingEntity entity, StatusEffectInstance effectInstance) {
        if (effectInstance.getEffectType() == HEALBANE && !entity.getWorld().isClient) {
            LivingEntityCustomNbt livingEntityCustomNbt = (LivingEntityCustomNbt) entity;
            NbtCompound nbtCompound = livingEntityCustomNbt.customNbt();
            if(nbtCompound.contains("Healbane_heal")){
                entity.heal(nbtCompound.getFloat("Healbane_heal"));
                nbtCompound.putFloat("Healbane_heal", 0);
            }
        }
    }

}

package me.tuanzi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;

import static me.tuanzi.items.ToolRegistry.DRAGON_SWORD;
import static net.minecraft.entity.effect.StatusEffectCategory.BENEFICIAL;

public class DragonSwordEffect extends StatusEffect {

    public DragonSwordEffect() {
        super(BENEFICIAL, 0xff0000);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.heal(entity.getMaxHealth() * (0.2f / 40));
        for (ItemStack itemStack : entity.getHandItems()) {
            if (itemStack.isOf(DRAGON_SWORD)) {
                return;
            }
        }
        entity.removeStatusEffect(this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 5 == 0;
    }
}

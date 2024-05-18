package me.tuanzi.enchantments.sword;

import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class AdvancedDamageEnchantment extends DamageEnchantment {
    public AdvancedDamageEnchantment(Rarity weight, int typeIndex, EquipmentSlot... slots) {
        super(weight, typeIndex, slots);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public Text getName(int level) {
        MutableText text = super.getName(level).copy();
        text.withColor(0xcc99fe);
        text.formatted(Formatting.BOLD);
        return text;
    }


    /**
     * {@return whether this enchantment will appear in the enchanted book trade
     * offers of librarian villagers}
     */
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    @Override
    public float getAttackDamage(int level, EntityGroup group) {
        if (this.typeIndex == 0) {
            return 2.0f + (float) Math.max(0, level - 1) * 1.15f;
        }
        if (this.typeIndex == 1 && group == EntityGroup.UNDEAD) {
            return (float) level * 3.75f;
        }
        if (this.typeIndex == 2 && group == EntityGroup.ARTHROPOD) {
            return (float) level * 3.75f;
        }
        return 0.0f;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            if (this.typeIndex == 2 && level > 0 && livingEntity.getGroup() == EntityGroup.ARTHROPOD) {
                int i = 40 + user.getRandom().nextInt(30 * level);
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, i, 5));
            }
        }
    }


}

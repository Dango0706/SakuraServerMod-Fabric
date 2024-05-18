package me.tuanzi.enchantments.sword;

import me.tuanzi.enchantments.SakuraEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class Education extends SakuraEnchantment {

    public Education(Enchantment.Rarity rarity, EnchantmentTarget target, int Rarity, EquipmentSlot... slotTypes) {
        super(rarity, target, Rarity, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 4;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    /**
     * {@return whether this enchantment will appear in the enchanted book trade
     * offers of librarian villagers}
     */
    @Override
    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }
}

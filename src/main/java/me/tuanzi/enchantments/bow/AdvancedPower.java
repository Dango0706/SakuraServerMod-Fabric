package me.tuanzi.enchantments.bow;

import me.tuanzi.enchantments.SakuraEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class AdvancedPower extends SakuraEnchantment {

    public AdvancedPower(Enchantment.Rarity rarity, EquipmentSlot... slotTypes) {
        super(rarity, EnchantmentTarget.BOW, slotTypes);
    }

    public AdvancedPower(Enchantment.Rarity rarity, int Rarity, EquipmentSlot... slotTypes) {
        super(rarity, EnchantmentTarget.BOW, Rarity, slotTypes);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}

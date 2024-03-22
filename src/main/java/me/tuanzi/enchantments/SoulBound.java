package me.tuanzi.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class SoulBound extends Enchantment {

    public SoulBound(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public int getMinPower(int level) {
        return 30;
    }
}

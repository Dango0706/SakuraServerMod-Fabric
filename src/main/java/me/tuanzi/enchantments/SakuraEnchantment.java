package me.tuanzi.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static me.tuanzi.items.utils.ItemUtils.getColor;

public class SakuraEnchantment extends Enchantment {
    int Rarity = 1;

    public SakuraEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot... slotTypes) {
        super(rarity, target, slotTypes);
    }

    public SakuraEnchantment(Rarity rarity, EnchantmentTarget target, int Rarity, EquipmentSlot... slotTypes) {
        super(rarity, target, slotTypes);
        this.Rarity = Rarity;
    }

    @Override
    public boolean isTreasure() {
        return true;
    }

    @Override
    public Text getName(int level) {
        MutableText text = super.getName(level).copy();
        text.withColor(getColor(Rarity));
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

}

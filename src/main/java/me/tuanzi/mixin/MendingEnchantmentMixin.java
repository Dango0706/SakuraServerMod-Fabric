package me.tuanzi.mixin;

import me.tuanzi.items.utils.CanNotWithMending;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.MendingEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MendingEnchantment.class)
public abstract class MendingEnchantmentMixin extends Enchantment {
    protected MendingEnchantmentMixin(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        if(stack.getItem() instanceof CanNotWithMending)
            return false;
        return super.isAcceptableItem(stack);
    }
}

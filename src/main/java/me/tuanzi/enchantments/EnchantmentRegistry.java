package me.tuanzi.enchantments;

import me.tuanzi.enchantments.all.SoulBound;
import me.tuanzi.enchantments.bow.AdvancedPower;
import me.tuanzi.enchantments.sword.AdvancedDamageEnchantment;
import me.tuanzi.enchantments.sword.Education;
import me.tuanzi.enchantments.tools.VeinMine;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static me.tuanzi.utils.Constants.MODID;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

public class EnchantmentRegistry {
    //enchantment
    public static final Enchantment SOUL_BOUND = new SoulBound(Enchantment.Rarity.RARE, EnchantmentTarget.VANISHABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment VEIN_MINE = new VeinMine(Enchantment.Rarity.RARE, EnchantmentTarget.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    public static final Enchantment ADVANCED_SHARPNESS = new AdvancedDamageEnchantment(Enchantment.Rarity.VERY_RARE, 0, EquipmentSlot.MAINHAND);
    public static final Enchantment ADVANCED_SMITE = new AdvancedDamageEnchantment(Enchantment.Rarity.VERY_RARE, 1, EquipmentSlot.MAINHAND);
    public static final Enchantment ADVANCED_BANE_OF_ARTHROPODS = new AdvancedDamageEnchantment(Enchantment.Rarity.VERY_RARE, 2, EquipmentSlot.MAINHAND);
    public static final Enchantment ADVANCED_POWER = new AdvancedPower(Enchantment.Rarity.VERY_RARE, 4, EquipmentSlot.MAINHAND);
    public static final Enchantment EDUCATION = new Education(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, 4, EquipmentSlot.MAINHAND);

    public EnchantmentRegistry() {
        printDebugLog("加载附魔...");
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "soul_bound"), SOUL_BOUND);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "vein_mine"), VEIN_MINE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_sharpness"), ADVANCED_SHARPNESS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_smite"), ADVANCED_SMITE);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_bane_of_arthropods"), ADVANCED_BANE_OF_ARTHROPODS);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "advanced_power"), ADVANCED_POWER);
        Registry.register(Registries.ENCHANTMENT, new Identifier(MODID, "education"), EDUCATION);


    }
}

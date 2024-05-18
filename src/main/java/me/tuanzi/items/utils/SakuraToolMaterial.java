package me.tuanzi.items.utils;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

public class SakuraToolMaterial implements ToolMaterial {

    private final int Rarity;
    private final int MiningLevel;
    private final int Durability;
    private final float miningSpeed;
    private final Ingredient RepairIngredient;

    public SakuraToolMaterial(int rarity) {
        Rarity = rarity;
        MiningLevel = rarity + 2;
        Durability = 700 + 600 * Rarity;
        miningSpeed = 3.0f + 1.5f * Rarity;
        RepairIngredient = Ingredient.EMPTY;
    }
    public SakuraToolMaterial(int rarity,int miningLevel) {
        Rarity = rarity;
        MiningLevel = miningLevel;
        Durability = 700 + 600 * Rarity;
        miningSpeed = 3.0f + 1.5f * Rarity;
        RepairIngredient = Ingredient.EMPTY;
    }

    public SakuraToolMaterial(int rarity, int miningLevel, int durability) {
        Rarity = rarity;
        MiningLevel = miningLevel;
        Durability = durability;
        miningSpeed = 3.0f + 1.5f * Rarity;
        RepairIngredient = Ingredient.EMPTY;
    }

    public SakuraToolMaterial(int rarity, int miningLevel, int durability, Ingredient repairIngredient) {
        Rarity = rarity;
        MiningLevel = miningLevel;
        Durability = durability;
        miningSpeed = 3.0f + 1.5f * Rarity;
        RepairIngredient = repairIngredient;
    }
    public SakuraToolMaterial(int rarity, int miningLevel, int durability,float miningSpeed, Ingredient repairIngredient) {
        Rarity = rarity;
        MiningLevel = miningLevel;
        Durability = durability;
        this.miningSpeed = miningSpeed;
        RepairIngredient = repairIngredient;
    }


    /**
     * {@return the total amount of durability a {@link ToolItem } using this {@link ToolMaterial} has}
     * <p>
     * The value returned here will set the {@link Item.Settings} max durability option when passed
     * into {@link ToolItem#ToolItem(ToolMaterial, Item.Settings)}
     * if the value was not already specified.
     */
    @Override
    public int getDurability() {

        return Durability;
    }

    /**
     * {@return the mining speed bonus applied when a {@link ToolItem} using this material is breaking an appropriate block}
     * {@code 1.0f} will result in no speed change.
     * <p>
     * <b>Note:</b> for default vanilla tool classes, this bonus is only applied in {@link MiningToolItem#getMiningSpeedMultiplier(ItemStack, BlockState)}.
     */
    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    /**
     * {@return the attack damage bonus applied to any {@link ToolItem} using this {@link ToolMaterial}}
     * <p>
     * In the case of {@link MiningToolItem} or {@link SwordItem}, the value returned
     * here will be added on top of the {@code attackDamage} value passed into the tool's constructor.
     */
    @Override
    public float getAttackDamage() {
        return -1;
    }

    /**
     * {@return the mining level of a {@link ToolItem} using this {@link ToolMaterial}}
     * To prevent this tool from successfully harvesting any mining level gated blocks, return {@link MiningLevels#HAND}.
     * <p>
     * For more information on mining levels, visit {@link MiningLevels}.
     */
    @Override
    public int getMiningLevel() {
        return MiningLevel;
    }

    /**
     * {@return the enchantment value sent back to {@link Item#getEnchantability()} for tools using this material}
     * <p>
     * By default, {@link ToolMaterial} will override {@link Item#getEnchantability()}
     * and delegate the call back to this method.
     * <p>
     * A higher return value will result in better enchantment results when using an {@code Enchanting Table}.
     * The highest enchantability value in vanilla is Netherite, at {@code 37}.
     */
    @Override
    public int getEnchantability() {
        return 5 + 5 * Rarity;
    }

    /**
     * {@return the {@link Ingredient } used to repair items using this {@link ToolMaterial}}
     * <p>
     * By default, {@link ToolMaterial} will delegate {@link Item#canRepair(ItemStack, ItemStack)}
     * back to this method.
     */
    @Override
    public Ingredient getRepairIngredient() {
        return RepairIngredient;
    }

    public int getRarity() {
        return Rarity;
    }
}

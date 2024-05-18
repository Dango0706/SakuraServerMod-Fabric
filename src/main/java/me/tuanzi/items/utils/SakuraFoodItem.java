package me.tuanzi.items.utils;

public class SakuraFoodItem extends SakuraItem {

    public SakuraFoodItem(Settings settings) {
        super(settings);
    }

    /**
     * Checks if this item is food and therefore is edible.
     */
    @Override
    public boolean isFood() {
        return true;
    }



}

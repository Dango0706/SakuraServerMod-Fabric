package me.tuanzi.items;

public class ItemUtils {

    public static int getColor(int Rarity) {
        if (Rarity == 0) {
            return 0xAAAAAA;
        } else if (Rarity == 1) {
            return 0x55FF55;
        } else if (Rarity == 2) {
            return 0x55FFFF;
        } else if (Rarity == 3) {
            return 0x5555FF;
        } else if (Rarity == 4) {
            return 0xFFFF55;
        } else if (Rarity == 5) {
            return 0xFFAA00;
        } else {
            return 0xAAAAAA;
        }
    }

}

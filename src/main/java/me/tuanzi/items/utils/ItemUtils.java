package me.tuanzi.items.utils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static me.tuanzi.draw.Draw.bluePool;
import static me.tuanzi.utils.Constants.MODID;

public class ItemUtils {

    public static void searchPool(PlayerEntity user, List<ItemStack> weaponUpGoldPool, List<ItemStack> weaponGoldPool, List<ItemStack> weaponUpPurplePool, List<ItemStack> weaponPurplePool) {
        user.sendMessage(Text.translatable("pool.up_golden"));
        MutableText text = Text.empty();
        for (ItemStack itemStack : weaponUpGoldPool) {
            text.append(itemStack.toHoverableText());
            text.append("§6X" + itemStack.getCount());
        }
        user.sendMessage(text);
        text = Text.empty();
        user.sendMessage(Text.translatable("pool.golden"));
        for (ItemStack itemStack : weaponGoldPool) {
            text.append(itemStack.toHoverableText());
            text.append("§6X" + itemStack.getCount());
        }
        user.sendMessage(text);
        text = Text.empty();
        user.sendMessage(Text.translatable("pool.up_purple").withColor(getColor(4)));
        for (ItemStack itemStack : weaponUpPurplePool) {
            text.append(itemStack.toHoverableText());
            text.append("§6X" + itemStack.getCount());
        }
        user.sendMessage(text);
        text = Text.empty();
        user.sendMessage(Text.translatable("pool.purple"));
        for (ItemStack itemStack : weaponPurplePool) {
            text.append(itemStack.toHoverableText());
            text.append("§6X" + itemStack.getCount());
        }
        user.sendMessage(text);
        text = Text.empty();
        user.sendMessage(Text.translatable("pool.blue"));
        for (ItemStack itemStack : bluePool) {
            text.append(itemStack.toHoverableText());
            text.append("§6X" + itemStack.getCount());
        }
        user.sendMessage(text);
    }

    public static int getColor(int Rarity) {
        if (Rarity == 0) {
            return 0xAAAAAA;
        } else if (Rarity == 1) {
            return 0x55FF55;
        } else if (Rarity == 2) {
            return 0x7ad779;
        } else if (Rarity == 3) {
            return 0x5555FF;
        } else if (Rarity == 4) {
            return 0xcc99fe;
        } else if (Rarity == 5) {
            return 0xFFAA00;
        } else {
            return 0xAAAAAA;
        }
    }

    public static ItemStack customPotions(StatusEffect statusEffect, int duration, int amplifier, int type) {
        ItemStack itemStack;
        if (type == 1) {
            itemStack = new ItemStack(Items.POTION);
        } else if (type == 2) {
            itemStack = new ItemStack(Items.SPLASH_POTION);
        } else {
            itemStack = new ItemStack(Items.LINGERING_POTION);
        }
        addCustomEffect(itemStack, statusEffect, duration, amplifier);
        return itemStack;

    }

    public static void addCustomEffect(ItemStack itemStack, StatusEffect statusEffect, int duration, int amplifier) {
        NbtCompound nbtCompound = itemStack.getNbt();
        NbtList nbtList;
        if (nbtCompound != null) {
            nbtList = nbtCompound.getList("custom_potion_effects", NbtElement.COMPOUND_TYPE);
        } else {
            nbtList = new NbtList();
        }
        NbtCompound nbtCompound1 = new NbtCompound();
        nbtCompound1.putString("id", String.valueOf(Registries.STATUS_EFFECT.getId(statusEffect)));
        nbtCompound1.putByte("amplifier", (byte) amplifier);
        nbtCompound1.putInt("duration", duration);
        nbtCompound1.putBoolean("ambient", false);
        nbtCompound1.putBoolean("ShowParticles", true);
        nbtList.add(nbtCompound1);
        itemStack.getOrCreateNbt().put("custom_potion_effects", nbtList);
    }

    //获取全部注册物品
    public static List<Item> getAllModItems() {
        List<Item> modItems = new ArrayList<>();
        for (Identifier id : Registries.ITEM.getIds()) {
            Item item = Registries.ITEM.get(id);
            // 检查物品是否属于你的模组
            if (id.getNamespace().equals(MODID)) {
                modItems.add(item);
            }
        }
        return modItems;
    }

    //获取全部注册附魔书
    public static List<ItemStack> getAllModEnchantmentBooks() {
        List<ItemStack> modItems = new ArrayList<>();
        for (Identifier id : Registries.ENCHANTMENT.getIds()) {
            Enchantment enchantment = Registries.ENCHANTMENT.get(id);
            // 检查物品是否属于你的模组
            if (id.getNamespace().equals(MODID)) {
                assert enchantment != null;
                modItems.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, enchantment.getMaxLevel())));
            }
        }
        return modItems;
    }

    //获取翻译key
    public static String getTranslateKey(Item item, String st) {
        return Registries.ITEM.getId(item) + "." + st;
    }


}

package me.tuanzi.draw;

import me.tuanzi.items.utils.ItemUtils;
import me.tuanzi.utils.LivingEntityCustomNbt;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static me.tuanzi.blocks.BlockRegisty.ANGEL_BLOCK;
import static me.tuanzi.blocks.BlockRegisty.TIMESPACE_ORE;
import static me.tuanzi.enchantments.EnchantmentRegistry.*;
import static me.tuanzi.items.ItemsRegisty.*;
import static me.tuanzi.items.ToolRegistry.*;
import static me.tuanzi.items.display.DrawSomething.DRAW_RARITY;
import static me.tuanzi.items.display.DrawSomething.DRAW_RESULT;
import static me.tuanzi.items.utils.ItemUtils.getColor;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;
import static me.tuanzi.utils.StatsRegisty.*;

public class Draw {
    public static final String WEAPON_GOLDEN_COUNT = "WeaponGoldenCount";
    public static final String WEAPON_PURPLE_COUNT = "WeaponPurpleCount";
    public static final String ARMOR_GOLDEN_COUNT = "ArmorGoldenCount";
    public static final String ARMOR_PURPLE_COUNT = "ArmorPurpleCount";
    public static final String MUST_BE_AN_UP_WEAPON_GOLDEN = "MustBeAnUpGoldenWeapon";
    public static final String MUST_BE_AN_UP_ARMOR_GOLDEN = "MustBeAnUpGoldenArmor";
    public static final String MUST_BE_AN_UP_WEAPON_PURPLE = "MustBeAnUpPurpleWeapon";
    public static final String MUST_BE_AN_UP_ARMOR_PURPLE = "MustBeAnUpPurpleArmor";
    public static final String WEAPON_HISTORY = "WEAPON_HISTORY";
    public static final String ARMOR_HISTORY = "ARMOR_HISTORY";
    public static List<ItemStack> weaponGoldPool = new ArrayList<>();
    public static List<ItemStack> weaponUpGoldPool = new ArrayList<>();
    public static List<ItemStack> armorGoldPool = new ArrayList<>();
    public static List<ItemStack> armorUpGoldPool = new ArrayList<>();
    public static List<ItemStack> weaponPurplePool = new ArrayList<>();
    public static List<ItemStack> weaponUpPurplePool = new ArrayList<>();
    public static List<ItemStack> armorPurplePool = new ArrayList<>();
    public static List<ItemStack> armorUpPurplePool = new ArrayList<>();
    public static List<ItemStack> bluePool = new ArrayList<>();
    public static List<ItemStack> anotherPool = new ArrayList<>();

    //初始化池子
    static {
        ItemStack itemStack;
        //金up index:1 武器
        weaponUpGoldPool.add(new ItemStack(DRAGON_SWORD));
        //金up index:2 材料/护甲
        armorUpGoldPool.add(new ItemStack(REPLICATORS, 4));
        //普通金 index:1 武器
        weaponGoldPool.add(new ItemStack(STARDUST_WAND));
        //普通金 index:2 材料/护甲
        armorGoldPool.add(ItemUtils.customPotions(StatusEffects.RESISTANCE, 20 * 60 * 8, 2, 1));
        //同样的金
        addSameGold(new ItemStack(ANGEL_BLOCK));
        addSameGold(new ItemStack(Items.DRAGON_EGG));
        //紫up index:1 武器
        weaponUpPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ADVANCED_SHARPNESS, 1)));
        weaponUpPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ADVANCED_SMITE, 1)));
        weaponUpPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ADVANCED_BANE_OF_ARTHROPODS, 1)));
        weaponUpPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(ADVANCED_POWER, 1)));
        weaponUpPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(EDUCATION, 1)));
        //紫up index:2 材料/护甲
        itemStack = new ItemStack(Items.FIREWORK_ROCKET);
        itemStack.getOrCreateNbt().putBoolean("isInfinite", true);
        itemStack.setCustomName(Text.translatable("item.sakura_server.infinite_firework").withColor(getColor(4)));
        armorUpPurplePool.add(itemStack);
        armorUpPurplePool.add(ItemUtils.customPotions(StatusEffects.RESISTANCE, 20 * 60 * 3, 1, 1));
        //紫 index:1 武器
        weaponPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.LOOTING, 3)));
        weaponPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.SHARPNESS, 3)));
        weaponPurplePool.add(EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.FIRE_ASPECT, 2)));
        //紫 index:2 材料/护甲
        armorPurplePool.add(ItemUtils.customPotions(StatusEffects.RESISTANCE, 20 * 60 * 1, 0, 1));
        armorPurplePool.add(new ItemStack(GAMBLING_PICKAXE));
        armorPurplePool.add(new ItemStack(ANTIMATTER_PICKAXE));
        armorPurplePool.add(new ItemStack(TIMESPACE_ORE));
        //同样的紫
        addSamePurple(new ItemStack(ANGEL_BLOCK_FRAMEWORK));
        addSamePurple(new ItemStack(ANGEL_WINGS_CORE));
        addSamePurple(new ItemStack(SACRED_FEATHERS));
        addSamePurple(new ItemStack(Items.DRAGON_BREATH));
        addSamePurple(new ItemStack(Items.NETHER_STAR));
        addSamePurple(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 8));
        addSamePurple(new ItemStack(Items.NETHERITE_INGOT, 4));
        addSamePurple(new ItemStack(COMPRESSED_COPPER_4, 1));
        addSamePurple(new ItemStack(COMPRESSED_COPPER_2, 10));
        //蓝
        bluePool.add(new ItemStack(Items.EMERALD, 16));
        bluePool.add(new ItemStack(Items.DIAMOND, 16));
        bluePool.add(new ItemStack(Items.GOLD_INGOT, 16));
        bluePool.add(new ItemStack(Items.COPPER_BLOCK, 16));
        bluePool.add(new ItemStack(Items.IRON_BLOCK, 16));
        bluePool.add(new ItemStack(Items.ZOMBIE_SPAWN_EGG, 8));
        bluePool.add(new ItemStack(Items.ALLAY_SPAWN_EGG, 1));
        bluePool.add(new ItemStack(Items.BLAZE_ROD, 16));
        bluePool.add(new ItemStack(Items.HONEYCOMB, 16));
        bluePool.add(new ItemStack(Items.EXPERIENCE_BOTTLE, 32));
        bluePool.add(new ItemStack(Items.GOLDEN_APPLE, 8));
        bluePool.add(new ItemStack(EMERALD_APPLE, 16));
        bluePool.add(new ItemStack(Items.NETHERITE_INGOT));
        bluePool.add(new ItemStack(Items.LEATHER,32));
    }

    private static void addSamePurple(ItemStack itemStack) {
        //紫 index:1
        weaponPurplePool.add(itemStack);
        //紫 index:2
        armorPurplePool.add(itemStack);
    }

    private static void addSameGold(ItemStack itemStack) {
        //普通金 index:1
        weaponGoldPool.add(itemStack);
        //普通金 index:2
        armorGoldPool.add(itemStack);
    }

    public static void pull(PlayerEntity player, int poolIndex, double x, double y, double z) {
        if (!player.getWorld().isClient) {
            LivingEntityCustomNbt customNbt = (LivingEntityCustomNbt) player;
            NbtCompound nbtCompound = customNbt.customNbt();
            //增加统计
            player.increaseStat(DRAW_COUNT, 1);
            int goldCount = 0;
            int purpleCount = 0;
            //获取玩家数据
            //index
            //1 = 武器
            //2 = 护甲
            if (poolIndex == 1) {
                goldCount = nbtCompound.getInt(WEAPON_GOLDEN_COUNT);
                purpleCount = nbtCompound.getInt(WEAPON_PURPLE_COUNT);

            }
            if (poolIndex == 2) {
                goldCount = nbtCompound.getInt(ARMOR_GOLDEN_COUNT);
                purpleCount = nbtCompound.getInt(ARMOR_PURPLE_COUNT);
            }
            //创建一个缓缓向上的物品,伴有粒子效果
            ItemEntity itemEntity = new ItemEntity(player.getWorld(), x, y, z, new ItemStack(DRAW_SOMETHING));
            itemEntity.setGlowing(true);
            itemEntity.setPickupDelay(32767);
            player.getWorld().spawnEntity(itemEntity);
            //添加紫色金色抽数
            purpleCount += 1;
            goldCount += 1;
            if (poolIndex == 1) {
                nbtCompound.putInt(WEAPON_GOLDEN_COUNT, goldCount);
                nbtCompound.putInt(WEAPON_PURPLE_COUNT, purpleCount);
            }
            if (poolIndex == 2) {
                nbtCompound.putInt(ARMOR_GOLDEN_COUNT, goldCount);
                nbtCompound.putInt(ARMOR_PURPLE_COUNT, purpleCount);
            }
            //先检查次数
            //无需,直接套用公式即可.
            double golden_rate = calculateGoldenProbability(goldCount);
            double purple_rate = calculatePurpleProbability(purpleCount);
            double player_rate = player.getRandom().nextDouble();
            //出金了
            if (player_rate <= golden_rate) {
                //金色.
                golden(player, poolIndex, itemEntity);
                //返还10浩瀚之星
                player.giveItemStack(new ItemStack(VAST_STAR, 10));
                //添加紫色抽数.
                if (poolIndex == 1) {
                    nbtCompound.putInt(WEAPON_PURPLE_COUNT, purpleCount + 1);
                    //清除goldcount
                    nbtCompound.putInt(WEAPON_GOLDEN_COUNT, 0);
                }
                if (poolIndex == 2) {
                    nbtCompound.putInt(ARMOR_PURPLE_COUNT, purpleCount + 1);
                    //清除goldcount
                    nbtCompound.putInt(ARMOR_GOLDEN_COUNT, 0);
                }
                return;
            }
            //出紫了
            if (player_rate <= purple_rate) {
                //紫色
                purple(player, poolIndex, itemEntity);
                //返还2浩瀚之星
                player.giveItemStack(new ItemStack(VAST_STAR, 2));
                //添加金色抽数.
                if (poolIndex == 1) {
                    nbtCompound.putInt(WEAPON_GOLDEN_COUNT, goldCount + 1);
                    //清除purple count
                    nbtCompound.putInt(WEAPON_PURPLE_COUNT, 0);
                }
                if (poolIndex == 2) {
                    nbtCompound.putInt(ARMOR_GOLDEN_COUNT, goldCount + 1);
                    //清除purple count
                    nbtCompound.putInt(ARMOR_PURPLE_COUNT, 0);
                }
                return;
            }
            //啥也没出
            blue(player, itemEntity);
        }
    }

    public static void pull(PlayerEntity player, int poolIndex) {
        pull(player, poolIndex, player.getX(), player.getY(), player.getZ());
    }

    public static void pullTen(PlayerEntity player, int poolIndex) {
        List<double[]> point = generateCirclePoints(player.getX(), player.getZ(), 3, 10);
        for (int i = 0; i < 10; i++) {
            if (point.size() == 10) {
                pull(player, poolIndex, point.get(i)[0], player.getY(), point.get(i)[1]);
            } else {
                printDebugLog("错误.");
            }
        }
    }

    // 计算第x次恰好出五星的概率
    private static double calculatePD(int x) {
        return 0.006 * Math.pow(0.994, x - 1);
    }

    // 获取本抽中五星的概率的方法
    public static double calculateGoldenProbability(int currentDraw) {
        if (currentDraw <= 73) {
            return calculatePD(currentDraw);
        } else if (currentDraw >= 90) {
            return 1.0; // 第90抽保证出五星
        } else {
            // 从第74抽开始，概率线性增加至第90抽的100%
            return calculatePD(73) + (1.0 - calculatePD(73)) * ((double) (currentDraw - 73) / (90 - 73));
        }
    }

    public static double calculatePurpleProbability(int purpleCount) {
        // 当抽数小于等于8时，概率为5.10%
        if (purpleCount <= 8) {
            return 5.10 / 100;
        } else {
            // 当抽数大于8时，概率线性增加，直到第10抽为100%
            double increment = (100.00 - 5.10) / (10 - 8);
            return (5.10 + increment * (purpleCount - 8)) / 100;
        }
    }

    public static void golden(PlayerEntity player, int poolIndex, ItemEntity itemEntity) {
        //增加统计
        player.increaseStat(DRAW_GOLDEN_COUNT, 1);
        LivingEntityCustomNbt customNbt = (LivingEntityCustomNbt) player;
        NbtCompound nbtCompound = customNbt.customNbt();
        LivingEntityCustomNbt customNbt1 = (LivingEntityCustomNbt) itemEntity;
        //put成果
        customNbt1.customNbt().putInt(DRAW_RARITY, 3);
        //划定池子
        ArrayList<ItemStack> goldPool = new ArrayList<>();
        ArrayList<ItemStack> upGoldPool = new ArrayList<>();
        boolean isUpGold = false;
        //区分池子
        //武器
        if (poolIndex == 1) {
            goldPool = (ArrayList<ItemStack>) weaponGoldPool;
            upGoldPool = (ArrayList<ItemStack>) weaponUpGoldPool;
            isUpGold = nbtCompound.getBoolean(MUST_BE_AN_UP_WEAPON_GOLDEN);
        }
        //护甲
        if (poolIndex == 2) {
            goldPool = (ArrayList<ItemStack>) armorGoldPool;
            upGoldPool = (ArrayList<ItemStack>) armorUpGoldPool;
            isUpGold = nbtCompound.getBoolean(MUST_BE_AN_UP_ARMOR_GOLDEN);

        }
        //抽取的物品
        ItemStack result;
        //抽取
        if (isUpGold) {
            //大保底
            //修改为小保底
            isUpGold = false;
            //随机抽取up武器
            int stack = player.getRandom().nextInt(upGoldPool.size());
            result = upGoldPool.get(stack);
        } else {
            //小保底
            //是否up?
            if (player.getRandom().nextBoolean()) {
                //up
                //修改为小保底
                //随机抽取up武器
                int stack = player.getRandom().nextInt(upGoldPool.size());
                result = upGoldPool.get(stack);
                //歪了
            } else {
                //修改为大保底
                isUpGold = true;
                //随机抽取非up武器
                int stack = player.getRandom().nextInt(goldPool.size());
                result = goldPool.get(stack);
            }
        }
        //抽取完成
        //put进nbt中.
        NbtCompound nbtCompound1 = new NbtCompound();
        result.writeNbt(nbtCompound1);
        customNbt1.customNbt().put(DRAW_RESULT, nbtCompound1);
        //put
        //区分池子
        //武器
        if (poolIndex == 1) {
            nbtCompound.putBoolean(MUST_BE_AN_UP_WEAPON_GOLDEN, isUpGold);
        }
        //护甲
        if (poolIndex == 2) {
            nbtCompound.putBoolean(MUST_BE_AN_UP_ARMOR_GOLDEN, isUpGold);
        }
    }

    public static void blue(PlayerEntity player, ItemEntity itemEntity) {
        player.increaseStat(DRAW_BLUE_COUNT, 1);
        LivingEntityCustomNbt customNbt1 = (LivingEntityCustomNbt) itemEntity;
        customNbt1.customNbt().putInt(DRAW_RARITY, 1);
        //抽取
        ItemStack result = bluePool.get(player.getRandom().nextInt(bluePool.size()));
        //putNbt
        NbtCompound nbtCompound1 = new NbtCompound();
        result.writeNbt(nbtCompound1);
        customNbt1.customNbt().put(DRAW_RESULT, nbtCompound1);
    }

    public static void purple(PlayerEntity player, int poolIndex, ItemEntity itemEntity) {
        //增加统计
        player.increaseStat(DRAW_PURPLE_COUNT, 1);
        LivingEntityCustomNbt customNbt = (LivingEntityCustomNbt) player;
        NbtCompound nbtCompound = customNbt.customNbt();
        LivingEntityCustomNbt customNbt1 = (LivingEntityCustomNbt) itemEntity;
        //put成果
        customNbt1.customNbt().putInt(DRAW_RARITY, 2);
        //划定池子
        ArrayList<ItemStack> purplePool = new ArrayList<>();
        ArrayList<ItemStack> upPurplePool = new ArrayList<>();
        boolean isUpPurple = false;
        //区分池子
        //武器
        if (poolIndex == 1) {
            purplePool = (ArrayList<ItemStack>) weaponPurplePool;
            upPurplePool = (ArrayList<ItemStack>) weaponUpPurplePool;
            isUpPurple = nbtCompound.getBoolean(MUST_BE_AN_UP_WEAPON_PURPLE);

        }
        //护甲
        if (poolIndex == 2) {
            purplePool = (ArrayList<ItemStack>) armorPurplePool;
            upPurplePool = (ArrayList<ItemStack>) armorUpPurplePool;
            isUpPurple = nbtCompound.getBoolean(MUST_BE_AN_UP_ARMOR_PURPLE);

        }
        //抽取的物品
        ItemStack result;
        //抽取
        if (isUpPurple) {
            //大保底
            //修改为小保底
            isUpPurple = false;
            //随机抽取up武器
            int stack = player.getRandom().nextInt(upPurplePool.size());
            result = upPurplePool.get(stack);
        } else {
            //小保底
            //是否up?
            if (player.getRandom().nextBoolean()) {
                //up
                //修改为小保底
                //随机抽取up武器
                int stack = player.getRandom().nextInt(upPurplePool.size());
                result = upPurplePool.get(stack);
                //歪了
            } else {
                //修改为大保底
                isUpPurple = true;
                //随机抽取非up武器
                int stack = player.getRandom().nextInt(purplePool.size());
                result = purplePool.get(stack);
            }
        }
        //抽取完成
        //put进nbt中.
        NbtCompound nbtCompound1 = new NbtCompound();
        result.writeNbt(nbtCompound1);
        customNbt1.customNbt().put(DRAW_RESULT, nbtCompound1);
        //put
        //区分池子
        //武器
        if (poolIndex == 1) {
            nbtCompound.putBoolean(MUST_BE_AN_UP_WEAPON_PURPLE, isUpPurple);
        }
        //护甲
        if (poolIndex == 2) {
            nbtCompound.putBoolean(MUST_BE_AN_UP_ARMOR_PURPLE, isUpPurple);
        }
    }

    //在平面画圆.
    public static List<double[]> generateCirclePoints(double centerX, double centerZ, int radius, int numberOfPoints) {
        List<double[]> points = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            double angle = 2 * Math.PI * i / numberOfPoints;
            double x = centerX + radius * Math.cos(angle);
            double y = centerZ + radius * Math.sin(angle);
            points.add(new double[]{x, y});
        }
        return points;
    }

}

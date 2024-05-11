package me.tuanzi.blocks.sorter;

import me.tuanzi.blocks.BlockUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.*;

import static me.tuanzi.SakuraServer.SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE;
import static me.tuanzi.blocks.BlockUtils.getAnotherChest;
import static me.tuanzi.blocks.sorter.Sorter.*;

public class SorterBlockEntity extends BlockEntity {

    //找到的所有的箱子
    static List<BlockPos> canSortChestBlockPos = new ArrayList<>();
    //可以移动物品的箱子pos与需要挪动的物品
    static HashMap<BlockPos, Item> teleportItem = new HashMap<>();

    static int tick = 0;
    static int tick2 = 0;

    public SorterBlockEntity(BlockPos pos, BlockState state) {
        super(SORTER_BLOCK_ENTITY_BLOCK_ENTITY_TYPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BlockEntity entity) {
        if (entity instanceof SorterBlockEntity && !world.isClient) {
            //获得此方块上面的方块pos
            BlockPos chestPos = pos.add(Direction.UP.getVector());
            //若是大箱子,则获取另外一个箱子的pos
            BlockPos anotherChestPos = getAnotherChest(world, chestPos);
            //如果是chest
            if (world.getBlockEntity(chestPos) instanceof ChestBlockEntity needSortChestBlockEntity && world instanceof ServerWorld serverWorld) {
                //范围
                Box box = new Box(chestPos).expand(state.get(SORTER_RANGE));
                //显示范围
                if (state.get(SORTER_DISPLAY_RANGE) && world.getTime() % 50 == 0)
                    BlockUtils.displayRange(box, serverWorld, ParticleTypes.GLOW);
                tick++;
                tick2++;
                //需要整理的物品
                ArrayList<ItemStack> needSortItems = new ArrayList<>();
                //需要整理的箱子另外一个BlockEntity
                ChestBlockEntity needSortChestBlockEntity1;
                //遍历添加所有itemStack
                for (int i = 0; i < needSortChestBlockEntity.size(); i++) {
                    if (!needSortChestBlockEntity.getStack(i).isEmpty())
                        needSortItems.add(needSortChestBlockEntity.getStack(i));
                }
                //若是大箱子,遍历添加另一个箱子的ItemStacks
                if (anotherChestPos != null) {
                    needSortChestBlockEntity1 = (ChestBlockEntity) world.getBlockEntity(anotherChestPos);
                    for (int i = 0; i < needSortChestBlockEntity1.size(); i++) {
                        if (!needSortChestBlockEntity1.getStack(i).isEmpty())
                            needSortItems.add(needSortChestBlockEntity1.getStack(i));
                    }
                }
                //如果需要整理的物品>0
                if (needSortItems.size() > 0) {
                    //寻找Range内的箱子
                    //少使用此函数
                    //每5s~10s查找一次箱子在哪
                    if (tick >= world.getRandom().nextBetween(100, 200)) {
                        tick = 0;
                        canSortChestBlockPos.clear();
                        for (int x = (int) box.minX; x < box.maxX; x++) {
                            for (int y = (int) box.minY; y < box.maxY; y++) {
                                for (int z = (int) box.minZ; z < box.maxZ; z++) {
                                    BlockPos blockPos = new BlockPos(x, y, z);
                                    BlockState blockState = world.getBlockState(blockPos);
                                    if (blockState.isOf(Blocks.CHEST) && blockPos != chestPos && blockPos != anotherChestPos) {
                                        canSortChestBlockPos.add(blockPos);
                                    }
                                }
                            }
                        }
                    }
                    //查找他前后左右上下是否贴了物品展示框
                    //贴了则记录坐标,并记录贴了什么物品
                    //然后查询是否是大箱子,是的话
                    //查询是否已经贴上物品?
                    //贴上则不覆盖,没贴则覆盖.
                    //物品展示框
                    List<ItemFrameEntity> itemFrameEntities = world.getEntitiesByClass(ItemFrameEntity.class, box, a -> true);
                    //每5~10s遍历可以移动的箱子
                    if (tick2 >= world.getRandom().nextBetween(100, 200)) {
                        tick2 = 0;
                        teleportItem.clear();
                        for (ItemFrameEntity itemFrameEntity : itemFrameEntities) {
                            //可以移动的箱子
                            BlockPos sortChestPos = itemFrameEntity.getBlockPos().offset(itemFrameEntity.getHorizontalFacing(), -1);
                            if (canSortChestBlockPos.contains(sortChestPos)) {
                                //添加另外一个箱子的坐标,可能是null
                                BlockPos sortChestPos1 = getAnotherChest(world, sortChestPos);
                                //获取分类的物品
                                Item sortItem = itemFrameEntity.getHeldItemStack().getItem();
                                //添加到<可以移动物品的箱子pos与需要挪动的物品>
                                teleportItem.put(sortChestPos, sortItem);
                                if (sortChestPos1 != null) {
                                    //如果包含,则不覆盖
                                    if (!teleportItem.containsKey(sortChestPos1)) {
                                        //有大箱子,额外添加
                                        teleportItem.put(sortChestPos1, sortItem);
                                    }
                                }
                            }
                        }
                    }
                    //如果激活
                    if (state.get(SORTER_USED)) {
                        //遍历需要整理的物品
                        for (ItemStack itemStack : needSortItems) {
                            //获取物品种类
                            Item item = itemStack.getItem();
                            //获得物品种类的所需要传送的箱子坐标
                            Set<BlockPos> sortChestPos = getKeysByValue(teleportItem, item);
                            if (sortChestPos.size() > 0) {
                                //如果坐标数量>0
                                for (BlockPos blockPos : sortChestPos) {
                                    //无需判断是否是大箱子.
                                    ChestBlockEntity sortChestBlockEntity = (ChestBlockEntity) world.getBlockEntity(blockPos);
                                    if(sortChestBlockEntity == null){
                                        return;
                                    }
                                    for (int i = 0; i < sortChestBlockEntity.size(); i++) {
                                        //获得箱子对应格子的ItemStack
                                        ItemStack itemStack1 = sortChestBlockEntity.getStack(i);
                                        //需要整理的物品数量>0
                                        if (itemStack.getCount() > 0) {
                                            //如果箱子内的格子是空的
                                            if (itemStack1.isEmpty()) {
                                                itemStack.decrement(1);
                                                itemStack1 = new ItemStack(item, 1);
                                            }
                                            //如果两个物品相同,且没超数量
                                            if (itemStack1.isOf(item)) {
                                                for (int j = 0; j < 100; j++) {
                                                    if (itemStack1.getCount() >= itemStack1.getMaxCount() || itemStack.getCount() <= 0) {
                                                        break;
                                                    }
                                                    itemStack.decrement(1);
                                                    itemStack1.increment(1);
                                                }
                                            }
                                            //更新箱子.
                                            sortChestBlockEntity.setStack(i, itemStack1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static <K, V> Set<K> getKeysByValue(Map<K, V> map, V value) {
        Set<K> keys = new HashSet<>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }


}

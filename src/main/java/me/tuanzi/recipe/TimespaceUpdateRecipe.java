package me.tuanzi.recipe;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;

import static me.tuanzi.SakuraServer.*;

//todo:使用合成.
public class TimespaceUpdateRecipe extends SpecialCraftingRecipe {
    int slot = 0;
    ItemStack hammaer;

    public TimespaceUpdateRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        ArrayList<ItemStack> itemStacks = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack1 = inventory.getStack(i);
            if (!itemStack1.isEmpty()) {
                if (itemStack1.isOf(TIMESPACE_GEM)) {
                    itemStacks.add(itemStack1);
                }
                if (itemStack1.isOf(NETHERITE_HAMMER)) {
                    itemStacks.add(itemStack1);
                }
                if (itemStack1.getItem() == Items.NETHERITE_AXE
                        || itemStack1.getItem() == Items.NETHERITE_PICKAXE
                        || itemStack1.getItem() == Items.NETHERITE_SHOVEL
                        || itemStack1.getItem() == Items.NETHERITE_HOE
                        || itemStack1.getItem() == Items.NETHERITE_SWORD
                ) {
                    itemStacks.add(itemStack1);
                }
            }
        }
        return itemStacks.size() == 3;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack itemStack = ItemStack.EMPTY;
        int type = 0;
        boolean hasGem = false;
        boolean hasNetherite = false;
        boolean hasHammer = false;
        int count = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                count += 1;
                if (stack.isOf(NETHERITE_HAMMER)) {
                    hammaer = stack;
                    hasHammer = true;
                }
                if (stack.isOf(TIMESPACE_GEM)) {
                    hasGem = true;
                }
                if (stack.getItem() == Items.NETHERITE_AXE) {
                    hasNetherite = true;
                    itemStack = stack;
                    type = 1;
                }
                if (stack.getItem() == Items.NETHERITE_SHOVEL) {
                    hasNetherite = true;
                    itemStack = stack;
                    type = 2;
                }
                if (stack.getItem() == Items.NETHERITE_PICKAXE) {
                    hasNetherite = true;
                    itemStack = stack;
                    type = 3;
                }
                if (stack.getItem() == Items.NETHERITE_HOE) {
                    hasNetherite = true;
                    itemStack = stack;
                    type = 4;
                }
                if (stack.getItem() == Items.NETHERITE_SWORD) {
                    hasNetherite = true;
                    itemStack = stack;
                    type = 5;
                }
            }

        }
        if (hasGem && hasHammer && hasNetherite && count == 3) {
            ItemStack result = ItemStack.EMPTY;
            if (type == 1) {
                result= TIMESPACE_AXE.getDefaultStack();
            }
            if (type == 2) {
                result= TIMESPACE_SHOVEL.getDefaultStack();
            }
            if (type == 3) {
                result= TIMESPACE_PICKAXE.getDefaultStack();
            }
            if (type == 4) {
                result= TIMESPACE_HOE.getDefaultStack();
            }
            if (type == 5) {
                result= TIMESPACE_SWORD.getDefaultStack();
            }
            result.setNbt(itemStack.getNbt());
            return result;
        }


        /*int count = 0;
        int type = 0;
        boolean hasGem = false;
        boolean hasNetherite = false;
        boolean hasHammer = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty() && !stack.isOf(Items.AIR)) {
                count++;
                if (stack.getItem() == Items.NETHERITE_AXE) {
                    hasNetherite = true;
                    type = 1;
                } else if (stack.getItem() == TIMESPACE_GEM) {
                    hasGem = true;
                } else if (stack.getItem() == NETHERITE_HAMMER) {
                    hasHammer = true;
                    slot = i;
                    itemStack = stack;
                } else if (stack.getItem() == Items.NETHERITE_SHOVEL) {
                    hasNetherite = true;
                    type = 2;
                } else if (stack.getItem() == Items.NETHERITE_PICKAXE) {
                    hasNetherite = true;
                    type = 3;
                } else if (stack.getItem() == Items.NETHERITE_HOE) {
                    hasNetherite = true;
                    type = 4;
                } else if (stack.getItem() == Items.NETHERITE_SWORD) {
                    hasNetherite = true;
                    type = 5;
                }
            }
        }
        if (hasGem && hasHammer && hasNetherite && count == 3) {
            if (type == 1) {
                return TIMESPACE_AXE.getDefaultStack();
            }
            if (type == 2) {
                return TIMESPACE_SHOVEL.getDefaultStack();
            }
            if (type == 3) {
                return TIMESPACE_PICKAXE.getDefaultStack();
            }
            if (type == 4) {
                return TIMESPACE_HOE.getDefaultStack();
            }
            if (type == 5) {
                return TIMESPACE_SWORD.getDefaultStack();
            }
        }*/
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return TIMESPACE_UPDATE_RECIPE_RECIPE_SERIALIZER;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(9, ItemStack.EMPTY);
        hammaer.setDamage(hammaer.getDamage() + 1);
        defaultedList.set(slot, hammaer);
        return defaultedList;
    }

    public static void changeItem(ItemStack itemStack1 , ItemStack itemStack2){
//        Item item = itemStack2.getItem();
        itemStack2.setNbt(itemStack1.getNbt());
    }

}

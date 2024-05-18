package me.tuanzi.items.msic;

import me.tuanzi.items.utils.SakuraItem;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class NetheriteHammer extends SakuraItem {

    public NetheriteHammer(Settings settings) {
        super(settings);
    }

    /**
     * Returns a leftover item stack after {@code stack} is consumed in a recipe.
     * (This is also known as "recipe remainder".)
     * For example, using a lava bucket in a furnace as fuel will leave an empty bucket.
     *
     * <p>Here is an example for a recipe remainder that increments the item's damage.
     *
     * <pre>{@code
     *  if (stack.getDamage() < stack.getMaxDamage() - 1) {
     *  	ItemStack moreDamaged = stack.copy();
     *  	moreDamaged.setDamage(stack.getDamage() + 1);
     *  	return moreDamaged;
     *  }
     *
     *  return ItemStack.EMPTY;
     * }</pre>
     *
     *
     * <p>This is a stack-aware version of {@link Item#getRecipeRemainder()}.
     *
     * <p>Note that simple item remainders can also be set via {@link Item.Settings#recipeRemainder(Item)}.
     *
     * <p>If you want to get a remainder for a stack,
     * is recommended to use the stack version of this method: {@link FabricItemStack#getRecipeRemainder()}.
     *
     * @param stack the consumed {@link ItemStack}
     * @return the leftover item stack
     */
    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        if (stack.getDamage() < stack.getMaxDamage() - 1) {
            ItemStack moreDamaged = stack.copy();
            moreDamaged.setDamage(stack.getDamage() + 1);
            return moreDamaged;
        }
        return ItemStack.EMPTY;
    }
}

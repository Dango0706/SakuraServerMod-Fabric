package me.tuanzi.items.functional;

import me.tuanzi.items.utils.CanNotBeCopy;
import me.tuanzi.items.utils.SakuraItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulGem extends SakuraItem implements CanNotBeCopy {

    public SoulGem(Settings settings) {
        super(settings);
    }

    /**
     * Checks if the glint effect should be applied when the item is rendered.
     *
     * <p>By default, returns true if the item has enchantments.
     *
     * @param stack
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        if (stack.hasNbt())
            return stack.getNbt().getBoolean("hasEntity");
        return false;
    }
/*星辰之诺 (Stellar Promise)
梦境之钥 (Dream Key)
* */

    /**
     * Called by the client to append tooltips to an item. Subclasses can override
     * this and add custom tooltips to {@code tooltip} list.
     *
     * @param stack
     * @param world
     * @param tooltip the list of tooltips to show
     * @param context
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt() && stack.getNbt().contains("UUID"))
            tooltip.add(Text.empty().append(Text.translatable("item.sakura_server.soul_gem.uuid")).append(Text.literal(stack.getNbt().getUuid("UUID").toString())));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

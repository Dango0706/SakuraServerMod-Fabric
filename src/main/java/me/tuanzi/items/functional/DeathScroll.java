package me.tuanzi.items.functional;

import me.tuanzi.items.utils.SakuraItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;

public class DeathScroll extends SakuraItem {

    public DeathScroll(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient && user instanceof PlayerEntity player) {
            if (player.getLastDeathPos().isEmpty()) {
                player.sendMessage(Text.translatable("item.sakura_server.death_scroll.not_death"));
            } else {
                GlobalPos pos = player.getLastDeathPos().get();
                if (pos.getDimension().getValue().equals(world.getDimension().effects())) {
                    player.requestTeleport(pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ());
                    world.playSound(null,pos.getPos().getX(), pos.getPos().getY(), pos.getPos().getZ(), SoundEvents.BLOCK_PORTAL_TRAVEL, SoundCategory.PLAYERS);
                    if(!player.isSpectator() && !player.isCreative())
                        stack.decrement(1);
                }else{
                    player.sendMessage(Text.translatable("item.sakura_server.death_scroll.not_dimension"));
                }
            }
        }

        return super.finishUsing(stack, world, user);
    }
}

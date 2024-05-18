package me.tuanzi.items.foods;

import me.tuanzi.items.utils.SakuraFoodItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillageGossipType;
import net.minecraft.world.World;

public class EmeraldApple extends SakuraFoodItem {

    public EmeraldApple() {
        super(new FabricItemSettings().food(new FoodComponent.Builder().
                hunger(6)
                //补充的饥饿值 * 2 * 这个 = 最终饱和度
                //(float)hunger * saturationModifier * 2.0f
                .saturationModifier(1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20 * 180, 0), 1)
                .statusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20 * 180, 1)), 1)
                .statusEffect(new StatusEffectInstance(new StatusEffectInstance(StatusEffects.HASTE, 20 * 180, 3)), 0.4f)
                .meat()
                .alwaysEdible()
                .build()));
    }

    @Override
    public int getRarity() {
        return 2;
    }

    /**
     * Called on both the client and the server when a player uses the item on an entity.
     *
     * <p>This method is called on both the logical client and logical server, so take caution
     * when overriding this method. The logical side can be checked using {@link
     * World#isClient}.
     *
     * <p>This should be used if the item can be used on multiple types of entities,
     * such as name tags or saddles.
     *
     * @param stack
     * @param user
     * @param entity
     * @param hand
     * @return the action result
     */
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof VillagerEntity villager && !villager.getWorld().isClient) {
            boolean isMax = false;
            for (TradeOffer offer : villager.getOffers()) {
                if (offer.isDisabled()) {
                    isMax = true;
                    break;
                }
            }
            if (isMax || villager.getHealth() < villager.getMaxHealth()) {
                for (TradeOffer offer : villager.getOffers()) {
                    if (offer.isDisabled()) {
                        offer.resetUses();
                        break;
                    }
                }
                villager.heal(4.0f);
                villager.getGossip().remove(VillageGossipType.MINOR_NEGATIVE);
                user.playSound(SoundEvents.ENTITY_VILLAGER_YES, SoundCategory.PLAYERS, 1, 1.2f);
                villager.eatFood(villager.getWorld(), stack);
                return ActionResult.SUCCESS;
            }

        }
        return ActionResult.PASS;
    }
}

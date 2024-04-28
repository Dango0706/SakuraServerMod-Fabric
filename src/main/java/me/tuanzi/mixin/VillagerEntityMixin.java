package me.tuanzi.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.InteractionObserver;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.VillagerDataContainer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.tuanzi.SakuraServer.EMERALD_APPLE;
import static me.tuanzi.SakuraServer.MOVE_VILLAGER;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends MerchantEntity
        implements InteractionObserver,
        VillagerDataContainer {
    public VillagerEntityMixin(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(EMERALD_APPLE)) {
            itemStack.useOnEntity(player, this, hand);
            cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
            cir.cancel();
        }
       if(itemStack.isOf(MOVE_VILLAGER)){
           itemStack.useOnEntity(player,this, hand);
           cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
           cir.cancel();
       }


    }

}

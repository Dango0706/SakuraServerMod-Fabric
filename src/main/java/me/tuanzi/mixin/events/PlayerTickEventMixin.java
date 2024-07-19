package me.tuanzi.mixin.events;

import me.tuanzi.events.player.PlayerTickEvent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerTickEventMixin extends LivingEntity {

    protected PlayerTickEventMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At(value = "HEAD"),method = "tick")
    private void onPlayerTick(CallbackInfo ci) {
        PlayerTickEvent.EVENT.invoker().onPlayerTick((PlayerEntity) (Object) this);
    }

}

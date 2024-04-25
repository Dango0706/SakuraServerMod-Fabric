package me.tuanzi.mixin;

import me.tuanzi.events.LivingEntityEffectRemoveEvent;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityRemoveEffectMixin extends Entity
        implements Attackable {

    public LivingEntityRemoveEffectMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "onStatusEffectRemoved", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateAttributes()V"/*,shift = At.Shift.AFTER*/))
    protected void onStatusEffectRemoved(StatusEffectInstance effect, CallbackInfo ci) {
        LivingEntityEffectRemoveEvent.EVENT.invoker().remove((LivingEntity) (Object) this, effect);
    }

}

package me.tuanzi.mixin;

import me.tuanzi.events.LivingEntityHealEvent;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityHealMixin extends Entity
        implements Attackable {



    public LivingEntityHealMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "heal", at = @At(value = "HEAD"), cancellable = true)
    public void heal(float amount, CallbackInfo ci) {
        boolean b = LivingEntityHealEvent.EVENT.invoker().heal((LivingEntity) (Object) this, amount);
        if (b)
            ci.cancel();


    }

}

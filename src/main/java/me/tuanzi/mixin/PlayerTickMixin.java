package me.tuanzi.mixin;

import me.tuanzi.events.PlayerTickEvent;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerTickMixin {

    @Inject(at = @At(value = "HEAD"),method = "tick")
    private void tick(CallbackInfo ci){
        PlayerTickEvent.EVENT.invoker().tick((PlayerEntity) (Object) this);
    }

}

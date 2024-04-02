package me.tuanzi.mixin;

import me.tuanzi.events.PlayerJumpEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerJumpMixin {

    @Inject(at = @At(value = "HEAD"), method = "jump", cancellable = true)
    private void jump(final CallbackInfo ci) {
        ActionResult actionResult = PlayerJumpEvent.EVENT.invoker().interact((PlayerEntity) (Object) this);
        if (actionResult == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}

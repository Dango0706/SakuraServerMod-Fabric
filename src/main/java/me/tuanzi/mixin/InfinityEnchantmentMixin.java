package me.tuanzi.mixin;

import net.minecraft.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.enchantment.InfinityEnchantment.class)
public class InfinityEnchantmentMixin {
	@Inject(at = @At("RETURN"), method = "canAccept", cancellable = true)
	private void inject(Enchantment other, CallbackInfoReturnable<Boolean> cir) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
		cir.setReturnValue(true);
	}
}
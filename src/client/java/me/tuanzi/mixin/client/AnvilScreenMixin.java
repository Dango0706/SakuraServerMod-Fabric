package me.tuanzi.mixin.client;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

    @ModifyConstant(method = "drawForeground" ,constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        return Integer.MAX_VALUE;
    }

}

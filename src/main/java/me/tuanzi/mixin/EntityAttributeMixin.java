package me.tuanzi.mixin;

import net.minecraft.entity.attribute.EntityAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityAttributes.class)
public class EntityAttributeMixin {
    @ModifyArg(method = "<clinit>",at =   @At(value = "INVOKE",target = "Lnet/minecraft/entity/attribute/ClampedEntityAttribute;<init>(Ljava/lang/String;DDD)V"),index = 3)
    private static double aDouble(double fallback){
        return Double.MAX_VALUE;
    }
}

package me.tuanzi.mixin;

import me.tuanzi.utils.LivingEntityCustomNbt;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class LivingEntityCustomNbtMixin implements LivingEntityCustomNbt {

    private NbtCompound custom;

    @Override
    public NbtCompound customNbt() {
        if(this.custom == null){
            this.custom = new NbtCompound();
        }
        return custom;
    }

    @Inject(method = "writeNbt", at = @At(value = "HEAD"))
    public void writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if(this.custom != null){
            nbt.put("sakura.custom",custom);
        }
    }

    @Inject(method = "readNbt", at = @At(value = "HEAD"))
    public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if(nbt.contains("sakura.custom",10)){
            custom = nbt.getCompound("sakura.custom");
        }
    }

}

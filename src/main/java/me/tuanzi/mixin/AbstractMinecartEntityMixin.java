package me.tuanzi.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin
        extends VehicleEntity {
    public AbstractMinecartEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @ModifyArg(method = "moveOnRail", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/vehicle/AbstractMinecartEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", ordinal = 9), index = 0)
    protected Vec3d moveOnRail(Vec3d par1) {
        if (!this.getWorld().isClient() && this.getWorld().getBlockState(this.getBlockPos().add(0, -1, 0)).isOf(Blocks.REDSTONE_BLOCK)) {
            Vec3d vec3d5dd = this.getVelocity();
            double ddd = vec3d5dd.horizontalLength();
            return vec3d5dd.add(vec3d5dd.x / ddd * 0.12, 0.0, vec3d5dd.z / ddd * 0.12);
        }
        return par1;
    }

    @Inject(method = "getMaxSpeed", at = @At(value = "HEAD"), cancellable = true)
    protected void getMaxSpeed(CallbackInfoReturnable<Double> cir) {
        if (!this.getWorld().isClient() && this.getWorld().getBlockState(this.getBlockPos().add(0, -1, 0)).isOf(Blocks.REDSTONE_BLOCK)) {
            cir.setReturnValue((this.isTouchingWater() ? 10.0 : 20.0) / 20.0);
        }
    }

}

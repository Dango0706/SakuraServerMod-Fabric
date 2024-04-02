package me.tuanzi.mixin;

import me.tuanzi.events.LivingEntityFinalDamage;
import me.tuanzi.events.LivingEntityModifyAppliedDamage;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


/*
 * 计算盔甲与药水效果后最终造成的伤害.
 * 计算盔甲抵御伤害:applyArmorToDamage
 * 计算药水效果以及保护等附魔抵御伤害:modifyAppliedDamage
 * */
@Mixin(LivingEntity.class)
public abstract class LivingEntityFinalDamageMixin extends Entity implements Attackable {

    public LivingEntityFinalDamageMixin(EntityType<?> type, World world) {
        super(type, world);
    }


    @Inject(method = "applyDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;modifyAppliedDamage(Lnet/minecraft/entity/damage/DamageSource;F)F", shift = At.Shift.AFTER))
    public void applyDamage(DamageSource source, float amount, CallbackInfo ci) {
        LivingEntityFinalDamage.EVENT.invoker().applyDamage((LivingEntity) (Object) this, source, amount);
    }
    //todo
    @Inject(method = "modifyAppliedDamage", at = @At(value = "HEAD"))
    protected void modifyAppliedDamage(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        LivingEntityModifyAppliedDamage.EFFECT.invoker().modifyAppliedDamageEffect((LivingEntity) (Object) this, source, amount/*, this.hasStatusEffect(StatusEffects.RESISTANCE) ? this.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1 : 0*/);
    }
    //todo
    @Inject(method = "modifyAppliedDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getInflictedDamage(FF)F"/*,shift = At.Shift.AFTER*/))
    protected void modifyAppliedDamage2(DamageSource source, float amount, CallbackInfoReturnable<Float> cir/*, @Local(ordinal = 0) int i*/) {
        LivingEntityModifyAppliedDamage.PROTECTION.invoker().modifyAppliedDamageProtection((LivingEntity) (Object) this, source, amount/*, i*/);
    }


}

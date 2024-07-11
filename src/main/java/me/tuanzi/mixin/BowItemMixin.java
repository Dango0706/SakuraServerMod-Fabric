package me.tuanzi.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static me.tuanzi.enchantments.EnchantmentRegistry.ADVANCED_POWER;
import static me.tuanzi.utils.LoggerUtils.printDebugLog;

@Mixin(BowItem.class)
public abstract class BowItemMixin extends RangedWeaponItem
        implements Vanishable {
    public BowItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;setVelocity(Lnet/minecraft/entity/Entity;FFFFF)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci,
                               PlayerEntity playerEntity, boolean bl, ItemStack itemStack, int i, float f, boolean bl2,
                               ArrowItem arrowItem, PersistentProjectileEntity persistentProjectileEntity) {
        int level;
        if ((level = EnchantmentHelper.getLevel(ADVANCED_POWER, stack)) > 0) {
            printDebugLog("vec.length:" + persistentProjectileEntity.getVelocity().length());
            //基础 = 2
            persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) level * 1.1);
            //length ≈ 3
            //原版:15~24.5 damage = 5
            //22.5 ~ 35.75 damage = 7.5
            //length * damage + (0 ~ ( (length * damage) / 2 + 2)
            printDebugLog("getDamage:" + persistentProjectileEntity.getDamage());
        }
    }


}

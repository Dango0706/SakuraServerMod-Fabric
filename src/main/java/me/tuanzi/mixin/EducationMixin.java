package me.tuanzi.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.tuanzi.SakuraServer.EDUCATION;

@Mixin(LivingEntity.class)
public abstract class EducationMixin extends Entity
        implements Attackable {

    @Shadow protected abstract void dropXp();

    public EducationMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "drop", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;dropXp()V"))
    protected void drop(DamageSource source, CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (source.getAttacker() instanceof PlayerEntity player) {
            int level = EnchantmentHelper.getLevel(EDUCATION, player.getMainHandStack());
            if (level > 0) {
                /*if (this.getWorld() instanceof ServerWorld && !livingEntity.isExperienceDroppingDisabled() && (livingEntity.shouldAlwaysDropXp() || livingEntity.playerHitTimer > 0 && livingEntity.shouldDropXp() && this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT))) {
                    ExperienceOrbEntity.spawn((ServerWorld) this.getWorld(), this.getPos(), livingEntity.getXpToDrop() * level);
                }*/
                for (int i = 0; i < level; i++) {
                    this.dropXp();
                }
            }
        }
    }

}

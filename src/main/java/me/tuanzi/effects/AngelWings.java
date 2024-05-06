package me.tuanzi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

import java.util.Objects;

public class AngelWings extends StatusEffect {

    public AngelWings() {
        super(StatusEffectCategory.BENEFICIAL, 0x000000);
    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration == 20 * 10
                || duration == 20 * 5
                || duration == 20 * 4
                || duration == 20 * 3
                || duration == 20 * 2
                || duration == 20 * 1;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player && !player.getWorld().isClient && !player.isCreative() && !player.isSpectator()) {
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        }
        super.onApplied(entity, amplifier);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        int time;
        if (entity instanceof ServerPlayerEntity player && !player.getWorld().isClient() && !player.isCreative() && !player.isSpectator()) {
            time = Objects.requireNonNull(player.getStatusEffect(this)).getDuration() / 20;
            player.sendMessage(Text.translatable("effect.sakura_server.angle_wings_warning", time));
            if (time <= 5)
                player.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP), SoundCategory.PLAYERS, player.getX(), player.getY(), player.getZ(), 1, 0.8f, player.getWorld().getRandom().nextLong()));
        }
        super.applyUpdateEffect(entity, amplifier);
    }

}

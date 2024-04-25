package me.tuanzi.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static me.tuanzi.SakuraServer.SOUL_GEM;

@Mixin(WolfEntity.class)
public abstract class WolfEntityMixin extends TameableEntity implements Angerable {

    public WolfEntityMixin(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
    }


    @Inject(method = "interactMob", at = @At(value = "HEAD"), cancellable = true)
    public void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(SOUL_GEM)) {
            if (/*true*/ !player.getWorld().isClient()) {
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                if (this.isTamed()) {
                    if (this.isOwner(player)) {
                        if (itemStack.hasNbt()) {
                            if (itemStack.getNbt().getBoolean("hasEntity")) {
                                if(player.getPose() == EntityPose.CROUCHING && itemStack.getNbt().getUuid("UUID").equals(this.uuid)){
                                    itemStack.setNbt(new NbtCompound());
                                    player.setStackInHand(hand,itemStack);
                                    serverPlayer.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.ENTITY_PLAYER_LEVELUP), SoundCategory.PLAYERS, player.getX(), player.getY(), player.getZ(), 1, 1.0f, player.getWorld().getRandom().nextLong()));
                                    player.sendMessage(Text.translatable("item.sakura_server.soul_gem.unbound"));
                                }else{
                                    player.sendMessage(Text.translatable("item.sakura_server.soul_gem.fail.has_entity"));
                                }
                            } else {
                                NbtCompound nbtCompound = itemStack.getOrCreateNbt();
                                nbtCompound.putBoolean("hasEntity", true);
                                nbtCompound.putBoolean("entityDeath", false);
                                nbtCompound.putUuid("UUID", this.uuid);
                                itemStack.writeNbt(nbtCompound);
                                player.setStackInHand(hand,itemStack);
                                serverPlayer.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.ENTITY_PLAYER_LEVELUP), SoundCategory.PLAYERS, player.getX(), player.getY(), player.getZ(), 1, 1.2f, player.getWorld().getRandom().nextLong()));

                            }
                        } else {
                            NbtCompound nbtCompound = itemStack.getOrCreateNbt();
                            nbtCompound.putBoolean("hasEntity", true);
                            nbtCompound.putBoolean("entityDeath", false);
                            nbtCompound.putUuid("UUID", this.uuid);
                            itemStack.writeNbt(nbtCompound);
                            player.setStackInHand(hand,itemStack);
                            serverPlayer.networkHandler.sendPacket(new PlaySoundS2CPacket(RegistryEntry.of(SoundEvents.ENTITY_PLAYER_LEVELUP), SoundCategory.PLAYERS, player.getX(), player.getY(), player.getZ(), 1, 1.2f, player.getWorld().getRandom().nextLong()));

                        }
                    }else{
                        player.sendMessage(Text.translatable("item.sakura_server.soul_gem.fail.not_owner"));
                    }
                } else {
                    player.sendMessage(Text.translatable("item.sakura_server.soul_gem.fail.not_tamed"));
                }

            }
            cir.setReturnValue(ActionResult.PASS);
            cir.cancel();
        }


    }


}



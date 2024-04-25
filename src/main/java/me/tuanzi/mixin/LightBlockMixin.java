package me.tuanzi.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LightBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightBlock.class)
public abstract class LightBlockMixin extends Block
        implements Waterloggable {

    @Shadow @Final public static IntProperty LEVEL_15;

    public LightBlockMixin(Settings settings) {
        super(settings);
    }


    @Inject(method = "onUse",at = @At(value = "HEAD"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if(!world.isClient && !player.isCreativeLevelTwoOp()){
            world.setBlockState(pos, state.cycle(LEVEL_15), Block.NOTIFY_LISTENERS);
        }
    }



    }

package me.tuanzi.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import java.util.ArrayList;

import static me.tuanzi.SakuraServer.MODID;
import static net.minecraft.server.command.CommandManager.literal;

public class RemoveNBT {

    public static void removeLore(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("removelore")
                .executes(RemoveNBT::remove));
    }

    public static int remove(CommandContext<ServerCommandSource> ctx) {
        final ServerCommandSource source = ctx.getSource();
        int count = 0;
        if (!source.isExecutedByPlayer())
            source.sendError(Text.translatable("commands." + MODID + ".error.not_player"));
        final PlayerEntity self = source.getPlayer();
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            assert self != null;
            ItemStack itemStack = self.getInventory().getStack(i);
            if (itemStack.getItem() != Items.AIR)
                itemStacks.add(itemStack);
        }
        for (ItemStack itemStack : itemStacks) {
            NbtCompound nbtCompound = itemStack.getSubNbt("display");
            if (nbtCompound != null) {
                if (nbtCompound.contains("Lore")) {
                    nbtCompound.remove("Lore");
                    count++;
                }
            } else
                continue;
            itemStack.setSubNbt("display", nbtCompound);
        }
        source.sendMessage(Text.translatable("commands." + MODID + ".removeLore.ok", count));
        return Command.SINGLE_SUCCESS;

    }

}

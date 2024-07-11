package me.tuanzi.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import me.tuanzi.stats.StateSaverAndLoader;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static me.tuanzi.utils.StatsRegisty.*;
import static net.minecraft.server.command.CommandManager.literal;

public class ServerStates {

    public static void serverStates(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("ss")
                .requires(a -> a.hasPermissionLevel(2))
                .executes(ServerStates::states)
                .then(literal("self"))
                .requires(ServerCommandSource::isExecutedByPlayer)
                .executes(ServerStates::states)

        );
    }

    public static int states(CommandContext<ServerCommandSource> ctx) {

        MinecraftServer server = ctx.getSource().getServer();
        StateSaverAndLoader serverState = StateSaverAndLoader.getServerState(server);
        ServerCommandSource source = ctx.getSource();
        source.sendMessage(Text.literal("======服务器统计======").withColor(0xcc0000));
        source.sendMessage(Text.literal(
                "服务器玩家总共挖了:§6" +
                        serverState.totalBreakBlock +
                        "§r个方块,你总共挖了:§6" +
                        source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(BREAK_BLOCK_COUNT)) +
                        "§r个方块,你在其中占比§6" +
                        formatDouble((double) source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(BREAK_BLOCK_COUNT)) / serverState.totalBreakBlock)
        ));
        source.sendMessage(Text.literal(
                "服务器玩家总共在线了:§6" +
                        serverState.totalOnlineTime +
                        "§r分钟,你总共在线了:§6" +
                        source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_ONLINE_TIME)) +
                        "§r分钟,你在其中占比§6" +
                        formatDouble((double) source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_ONLINE_TIME)) / serverState.totalOnlineTime)
        ));
        source.sendMessage(Text.literal(
                "服务器玩家总共造成了:§6" +
                        serverState.totalDamageCaused / 2 +
                        "§r❤,你总共造成了:§6" +
                        source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_CAUSED)) / 20 +
                        "§r❤,你在其中占比§6" +
                        formatDouble((double) source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_CAUSED)) / 10 / serverState.totalDamageCaused)
        ));
        source.sendMessage(Text.literal(
                "服务器玩家总共对玩家造成了:§6" +
                        serverState.totalPlayerDamageCaused / 2 +
                        "§r❤,你总共对玩家造成了:§6" +
                        source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_CAUSED)) / 20 +
                        "§r❤,你在其中占比§6" +
                        formatDouble((double) source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_CAUSED)) / 10 / serverState.totalPlayerDamageCaused)
        ));
        source.sendMessage(Text.literal(
                "服务器玩家总共受到了:§6" +
                        serverState.totalDamageTaken / 2 +
                        "§r❤,你总共受到了:§6" +
                        source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_TAKEN)) / 20 +
                        "§r❤,你在其中占比§6" +
                        formatDouble((double) source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_DAMAGE_TAKEN)) / 10 / serverState.totalDamageTaken)
        ));
        source.sendMessage(Text.literal(
                "服务器玩家总共受到来自玩家:§6" +
                        serverState.totalPlayerDamageTaken / 2 +
                        "§r❤,你总共受到来自玩家:§6" +
                        source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_TAKEN)) / 20 +
                        "§r❤,你在其中占比§6" +
                        formatDouble((double) source.getPlayer().getStatHandler().getStat(Stats.CUSTOM.getOrCreateStat(TOTAL_PLAYER_DAMAGE_TAKEN)) / 10 / serverState.totalPlayerDamageTaken)
        ));
        source.sendMessage(Text.literal("=====服务器统计结束=====").withColor(0xcc0000));
        return Command.SINGLE_SUCCESS;

    }

    public static String formatDouble(double value) {
        BigDecimal bd = new BigDecimal(value * 100).setScale(2, RoundingMode.HALF_UP);
        return bd + "%";
    }

}

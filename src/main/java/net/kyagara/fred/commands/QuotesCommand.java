package net.kyagara.fred.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.random.Random;

public class QuotesCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("quotes").executes(ctx -> run(ctx.getSource())));
    }

    private static int run(ServerCommandSource context) throws CommandSyntaxException {
        final int random = Random.create().nextBetween(0, FredConfig.quotesList.size() - 1);

        context.getServer().getPlayerManager()
                .broadcast(ModCommands.CreateCommandText(FredConfig.quotesList.get(random)), false);

        return Command.SINGLE_SUCCESS;
    }
}

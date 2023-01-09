package net.kyagara.fred.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kyagara.fred.Fred;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.random.Random;

public class QuotesCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("quotes").executes(ctx -> run(ctx.getSource())));
	}

	private static int run(ServerCommandSource context) {
		int random = Random.create().nextBetween(0, Fred.CONFIG.quotesList().size() - 1);

		context.getServer().getPlayerManager().broadcast(ModCommands.CreateCommandText(Fred.CONFIG.quotesList().get(random)), false);

		return Command.SINGLE_SUCCESS;
	}
}

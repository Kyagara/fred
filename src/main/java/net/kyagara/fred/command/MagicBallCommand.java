package net.kyagara.fred.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.kyagara.fred.Fred;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.random.Random;

public class MagicBallCommand {
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("8ball").executes(ctx -> run(ctx.getSource())));
	}

	private static int run(ServerCommandSource context) {
		int random = Random.create().nextBetween(0, Fred.CONFIG.magicBallAnswersList().size() - 1);
		String answer = "The Magic 8 Ball has decided: " + Fred.CONFIG.magicBallAnswersList().get(random);
		context.getServer().getPlayerManager().broadcast(ModCommands.CreateCommandText(answer), false);
		return Command.SINGLE_SUCCESS;
	}
}

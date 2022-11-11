package net.kyagara.fred.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.kyagara.fred.config.FredConfig;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.random.Random;

public class MagicBallCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("8ball").executes(ctx -> run(ctx.getSource())));
    }

    private static int run(ServerCommandSource context) throws CommandSyntaxException {
        final int random = Random.create().nextBetween(0, FredConfig.magicBallAnswersList.size() - 1);

        final String answer = "The Magic 8 Ball has decided: " + FredConfig.magicBallAnswersList.get(random);

        context.getServer().getPlayerManager().broadcast(ModCommands.CreateCommandText(answer), false);

        return Command.SINGLE_SUCCESS;
    }
}

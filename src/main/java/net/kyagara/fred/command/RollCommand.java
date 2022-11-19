package net.kyagara.fred.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.random.Random;

public class RollCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("roll").then(CommandManager.argument("number", IntegerArgumentType.integer())
                        .executes(ctx -> run(ctx.getSource(), ctx.getArgument("number", Integer.class)))));
    }

    private static int run(ServerCommandSource context, int number) throws CommandSyntaxException {
        final int random = Random.create().nextBetween(1, number);

        final String message = context.getDisplayName().getString() + " rolls " + random;

        context.getServer().getPlayerManager().broadcast(ModCommands.CreateCommandText(message), false);

        return Command.SINGLE_SUCCESS;
    }
}

package net.kyagara.fred.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class UnflipTableCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("unflip").executes(ctx -> run(ctx.getSource())));
    }

    private static int run(FabricClientCommandSource context) throws CommandSyntaxException {
        context.getPlayer().sendChatMessage("┬─┬ノ( º _ ºノ)", null);

        return Command.SINGLE_SUCCESS;
    }
}

package net.kyagara.fred.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.network.ClientPlayerEntity;

public class CoordsCommand {
	public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(ClientCommandManager.literal("coords").executes(ctx -> run(ctx.getSource())));
	}

	private static int run(FabricClientCommandSource context) {
		ClientPlayerEntity player = context.getPlayer();
		String coordinates = "X: " + (int) player.getX() + " Y: " + (int) player.getY() + " Z: " + (int) player.getZ();
		player.networkHandler.sendChatMessage(coordinates);
		return Command.SINGLE_SUCCESS;
	}
}

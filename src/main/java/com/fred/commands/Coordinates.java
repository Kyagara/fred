package com.fred.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandRegistryAccess;

public class Coordinates implements ClientCommand {
	@Override
	public void register(CommandDispatcher<ClientCommandRegistrationEvent.ClientCommandSourceStack> dispatcher, CommandRegistryAccess context) {
		dispatcher.register(ClientCommandRegistrationEvent.literal("coords").executes(this::execute));
	}

	@Override
	public int execute(CommandContext<ClientCommandRegistrationEvent.ClientCommandSourceStack> context) {
		ClientPlayerEntity player = context.getSource().arch$getPlayer();
		String coordinates = "X: " + (int) player.getX() + " Y: " + (int) player.getY() + " Z: " + (int) player.getZ();
		player.networkHandler.sendChatMessage(coordinates);
		return Command.SINGLE_SUCCESS;
	}
}

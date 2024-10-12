package com.fred.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandRegistryAccess;

public class Shrug implements ClientCommand {
	@Override
	public void register(CommandDispatcher<ClientCommandRegistrationEvent.ClientCommandSourceStack> dispatcher, CommandRegistryAccess context) {
		dispatcher.register(ClientCommandRegistrationEvent.literal("shrug").executes(this::execute));
	}

	@Override
	public int execute(CommandContext<ClientCommandRegistrationEvent.ClientCommandSourceStack> context) {
		ClientPlayerEntity player = context.getSource().arch$getPlayer();
		player.networkHandler.sendChatMessage("¯\\_(ツ)_/¯");
		return Command.SINGLE_SUCCESS;
	}
}

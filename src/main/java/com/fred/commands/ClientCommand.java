package com.fred.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import net.minecraft.command.CommandRegistryAccess;

/* A Minecraft client command. */
public interface ClientCommand {
	/** Registers the command. */
	void register(CommandDispatcher<ClientCommandRegistrationEvent.ClientCommandSourceStack> dispatcher, CommandRegistryAccess context);

	/** Executes the command. */
	int execute(CommandContext<ClientCommandRegistrationEvent.ClientCommandSourceStack> context);
}

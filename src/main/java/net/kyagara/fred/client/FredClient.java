package net.kyagara.fred.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kyagara.fred.Fred;
import net.kyagara.fred.command.ModCommands;
import net.kyagara.fred.event.JoiningWorldEvent;
import net.kyagara.fred.event.LeavingWorldEvent;
import net.kyagara.fred.keybind.ModKeybinds;

public class FredClient implements ClientModInitializer {
	private static void connectionEvents() {
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
			if (!Fred.CONFIG.clearChatOnLeave() && Fred.CONFIG.enableChatSessionSeparator()) {
				LeavingWorldEvent.SendChatSessionSeparator(client);
			}

			if (Fred.CONFIG.enableJoinChatMessage()) {
				JoiningWorldEvent.SendJoinMessage(client);
			}
		});
	}

	@Override
	public void onInitializeClient() {
		ModKeybinds.registerModKeybinds();
		ModCommands.registerClientCommands();

		// Events
		connectionEvents();
	}
}
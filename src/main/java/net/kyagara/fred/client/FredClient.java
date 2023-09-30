package net.kyagara.fred.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kyagara.fred.Fred;
import net.kyagara.fred.command.ModCommands;
import net.kyagara.fred.event.LeavingWorldEvent;
import net.kyagara.fred.keybind.ModKeybinds;

public class FredClient implements ClientModInitializer {
	private static boolean isFirstJoin = true;

	private static void connectionEvents() {
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
			if (!Fred.CONFIG.clearChatOnLeave() && Fred.CONFIG.enableChatSessionSeparator() && !isFirstJoin && !client.inGameHud.getChatHud().getMessageHistory().isEmpty()) {
				LeavingWorldEvent.SendChatSessionSeparator(client);
			}

			isFirstJoin = false;
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
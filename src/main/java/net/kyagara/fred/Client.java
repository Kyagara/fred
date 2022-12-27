package net.kyagara.fred;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.kyagara.fred.command.ModCommands;
import net.kyagara.fred.config.FredConfig;
import net.kyagara.fred.event.JoiningWorldEvent;
import net.kyagara.fred.event.LeavingWorldEvent;
import net.kyagara.fred.keybind.ModKeybinds;

public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeybinds.registerModKeybinds();
        ModCommands.registerClientCommands();

        // Events
        connectionEvents();
    }

    private static void connectionEvents() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (!FredConfig.clearChat) {
                LeavingWorldEvent.ClearChat(client);
            }

            if (FredConfig.joinMessage) {
                JoiningWorldEvent.SendJoinMessage(client);
            }
        });
    }
}
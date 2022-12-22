package net.kyagara.fred.hook;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.MinecraftClient;

public class ClientEventsHooks {
    public static final Event<KeyPressedCallback> KEY_PRESSED = EventFactory
            .createArrayBacked(KeyPressedCallback.class, listeners -> {
                return (client, key, scancode, modifiers) -> {
                    for (KeyPressedCallback listener : listeners) {
                        listener.keyPressed(client, key, scancode, modifiers);
                    }
                };
            });

    public interface KeyPressedCallback {
        void keyPressed(MinecraftClient client, int key, int scancode, int modifiers);
    }
}

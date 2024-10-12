package com.fred.keybinds;

import com.fred.Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;

public class ScreenMovement {
	private static final long handle = MinecraftClient.getInstance().getWindow().getHandle();

	public static void CheckForMovementKeybind(MinecraftClient client) {
		for (KeyBinding key : client.options.allKeys) {
			if (key.getCategory().equals(KeyBinding.MOVEMENT_CATEGORY)) {
				if (key.equals(client.options.forwardKey) && Client.isAutoWalking) {
					Client.isAutoWalking = false;
				}
			}
		}
	}
}

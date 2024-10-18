package com.fred;

import com.fred.keybinds.MusicControl;
import com.fred.keybinds.Zoom;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundEvents;
import org.lwjgl.glfw.GLFW;

public class Client {
	// Music keybinds
	public static final KeyBinding DISPLAY_MUSIC_KEYBIND = register(GLFW.GLFW_KEY_RIGHT_CONTROL, "key.fred.display_music");
	public static final KeyBinding MUSIC_PLAYER_SCREEN_KEYBIND = register(GLFW.GLFW_KEY_LEFT, "key.fred.music_player_screen");
	public static final KeyBinding SKIP_KEYBIND = register(GLFW.GLFW_KEY_RIGHT, "key.fred.skip_music");
	public static final KeyBinding INCREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_UP, "key.fred.increase_volume");
	public static final KeyBinding DECREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_DOWN, "key.fred.decrease_volume");

	// Misc
	public static final KeyBinding ZOOM_KEYBIND = register(GLFW.GLFW_KEY_C, "key.fred.zoom");
	public static final KeyBinding AUTO_WALK_KEYBIND = register(GLFW.GLFW_KEY_G, "key.fred.auto_walk");

	public static boolean isAutoWalking = false;

	public static void init() {
		ClientRawInputEvent.KEY_PRESSED.register((client, keyCode, scanCode, action, modifiers) -> {
			if (Configuration.enableChatTypingSound() && client.player != null && client.currentScreen instanceof ChatScreen) {
				client.player.playSound(SoundEvents.BLOCK_STONE_PLACE, Configuration.chatTypingVolume(), Configuration.chatTypingPitch());
			}

			Zoom.isZooming = ZOOM_KEYBIND.isPressed();
			client.options.smoothCameraEnabled = Zoom.isZooming;

			if (AUTO_WALK_KEYBIND.isPressed()) {
				isAutoWalking = !isAutoWalking;
				client.options.forwardKey.setPressed(isAutoWalking);
			}

			// Music keybinds
			if (DISPLAY_MUSIC_KEYBIND.isPressed()) {
				MusicControl.Display(client);
			}
			if (MUSIC_PLAYER_SCREEN_KEYBIND.isPressed()) {
				MusicControl.ShowMusicPlayerScreen(client);
			}
			if (SKIP_KEYBIND.isPressed()) {
				MusicControl.Skip(client);
			}
			if (INCREASE_VOLUME_KEYBIND.isPressed()) {
				MusicControl.IncreaseVolume(client);
			}
			if (DECREASE_VOLUME_KEYBIND.isPressed()) {
				MusicControl.DecreaseVolume(client);
			}

			return EventResult.pass();
		});
	}

	private static KeyBinding register(int keycode, String name) {
		KeyBinding keybinding = new KeyBinding(name, InputUtil.Type.KEYSYM, keycode, "key.category.fred");
		KeyMappingRegistry.register(keybinding);
		return keybinding;
	}
}

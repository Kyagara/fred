package com.fred;

import com.fred.commands.Coordinates;
import com.fred.commands.FlipTable;
import com.fred.commands.Shrug;
import com.fred.commands.UnflipTable;
import com.fred.keybinds.MusicControl;
import com.fred.keybinds.Zoom;
import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import dev.architectury.event.events.client.ClientScreenInputEvent;
import dev.architectury.platform.Platform;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import dev.architectury.utils.Env;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Client {
	public static final KeyBinding DISPLAY_MUSIC_KEYBIND = register(GLFW.GLFW_KEY_RIGHT_CONTROL, "key.fred.display_music");
	public static final KeyBinding MUSIC_PLAYER_SCREEN_KEYBIND = register(GLFW.GLFW_KEY_LEFT, "key.fred.music_player_screen");
	public static final KeyBinding SKIP_KEYBIND = register(GLFW.GLFW_KEY_RIGHT, "key.fred.skip_music");
	public static final KeyBinding INCREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_UP, "key.fred.increase_volume");
	public static final KeyBinding DECREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_DOWN, "key.fred.decrease_volume");
	public static final KeyBinding ZOOM_KEYBIND = register(GLFW.GLFW_KEY_C, "key.fred.zoom");
	public static final KeyBinding AUTO_WALK_KEYBIND = register(GLFW.GLFW_KEY_G, "key.fred.auto_walk");

	public static boolean isAutoWalking = false;

	public static void init() {
		if (Platform.getEnvironment() == Env.SERVER) {
			return;
		}

		ClientScreenInputEvent.KEY_PRESSED_PRE.register((client, screen, keyCode, scanCode, modifiers) -> {
			Zoom.isZooming = ZOOM_KEYBIND.isPressed();
			client.options.smoothCameraEnabled = Zoom.isZooming;

			if (DISPLAY_MUSIC_KEYBIND.wasPressed()) {
				MusicControl.Display(client);
			}

			if (MUSIC_PLAYER_SCREEN_KEYBIND.wasPressed()) {
				MusicControl.ShowMusicPlayerScreen(client);
			}

			if (SKIP_KEYBIND.wasPressed()) {
				MusicControl.Skip(client);
			}

			if (INCREASE_VOLUME_KEYBIND.wasPressed()) {
				MusicControl.IncreaseVolume(client);
			}

			if (DECREASE_VOLUME_KEYBIND.wasPressed()) {
				MusicControl.DecreaseVolume(client);
			}

			if (AUTO_WALK_KEYBIND.wasPressed()) {
				isAutoWalking = !isAutoWalking;
				client.options.forwardKey.setPressed(isAutoWalking);
			}

			return EventResult.pass();
		});

		ClientCommandRegistrationEvent.EVENT.register((dispatcher, context) -> {
			if (Main.CONFIG.enableShrugCommand()) {
				new Shrug().register(dispatcher, context);
			}

			if (Main.CONFIG.enableFlipTableCommand()) {
				new FlipTable().register(dispatcher, context);
			}

			if (Main.CONFIG.enableUnflipTableCommand()) {
				new UnflipTable().register(dispatcher, context);
			}

			if (Main.CONFIG.enableCoordsCommand()) {
				new Coordinates().register(dispatcher, context);
			}
		});
	}

	private static KeyBinding register(int keycode, String name) {
		KeyBinding keybinding = new KeyBinding(name, InputUtil.Type.KEYSYM, keycode, "key.category.fred");
		KeyMappingRegistry.register(keybinding);
		return keybinding;
	}
}
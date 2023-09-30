package net.kyagara.fred.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents;
import net.kyagara.fred.Fred;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
	public static final KeyBinding DISPLAY_MUSIC_KEYBIND = register(GLFW.GLFW_KEY_RIGHT_CONTROL, "key.fred.display_music");
	public static final KeyBinding MUSIC_PLAYER_SCREEN_KEYBIND = register(GLFW.GLFW_KEY_LEFT, "key.fred.music_player_screen");
	public static final KeyBinding SKIP_KEYBIND = register(GLFW.GLFW_KEY_RIGHT, "key.fred.skip_music");
	public static final KeyBinding INCREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_UP, "key.fred.increase_volume");
	public static final KeyBinding DECREASE_VOLUME_KEYBIND = register(GLFW.GLFW_KEY_DOWN, "key.fred.decrease_volume");
	public static final KeyBinding LINK_ITEM_KEYBIND = register(GLFW.GLFW_KEY_LEFT_ALT, "key.fred.link_item");
	public static final KeyBinding ZOOM_KEYBIND = register(GLFW.GLFW_KEY_C, "key.fred.zoom");
	public static final KeyBinding AUTO_WALK_KEYBIND = register(GLFW.GLFW_KEY_G, "key.fred.auto_walk");

	public static boolean isAutoWalking = false;

	private static void registerKeybinds() {
		ScreenEvents.BEFORE_INIT.register(((client, screen, scaledWidth, scaledHeight) -> {
			ScreenKeyboardEvents.beforeKeyPress(screen).register((screen2, keyCode, scanCode, modifiers) -> {
				if (LINK_ITEM_KEYBIND.matchesKey(keyCode, scanCode) && Fred.CONFIG.enableLinkItemInChat()) {
					LinkItemKeybind.SendLinkItemPacket(client);
				}
			});
		}));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			ZoomKeybind.isZooming = ZOOM_KEYBIND.isPressed();
			client.options.smoothCameraEnabled = ZoomKeybind.isZooming;

			if (DISPLAY_MUSIC_KEYBIND.wasPressed()) {
				MusicControlKeybind.Display(client);
			}

			if (MUSIC_PLAYER_SCREEN_KEYBIND.wasPressed()) {
				MusicControlKeybind.ShowMusicPlayerScreen(client);
			}

			if (SKIP_KEYBIND.wasPressed()) {
				MusicControlKeybind.Skip(client);
			}

			if (INCREASE_VOLUME_KEYBIND.wasPressed()) {
				MusicControlKeybind.IncreaseVolume(client);
			}

			if (DECREASE_VOLUME_KEYBIND.wasPressed()) {
				MusicControlKeybind.DecreaseVolume(client);
			}

			if (AUTO_WALK_KEYBIND.wasPressed()) {
				isAutoWalking = !isAutoWalking;
				client.options.forwardKey.setPressed(isAutoWalking);
			}
		});
	}

	public static KeyBinding register(int keybind, String name) {
		return KeyBindingHelper.registerKeyBinding(new KeyBinding(name, InputUtil.Type.KEYSYM, keybind, "key.category.fred"));
	}

	public static void registerModKeybinds() {
		Fred.LOGGER.debug("Registering keybinds from " + Fred.MOD_ID);
		registerKeybinds();
	}
}
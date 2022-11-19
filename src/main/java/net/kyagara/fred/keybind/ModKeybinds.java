package net.kyagara.fred.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kyagara.fred.Main;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    public static boolean isPaused = false;
    public static boolean pauseResume = false;
    public static boolean printMusic = false;

    public static final KeyBinding PRINT_MUSIC_KEYBIND = register(GLFW.GLFW_KEY_RIGHT_CONTROL, "key.category.fred",
            "key.fred.print_music");

    public static final KeyBinding PAUSE_MUSIC_KEYBIND = register(GLFW.GLFW_KEY_P, "key.category.fred",
            "key.fred.pause_music");

    public static KeyBinding register(int keybind, String category, String name) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (PRINT_MUSIC_KEYBIND.wasPressed()) {
                printMusic = true;
            }

            if (PAUSE_MUSIC_KEYBIND.wasPressed()) {
                pauseResume = true;
            }
        });

        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                name,
                InputUtil.Type.KEYSYM,
                keybind,
                category));
    }

    public static void registerModKeybinds() {
        Main.LOGGER.debug("Registering keybinds from " + Main.MOD_ID);
    }
}